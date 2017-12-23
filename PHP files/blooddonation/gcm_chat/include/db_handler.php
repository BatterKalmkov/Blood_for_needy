<?php
class DbHandler {
 
    private $conn;
 
    function __construct() {
        require_once dirname(__FILE__) . '/db_connect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }
 
    // creating new user if not existed
    public function createUser($name, $email) {
        $response = array();
 
        // First check if user already existed in db
        if (!$this->isUserExists($email)) {
            // insert query
            $stmt = $this->conn->prepare("INSERT INTO users(name, email) values(?, ?)");
            $stmt->bind_param("ss", $name, $email);
 
            $result = $stmt->execute();
 
            $stmt->close();
 
            // Check for successful insertion
            if ($result) {
                // User successfully inserted
                $response["error"] = false;
                $response["user"] = $this->getUserByEmail($email);
            } else {
                // Failed to create user
                $response["error"] = true;
                $response["message"] = "Oops! An error occurred while registereing";
            }
        } else {
            // User with same email already existed in the db
            $response["error"] = false;
            $response["user"] = $this->getUserByEmail($email);
        }
 
        return $response;
    }
 
    // updating user GCM registration ID
    public function updateGcmID($user_id, $gcm_registration_id) {
        $response = array();
        $stmt = $this->conn->prepare("UPDATE users SET gcm_registration_id = ? WHERE user_id = ?");
        $stmt->bind_param("si", $gcm_registration_id, $user_id);
 
        if ($stmt->execute()) {
            // User successfully updated
            $response["error"] = false;
            $response["message"] = 'GCM registration ID updated successfully';
        } else {
            // Failed to update user
            $response["error"] = true;
            $response["message"] = "Failed to update GCM registration ID";
            $stmt->error;
        }
        $stmt->close();
 
        return $response;
    }
 
    // fetching single user by id
    public function getUser($user_id) {
        $stmt = $this->conn->prepare("SELECT user_id, name, email, gcm_registration_id, created_at FROM users WHERE user_id = ?");
        $stmt->bind_param("s", $user_id);
        if ($stmt->execute()) {
            // $user = $stmt->get_result()->fetch_assoc();
            $stmt->bind_result($user_id, $name, $email, $gcm_registration_id, $created_at);
            $stmt->fetch();
            $user = array();
            $user["user_id"] = $user_id;
            $user["name"] = $name;
            $user["email"] = $email;
            $user["gcm_registration_id"] = $gcm_registration_id;
            $user["created_at"] = $created_at;
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }
 
    // fetching multiple users by ids
    public function getUsers($user_ids) {
 
        $users = array();
        if (sizeof($user_ids) > 0) {
            $query = "SELECT user_id, name, email, gcm_registration_id, created_at FROM users WHERE user_id IN (";
 
            foreach ($user_ids as $user_id) {
                $query .= $user_id . ',';
            }
 
            $query = substr($query, 0, strlen($query) - 1);
            $query .= ')';
 
            $stmt = $this->conn->prepare($query);
            $stmt->execute();
            $stmt->bind_result($user_id, $name, $email, $gcm_registration_id, $created_at);
 
            $users = array();
            $users["error"] = false;
           
        
            while ($user = $stmt->fetch()) {
                $tmp = array();
                $tmp["user_id"] = $user_id;
                $tmp["name"] = $name;
                $tmp["email"] = $email;
                $tmp["gcm_registration_id"] = $gcm_registration_id;
                $tmp["created_at"] = $created_at;
                array_push($users, $tmp);
            }
        }
 
        return $users;
    }
 
    // messaging in a chat room / to persional message
    public function addMessage($user_id, $chat_room_id, $message) {
        $response = array();
 
        $stmt = $this->conn->prepare("INSERT INTO messages (chat_room_id, user_id, message) values(?, ?, ?)");
        $stmt->bind_param("iis", $chat_room_id, $user_id, $message);
 
        $result = $stmt->execute();
 
        if ($result) {
            $response['error'] = false;
 
            // get the message
            $message_id = $this->conn->insert_id;
            $stmt = $this->conn->prepare("SELECT message_id, user_id, chat_room_id, message, created_at FROM messages WHERE message_id = ?");
            $stmt->bind_param("i", $message_id);
            if ($stmt->execute()) {
                $stmt->bind_result($message_id, $user_id, $chat_room_id, $message, $created_at);
                $stmt->fetch();
                $tmp = array();
                $tmp['message_id'] = $message_id;
                $tmp['chat_room_id'] = $chat_room_id;
                $tmp['message'] = $message;
                $tmp['created_at'] = $created_at;
                $response['message'] = $tmp;
            }
        } else {
            $response['error'] = true;
            $response['message'] = 'Failed send message';
        }
 
        return $response;
    }
 
    // fetching all chat rooms
    public function getAllChatrooms() {
        $stmt = $this->conn->prepare("SELECT chat_room_id,name,created_at FROM chat_rooms");
        $stmt->execute();
        $stmt->bind_result($chat_room_id,$name,$created_at);
        
        $response = array();
        $response["error"] = false;
        $response["chat_rooms"] = array();
 
    // pushing single chat room into array
    while ($chat_room = $stmt->fetch()) {
        $tmp = array();
        $tmp["chat_room_id"] =$chat_room_id;
        $tmp["name"] = $name;
        $tmp["created_at"] = $created_at;
        array_push($response["chat_rooms"], $tmp);
    }
        //$stmt->close();
        return $response;
    }
 
    // fetching single chat room by id
    function getChatRoom($chat_room_id) {
        $stmt = $this->conn->prepare("SELECT cr.chat_room_id, cr.name, cr.created_at as chat_room_created_at, u.name as username, c.* FROM chat_rooms cr LEFT JOIN messages c ON c.chat_room_id = cr.chat_room_id LEFT JOIN users u ON u.user_id = c.user_id WHERE cr.chat_room_id = ?");
        $stmt->bind_param("i", $chat_room_id);
        $stmt->execute();
        
        $stmt->bind_result($chat_room_id,$name,$chat_room_created_at,$username,$message_id,$chat_room_id,$user_id,$message,$created_at);
        $response["error"] = false;
        $response["messages"] = array();
        $response['chat_room'] = array();
        
        $i = 0;
    // looping through result and preparing tasks array
    while ($chat_room = $stmt->fetch()) {
        // adding chat room node
        if ($i == 0) {
            $tmp = array();
            $tmp["chat_room_id"] = $chat_room_id;
            $tmp["name"] = $name;
            $tmp["created_at"] = $chat_room_created_at;
            $response['chat_room'] = $tmp;
        }
 
        if ($response['chat_room']['chat_room_id'] != NULL) {
            // message node
            $cmt = array();
            $cmt["message"] = $message;
            $cmt["message_id"] = $message_id;
            $cmt["created_at"] = $created_at;
 
            // user node
            $user = array();
            $user['user_id'] = $user_id;
            $user['username'] = $username;
            $cmt['user'] = $user;
 
            array_push($response["messages"], $cmt);
        }
    }
        
        
        $stmt->close();
        return $response;
    }
 
    /**
     * Checking for duplicate user by email address
     * @param String $email email to check in db
     * @return boolean
     */
    private function isUserExists($email) {
        $stmt = $this->conn->prepare("SELECT user_id from users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();
        return $num_rows > 0;
    }
 
    /**
     * Fetching user by email
     * @param String $email User email id
     */
    public function getUserByEmail($email) {
        $stmt = $this->conn->prepare("SELECT user_id, name, email, created_at FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        if ($stmt->execute()) {
            // $user = $stmt->get_result()->fetch_assoc();
            $stmt->bind_result($user_id, $name, $email, $created_at);
            $stmt->fetch();
            $user = array();
            $user["user_id"] = $user_id;
            $user["name"] = $name;
            $user["email"] = $email;
            $user["created_at"] = $created_at;
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }
 
}
 
?>