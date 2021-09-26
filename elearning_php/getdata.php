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
	$subject=$_POST['subject'];
	$subtype=$_POST['sub_type'];
	$filet=$_POST['filetype'];
	
	$unit=$_POST['unit'];
	
	
	$result = mysqli_query($con,"SELECT filename,description FROM studymaterial where branch='$branch' and year='$year' and filetype='$filet' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );//where email='$username' and password='$password'");
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response[] = array("filename"=>$row[0],"description"=>$row[1]);
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
