package scau.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import scau.services.BarChartService;
import scau.services.BoxPlotService;
import scau.services.ColumnNameService;
import scau.services.GeoGraphService;
import scau.services.LineGraphService;
import scau.services.PieChartService;
import scau.services.ScatterChartService;
import scau.services.TableNameService;

@Controller
public class AssembleController {
    @Autowired
    BarChartService barChartService;
    
    @Autowired
    GeoGraphService geoGraphService;
    
    @Autowired
    BoxPlotService boxPlotService;
    
    @Autowired
    LineGraphService lineGraphService;
    
    @Autowired
    TableNameService tableNameService;
    
    @Autowired
    ColumnNameService columnNameService;
    
    @Autowired
    PieChartService pieChartService;
    
    @Autowired
    ScatterChartService scatterChartService;
    
    @RequestMapping("/assembleQuery")
	public @ResponseBody JsonObject assembleQuery(@RequestParam(value="sql",required=false) String sql,@RequestParam(value="x",required=false) String x,@RequestParam(value="y",required=false) String y,@RequestParam(value="t",required=false) String t,@RequestParam(value="isSQL") Integer isSQL,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
		LinkedHashMap property=new LinkedHashMap();
		property.put("x", x);
		property.put("y", y);
		property.put("t", t);
		property.put("sql", sql);
		property.put("isSQL", isSQL);
		return barChartService.customizedQuery(property);
	}
    
    @RequestMapping("/geo_graph")
	public @ResponseBody JsonObject geoGraph(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
		LinkedHashMap property=new LinkedHashMap();
		property.put("x", x);
		property.put("y", y);
		property.put("t", t);
		return geoGraphService.customizedQuery(property);
	}
    
    @RequestMapping("/pie_chart")
    public @ResponseBody JsonObject pieChart(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
    	LinkedHashMap property=new LinkedHashMap();
    	property.put("x", x);
    	property.put("y", y);
    	property.put("t", t);
    	return pieChartService.customizedQuery(property);
    }
    
    @RequestMapping("/box_plot")
 	public @ResponseBody JsonObject boxPlot(@RequestParam(value="sql",required=false) String sql,@RequestParam(value="isSQL") Integer isSQL,@RequestParam(value="x",required=false) String x,@RequestParam(value="table",required=false) String table,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x);
    	LinkedHashMap property=new LinkedHashMap();
    	if(x!=null){
    		String[] column=x.split(",");
     		property.put("x", column[0].toString());
     		property.put("y",  column[1].toString());
    	}
 		property.put("isSQL", isSQL);
 		property.put("sql", sql);
 		property.put("t", table);
 		System.out.println("进入box");
    	return boxPlotService.customizedQuery(property);
 	}
    
    @RequestMapping("/line_graph")
    public @ResponseBody JsonObject lineGraph(@RequestParam(value="x") String x,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x);
    	LinkedHashMap property=new LinkedHashMap();
    	property.put("x", x);
    	property.put("t", t);
    	return lineGraphService.customizedQuery(property);
    }
    
    @RequestMapping("/scatter_chart")
    public @ResponseBody JsonObject scatterChart(@RequestParam(value="sql",required=false) String sql,@RequestParam(value="isSQL") Integer isSQL,@RequestParam(value="x",required=false) String x,@RequestParam(value="table",required=false) String table,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x);
    	LinkedHashMap property=new LinkedHashMap();
    	if(x!=null){
    		String[] column=x.split(",");
     		property.put("x", column[0].toString());
     		property.put("y",  column[1].toString());
    	}
 		property.put("isSQL",  isSQL);
 		property.put("sql", sql);
 		property.put("t", table);
    	return scatterChartService.customizedQuery(property);
    }
    
    @RequestMapping("/getTableName")
    public  @ResponseBody JsonObject getTableName(HttpServletRequest request, HttpServletResponse response) {
    	return tableNameService.getTableName();
    }
    
    @RequestMapping("/getColumnName")
    public  @ResponseBody JsonObject getColumnName(@RequestParam(value="tableName") String tableName,HttpServletRequest request, HttpServletResponse response) {
    	HashMap map=new HashMap();
    	map.put("tableName", tableName);
    	return columnNameService.getColumnName(map);
    }
    
    @RequestMapping("/changePage")
    public  String changeTo(@RequestParam(value="action_name") String action_name,HttpServletRequest request, HttpServletResponse response) {
    	return action_name;
    }
    
}
