<?php	//Declared Variables
/*
	Project:	IRP300S Web application
	Name:		Dylan Baadjies
	Stud no:	203064690
*/
 // Declare variables
	if (!isset($_SESSION)){
		session_start();
	}


// initializing variables
$username = "";
$recoveryQuestion = "";
$recoveryAnswer = "";
$cell = "";
$type = "";
$emailAddress = "";
$password = "";
$errors = array(); 
$stud_num = "";
$stud_name = "";
$stud_sname = "";
$stud_email = "";
$stud_cell = "";
$prof_num = "";
$prof_name = "";
$prof_sname = "";
$prof_email = "";
$stud_Rank = "";
$prof_sub = "";
$ven_code = "";
$ven_type = "";
$asses_date = "";
$asses_time = "";
$asses_subject = "";
$asses_room = "";
$stud_Fname = "";
$stud_no_result = "";
$sub_code_result = "";
$stud_no_check = "";
$sub_code_check = "";
$asses_date_check = "";
$image = "";
 

// connect to the database
include_once("DBConn.php");
?>

<?php // REGISTER USER
if (isset($_POST['user'])) {
  // receive all input values from the form
  $recoveryQuestion = mysqli_real_escape_string($DBConnect, $_POST['recoveryQuestion']);
  $recoveryAnswer = mysqli_real_escape_string($DBConnect, $_POST['recoveryAnswer']);
  $emailAddress = mysqli_real_escape_string($DBConnect, $_POST['emailAddress']);
  $password = mysqli_real_escape_string($DBConnect, $_POST['password']);
  

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($recoveryQuestion)) { array_push($errors, "Recovery Question is required"); }
  if (empty($recoveryAnswer)) { array_push($errors, "Recovery answer is required"); }
  if (empty($emailAddress)) { array_push($errors, "Email is required"); }
  if (empty($password)) { array_push($errors, "Password is required"); }

		$_SESSION['success'] = "You are registered successfully";
		$_SESSION['reg'] = false;
		header('location: login');
	  }
	  else{
		  foreach($errors as $error){
			  echo $error."</br>";
		  }
		  ?><p><a href="register.php">back</a></p><?php
	  }
	
}

// ... 
?>

<?php // LOGIN USER
if (isset($_POST['login_user'])) {
  $username = mysqli_real_escape_string($DBConnect, $_POST['username']);
  $password = rtrim(mysqli_real_escape_string($DBConnect, $_POST['password']));
//  $image = rtrim(mysqli_real_escape_string($DBConnect, $_POST['userImage']));

  if (empty($username)) {
  	array_push($errors, "Username is required");
  }
  if (empty($password)) {
  	array_push($errors, "Password is required");
  }

  if (count($errors) == 0) {
  	$password = md5($password);
  	$query = "SELECT * FROM `tbl_User` WHERE `username`= '$username' AND `password`= '$password';";
  	$results = mysqli_query($DBConnect, $query);
	$total_result = mysqli_fetch_assoc($results);
	$macth_fname = $total_result['FName'];
	$macth_lname = $total_result['LName'];
	
	$query2 = "SELECT * FROM `student` WHERE `Stud_FName`= '$macth_fname' AND `Stud_lName`= '$macth_lname';";
	$results2 = mysqli_query($DBConnect, $query2);
	$total_result2 = mysqli_fetch_assoc($results2);
	$stud_match_name = $total_result2['Stud_FName'];
	$stud_match_last_name = $total_result2['Stud_lName'];
	echo $macth_fname."</br>";
	echo $macth_lname."</br>";
	echo $stud_match_name."</br>";
	echo $stud_match_last_name."</br>";
	
  	if (mysqli_num_rows($results) == 1) {
		if('admin'===$username){
			$_SESSION['username'] = $username;
		  $_SESSION['success'] = "You are now logged in";
		  header('location: admin_index.php');
		}else if('didi'===$username){
			$_SESSION['username'] = $username;
		  $_SESSION['success'] = "You are now logged in";
		  header('location: lecturer_index.php');
		}
		else if($macth_fname===$stud_match_name){
		  $_SESSION['username'] = $username;
		//  $_SESSION['userImage'] = $image;
		  $_SESSION['success'] = "You are now logged in";
		  header('location: student_index.php');
		}else{
			$_SESSION['username'] = $username;
		 // print($image);
		  $_SESSION['success'] = "You are now logged in";
		  $_SESSION['reg'] = true;
		  header('location: index.php');
		}
  	}else {
  		array_push($errors, "Wrong username/password combination");
  	}
  }
}
?>

<?php //View user list
if (isset($_POST['view_users'])) {
			  $TableName = "tbl_User";
			  $SQLstring = "SELECT * FROM $TableName";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='100%' border='1'>\n";
			echo "<tr><th>ID</th>" . "<th>Username</th>" ."<th>FName</th><th>LName</th><th>Email</th>" ."<th>Cell Number</th>" ."<th>Password</th><th>User Image</th></tr>\n";
			if ($QueryResult === FALSE)
				echo "<p>Unable to perform SQL Select Table </p>";
			else{
				echo "<p><h1>User List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['id']}</td>";
						echo "<td>{$Row['username']}</td>";
						echo "<td>{$Row['FName']}</td>";
						echo "<td>{$Row['LName']}</td>";
						echo "<td>{$Row['email']}</td>";
						echo "<td>{$Row['CellNum']}</td>";
						echo "<td>{$Row['password']}</td>";//P@55w0rd!
						echo '<td><center><img src="data:image/jpeg;base64,'.base64_encode($Row['userImage']).'"/></center></td></tr>';
					}
			}
			mysqli_close($DBConnect);

			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
}
?>

<?php //View Student list
if (isset($_POST['view_students'])) {
	
			  $TableName = "student";
			  $SQLstring = "SELECT * FROM $TableName";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='90%' border='1'>\n";
			echo "<tr><th>Student no</th>" . "<th>Name</th>" ."<th>Surname</th><th>Email</th><th>Cell Number</th>" ."<th>Profile</th>"."</tr>\n";
			if ($QueryResult === FALSE){
				//echo "<p>Unable to perform SQL Select Table </p>";
			}
			else{
				echo "<p><h1>Student List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['Stud_no']}</td>";
						echo "<td>{$Row['Stud_FName']}</td>";
						echo "<td>{$Row['Stud_lName']}</td>";
						echo "<td>{$Row['Stud_Email']}</td>";
						echo "<td>{$Row['Stud_CellNum']}</td>";	
						echo '<td><center><img src="data:image/jpeg;base64,'.base64_encode($Row['stud_image']).'"/></center></td></tr>';
					}
			}
			mysqli_close($DBConnect);
			
			if('admin'===$_SESSION['username']){
			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
			}else if ('didi'===$_SESSION['username']){
			?>
			<p><a href="lecturer_index.php">back</a></p>
			<?php
			}
}
?>

<?php //View Enroll
if (isset($_POST['view_enroll'])) {
	if('didi'===$_SESSION['username']||$_SESSION['username']==='admin'){
			  $TableName = "enroll";
			  $SQLstring = "SELECT * FROM $TableName";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='90%' border='1'>\n";
			echo "<tr><th>Class</th>" . "<th>Student no</th>" ."<th>Subject code</th><th>Grade</th><th>Enroll Date</th>" ."</tr>\n";
			if ($QueryResult === FALSE){
				//echo "<p>Unable to perform SQL Select Table </p>";
			}
			else{
				echo "<p><h1>Enroll List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['Class_Code']}</td>";
						echo "<td>{$Row['Stud_no']}</td>";
						echo "<td>{$Row['Sub_code']}</td>";
						echo "<td>{$Row['Enroll_Grade']}</td>";
						echo "<td>{$Row['Enroll_Date']}</td></tr>";						
					}
			}
			mysqli_close($DBConnect);
			if('admin'===$_SESSION['username']){
			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
			}else if ('didi'===$_SESSION['username']){
			?>
			<p><a href="lecturer_index.php">back</a></p>
			<?php
			}
	}else{
		$username = $_SESSION['username'];
		$user_name_check_query = "SELECT FName FROM tbl_User WHERE username ='$username'LIMIT 1";
		$user_name_result_query = mysqli_query($DBConnect, $user_name_check_query);
		$user_name = mysqli_fetch_assoc($user_name_result_query);
		$stud_fname = $user_name['FName'];
		
		$stud_name_check_query = "SELECT Stud_no, Stud_FName FROM student WHERE Stud_FName = '$stud_fname' LIMIT 1";
		$stud_name_result_query = mysqli_query($DBConnect, $stud_name_check_query);
		$stud_name = mysqli_fetch_assoc($stud_name_result_query);
		
		$stud_no_check = $stud_name['Stud_no'];
	
		$TableName = "enroll";
			  $SQLstring = "SELECT * FROM enroll WHERE Stud_no = '$stud_no_check'";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='90%' border='1'>\n";
			echo "<tr><th>Class</th>" . "<th>Student no</th>" ."<th>Subject code</th><th>Grade</th><th>Enroll Date</th>" ."</tr>\n";
			if ($QueryResult === FALSE){
				//echo "<p>Unable to perform SQL Select Table </p>";
			}
			else{
				echo "<p><h1>Enroll List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['Class_Code']}</td>";
						echo "<td>{$Row['Stud_no']}</td>";
						echo "<td>{$Row['Sub_code']}</td>";
						echo "<td>{$Row['Enroll_Grade']}</td>";
						echo "<td>{$Row['Enroll_Date']}</td></tr>";						
					}
			}
			mysqli_close($DBConnect);
			
			?>
			<p><a href="student_index.php">back</a></p>
			<?php
	}
}
?>

<?php //View Lecturer list
if (isset($_POST['view_lecturer'])) {
	
			  $TableName = "lecturer";
			  $SQLstring = "SELECT * FROM $TableName";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='90%' border='1'>\n";
			echo "<tr><th>Staff no</th>" . "<th>Name</th>" ."<th>Surname</th><th>Email</th><th>Rank</th>" ."<th>Subject_code</th></tr>\n";
			if ($QueryResult === FALSE){
				//echo "<p>Unable to perform SQL Select Table </p>";
			}
			else{
				echo "<p><h1>Lecturer List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['Prof_No']}</td>";
						echo "<td>{$Row['Prof_FName']}</td>";
						echo "<td>{$Row['Prof_LName']}</td>";
						echo "<td>{$Row['Prof_Email']}</td>";
						echo "<td>{$Row['Prof_Rank']}</td>";						
						echo "<td>{$Row['Sub_code']}</td></tr>";
					}
			}
			mysqli_close($DBConnect);
			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
}
?>

<?php // Upload student
if (isset($_POST['upload_stud'])) {
	?>
			<a href="admin_index.php">back</a>
			
<?php	
	include_once("DBConn.php");
  // receive all input values from the form
  $stud_num = mysqli_real_escape_string($DBConnect, $_POST['stud_no']);
  $stud_name = mysqli_real_escape_string($DBConnect, $_POST['stud_FName']);
  $stud_sname = mysqli_real_escape_string($DBConnect, $_POST['stud_LName']);
  $stud_email = mysqli_real_escape_string($DBConnect, $_POST['stud_email']);
  $stud_cell = mysqli_real_escape_string($DBConnect, $_POST['stud_CellNum']);

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($stud_num)) { array_push($errors, "Student number is required"); }
  if (empty($stud_name)) { array_push($errors, "First name is required"); }
  if (empty($stud_sname)) { array_push($errors, "Last name is required"); }
  if (empty($stud_cell)) { array_push($errors, "Contact number is required"); }
  if (empty($stud_email)) { array_push($errors, "Email is required"); }
  
	$file = $_FILES['StudImage'];     //Path to store uploaded images
	$fileName = $file['name']; 
	$fileTmp = $_FILES['StudImage']['tmp_name'];
	$image = addslashes(file_get_contents($_FILES['StudImage']['tmp_name']));
	$fileSize = $_FILES['StudImage']['size'];
	$fileError = $_FILES['StudImage']['error'];
	$fileExt = explode('.', $fileName);		//split the name from the extension
	$fileActualExt = strtolower(end($fileExt));		//make sure extensions is small caps
	
	$allowed = array('jpg', 'jpeg', 'png');		//to make sure only these extensions are uploaded
	
	
	
  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $stud_check_query = "SELECT * FROM student WHERE Stud_no='$stud_num' OR Stud_Email='$stud_email' LIMIT 1";
  $result = mysqli_query($DBConnect, $stud_check_query);
  $stud = mysqli_fetch_assoc($result);

  if ($stud) { // if user exists
    if ($stud['Stud_no'] === $stud_num) {
      array_push($errors, "Student already exists");
    }

    if ($stud['Stud_Email'] === $stud_email) {
      array_push($errors, "email already exists");
    }
	
	if(!in_array($fileActualExt, $allowed)){
		array_push($errors, "You cannot upload this type of file!");
	}
	if($fileError!=0){
		array_push($errors, "There was an error uploading your file!");
	}
	if($fileSize>1000000){
		array_push($errors, "Your file is too Big!");
	}
  }

  // Finally, register Student if there are no errors in the form
  if (count($errors) == 0) {
  	$query = "INSERT INTO student (Stud_no, Stud_FName, Stud_lName, Stud_Email, Stud_CellNum, stud_image) 
  			  VALUES('$stud_num','$stud_name', '$stud_sname', '$stud_email', '$stud_cell', '$image')";
  	mysqli_query($DBConnect, $query);
  	$_SESSION['stud_no'] = $stud_num;
	$_SESSION['StudImage'] = $image;
  	$_SESSION['success'] = "Student Uploaded successfully";
	mysqli_close($DBConnect);
  	header('location: admin_index.php');
  }
  else{
	  foreach($errors as $error){
			  echo $error."</br>";
		  }
  }
}

?>

<?php // Upload Lecturer
if (isset($_POST['upload_lect'])) {
	?>
			<a href="admin_index.php">back</a>
<?php	
	include_once("DBConn.php");
  // receive all input values from the form
  $prof_num = mysqli_real_escape_string($DBConnect, $_POST['staff_no']);
  $prof_name = mysqli_real_escape_string($DBConnect, $_POST['prof_FName']);
  $prof_sname = mysqli_real_escape_string($DBConnect, $_POST['prof_LName']);
  $prof_email = mysqli_real_escape_string($DBConnect, $_POST['prof_email']);
  $stud_Rank = mysqli_real_escape_string($DBConnect, $_POST['prof_Rank']);
  $prof_sub = mysqli_real_escape_string($DBConnect, $_POST['prof_Sub']);

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($prof_num)) { array_push($errors, "Staff number is required"); }
  if (empty($prof_name)) { array_push($errors, "First name is required"); }
  if (empty($prof_sname)) { array_push($errors, "Last name is required"); }
  if (empty($prof_email)) { array_push($errors, "Email is required"); }
  if (empty($stud_Rank)) { array_push($errors, "Rank is required"); }
  if (empty($prof_sub)) { array_push($errors, "Subject is required"); }
  
  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $prof_check_query = "SELECT * FROM lecturer WHERE Prof_No='$prof_num' OR Prof_Email='$prof_email' LIMIT 1";
  $sub_code_check_query = "SELECT Sub_code, Sub_Title FROM `subjects` WHERE `Sub_Title` = '$prof_sub'";
  $result = mysqli_query($DBConnect, $prof_check_query);
  $result2 = mysqli_query($DBConnect, $sub_code_check_query);
  $prof = mysqli_fetch_assoc($result);
  $sub_code = mysqli_fetch_assoc($result2);
	$sub_code_result = $sub_code['Sub_code'];
	
  if ($prof) { // if user exists
    if ($prof['Prof_No'] === $prof_num) {
      array_push($errors, "Lecturer already exists");
	  echo $prof['Prof_No'].' Already exist<br>';
    }

    if ($prof['Prof_Email'] === $prof_email) {
      array_push($errors, "email already exists");
	  echo $prof['Prof_Email'].' Already exist<br>';
    }
	
  }

  // Finally, register Student if there are no errors in the form
  if (count($errors) == 0) {
  	$query = "INSERT INTO Lecturer (Prof_No, Prof_FName, Prof_LName, Prof_Email, Prof_Rank, Sub_code) 
  			  VALUES('$prof_num','$prof_name', '$prof_sname', '$prof_email', '$stud_Rank', '$sub_code_result')";
  	mysqli_query($DBConnect, $query);
  	$_SESSION['Prof_No'] = $prof_num;
  	$_SESSION['success'] = "Lecturer Uploaded successfully";
	mysqli_close($DBConnect);
  	header('location: admin_index.php');
  }
}

?>

<?php // Upload Venue
if (isset($_POST['upload_ven'])) {
	if('admin'===$_SESSION['username']){
	?>
		<p><a href="admin_index.php">back</a></p>
	<?php
	}else if ('didi'===$_SESSION['username']){
		?>
		<p><a href="lecturer_index.php">back</a></p>
			<?php
	}
	include_once("DBConn.php");
  // receive all input values from the form
  $ven_code = mysqli_real_escape_string($DBConnect, $_POST['Ven_code']);
  $ven_type = mysqli_real_escape_string($DBConnect, $_POST['Ven_type']);

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($ven_code)) { array_push($errors, "Venue code is required"); }
  if (empty($ven_type)) { array_push($errors, "Venue type is required"); }

  
  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
  $ven_check_query = "SELECT * FROM venue WHERE Ven_code='$ven_code'LIMIT 1";
  $result = mysqli_query($DBConnect, $ven_check_query);
  $ven = mysqli_fetch_assoc($result);

  if ($ven) { // if venue exists
    if ($ven['Ven_code'] === $ven_code) {
      array_push($errors, "Venue already exists");
	  echo $ven['Ven_code'].' Already exist<br>';
    }
  }

  // Finally, register Student if there are no errors in the form
  if (count($errors) == 0) {
  	$query = "INSERT INTO venue (Ven_code, Ven_type) 
  			  VALUES('$ven_code','$ven_type')";
  	mysqli_query($DBConnect, $query);
  	$_SESSION['Ven_code'] = $ven_code;
  	$_SESSION['success'] = "Venue Uploaded successfully";
	mysqli_close($DBConnect);
	if('admin'===$_SESSION['username']){
		header('location: admin_index.php');
	}else if ('didi'===$_SESSION['username']){
		header('location: lecturer_index.php');
	}
  }
}

?>

<?php // Enroll
if (isset($_POST['enrol_sub'])) {
	?>
			<p><a href="student_index.php">back</a></p>
<?php	
	include_once("DBConn.php");
  // receive all input values from the form
  $enroll = mysqli_real_escape_string($DBConnect, $_POST['Enroll']);
  $name = $_SESSION['username'];

  
  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($enroll)) { array_push($errors, "Subject title is required"); }
  if (empty($name)) { array_push($errors, "Student needs to be registered. Please consult Lecturer"); }
	
  echo $enroll."<br>";
  echo $name."<br>";

  // first check the database to make sure 
  // a user does not already exist with the same username and/or email
	$user_name_check_query = "SELECT FName FROM tbl_User WHERE username ='$name'LIMIT 1";
	$user_name_result_query = mysqli_query($DBConnect, $user_name_check_query);
	$user_name = mysqli_fetch_assoc($user_name_result_query);
	
	$sub_check_query = "SELECT Sub_code  FROM subjects WHERE Sub_Title ='$enroll'LIMIT 1";
	$result = mysqli_query($DBConnect, $sub_check_query);
	$Sub_code = mysqli_fetch_assoc($result);
	
	echo $user_name['FName']."<br>";
	echo $Sub_code['Sub_code']."<br>";
	$stud_fname = $user_name['FName'];
	
	
	$stud_name_check_query = "SELECT Stud_no, Stud_FName FROM student WHERE Stud_FName = '$stud_fname' LIMIT 1";
	$stud_name_result_query = mysqli_query($DBConnect, $stud_name_check_query);
	$stud_name = mysqli_fetch_assoc($stud_name_result_query);
	
	$sub_code_result = $Sub_code['Sub_code'];
	$stud_no_check = $stud_name['Stud_no'];
	
	$stud_exist_check_query = "SELECT * FROM enroll WHERE Stud_no = '$stud_no_check' AND Sub_code = '$sub_code_result'";
	$stud_exist_result_query = mysqli_query($DBConnect, $stud_exist_check_query);
	$stud_exist_result = mysqli_fetch_assoc($stud_exist_result_query);
	echo $stud_exist_result['Stud_no']."<br>";
  
  if (mysqli_num_rows($stud_exist_result_query)==null) { // if user exists
    if ($stud_name['Stud_FName'] === $user_name['FName']) {
	  echo $stud_name['Stud_FName'].' Ready for enrollment<br>';
	  $stud_no_result = $stud_name['Stud_no'];
    }
		
  }else{
		array_push($errors, "Student Already Enrolled for this subject");
		echo "<p>Student Already Enrolled for this subject.  </p>";
	}

  // Finally, register Student if there are no errors in the form
  if (count($errors) == 0) {
	  
  	$query = "INSERT INTO enroll (Class_Code, Stud_no, Sub_code, Enroll_Grade, Enroll_Date) 
  			  VALUES('WD001','$stud_no_result', '$sub_code_result', 'B', Current_Date())";
  	mysqli_query($DBConnect, $query);
  	$_SESSION['stud_no'] = $stud_num;
  	$_SESSION['success'] = "Enrollment successfully";
	mysqli_close($DBConnect);
  	header('location: student_index.php');
  }
  else
		echo "<p>Unable to perform SQL Select Table </p>";
}

?>

<?php // Upload Assessment
if (isset($_POST['upload_assess'])) {
		?>
		<p><a href="lecturer_index.php">back</a></p>
			<?php
	
	include_once("DBConn.php");
  // receive all input values from the form
  $asses_date = mysqli_real_escape_string($DBConnect, $_POST['Asses_date']);
  $asses_time = mysqli_real_escape_string($DBConnect, $_POST['time']);
  $asses_room = mysqli_real_escape_string($DBConnect, $_POST['Asses_room']);
  $asses_subject = mysqli_real_escape_string($DBConnect, $_POST['Asses_subject']);

  // form validation: ensure that the form is correctly filled ...
  // by adding (array_push()) corresponding error unto $errors array
  if (empty($asses_date)) { array_push($errors, "Date is required"); }
  if (empty($asses_time)) { array_push($errors, "Time is required"); }
  if (empty($asses_room)) { array_push($errors, "Room is required"); }
  if (empty($asses_subject)) { array_push($errors, "Subject is required"); }
  
   // first check the database to make sure 
  // a user does not already exist with the same username and/or email
	$asses_date_check_query = "SELECT assessment_date FROM assessment WHERE assessment_date ='$asses_date'LIMIT 1";
	$asses_date_result_query = mysqli_query($DBConnect, $asses_date_check_query);
	$asses_date_end = mysqli_fetch_assoc($asses_date_result_query);
	
	$ven_check_query = "SELECT Ven_code FROM venue WHERE Ven_Type ='$asses_room'LIMIT 1";
	$result = mysqli_query($DBConnect, $ven_check_query);
	$Ven_code = mysqli_fetch_assoc($result);
	
	$asses_subject = "SELECT Sub_code FROM subjects WHERE Sub_Title ='$asses_subject'LIMIT 1";
	$result = mysqli_query($DBConnect, $asses_subject);
	$Sub_code = mysqli_fetch_assoc($result);
	
	echo $asses_date."<br>";
  echo $asses_time."<br>";
  echo $asses_room."<br>";
  echo $Sub_code['Sub_code']."<br>";
	

  // Finally, register Student if there are no errors in the form
  if (count($errors) == 0) {
	  $asses_subject = $Sub_code['Sub_code'];
  	$query = "INSERT INTO assessment (assessment_date, Class_Time, Sub_code, Ven_Type) VALUES('$asses_date','$asses_time','$asses_subject','$asses_room')";
  	mysqli_query($DBConnect, $query);
  	$_SESSION['success'] = "Assessment Uploaded successfully";
	mysqli_close($DBConnect);
	if ('didi'===$_SESSION['username']){
		header('location: lecturer_index.php');
	}
  }
}

?>

<?php //View Assessment
if (isset($_POST['view_assess'])) {
	
			  $TableName = "assessment";
			  $SQLstring = "SELECT * FROM $TableName";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			
			echo "<table width='90%' border='1'>\n";
			echo "<tr><th>Date</th>" . "<th>Time</th>" ."<th>Subject code</th><th>Venue</th>" ."</tr>\n";
			if ($QueryResult === FALSE){
				//echo "<p>Unable to perform SQL Select Table </p>";
			}
			else{
				echo "<p><h1>Assessment List". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) 
					{
						echo "<tr><td>{$Row['assessment_date']}</td>";
						echo "<td>{$Row['Class_Time']}</td>";
						echo "<td>{$Row['Sub_code']}</td>";
						echo "<td>{$Row['Ven_Type']}</td></tr>";						
					}
			}
			mysqli_close($DBConnect);
			if('admin'===$_SESSION['username']){
			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
			}else if ('didi'===$_SESSION['username']){
			?>
			<p><a href="lecturer_index.php">back</a></p>
			<?php
			}else {
				?>
				<p><a href="student_index.php">back</a></p>
				<?php
			}
}
?>

<?php //Check Student
if (isset($_POST['check_stud'])) {
		$stud_no = $_POST['stud_no'];
		//$asses_date_check = Current_Date();
		if (empty($stud_no)) {
			array_push($errors, "Please enter Student number");
		}
	
			  $TableName = "student";
			  $SQLstring = "SELECT * FROM $TableName WHERE Stud_no = '$stud_no';";
			  $QueryResult = mysqli_query($DBConnect, $SQLstring);
			  
			  $TableName2 = "enroll";
			  $SQLstring2 = "SELECT * FROM $TableName2 WHERE Stud_no = '$stud_no';";
			  $QueryResult2 = mysqli_query($DBConnect, $SQLstring2);

			  
			
			echo $stud_no;
			echo $stud_no_check;
			echo $sub_code_check;
			
			if ($QueryResult == FALSE){
				array_push($errors, "Student does not exist!!");
			}
			/*if ($QueryResult2 === FALSE){
				array_push($errors, "Student exist but not yet enrolled in a subject");
			}*/
			
			if (count($errors) == 0) {
				echo "<p><h1>Student". "</p></h1>";
				while ($Row = mysqli_fetch_assoc($QueryResult)) {
					echo "<tr><td>{$Row['Stud_no']}</td>";
						echo "<td>{$Row['Stud_FName']}</td>";
						echo "<td>{$Row['Stud_lName']}</td>";	
						if($Row['stud_image']!=null){
							echo '<td><center><img src="data:image/jpeg;base64,'.base64_encode($Row['stud_image']).'"/></center></td></tr>';
						}
						else{
							echo '<td><center><img scr = "images/profiledefault.jpg"></center></td></tr>';
						}
						
						while ($Row1 = mysqli_fetch_assoc($QueryResult2)) 
						{
							$sub_code_check = $Row1['Sub_code'];
							$TableName3 = "assessment";
							  $SQLstring3 = "SELECT * FROM $TableName3 WHERE Sub_code = '$sub_code_check';";
							  $QueryResult3 = mysqli_query($DBConnect, $SQLstring3);
							
							if ($QueryResult3 === FALSE){
								echo "No Assessment yet for Student";
							}
							
							while ($Row2 = mysqli_fetch_assoc($QueryResult3)) 
							{
								$date = Current_Date();
								if($date!==$Row2['assessment_date']){
									echo "Student is writting".$Row2['Sub_code']." today in ".$Row2['Ven_Type']."</br>";
								}
								else
									echo "Student is not writting today";
							}
						}
				}
					
				
			mysqli_close($DBConnect);
			}
			else{
				foreach($errors as $error){
			  echo $error."</br>";
			}
			}
			?>
			<p><a href="admin_index.php">back</a></p>
			<?php
			
}
?>
