<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Reporting System</title>
<!-- 引入 echarts.js -->
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/echarts.js"></script>
<script src="js/dataTool.min.js"></script>
</head>
<body>
	<h2>head page</h2>
	<form action="changePage" method="POST">
		<input name="action_name" value="bar_chart" type="hidden" />
		<button id="bar_chart">bar_chart</button>
	</form>
	<form action="changePage" method="POST">
		<input name="action_name" value="box_plot" type="hidden" />
		<button id="box_plot">box_plot</button>
	</form>
	<form action="changePage" method="POST">
		<input value="geo_graph" type="hidden" />
		<button id="geo_graph">geo_graph</button>
	</form>
	<form action="changePage" method="POST">
		<input name="action_name" value="line_graph" type="hidden" />
		<button id="line_graph">line_graph</button>
	</form>
	<form action="index.jsp" method="POST">
		<input name="action_name" value="data_minning" type="hidden" />
		<button id="data_minning">data_minning</button>
	</form>
</body>
</html>
