package scau.services;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import scau.mappers.AssembleMapper;

@Service
public class LineGraphService {
	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject customizedQuery(LinkedHashMap map){
		List<LinkedHashMap> result=assembleMapper.lineGraphQuery(map);
		JsonObject jo=new JsonObject();
		LinkedHashMap tem_map=new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			tem_map=result.get(i);
			Iterator it=tem_map.entrySet().iterator();
			while(it.hasNext()){
				Entry entry=(Entry) it.next();
				String[] year=entry.getKey().toString().split("-");
			}
		}
		return null;
	}
}
