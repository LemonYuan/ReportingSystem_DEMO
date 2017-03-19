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
	<h2 align="center">箱型图</h2>
	<textarea rows="2" cols="40" id="sql"></textarea><button name="superQuery" class="generating">自写查询</button><br>
	 <select id="tableOne" name="可选表格"></select><br>
	<button name="assembleQuery" class="generating">blox_plot</button>
	<div id="main" style="width: 800px; height: 800px;"></div>
	<script type="text/javascript">
         $(document).ready(function(){
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/getTableName",
   			  dataType:"JSON",
 			  type:'post',
   			  success: function(result){
   					var tableName=result.tableName
   					$("#tableOne").empty()
   					for (var i=0;i<tableName.length;i++){
   						var index=tableName[i]
   						$("#tableOne").append("<option value='"+index+"'>"+index+"<br>")
   					}
   					$("#tableOne").trigger("change");
   		      },
   		    error: function(){
   		    	alert("访问失败")
   			  }
   		  });
        });
         
         $("#tableOne").on("change",function(){
        	var tableName=$('#tableOne option:selected').val()
        	 $.ajax({
      			  url: "http://localhost:8080/reportsystem/getColumnName",
      			  dataType:"JSON",
      			  data:{"tableName":tableName},
    			  type:'post',
      			  success: function(result){
      					alert(result)
      					var columnName=result.columnName
       					for (var i=0;i<columnName.length;i++){
       						var index=columnName[i]
       						$("#tableOne").after("<input  type='checkbox' value='"+index+"' />"+index)
       					}
      		      },
      		    error: function(){
      		    	alert("访问失败")
      			  }
      		  });
         });
         
         $(document).on("click",":checkbox",function(){
        	 var number= $(":checkbox:checked").length
        	 if(number>2)
        		 {
        		     alert("只可选择两个选项")
        		     $(":checkbox").prop('checked',false)
        		 }
         });
	</script>
	

	 
	<script type="text/javascript">
	 $(".generating").click(function(){
		  var data;
     	if($(this).attr('name')=='assembleQuery'){
     		var columnArray=new Array()
       	var tableName=$('#tableOne option:selected').val()
       	$(':checkbox:checked').each(function() { 
       		columnArray.push($(this).val())
       	}); 
         	var obj='{"x":"'+columnArray.join()+'","table":"'+tableName+'","isSQL":"0"}';
         	data=jQuery.parseJSON(obj);
     	}
     	else{
     		var sql=$('#sql').val()
     		var obj='{"sql":"'+sql+'","isSQL":"1"}';
     		data =jQuery.parseJSON(obj);
     	}
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/box_plot",
   			  dataType:"JSON",
   			  data:data,
   			  type:'post',
   			  success: function(result){
   				   if(result){
   					alert(result)
   					var myChart = echarts.init(document.getElementById('main'));
   					var data = echarts.dataTool.prepareBoxplotData(result.data);
   					                                         option = {
   					                                             title: [
   					                                                 {
   					                                                     text: 'Michelson-Morley Experiment',
   					                                                     left: 'center',
   					                                                 },
   					                                                 {
   					                                                     text: 'upper: Q3 + 1.5 * IRQ \nlower: Q1 - 1.5 * IRQ',
   					                                                     borderColor: '#999',
   					                                                     borderWidth: 1,
   					                                                     textStyle: {
   					                                                         fontSize: 14
   					                                                     },
   					                                                     left: '10%',
   					                                                     top: '90%'
   					                                                 }
   					                                             ],
   					                                             tooltip: {
   					                                                 trigger: 'item',
   					                                                 axisPointer: {
   					                                                     type: 'shadow'
   					                                                 }
   					                                             },
   					                                             grid: {
   					                                                 left: '10%',
   					                                                 right: '10%',
   					                                                 bottom: '15%'
   					                                             },
   					                                             xAxis: {
   					                                                 type: 'category',
   					                                                 data: data.axisData,
   					                                                 boundaryGap: true,
   					                                                 nameGap: 30,
   					                                                 splitArea: {
   					                                                     show: false
   					                                                 },
   					                                                 axisLabel: {
   					                                                     formatter: 'expr {value}'
   					                                                 },
   					                                                 splitLine: {
   					                                                     show: false
   					                                                 }
   					                                             },
   					                                             yAxis: {
   					                                                 type: 'value',
   					                                                 name: 'km/s minus 299,000',
   					                                                 splitArea: {
   					                                                     show: true
   					                                                 }
   					                                             },
   					                                             series: [
   					                                                 {
   					                                                     name: 'boxplot',
   					                                                     type: 'boxplot',
   					                                                     data: data.boxData,
   					                                                     tooltip: {
   					                                                         formatter: function (param) {
   					                                                             return [
   					                                                                 'Experiment ' + param.name + ': ',
   					                                                                 'upper: ' + param.data[4],
   					                                                                 'Q3: ' + param.data[3],
   					                                                                 'median: ' + param.data[2],
   					                                                                 'Q1: ' + param.data[1],
   					                                                                 'lower: ' + param.data[0]
   					                                                             ].join('<br/>')
   					                                                         }
   					                                                     }
   					                                                 },
   					                                                 {
   					                                                     name: 'outlier',
   					                                                     type: 'scatter',
   					                                                     data: data.outliers
   					                                                 }
   					                                             ]
   					                                         };
   			                                        myChart.setOption(option);
   				   }
   				   else{
   					   alert("error in result")
   				   }
   		      },
   		    error: function(){
   		    	alert("error in com")
   			  }
   		  });
        })
        
    </script>
</body>
</html>
