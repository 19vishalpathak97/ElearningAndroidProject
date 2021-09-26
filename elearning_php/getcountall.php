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
	
	
	
	$pdfcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='pdf' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject'" );
	$vidcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='mp4' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' " );
	$doccount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='docx' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' " );
	$pptcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='pptx' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' " );
	$imgcount = mysqli_query($con,"SELECT count(filetype) FROM studymaterial where filetype='img' and branch='$branch' and year='$year' and course='$course' and sem='$sem' and subject='$subject' " );
	//echo $count1;
	/*$result = mysqli_query($con,"SELECT filename,filetype,description FROM studym" );//where email='$username' and password='$password'");*/
$response = array();
 
   $row1 = mysqli_fetch_array($pdfcount);
   $row2 = mysqli_fetch_array($vidcount);
   $row3 = mysqli_fetch_array($doccount);
   $row4 = mysqli_fetch_array($pptcount);
   $row5 = mysqli_fetch_array($imgcount);
  
   $response[] = array("totalcount"=>$row1[0]+$row2[0]+$row3[0]+$row4[0]+$row5[0]);

 
echo json_encode(($response));
	
	
	
	
	
	
	

   mysqli_close($con);
?>
