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
public class BarChartService {
	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject customizedQuery(LinkedHashMap map){
		int isSQL=(int) map.get("isSQL");
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.customizedQuery(map);
		}
		else{
			result=assembleMapper.superQuery(map.get("sql").toString());
		}
		JsonObject jo = new JsonObject();
		JsonArray categories = new JsonArray();
		JsonArray data = new JsonArray();
		LinkedHashMap temp_map = new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			temp_map= result.get(i);
			Iterator iter = temp_map.entrySet().iterator();
			int index = 0;
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();
				System.out.println(entry.getKey() + "  " + entry.getValue());
				if (index == 0) {
					JsonPrimitive tem_js = new JsonPrimitive(entry.getValue().toString());
					categories.add(tem_js);
					index = 1;
				} else {
					JsonPrimitive tem_js = new JsonPrimitive(entry.getValue().toString());
					data.add(tem_js);
					index = 0;
				}
			}
		}
		jo.add("categories", categories);
		jo.add("data", data);
		System.out.println(jo.toString());
		return jo;
	}
}
