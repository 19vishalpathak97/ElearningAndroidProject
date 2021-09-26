<?php
   $con=mysqli_connect("localhost","root","root","register");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
	
   $username =$_POST['email'];
  // $password = $_POST['password'];
   $result = mysqli_query($con,"select photo from ig where 
   photoid='$username' and id=(select max(id) from ig)");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if($data){
      echo $data;
   }
   else
   	echo null;
	
   mysqli_close($con);
?>
