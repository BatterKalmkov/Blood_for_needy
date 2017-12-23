<?php
require_once 'config.php';
       $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
       $response=array();

  $res=array("Fetching events","Error");
  $mysqliselect="Select * from bdevents order by ID DESC";
  $result=mysqli_query($conn,$mysqliselect);
  if(mysqli_num_rows($result)>0)
 { 
             
             while($row=mysqli_fetch_array($result))
             {
           
               	array_push($response,array("venue"=>$row[1],"time"=>$row[2],"contactperson"=>$row[3],"event"=>$row[4],"date"=>$row[5],"mobile"=>$row[7],"description"=>$row[6],"latitude"=>$row[8],"longitude"=>$row[9]));
              }
              
             echo json_encode(array("server_response"=>$response));     
    }
else 
{
              echo json_encode($res[1]);
     }    
  mysqli_close($conn);
?>