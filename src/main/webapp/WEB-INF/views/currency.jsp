<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<title>Chart</title>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/external/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" rel="stylesheet">
<!--   <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<script>
	$(function() {
		$("#datepicker2").datepicker();
	});
</script>
	<style>
	body{
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
	.button {
       position:absolute;
    transition: .5s ease;
    top: 76%;
    left: 37%;
	}
	</style>

</head>
<body>
	<img alt="Chart" src=${chartUrl } />
	
	<form action="test">
	<p> &nbsp; &nbsp;
		Date from: <input type="text" id="datepicker">
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		Date to: <input type="text" id="datepicker2">
	</p>
	<div >
	<input type="submit" value="Refresh chart" class="button">
	</div>
	</form>
</body>
</html>
