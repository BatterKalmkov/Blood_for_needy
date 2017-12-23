<?php
require_once 'config.php';
       $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

  $fbname=$_POST["fbname"];
  $fbid=$_POST["fbid"];
  
  $res=array("Fetching userdetails","Error");
  $mysqliselect="Select * from bdfbsignup where fbname='$fbname' and fbid='$fbid'";
  $result=mysqli_query($conn,$mysqliselect);
  if(mysqli_num_rows($result)>0)
 { 
             $response=array();
             while($row=mysqli_fetch_array($result))
             {
               	array_push($response,array("name"=>$row[1],"mobile"=>$row[9],"email"=>$row[3],"state"=>$row[7],"district"=>$row[8]));
             }
             echo json_encode(array("server_response"=>$response));     
    }
else {
              echo json_encode($res[1]);
     }    
  mysqli_close($conn);
?>