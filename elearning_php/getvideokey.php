<?php
   $con=mysqli_connect("localhost","root","root","utube");

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
	$unit=$_POST['unit'];
	$itemname=$_POST['itemname'];
	$filetype=$_POST['filetype'];
	
	//$sql="select course from spinner where branch='$branch' and year='$year'";	
	/*$result = mysqli_query($con,"select filename from studym where branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and unit='$unit'");*/
	$result = mysqli_query($con,"SELECT youtube_key FROM studym where branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and unit='$unit' and filename='$itemname' and filetype='$filetype'" );//where email='$username' and password='$password'");
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response[] = array("youtube_key"=>$row[0]);
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
