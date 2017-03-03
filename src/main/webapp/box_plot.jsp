<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
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
<h2>Hello World!</h2>
     <button id="assembleQuery" >blox_plot</button>
     <div id="main" style="width: 800px;height:800px;"></div>
 <script type="text/javascript">
        $("#assembleQuery").click(function(){
        	 $.ajax({
   			  url: "http://localhost:8080/reportsystem/box_plot",
   			  dataType:"JSON",
   			  data:{x:"quotedPrice",y:"originalPrice",t:"records"},
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
