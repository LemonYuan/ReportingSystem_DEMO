<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<html>
 <head>
  <meta charset="utf-8">
    <title>Reporting System</title>
    <!-- 引入 echarts.js -->
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
 </head>
<body>
<h2>Hello World!</h2>
     <button id="assembleQuery" >assembleQuery</button>
     <div id="main" style="width: 600px;height:400px;"></div>
 <script type="text/javascript">
        $("#assembleQuery").click(function(){
		     
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/assembleQuery",
   			  dataType:"JSON",
   			  data:{x:"quotedPrice",y:"count(quotedPrice)",t:"records"},
   			  type:'post',
   			  success: function(result){
   				   if(result){
   					   alert(result)
   					 var myChart = echarts.init(document.getElementById('main'));
      				    var option = {
      				        title: {
      				            text: 'bar chart'
      				        },
      				        tooltip: {},
      				        legend: {
      				            data:"quantities"
      				        },
      				        xAxis: {
      				            data:result.categories
      				        },
      				        yAxis: {},
      				        series: [{
      				            name: 'quantities',
      				            type: 'bar',
      				            data: result.data
      				        }]
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
