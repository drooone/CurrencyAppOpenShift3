<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="us">
<head>
<meta charset="utf-8">
<title>Chart</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/external/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" rel="stylesheet">
<script>
	$(function() {
		$("#dateFrom").datepicker();
	});
</script>
<script>
	$(function() {
		$("#dateTo").datepicker();
	});
</script>
<style>
body {
	font: 80% "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog-link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog-link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}

.fakewindowcontain .ui-widget-overlay {
	position: absolute;
}

select {
	width: 200px;
}
</style>

</head>
<body>
	
	<c:set var="path" value="${currencyCode}" />
	<%-- 	<a href="currency?currencyCode=${path}&dateFrom=01/01/2009&dateTo=01/04/2015">link</a> --%>
	<img alt="Chart" src=${chartUrl } />

	<form:form method="post" action="currency2">
		<p>
			&nbsp; &nbsp; Code: <input name="currencyCode" value="${currencyCode}"> Date from: 
			<input name="dateFrom" id="dateFrom" value="${dateFrom}"/> &nbsp;
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Date to: 
			<input name="dateTo" id="dateTo" value="${dateTo}"/>
		</p>
		<div>
			<input type="submit" value="Refresh chart" class="button">
		</div>
	</form:form>
<a href="test?currencyCode=${currencyCode}">Link to D3 chart</a>
</body>
</html>
