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
	//$subject=$_POST['subject'];
	
	//$sql="select course from spinner where branch='$branch' and year='$year'";	
	$result = mysqli_query($con,"select distinct(subtype) from studymaterial where branch='$branch' and year='$year' and course='$course' and sem='$sem' ");
	//$num_rows = mysqli_num_rows($result);
	$name=array();
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
