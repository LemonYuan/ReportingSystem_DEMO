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
public class ScatterChartService {

	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject customizedQuery(LinkedHashMap map){
		List<LinkedHashMap> result=assembleMapper.boxPlotQuery(map);
		JsonObject jo=new JsonObject();
		JsonArray ja=new JsonArray();
		for(int i=0;i<result.size();i++){
			LinkedHashMap hm=result.get(i);
			JsonArray tem_ja=new JsonArray();
			Iterator iter=hm.entrySet().iterator();
			while(iter.hasNext()){
				Entry entry = (Entry) iter.next();
				tem_ja.add(new JsonPrimitive(entry.getValue().toString()));
			}
			ja.add(tem_ja);
		}
		jo.add("data", ja);
		System.out.println(jo.toString());
		return jo;
	}
	
}
