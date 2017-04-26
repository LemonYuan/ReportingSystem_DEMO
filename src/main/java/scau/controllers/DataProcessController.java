package scau.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scau.services.DataPreprocessService;
@Controller
public class DataProcessController {
     @Autowired
     DataPreprocessService dataPreprocessService;	
     
     @RequestMapping("/alterTableName")
     public @ResponseBody int alterTableName(@RequestParam(value = "originalName") String originalName,@RequestParam(value = "newName") String newName){
    	 LinkedHashMap map=new LinkedHashMap();
    	 map.put("originalName", originalName);
    	 map.put("newName", newName);
    	 return dataPreprocessService.alterTableName(map);
     }
     @RequestMapping("/alterColumnName")
     public @ResponseBody int alterColumnName(@RequestParam(value = "tableName") String tableName,@RequestParam(value = "originalName") String originalName,@RequestParam(value = "newName") String newName){
    	 LinkedHashMap map=new LinkedHashMap();
    	 map.put("originalName", originalName);
    	 map.put("newName", newName);
    	 map.put("tableName", tableName);
    	 return dataPreprocessService.alterColumnName(map);
     }
}
