package scau.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import scau.services.DataMiningService;

@Controller
public class DataMiningController {
	@Autowired
	DataMiningService dataMiningService;

	@RequestMapping("/Kmeans")
  	public @ResponseBody String Kmeans(@RequestParam(value = "sql", required = false) String sql,
			@RequestParam(value = "isSQL") Integer isSQL,
			@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "table", required = false) String table, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(columns);
		LinkedHashMap property = new LinkedHashMap();
		if (columns != null) {
			String[] column = columns.split(",");
			List list = new ArrayList();
			for (int i = 0; i < column.length; i++) {
				list.add(column[i]);
			}
			property.put("columns", list);
		}
		property.put("sql", sql);
		property.put("table", table);
  		String url=request.getSession().getServletContext().getRealPath("/");
  		System.out.println("进入box");
     	return dataMiningService.Kmeans(property, url);
  	}
	
	@RequestMapping("/EM")
  	public @ResponseBody String EM(@RequestParam(value = "sql", required = false) String sql,
			@RequestParam(value = "isSQL") Integer isSQL,
			@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "table", required = false) String table, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(columns);
		LinkedHashMap property = new LinkedHashMap();
		if (columns != null) {
			String[] column = columns.split(",");
			List list = new ArrayList();
			for (int i = 0; i < column.length; i++) {
				list.add(column[i]);
			}
			property.put("columns", list);
		}
		property.put("sql", sql);
		property.put("table", table);
  		String url=request.getSession().getServletContext().getRealPath("/");
  		System.out.println("进入box");
     	return dataMiningService.EM(property, url);
  	}
	
	@RequestMapping("/ID3")
  	public @ResponseBody String ID3(@RequestParam(value = "sql", required = false) String sql,
			@RequestParam(value = "isSQL") Integer isSQL,
			@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "table", required = false) String table, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(columns);
		LinkedHashMap property = new LinkedHashMap();
		if (columns != null) {
			String[] column = columns.split(",");
			List list = new ArrayList();
			for (int i = 0; i < column.length; i++) {
				list.add(column[i]);
			}
			property.put("columns", list);
		}
		property.put("sql", sql);
		property.put("table", table);
  		String url=request.getSession().getServletContext().getRealPath("/");
  		System.out.println(url);
     	return dataMiningService.ID3(property, url);
  	}
}
