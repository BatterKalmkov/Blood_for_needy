<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$title=$_POST["title"];

$mysqlq="Select * from bdtestimonial order by created_at DESC" ;
$result=mysqli_query($conn,$mysqlq);

$response=array();
while($row=mysqli_fetch_array($result))
{
	array_push($response,array("email"=>$row[3],"date"=>$row[2],"district"=>$row[4],"state"=>$row[5],"testimonial"=>$row[0],"pic"=>$row[6],"name"=>$row[7]));
       
}
  echo json_encode(array("server_response"=>$response));
  
  mysqli_close($conn);
?>