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
		<input name="action_name" value="geo_graph" type="hidden" />
		<button id="geo_graph">geo_graph</button>
	</form>
	<form action="changePage" method="POST">
		<input name="action_name" value="line_graph" type="hidden" />
		<button id="line_graph">line_graph</button>
	</form>
	<form action="changePage" method="POST">
		<input name="action_name" value="pie_chart" type="hidden" />
		<button id="pie_chart">pie_chart</button>
	</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="scatter_chart" type="hidden" />
		<button id="scatter_chart">scatter_chart</button>
	</form><br>
	</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="update_all" type="hidden" />
		<button id="update_all">update_all</button>
	</form><br>
	</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="delete_all" type="hidden" />
		<button id="delete_all">delete_all</button>
	</form><br>
	</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="kmeans" type="hidden" />
		<button id="Kmeans">Kmeans</button>
	</form><br>
	</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="EM" type="hidden" />
		<button id="EM">EM</button>
	</form><br>
		</form>
		<form action="changePage" method="POST">
		<input name="action_name" value="ID3" type="hidden" />
		<button id="ID3">ID3</button>
	</form><br>
</body>
</html>
