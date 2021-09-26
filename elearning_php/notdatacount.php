<?php
  
   $con=mysqli_connect("localhost","nileshkorade","Nil@1234","pccoerelearning");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
    

 
  date_default_timezone_set("Asia/Kolkata");
  
  $date=date("d");
  $hrs=date("h");
  $ap=date("a");
 // echo $hrs;
 // echo $date;
 // echo $ap;
  
 // $hour=date("")
  $next=$date+1;
 // echo $next;//for 24 hrs display the data
  
  
  /*$sql = "UPDATE studymaterial SET auth='NO' WHERE day(tt) < $date ";

if ($con->query($sql)) {
    //echo "Record updated successfully";
} else {
    //echo "Error updating record: " . $con->error;
	
}*/


$abs=$next-1;


 //$sql2="UPDATE studymaterial SET auth='NO' WHERE day(tt)<='$next' and hour(tt) <= '$hrs'and DATE_FORMAT(tt,'%p')='$ap' and auth='YES'";
  
  

 $sql2="UPDATE studymaterial SET auth='NO' WHERE day(tt)<'$date' and  auth='YES'";
if ($con->query($sql2)) {
    //echo "Record Checked and updated successfully";
} else {
   // echo "Error updating record: " . $con->error;
	
}
$firstc = mysqli_query($con,"SELECT count(filename) FROM studymaterial where branch='FirstYear' and auth='YES' ");  
$computerc = mysqli_query($con,"SELECT count(filename) FROM studymaterial where branch='ComputerEngineering' and auth='YES' ");
$mechanicalc = mysqli_query($con,"SELECT count(filename) FROM studymaterial where branch='MechanicalEngineering' and auth='YES' ");
$electronicsc = mysqli_query($con,"SELECT count(filename) FROM studymaterial where branch='Electronics&TelecommunicationEngineering' and auth='YES' ");
$civilc = mysqli_query($con,"SELECT count(filename) FROM studymaterial where branch='CivilEngineering' and auth='YES' ");

   $row1 = mysqli_fetch_array($firstc);
   $row2 = mysqli_fetch_array($computerc);
   $row3 = mysqli_fetch_array($mechanicalc);
   $row4 = mysqli_fetch_array($electronicsc);
   $row5 = mysqli_fetch_array($civilc);
   
   $response[] = array("firstc"=>$row1[0],"computerc"=>$row2[0],"mechanicalc"=>$row3[0],"electronicsc"=>$row4[0],"civilc"=>$row5[0]);
//$response = array();
 
//while($row = mysqli_fetch_array($result)){
  //  $response[] = array("totalcount"=>$row[0]);
//}
 
echo json_encode(($response));  
  
  
      mysqli_close($con);
?>
