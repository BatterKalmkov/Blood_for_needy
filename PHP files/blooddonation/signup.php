<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$name=$_POST["username"];
$mobile=$_POST["mobile"];
$email=$_POST["email"];
$password=$_POST["password"];
$pic=$_POST["pic"];
$state=$_POST["state"];
$district=$_POST["district"];
$bloodgroup=$_POST["bloodgroup"];
 
  $response=array("Successfully registered","Please try registering using different username or email");
  $mysqlinsert="Insert into bdsignup(username,mobile,password,email,state,district,bloodgroup,pic,created_at) values('$name','$mobile','$password','$email','$state','$district','$bloodgroup','$pic',Now())" ;
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