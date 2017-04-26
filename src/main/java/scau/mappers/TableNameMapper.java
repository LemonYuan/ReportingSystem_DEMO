package scau.mappers;

import java.util.LinkedHashMap;
import java.util.List;

public interface TableNameMapper {
   public List<LinkedHashMap> getTableName();
   public int alterTableName(LinkedHashMap map);
}
