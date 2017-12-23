<?php
class GCM {
 
    // constructor
    function __construct() {
         
    }
 
    // sending push message to single user by gcm registration id
    public function send($to, $message) {
        $fields = array(
            'to' => $to,
            'data' => $message,
        );
        return $this->sendPushNotification($fields);
    }
 
    // Sending message to a topic by topic id
    public function sendToTopic($to, $message) {
        
        require_once dirname(__FILE__) . '/../../include/db_connect.php';
        // opening db connection
        $db = new DbConnect();
        $conn = $db->connect();
        $stmt = $conn->prepare("SELECT gcm_registration_id FROM users");
        $gcmRegIds = array();
        if ($stmt->execute())
        {
         $stmt->bind_result($gcm_registration_id); 
         
         while ($stmt->fetch()) 
         {
           array_push($gcmRegIds,$gcm_registration_id);
         }

        }

        $regIdChunk = array_chunk($gcmRegIds, 1000);
        
        foreach ($regIdChunk as $RegId)
        {
            $fields = array(
            'registration_ids' => $RegId,
            'data' => $message,
        );
        
         $this->sendPushNotification($fields);
        }
        
    }
 
    // sending push message to multiple users by gcm registration ids
    public function sendMultiple($registration_ids, $message) {
        $fields = array(
            'registration_ids' => $registration_ids,
            'data' => $message,
        );
 
        return $this->sendPushNotification($fields);
    }
 
    // function makes curl request to gcm servers
    private function sendPushNotification($fields) {
 
        // include config
        include_once __DIR__ . '/../../include/config.php';
 
        // Set POST variables
        $url = 'https://gcm-http.googleapis.com/gcm/send';
 
        $headers = array(
            'Authorization: key=' . GOOGLE_API_KEY,
            'Content-Type: application/json'
        );
        // Open connection
        $ch = curl_init();
 
        // Set the url, number of POST vars, POST data
        curl_setopt($ch, CURLOPT_URL, $url);
 
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
        // Disabling SSL Certificate support temporarly
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
 
        // Execute post
        $result = curl_exec($ch);
        if ($result === FALSE) {
            die('Curl failed: ' . curl_error($ch));
        }
 
        // Close connection
        curl_close($ch);
 
        //return $result;
    }
 
}
 
?>