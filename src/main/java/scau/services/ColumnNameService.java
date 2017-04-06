package scau.services;

import java.util.HashMap;
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
import scau.mappers.ColumnNameMapper;

@Service
public class ColumnNameService {

	@Autowired
	ColumnNameMapper columnNameMapper;
	
	@Autowired
	AssembleMapper assembleMapper;
	
	public JsonObject getColumnName(HashMap map) {
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		List<LinkedHashMap> result = columnNameMapper.getColumnName(map);
		LinkedHashMap tem_map = new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			tem_map = result.get(i);
			Iterator it = tem_map.entrySet().iterator();
			while (it.hasNext()) {
				Entry en = (Entry) it.next();
				ja.add(new JsonPrimitive(en.getValue().toString()));
			}
		}
		jo.add("columnName", ja);
		System.out.println(jo.toString());
		return jo;
	}
	
	public JsonObject getColumnParam(LinkedHashMap map) {
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		List<LinkedHashMap> result = assembleMapper.singleParamGroupBy(map);
		LinkedHashMap tem_map = new LinkedHashMap();
		for (int i = 0; i < result.size(); i++) {
			tem_map = result.get(i);
			Iterator it = tem_map.entrySet().iterator();
			while (it.hasNext()) {
				Entry en = (Entry) it.next();
				ja.add(new JsonPrimitive(en.getValue().toString()));
			}
		}
		jo.add("column_param", ja);
		System.out.println("column_param"+jo.toString());
		return jo;
	}
}
