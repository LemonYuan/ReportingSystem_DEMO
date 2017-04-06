package scau.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import scau.services.DataMiningService;

@Controller
public class DataMiningController {
	@Autowired
	DataMiningService dataMiningService;

	@RequestMapping("/Kmeans")
  	public @ResponseBody String Kmeans(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println(request.getSession().getServletContext().getRealPath("/"));
     	String url=request.getSession().getServletContext().getRealPath("/");
	    LinkedHashMap property=new LinkedHashMap();
     		List list =new ArrayList();
     		list.add("originalPrice");
     		list.add("quotedPrice");
     	property.put("columns", list);
  		property.put("table", "records");
  		System.out.println("进入box");
     	return dataMiningService.ID3(property,url);
  	}
}
