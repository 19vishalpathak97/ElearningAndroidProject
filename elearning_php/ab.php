<?php
	$conn=mysqli_connect("localhost","root","root","utube");
	
	
	if(!$conn)
	{
		die ('could not connect : ' . mysqli_error());
		
	}
	echo 'connected successfully';
	
	$branch='computer'//$_POST['branch'];
		$year='se'//$_POST['year'];
	
	
	
	/*//$sql = 'create table employee(id int , name varchar(20))';
	mysql_select_db('example');
	$sql='select course from spinner where branch=$branch and year=$year;
	$retval = mysql_query($sql , $conn);
	
	if(!$retval)
	{
		die ('could not create table : ' . mysql_error());
		
	}
	echo 'table employee created successfully';
	
	
	//
	
	
	$num_rows = mysql_num_rows($i);
12
	while($row = mysql_fetch_array($i))
13
	{
14
	 
15
	echo $row;
	//$r[]=$row;
16
	//$check=$row['Id'];
17
	}
18
	 
19
	/*if($check==NULL)
20
	{
21
	$r[$num_rows]=”Record is not available”;
22
	print(json_encode($r));
23
	}
24
	else
25
	{
26
	$r[$num_rows]=”success”;
27
	print(json_encode($r));
28
	}
29
	 
30
	//mysql_close($localhost);
	*/
	mysqli_close($conn);
	?>

