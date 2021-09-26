<?php
   $con=mysqli_connect("localhost","nileshkorade","Nil@1234","pccoerelearning");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
$branch=$_POST['branch'];
  date_default_timezone_set("Asia/Kolkata");
  $date=date("d");
  //echo $date;
  $hrs=date("h");
 // echo $hrs;
  
  $ap=date("a");
  //echo $ap;//am,pm
  
 // $hour=date("")
  $next=$date+1;
  //echo $next;//for 24 hrs display the data
  
  /*//$sql = "UPDATE studym SET auth='YES' WHERE day(tt) < $date ";

if ($con->query($sql)) {
   // echo "Record updated successfully";
} else {
    //echo "Error updating record: " . $con->error;
	
}
*/
$abs=$next-1;
//echo $abs;

 $sql2="UPDATE studymaterial SET auth='NO' WHERE day(tt)='$next' and hour(tt) >= '$hrs'and DATE_FORMAT(tt,'%p')='$ap' and auth='YES'";//  
  
if ($con->query($sql2)) {
    //echo "Record Checked and updated successfully";
} else {
   // echo "Error updating record: " . $con->error;
	
}
  
$result = mysqli_query($con,"SELECT filename,filetype,description FROM studymaterial where branch='$branch' and auth='YES' ");
$response = array();
 
while($row = mysqli_fetch_array($result)){
    $response[] = array("filename"=>$row[0],"filetype"=>$row[1],"description"=>$row[2]);
}
 
echo json_encode(($response));  
  
  
  
      mysqli_close($con);
?>
