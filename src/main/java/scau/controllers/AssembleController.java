package scau.controllers;

import java.util.LinkedHashMap;

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
import scau.services.GeoGraphService;
import scau.services.LineGraphService;

@Controller
public class AssembleController {
    @Autowired
    BarChartService barChartService;
    
    @Autowired
    GeoGraphService geoGraphService;
    
    @Autowired
    BoxPlotService BoxPlotService;
    
    @Autowired
    LineGraphService lineGraphService;
    
    @RequestMapping("/assembleQuery")
	public @ResponseBody JsonObject assembleQuery(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
		LinkedHashMap property=new LinkedHashMap();
		property.put("x", x);
		property.put("y", y);
		property.put("t", t);
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
    
    @RequestMapping("/box_plot")
 	public @ResponseBody JsonObject boxPlot(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
     	System.out.println(x+y);
 		LinkedHashMap property=new LinkedHashMap();
 		property.put("x", x);
 		property.put("y", y);
 		property.put("t", t);
 		return BoxPlotService.customizedQuery(property);
 	}
    
    @RequestMapping("/line_graph")
    public @ResponseBody JsonObject lineGraph(@RequestParam(value="x") String x,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x);
    	LinkedHashMap property=new LinkedHashMap();
    	property.put("x", x);
    	property.put("t", t);
    	return lineGraphService.customizedQuery(property);
    }
}
