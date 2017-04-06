package scau.mappers;

import java.util.LinkedHashMap;
import java.util.List;

public interface AssembleMapper {
	public List<LinkedHashMap> multiParamQuery(LinkedHashMap map);
     public List<LinkedHashMap> doubleParamGroupBy(LinkedHashMap map);
     public List<LinkedHashMap> doubleParamQuery(LinkedHashMap map);
     public List<LinkedHashMap> singleParamGroupBy(LinkedHashMap map);
     public List<LinkedHashMap> superQuery(String sql);
     public int updateAll(LinkedHashMap map);
     public int deleteAll(LinkedHashMap map);
}
