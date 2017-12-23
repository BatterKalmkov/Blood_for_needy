<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$gender=$_POST["gender"];
$age=$_POST["age"];
$weight=$_POST["weight"];
$donortype=$_POST["donortype"];
$lastdonation=$_POST["lastdonation"];
$smsalert=$_POST["smsalert"];
$id=$_POST["id"];
$type=$_POST["type"];
 
  $response=array("Thank you for registering with us","Please try after some time");
  $mysqlinsert="Insert into bddonorregister(gender,age,weight,donortype,lastdonation,smsalert,created_at,id,type) values('$gender','$age','$weight','$donortype','$lastdonation','$smsalert',Now(),'$id','$type')" ;
  if(mysqli_query($conn,$mysqlinsert))
{
              echo json_encode($response[0]);
            } 
            else 
            {
              echo json_encode($response[1]);
        
            }
  mysqli_close($conn);
?>