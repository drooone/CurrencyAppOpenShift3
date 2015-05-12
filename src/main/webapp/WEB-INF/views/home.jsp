<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</style>
</head>
<body>

	<br>
	<h1>Currency project</h1>
	<p class="tg">The time on the server is ${serverTime}.</p>
	<p>
		Tabela prezentuje kursy walut z ostatniego dnia miesiąca pobrane ze strony NBP <a
			href="http://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html">(link)</a> <br> <strong>Zaaaaa </strong>
	<ul>
		<li>
		<li>
	</ul>

	<h2 align="CENTER">Currency List</h2>

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
					Iterator<Object> itr = ((List) list).iterator();
					while (itr.hasNext()) {

						Object[] obj = (Object[]) itr.next();
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
		<a href="test">Link</a>
	</c:if>
</body>
</html>