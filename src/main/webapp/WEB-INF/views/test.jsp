<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<html>

<head>
<title>Currency Page</title>

</head>
<body>
<h1>Test Page</h1>
	<p >The time on the server is ${serverTime}.</p>
	
	<form:form method="post" action="test" >
       
        <!--Notice, this is normal html tag, will not be bound to an object -->
       <input name="currencyCode" type="text"/>
   
            <input type="submit" value="send"/>
   
</form:form>
</body>
</html>