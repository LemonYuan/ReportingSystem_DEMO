package scau.mappers;

import java.util.LinkedHashMap;
import java.util.List;

public interface AssembleMapper {
     public List<LinkedHashMap> customizedQuery(LinkedHashMap map);
     public List<LinkedHashMap> boxPlotQuery(LinkedHashMap map);
     public List<LinkedHashMap> boxPlotQuery2(LinkedHashMap map);
     public List<LinkedHashMap> lineGraphQuery(LinkedHashMap map);
     public List<LinkedHashMap> superQuery(String sql);
}
