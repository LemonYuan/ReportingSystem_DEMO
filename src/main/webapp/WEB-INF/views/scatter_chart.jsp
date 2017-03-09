<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Reporting System</title>
<!-- 引入 echarts.js -->
<script src="js/echarts.min.js"></script>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/ecStat.min.js"></script>
</head>
<body>
	<h2 align="center">柱状图</h2><br>
    <select id="tableOne" name="可选表格"></select><br>
	<button id="assembleQuery">blox_plot</button>
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
        $("#assembleQuery").click(function(){
        	var columnArray=new Array()
        	var tableName=$('#tableOne option:selected').val()
        	$(':checkbox:checked').each(function() { 
        		columnArray.push($(this).val())
        	}); 
        	alert(tableName)
        	alert(columnArray.join())
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/scatter_chart",
   			  dataType:"JSON",
   			  data:{"x":columnArray.join(),"table":tableName},
   			  type:'post',
   			  success: function(result){
   				   if(result){
   					   alert(result)
   					 var myChart = echarts.init(document.getElementById('main'));
   					var data = result.data;

   				 var myRegression = ecStat.regression('polynomial', data, 3);

   				 myRegression.points.sort(function(a, b) {
   				     return a[0] - b[0];
   				 });

   				 myChart.setOption({

   				     tooltip: {
   				         trigger: 'axis',
   				         axisPointer: {
   				             type: 'cross'
   				         }
   				     },
   				     title: {
   				         text: '散点图',
   				         left: 'center',
   				         top: 16
   				     },
   				     xAxis: {
   				         type: 'value',
   				         splitLine: {
   				             lineStyle: {
   				                 type: 'dashed'
   				             }
   				         },
   				         splitNumber: 20
   				     },
   				     yAxis: {
   				         type: 'value',
   				         min: -40,
   				         splitLine: {
   				             lineStyle: {
   				                 type: 'dashed'
   				             }
   				         }
   				     },
   				     series: [{
   				         name: 'scatter',
   				         type: 'scatter',
   				         label: {
   				             emphasis: {
   				                 show: true
   				             }
   				         },
   				         data: data
   				     }, {
   				         name: 'line',
   				         type: 'line',
   				         smooth: true,
   				         showSymbol: false,
   				         data: myRegression.points,
   				         markPoint: {
   				             itemStyle: {
   				                 normal: {
   				                     color: 'transparent'
   				                 }
   				             },
   				             label: {
   				                 normal: {
   				                     show: true,
   				                     position: 'left',
   				                     formatter: myRegression.expression,
   				                     textStyle: {
   				                         color: '#333',
   				                         fontSize: 14
   				                     }
   				                 }
   				             },
   				             data: [{
   				                 coord: myRegression.points[myRegression.points.length - 1]
   				             }]
   				         }
   				     }]
   				 });
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
