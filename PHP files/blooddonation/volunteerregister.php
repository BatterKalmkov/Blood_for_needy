<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$gender=$_POST["gender"];
$age=$_POST["age"];
$time=$_POST["time"];
$volunteeringtype=$_POST["volunteeringtype"];
$place=$_POST["place"];
$referral=$_POST["referral"];
$areaofinterest=$_POST["areaofinterest"];
$id=$_POST["id"];
$type=$_POST["type"];
 
  $response=array("Thank you for registering with us","Please try after some time");
  $mysqlinsert="Insert into bdvolunteerregister(gender,age,time,volunteeringtype,place,referral,areaofinterest,created_at,id,type) values('$gender','$age','$time','$volunteeringtype','$place','$referral','$areaofinterest',Now(),'$id','$type')" ;
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