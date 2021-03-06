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
<script src="js/showText.js"></script>
</head>
<body>
	<h2 align="center">ID3</h2>
	<textarea rows="2" cols="40" id="sql"></textarea>
	<button name="superQuery" class="generating">自写查询</button>
	<br> 表名：
	<select id="tableOne" name="可选表格"></select> 分类字段 ：
	<select id="classification_column"></select>
	<br> 字段名：
	<div id="columns"></div>
	<button name="assembleQuery" class="generating">ID3</button>
	<div id="main" style="width: 800px; height: 800px;"></div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.ajax({
										url : "http://localhost:8080/reportsystem/getTableName",
										dataType : "JSON",
										type : 'post',
										success : function(result) {
											var tableName = result.tableName
											$("#tableOne").empty()
											for (var i = 0; i < tableName.length; i++) {
												var index = tableName[i]
												$("#tableOne").append(
														"<option value='"+index+"'>"
																+ index
																+ "<br>")
											}
											$("#tableOne").trigger("change");
										},
										error : function() {
											alert("访问失败")
										}
									});
						});

		$("#tableOne")
				.on(
						"change",
						function() {
							var tableName = $('#tableOne option:selected')
									.val()
							$
									.ajax({
										url : "http://localhost:8080/reportsystem/getColumnName",
										dataType : "JSON",
										data : {
											"tableName" : tableName
										},
										type : 'post',
										success : function(result) {
											var columnName = result.columnName
											$("#columns").empty();
											for (var i = 0; i < columnName.length; i++) {
												var index = columnName[i]
												$("#columns").append(
														"<label><input  type='checkbox' value='"+index+"' />"
																+ index
																+ "</label>")
											}
											$("#classification_column").empty()
											for (var i = 0; i < columnName.length; i++) {
												var index = columnName[i]
												$("#classification_column")
														.append(
																"<option value='"+index+"'>"
																		+ index
																		+ "<br>")
											}
										},
										error : function() {
											alert("访问失败")
										}
									});
						});
	</script>



	<script type="text/javascript">
		$(".generating").click(
				function() {
					var data;
					if ($(this).attr('name') == 'assembleQuery') {
						var columnArray = new Array()
						var tableName = $('#tableOne option:selected').val()
						$(':checkbox:checked').each(function() {
							columnArray.push($(this).val())
						});
						columnArray.push($('#classification_column option:selected').val());
						var obj = '{"columns":"' + columnArray.join()
								+ '","table":"' + tableName + '","isSQL":"0"}';
						data = jQuery.parseJSON(obj);
					} else {
						var sql = $('#sql').val()
						var obj = '{"sql":"' + sql + '","isSQL":"1"}';
						data = jQuery.parseJSON(obj);
					}
					$.ajax({
						url : "http://localhost:8080/reportsystem/ID3",
						dataType : "TEXT",
						data : data,
						type : 'post',
						success : function(result) {
							   showText("output.txt");
						},
						error : function() {
							alert("error in com")
						}
					});
				})
	</script>
</body>
</html>
