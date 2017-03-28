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
public class PieChartService {

	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject customizedQuery(LinkedHashMap map){
		int isSQL=(int) map.get("isSQL");
		List<LinkedHashMap> result;
		if(isSQL==0){
			result=assembleMapper.customizedQuery(map);
		}
		else{
			String sql=(String) map.get("sql");
			result=assembleMapper.superQuery(sql);
		}
		JsonObject jo=new JsonObject();
		JsonArray title=new JsonArray();
		JsonArray ja=new JsonArray();
		for(int i=0;i<result.size();i++){
			int index=0;
			LinkedHashMap hm=result.get(i);
			JsonObject tem_jo=new JsonObject();
			Iterator iter=hm.entrySet().iterator();
			while(iter.hasNext()){
				Entry entry = (Entry) iter.next();
				if(index==0){
					tem_jo.addProperty("name", entry.getValue().toString());
					title.add(new JsonPrimitive( entry.getValue().toString()));;
					index=1;
				}
				else{
					System.out.println( entry.getKey()+"  "+entry.getValue());
					tem_jo.addProperty("value", entry.getValue().toString());
					index=0;
				}
			}
			ja.add(tem_jo);
		}
		jo.add("data", ja);
		jo.add("title", title);
		System.out.println(jo.toString());
		return jo;
	}
	
}
