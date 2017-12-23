<?php
     require_once 'config.php';
       $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
         
         $email=$_POST["email"];
         $password =$_POST["password"];
         $OTP1=$_POST["OTP"];
             
             $mysqliselect1="Select OTP from bdforgetpassword where email='$email' ";
             $result1=mysqli_query($conn,$mysqliselect1);
             if(mysqli_num_rows($result1)>0)
             {
                while($row=mysqli_fetch_array($result1))
                { 
	           $OTP=$row[0];
                }
                
                if($OTP1==$OTP)
                {
                  if(mysqli_query($conn, "UPDATE bdsignup SET password='$password' WHERE email='$email'"))
                  {
                    echo "Password changed successfully";
                  } 
                  else 
                  {
                     echo "Error";
                  }
                }
             
           else 
           {
              echo "Please enter correct OTP";
           }
      }
         
           mysqli_close($conn);
      ?>