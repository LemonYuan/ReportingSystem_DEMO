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
	
	public JsonObject customizedQuery(LinkedHashMap map){
		int isSQL=(int) map.get("isSQL");
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.boxPlotQuery(map);
		}
		else{
			result=assembleMapper.superQuery(map.get("sql").toString());
		}
		JsonObject jo=new JsonObject();
		JsonArray data1 = new JsonArray();
		JsonArray data0 = new JsonArray();
		JsonArray data2=new JsonArray();
		int index =0;
		LinkedHashMap temp_map = new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			temp_map= result.get(i);
	        Iterator iter = temp_map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();
				System.out.println(entry.getKey() + "  " + entry.getValue());
				if (index == 0) {
					JsonPrimitive tem_js = new JsonPrimitive(entry.getValue().toString());
					data1.add(tem_js);
					index = 1;
				} else {
					JsonPrimitive tem_js = new JsonPrimitive(entry.getValue().toString());
					data2.add(tem_js);
					index = 0;
				}
			}
		}
		data0.add(data1);
		data0.add(data2);
		jo.add("data", data0);
		System.out.println(jo.toString());
		return jo;
	}
	
	public JsonObject customizedQuery2(LinkedHashMap map,int isSQL){
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.boxPlotQuery2(map);
			System.out.println(map.get("columns").toString());
		}
		else{
			result=assembleMapper.superQuery(map.get("sql").toString());
		}
		JsonObject jo=new JsonObject();
		JsonArray ja=new JsonArray();
		List list=(List) map.get("columns");
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
		System.out.println(jo.toString());
		return jo;
	}
}
