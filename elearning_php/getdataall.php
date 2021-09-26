<?php
  $con=mysqli_connect("localhost","nileshkorade","Nil@1234","pccoerelearning");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
	
   //echo"Successfully connected";


	$branch=$_POST['branch'];
	$year=$_POST['year'];
	$course=$_POST['course'];
	$sem=$_POST['sem'];
	//$subtype=$_POST['sub_type'];
	$subject=$_POST['subject'];
	
	//$unit=$_POST['unit'];
	
	//$sql="select course from spinner where branch='$branch' and year='$year'";	
	/*$result = mysqli_query($con,"select filename from studym where branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and unit='$unit'");*/
	$result = mysqli_query($con,"SELECT filename,filetype,description FROM studymaterial  where branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject'" );//whe='$username' and password='$password'");
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response[] = array("filename"=>$row[0],"filetype"=>$row[1],"description"=>$row[2]);
}
 
echo json_encode(($response));
	
	
	
	
	
	
	//$num_rows = mysqli_num_rows($result);
	/*$name=array();
	while($row = mysqli_fetch_array($result))
	{
		$name[]=$row[0];
	}
	$l=count($name);
	for($i=0;$i<$l;$i++)
	{
		print $name[$i];
		print "<br>";
	}

	/*while($row = mysqli_fetch_array($result)){
   // $response = array("course"=>$row[0]);
     //  echo json_encode(array($response));
	
}
   
echo $row[0];*/

   mysqli_close($con);
?>
