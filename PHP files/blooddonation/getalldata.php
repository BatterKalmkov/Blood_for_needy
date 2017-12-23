<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
$username=$_POST["username"];

  $res=array("logged in","Check your username/password");
  $mysqliselect="Select * from bdsignup where username='$username'";
  $result=mysqli_query($conn,$mysqliselect);
  $response=array();
  while($row=mysqli_fetch_array($result))
  {
	array_push($response,array("email"=>$row[3],"state"=>$row[5],"district"=>$row[6],"pic"=>$row[8],"name"=>$row[0]));
  }
  echo json_encode(array("server_response"=>$response));
   
  mysqli_close($conn);
?>