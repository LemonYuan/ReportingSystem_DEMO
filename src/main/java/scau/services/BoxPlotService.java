package scau.services;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import scau.mappers.AssembleMapper;

@Service
public class BoxPlotService {
	@Autowired
	AssembleMapper assembleMapper;
	
	
	public JsonObject customizedQuery2(LinkedHashMap map,int isSQL){
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.multiParamQuery(map);
			System.out.println(map.get("columns").toString());
		}
		else{
			result=assembleMapper.superQuery(map.get("sql").toString());
		}
		JsonObject jo=new JsonObject();
		JsonArray ja=new JsonArray();
		JsonArray ja_name=new JsonArray();
		List list=(List) map.get("columns");
		for (int i = 0; i < list.size(); i++) {
			ja_name.add(new JsonPrimitive(list.get(i).toString()));			
		}
		for (int i = 0; i < list.size(); i++) {
			JsonArray tem_array=new JsonArray();
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int j=0;j<result.size();j++){
				temp_map=result.get(j);
				tem_array.add(new JsonPrimitive(temp_map.get(list.get(i)).toString()));
			}
			ja.add(tem_array);
		}
		jo.add("data", ja);
		jo.add("name", ja_name);
		System.out.println(jo.toString());
		return jo;
	}
}
