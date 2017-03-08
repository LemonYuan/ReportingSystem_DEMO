package scau.services;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import scau.mappers.TableNameMapper;

@Controller
public class TableNameService {
	@Autowired
	TableNameMapper tableNameMapper;
	
	public JsonObject getTableName(){
		JsonObject jo=new JsonObject();
		JsonArray ja=new JsonArray();
		List<LinkedHashMap> result=tableNameMapper.getTableName();
		LinkedHashMap tem_map=new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			tem_map=result.get(i);
			Iterator it=tem_map.entrySet().iterator();
			while(it.hasNext()){
				Entry en=(Entry)it.next();
				ja.add(new JsonPrimitive(en.getValue().toString()));
			}
		}
		jo.add("tableName", ja);
		System.out.println(jo.toString());
		return jo;
	}
}
