<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quest</title>
</head>
<body>
<h1>
	Welcome to Quest 
</h1>

<form action="formRegister"  method="POST">
First Name    : <input type="text" name="fName">
<br /><br />
Last Name     : <input type="text" name="lName" />
<br /><br />
Date Of Birth : <input type="Date" name="dob" />
<br /><br />
Email Id        : <input type="email" name="eMail" />
<br /><br />
Phone         : <input type="text" name="pNum" />
<br /><br />

<input type="submit" value="Submit" />
</form>


</body>
</html>
