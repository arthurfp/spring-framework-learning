<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<title>ArthurFP Demo Project Home Page</title>
</head>

<body>
	<h2>ArthurFP Demo Project Home Page</h2>
	<hr>
	
	<p>
	Welcome to ArthurFP demo project home page!
	</p>
	
	<!-- Add a logout button -->
	
	<form:form
		action="${pageContext.request.contextPath}/logout"
		method="POST">
		
		<input type="submit" value="Logout" />
		
	</form:form>
</body>

</html>