<?php
   $con=mysqli_connect("localhost","nileshkorade","Nil@1234","pccoerelearning");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
	
  //echo"Successfully connected";

	$filename=$_POST['filename'];
	$filetype=$_POST['filetype'];
	
	
	//$sql="select course from spinner where branch='$branch' and year='$year'";	
	$result = mysqli_query($con,"SELECT filelocation FROM studymaterial WHERE filename='$filename' and filetype='$filetype' ");
	//$num_rows = mysqli_num_rows($result);
	
	$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response[] = array("address"=>$row[0]);
}
 
echo json_encode(($response));




   mysqli_close($con);
?>
