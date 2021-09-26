<?php   
$con=mysqli_connect("localhost","root","root","upload"); 
   $sql=mysqli_query($con,"select PdfName from PdfTable");  
   //while(
   //$row=mysqli_fetch_assoc($sql)
   //){  
     // $output[]=$row;   }  
      
       
   //print(json_encode($output)); 
   
   while($row = mysqli_fetch_array($sql)){
    $response = array("PdfName"=>$row[0]);
}
   echo json_encode(array("user_data"=>$response));
   mysqli_close(); 
   ?>
