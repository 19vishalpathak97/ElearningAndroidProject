<?php
 
ServerConfig();

$PdfUploadFolder = 'image/';
 
$ServerURL = 'http://localhost/'.$PdfUploadFolder;
 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
 if(isset($_POST['name']) and isset($_FILES['pdf']['name'])){

 $con = mysqli_connect(HostName,HostUser,HostPass,DatabaseName);
 
 $PdfName = $_POST['name'];
 $email = $_POST['email'];
 
 $PdfInfo = pathinfo($_FILES['pdf']['name']);
 
 $PdfFileExtension = $PdfInfo['extension'];
 
 $PdfFileURL =   $PdfName . '.' . $PdfFileExtension;
 
 $PdfFileFinalPath = $PdfUploadFolder . $PdfName  . '.'. $PdfFileExtension;
 
 try{
 
 move_uploaded_file($_FILES['pdf']['tmp_name'],$PdfFileFinalPath);
 
 $InsertTableSQLQuery = "INSERT INTO ig (id,photo,photoname,photoid) VALUES (NULL,'$PdfFileURL', '$PdfName','$email') ;";

 mysqli_query($con,$InsertTableSQLQuery);

 }catch(Exception $e){} 
 mysqli_close($con);
 
 }
}

function ServerConfig(){
 
define('HostName','localhost');
define('HostUser','root');
define('HostPass','root');
define('DatabaseName','register');
 
}

function GenerateFileNameUsingID(){
 
 $con2 = mysqli_connect(HostName,HostUser,HostPass,DatabaseName);
 
 $GenerateFileSQL = "SELECT max(id) as id FROM ig";
 
 $Holder = mysqli_fetch_array(mysqli_query($con2,$GenerateFileSQL));

 mysqli_close($con2);
 
 if($Holder['id']==null)
 {
 return 1;
 }
 else
 {
 return ++$_POST['name'];
 }
}

?>

