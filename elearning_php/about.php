<?php
   $con=mysqli_connect("localhost","nileshkorade","Nil@1234","pccoerelearning");

   if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
    
    
    $copyrightcount = mysqli_query($con,"SELECT copyright FROM about" );
    
    $patentcount = mysqli_query($con,"SELECT patents FROM about" );
    
    $placementcount = mysqli_query($con,"SELECT placement FROM about" );
    
    
    
    
    $response = array();
 
//while($row = mysqli_fetch_array($result)){
   // $response[] = array("filename"=>$row[0],"filetype"=>$row[1],"description"=>$row[2]);
   $row1 = mysqli_fetch_array($copyrightcount);
   $row2 = mysqli_fetch_array($patentcount);
   $row3 = mysqli_fetch_array($placementcount);
   $response[] = array("copyright"=>$row1[0],"patents"=>$row2[0],"placement"=>$row3[0]);
//}
 
echo json_encode(($response));


   mysqli_close($con);
    
    ?>