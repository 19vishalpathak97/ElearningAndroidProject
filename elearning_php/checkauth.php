<?php
   $con=mysqli_connect("localhost","root","root","register");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
	
   $username = $_POST['email'];
   $password =$_POST['password'];
  // echo $username;
  // echo $password;
   $result = mysqli_query($con,"SELECT auth FROM new_register where 
   email='$username' and password='$password'");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if($data){
      echo $data;
   }
	
   mysqli_close($con);
?>
