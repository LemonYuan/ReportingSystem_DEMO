package scau.services;

import java.util.LinkedHashMap;

import com.google.gson.JsonObject;

public interface ServiceInterface {
	public JsonObject customizedQuery(LinkedHashMap map);
}
