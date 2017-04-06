package scau.services;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import scau.mappers.AssembleMapper;
@Service
public class GeoGraphService {
	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject customizedQuery(LinkedHashMap map){
		int isSQL=(int) map.get("isSQL");
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.doubleParamGroupBy(map);
		}
		else{
			String sql=(String) map.get("sql");
			result=assembleMapper.superQuery(sql);
		}
		
		JsonArray data = new JsonArray();
		LinkedHashMap temp_map = new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			temp_map= result.get(i);
			Iterator iter = temp_map.entrySet().iterator();
			JsonObject tem_js = new JsonObject();
			int index=0;
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();
				if(index==0){
					  System.out.println(entry.getKey() + "  " + entry.getValue());
					  tem_js.addProperty("name", entry.getValue().toString());
					  index=1;
				}
				else{
					  System.out.println(entry.getKey() + "  " + entry.getValue());
					  tem_js.addProperty("value", entry.getValue().toString());
					  index=0;
				}
			}
			 data.add(tem_js);
		}
		JsonObject jo=new JsonObject();
		jo.add("data", data);
		System.out.println(jo.toString());
		return jo;
	}
}
