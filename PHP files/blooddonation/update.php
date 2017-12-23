<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$glname=$_POST["glname"];
$glid=$_POST["glid"];
$glpic=$_POST["glpic"];
$glemail=$_POST["glemail"];
 
  $response=array("Data inserted","Error");
  $mysqlinsert="Select * from signup where " ;
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