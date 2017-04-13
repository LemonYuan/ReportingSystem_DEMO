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
	<h2 align="center">散点图</h2><br>
		<textarea rows="2" cols="40" id="sql"></textarea><button name="superQuery" class="generating">自写查询</button><br>
    表名：<select id="tableOne" name="可选表格"></select>
    回归类型：<select id="regressionType">
    <option value="linear">linear
    <option value="exponential">exponential
    <option value="logarithmic">logarithmic
    <option value="polynomial">polynomial
    </select><br>
   字段名： <div id="columns"></div>
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
      					var columnName=result.columnName
      					$("#columns").empty()
       					for (var i=0;i<columnName.length;i++){
       						var index=columnName[i]
       						$("#columns").append("<label><input  type='checkbox' value='"+index+"' />"+index+"</label>")
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
   			  url: "http://localhost:8080/reportsystem/scatter_chart",
   			  dataType:"JSON",
   			  data:data,
   			  type:'post',
   			  success: function(result){
   				  var regression=$('#regressionType option:selected').val()
   				   if(result){
   					 var myChart = echarts.init(document.getElementById('main'));
   					var data = result.data;
   				 var myRegression = ecStat.regression(regression, data, 3);

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
