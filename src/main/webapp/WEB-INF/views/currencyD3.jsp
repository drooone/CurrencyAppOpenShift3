<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<style>
body {
	font: 10px sans-serif;
	text-align: center;
}

.axis path, .axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}

.area {
	fill: steelblue;
}

.holder{
     text-align: center;
     
  }

</style>
<body background="<%=request.getContextPath()%>/resources/pics/bg.jpg">
	<br><br><br><br><br><br><br><br><br><br><br><br><br>

	<script src="http://d3js.org/d3.v3.js"></script>


	<script>
		var margin = {
			top : 20,
			right : 20,
			bottom : 30,
			left : 50
		}, width = 960 - margin.left - margin.right, height = 500 - margin.top
				- margin.bottom;

		var parseDate = d3.time.format("%d/%m/%Y").parse;

		var x = d3.time.scale().range([ 0, width ]);

		var y = d3.scale.linear().range([ height, 0 ]);

		var xAxis = d3.svg.axis().scale(x).orient("bottom");

		var yAxis = d3.svg.axis().scale(y).orient("left");

		var area = d3.svg.area().x(function(d) {
			return x(d.x);
		}).y0(height).y1(function(d) {
			return y(d.y);
		});

		var svg = d3.select("body").append("svg").attr("width",
				width + margin.left + margin.right).attr("height",
				height + margin.top + margin.bottom).append("g").attr(
				"transform",
				"translate(" + margin.left + "," + margin.top + ")");

		var data = ${listOfDataValues};

		var dataCallback = function(d) {
			d.x = parseDate(d[0]);
			d.y = d[1];
		};
		data.forEach(dataCallback);

		x.domain(d3.extent(data, function(d) {
			return d.x;
		}));
		y.domain([ 0, d3.max(data, function(d) {
			return d.y;
		}) ]);

		svg.append("path").data([ data ]).attr("class", "area").attr("d", area);
		
		 svg.append("g").attr("class", "x axis").attr("transform",
				"translate(0," + height + ")").call(xAxis); 
		 
		 svg.append("g").attr("class", "y axis").call(yAxis).append("text")
				.attr("transform", "rotate(-90)").attr("y", 6).attr("dy",
						".71em").style("text-anchor", "end").text("Avg price"); 
	</script>

      
	
	<div class="holder">
	<font size="2">
      <b>
      Rys. 1 Wykres przygotowany za pomocą biblioteki <a href="http://d3js.org/">D3.js</a>
      </b>
	</font>
</div>
	</body>