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
import scau.services.GeoGraphService;

@Controller
public class AssembleController {
    @Autowired
    BarChartService barChartService;
    
    @Autowired
    GeoGraphService geoGraphService;
    
    @RequestMapping("/assembleQuery")
	public @ResponseBody JsonObject assembleQuery(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
		LinkedHashMap property=new LinkedHashMap();
		property.put("x", x);
		property.put("y", y);
		property.put("t", t);
		return barChartService.customizedQuery(property);
	}
    
    @RequestMapping("/assembleQuery1")
	public @ResponseBody JsonObject assembleQuery2(@RequestParam(value="x") String x,@RequestParam(value="y") String y,@RequestParam(value="t") String t,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(x+y);
		LinkedHashMap property=new LinkedHashMap();
		property.put("x", x);
		property.put("y", y);
		property.put("t", t);
		return geoGraphService.customizedQuery(property);
	}
}
