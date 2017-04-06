<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Reporting System</title>
<!-- 引入 echarts.js -->
<script src="js/echarts.min.js"></script>
<script src="js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h2 align="center">数据更新</h2><br>
   更新 表格<select id="tableOne"></select>，
    在<select id='premis_column' class="column"></select>等于 
    <select id='premis_colum_param'></select>的数据中，将属性
    <select id='column' class="column"></select>设置为
    <input id="column_param" type="text"></input><br>
    <button id="updata" >更新</button>
	
	
	
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
   		    	alert("数据库连接失败")
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
      					$(".column").empty()
       					for (var i=0;i<columnName.length;i++){
       						var index=columnName[i]
       						$(".column").append("<option value='"+index+"'>"+index+"<br>")
       					}
      					$("#premis_column").trigger("change");
      		      },
      		    error: function(){
      		    	alert("数据库连接失败")
      			  }
      		  });
         });
         
         $("#premis_column").on("change",function(){
         	var tableName=$('#tableOne option:selected').val()
         	var columnName=$('#premis_column option:selected').val()
         	 $.ajax({
       			  url: "http://localhost:8080/reportsystem/getColumnParam",
       			  dataType:"JSON",
       			  data:{"tableName":tableName,"columnName":columnName},
     			  type:'post',
       			  success: function(result){
       					var columnName=result.column_param
       					$("#premis_colum_param").empty()
        					for (var i=0;i<columnName.length;i++){
        						var index=columnName[i]
        						$("#premis_colum_param").append("<option value='"+index+"'>"+index+"<br>")
        					}
       		      },
       		    error: function(){
       		    	alert("数据库连接失败")
       			  }
       		  });
          });
	</script>
	<script type="text/javascript">
        $("#updata").click(function(){
        		var tableName=$('#tableOne option:selected').val()
            	var column=$('#column option:selected').val()
            	var column_param=$('#column_param').val()
            	var premis_column=$('#premis_column option:selected').val()
            	var premis_column_param=$('#premis_colum_param option:selected').val()
        	 $.ajax({
   			  url:"http://localhost:8080/reportsystem/update_data",
   			  dataType:"JSON",
   			  data:{"t":tableName,"column":column,"column_param":column_param,"premis_column":premis_column,"premis_column_param":premis_column_param},
   			  type:'post',
   			  success: function(result){
   				  
   		      },
   		    error: function(){
   		    	alert("error in com")
   			  }
   		  });
        })
   </script>
</body>
</html>