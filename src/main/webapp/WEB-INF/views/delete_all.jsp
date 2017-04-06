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
   删除表格<select id="tableOne"></select>中
属性<select id='column'></select>为
   <select id='column_param' ></select>的所有行<br>
    <button id="updata" >删除</button>
	
	
	
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
      					$("#column").empty()
       					for (var i=0;i<columnName.length;i++){
       						var index=columnName[i]
       						$("#column").append("<option value='"+index+"'>"+index+"<br>")
       					}
      					$("#column").trigger("change");
      		      },
      		    error: function(){
      		    	alert("数据库连接失败")
      			  }
      		  });
         });
         
         $("#column").on("change",function(){
         	var tableName=$('#tableOne option:selected').val()
         	var columnName=$('#column option:selected').val()
         	 $.ajax({
       			  url: "http://localhost:8080/reportsystem/getColumnParam",
       			  dataType:"JSON",
       			  data:{"tableName":tableName,"columnName":columnName},
     			  type:'post',
       			  success: function(result){
       					var columnName=result.column_param
       					$("#column_param").empty()
        					for (var i=0;i<columnName.length;i++){
        						var index=columnName[i]
        						$("#column_param").append("<option value='"+index+"'>"+index+"<br>")
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
        	 $.ajax({
   			  url:"http://localhost:8080/reportsystem/delete_all",
   			  dataType:"JSON",
   			  data:{"t":tableName,"column":column,"column_param":column_param},
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