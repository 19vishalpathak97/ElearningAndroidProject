
<?php
$database="register"; //database name
$email=$_POST['email'];//this ssvalues comes from html file after submitting 
$mobileno=$_POST['mobileno'];
$branch=$_POST['branch'];

$password=$_POST['password'];

$repassword=$_POST['repassword'];
$auth = $_POST['auth'];
    $con = mysqli_connect("localhost","root" ,"root","register");
    if (!$con)
    {
    die('Could not connect: ' . mysqli_error());
    }
    //mysqli_select_db("$database", $con);
    
    //if($auth==yes)
    //{
	//	$aa=1;
$sql = "INSERT INTO new_register(email,mobileno,branch,password,repassword,auth)VALUES('$email','$mobileno','$branch','$password','$repassword','$auth')";
//}else
//{
//$aa=0;
//$sql = "INSERT INTO new_register(email,mobileno,branch,password,repassword,auth)VALUES('$email','$mobileno','$branch','$password','$repassword','$aa')";
//}
//mysql_query($query);

$con->query($sql);


   $data = "welcome";
if($data){
      echo $data;
   }
mysqli_close($con);

?>
