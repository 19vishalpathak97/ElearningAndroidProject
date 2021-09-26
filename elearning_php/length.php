<?php 

   $url=$_POST['url'];

 $ch = curl_init($url);

 curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
 curl_setopt($ch, CURLOPT_HEADER, TRUE);
 curl_setopt($ch, CURLOPT_NOBODY, TRUE);

 $data = curl_exec($ch);
 $size = curl_getinfo($ch, CURLINFO_CONTENT_LENGTH_DOWNLOAD);

 curl_close($ch);
 
 $response[] = array("length"=>$size);
 //echo $size;
 echo json_encode(($response));  
?>