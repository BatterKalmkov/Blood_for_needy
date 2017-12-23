<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$email=$_POST["email"];
$pic=$_POST["pic"];
$state=$_POST["state"];
$district=$_POST["district"];
$testimonial=$_POST["testimonial"];
$name=$_POST["name"];
 
  $response=array("Thank you for your feedback.","Please try after sometime");
  $mysqlinsert="Insert into bdtestimonial(email,state,district,testimonial,pic,created_at,name) values('$email','$state','$district','$testimonial','$pic',Now(),'$name')" ;
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