<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
</head>
<body>
  <h2 align="center">饼状图</h2><br>
    <select id="tableOne" name="可选表格"></select> 
    <select id='columnOne' name='可选属性'></select>
    <button id="assembleQuery">图片生成</button>
    <div id="main" style="width:1000px; height: 400px;"></div>
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
        	alert(tableName)
        	 $.ajax({
      			  url: "http://localhost:8080/reportsystem/getColumnName",
      			  dataType:"JSON",
      			  data:{"tableName":tableName},
    			  type:'post',
      			  success: function(result){
      					alert(result)
      					var columnName=result.columnName
      					$("#columnOne").empty()
       					for (var i=0;i<columnName.length;i++){
       						var index=columnName[i]
       						$("#columnOne").append("<option value='"+index+"'>"+index+"<br>")
       					}
      		      },
      		    error: function(){
      		    	alert("访问失败")
      			  }
      		  });
         });
	</script>
	
    <script type="text/javascript">
        	
            $("#assembleQuery").click(function(){
            	var tableName=$('#tableOne option:selected').val()
            	var columnName=$('#columnOne option:selected').val()
           	 $.ajax({
      			  url: "http://localhost:8080/reportsystem/pie_chart",
      			  dataType:"JSON",
      			data:{x:columnName,y:"count("+columnName+")",t:tableName},
      			  success: function(result){
      				   if(result){
      					   alert(result)
      	   				    var myChart = echarts.init(document.getElementById('main'));
      					 option = {
      						    tooltip: {
      						        trigger: 'item',
      						        formatter: "{a} <br/>{b}: {c} ({d}%)"
      						    },
      						    legend: {
      						        orient: 'vertical',
      						        x: 'left',
      						        data:result.title
      						    },
      						    series: [
      						        {
      						            name:'访问来源',
      						            type:'pie',
      						            radius: ['50%', '70%'],
      						            avoidLabelOverlap: false,
      						            label: {
      						                normal: {
      						                    show: false,
      						                    position: 'center'
      						                },
      						                emphasis: {
      						                    show: true,
      						                    textStyle: {
      						                        fontSize: '30',
      						                        fontWeight: 'bold'
      						                    }
      						                }
      						            },
      						            labelLine: {
      						                normal: {
      						                    show: false
      						                }
      						            },
      						            data:result.data
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