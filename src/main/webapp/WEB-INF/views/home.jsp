<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page session="false"%>
<html lang="us">

<head>
<meta charset="utf-8">
<title>Currency Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
	position: center;
	margin: 0px auto;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
	text-align: center;
	vertical-align: middle;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}

ul { display:table; margin:0 auto;}
</style>
</head>
<body background="<%=request.getContextPath()%>/resources/pics/bg.jpg">
	<p class="tg">The time on the server is ${serverTime}.</p>
	<br>
	<h1 align="CENTER">Currencies exchange rate project</h1>
	
	<p align="CENTER">
	
		Tabela prezentuje kursy walut z ostatniego dnia miesiąca pobrane ze
		strony NBP <a href="http://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html">(link)</a>
		<br>
		Dane na stronie NBP zawarte są w plikach XML każdy plik reprezentuje notowanie z konkretnego dnia tygodnia.
		Do przygotowania danych użyto technologii:
		
	</p>		
	
	<ul>
		<li><b> Java 1.7 </b></li>
		<li><b> Java SAX Paraser </b></li>
		<li><b> Hibernate ver 4</b> </li>
		<li><b> Bazy MySQL ver 5.5</b></li>
		<li><b> Spring MVC framework</b> </li>
		<li><b> Apache Tomcat 6</b></li>
		<li><b> Projekt działa na serwerze <a href="https://www.openshift.com/">OpenShift</a></b></li>
		<li><b> JScript D3.js, jQuery </b></li>
	</ul>
	
	<h2 align="CENTER">A list of currencies</h2>

	<c:if test="${!empty listCurrency}">
		<table class="tg">
			<tr>
				<th width="80">Currency id</th>
				<th width="60">Publication Date</th>
				<th width="60">MAX exchange rate</th>
				<th width="60">MIN exchange rate</th>
				<th width="60">AVG exchange rate</th>
				<th width="60">Currency full name</th>
				<th width="60">Currency Code</th>
			</tr>
			<%
				Object list = request.getAttribute("listCurrency");
					Object[] obj = null;
					Iterator<Object> itr = ((List) list).iterator();
					while (itr.hasNext()) {

						obj = (Object[]) itr.next();
						/* Currency curr = Currency.getInstance((String) obj[2]); */
			%>
			<tr>
				<td><%=obj[0]%></td>
				<td><%=obj[1]%></td>
				<td><%=obj[4]%></td>
				<td><%=obj[5]%></td>
				<td><%=obj[6]%></td>
				<td><%=obj[3]%></td>
				<td><a href="currency?currencyCode=<%=obj[2]%>"><%=obj[2]%></a></td>
			</tr>

			<%
				}
			%>

		</table>
		<a href="currencyD3?currencyCode=<%=obj[2]%>&">Link</a>
	</c:if>
</body>
</html>