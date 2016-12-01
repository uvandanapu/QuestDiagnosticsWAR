<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<html>
<head>
	<title>Quest</title>
</head>
<body>
<h1>
	Patient Information saved successfully!
</h1>

<a href="/patientRecord/">Click Here To Go Home Page</a>
<br><br><br>
<table>
<tr>
<td>First Name</td>
<td>${savedInfo.fName}</td>
</tr>
</table>
 
</body>
</html>
