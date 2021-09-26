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
	$unit=$_POST['unit'];
	
	//$sql="select course from spinner where branch='$branch' and year='$year'";	
	/*$result = mysqli_query($con,"select filename from studym where branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and unit='$unit'");*/
	
	
	$pdfcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='pdf' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );
	$vidcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='mp4' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );
	$doccount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='docx' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );
	$pptcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='pptx' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );
	$imgcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='img' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' and subtype='$subtype' and unit='$unit'" );
	//echo $count1;
	/*$result = mysqli_query($con,"SELECT filename,filetype,description FROM studym" );//where email='$username' and password='$password'");*/
$response = array();
 
//while($row = mysqli_fetch_array($result)){
   // $response[] = array("filename"=>$row[0],"filetype"=>$row[1],"description"=>$row[2]);
   $row1 = mysqli_fetch_array($pdfcount);
   $row2 = mysqli_fetch_array($vidcount);
   $row3 = mysqli_fetch_array($doccount);
   $row4 = mysqli_fetch_array($pptcount);
   $row5 = mysqli_fetch_array($imgcount);
   $response[] = array("pdfcount"=>$row1[0],"vidcount"=>$row2[0],"doccount"=>$row3[0],"pptcount"=>$row4[0],"imgcount"=>$row5[0]);
//}
 
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
