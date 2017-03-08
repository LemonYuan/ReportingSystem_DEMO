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
     <button id="assembleQuery" >line_graph</button>
     <div id="main" style="width: 600px;height:400px;"></div>
 <script type="text/javascript">
        $("#assembleQuery").click(function(){
		     
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/line_graph",
   			  dataType:"JSON",
   			  data:{x:"plateNumTime",t:"records"},
   			  type:'post',
   			  success: function(result){
   				   if(result){
   					   alert(result)
   					 var myChart = echarts.init(document.getElementById('main'));
   					option = {
   						    title: {
   						        text: 'Awesome Chart'
   						    },
   						    xAxis: {
   						        data:result.x
   						    },
   						    yAxis: {
   						        scale: true,
   						        axisLabel: {
   						            textStyle: {
   						                color: function(val) {
   						                    if (val > 2500) {
   						                        return 'red';
   						                    }
   						                    else {
   						                        return 'green';
   						                    }
   						                }
   						            }
   						        }
   						    },
   						    series: [{
   						        type: 'line',
   						        data:result.y
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
