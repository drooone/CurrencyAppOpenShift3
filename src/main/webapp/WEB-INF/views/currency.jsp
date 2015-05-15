<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="us">
<head>
<meta charset="utf-8">
<title>Charts4J</title>
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
.holder{
     text-align: center;
  }
</style>

</head>
<body background="<%=request.getContextPath()%>/resources/pics/bg.jpg">
<br><br><br><br><br>
	<c:set var="path" value="${currencyCode}" />
	<div class="holder" style="float:left">
      <div class="newsfeed_photo">
      
          <img alt="Small" class="photo_thumb_frame" src=${chartUrl } border="3" vspace="5"/>
      </div>
      <div class="newsfeed_date">
      <b>
      Rys. 1 Wykres przygotowany za pomocą <a href="https://code.google.com/p/charts4j/">Charts4J</a>
      </b>
      </div>
	</div>
	
	<h2 align="center">Zakres dat</h2>

	<form:form id="myform" method="post" action="currency2">
		<p>
			&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<b>Code:</b> <input name="currencyCode" value="${currencyCode}" readonly>
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
			<b>Date from:</b> <input name="dateFrom" id="dateFrom" value="${dateFrom}" /> 
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
			<b>Date to:</b> <input name="dateTo" id="dateTo" value="${dateTo}" />
		</p>
		<div align="center">
			<input type="submit" value="Refresh chart" >
		</div>
	</form:form>
	<br>
	<br>
	<br>
	<p align="center">
	<font size="3">
	<a href="currencyD3?currencyCode=${currencyCode}&dateFrom=${dateFrom}&dateTo=${dateTo}">Alternatywna wersja wykresy z użyciem JavaScript i biblioteki D3.js</a>
	</font>
	</p>
</body>
</html>
