package scau.services;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scau.mappers.AssembleMapper;
import scau.mappers.ColumnNameMapper;
import scau.mappers.TableNameMapper;

@Service
public class DataPreprocessService {
	@Autowired
	AssembleMapper assembleMapper;
	
	@Autowired
	ColumnNameMapper columnNameMapper;
	
	@Autowired
	TableNameMapper tableNameMapper;
    
	public int updateAll(LinkedHashMap map) {
		int i=assembleMapper.updateAll(map);
		System.out.println("更新了："+String.valueOf(i)+"行");
		return i;
	}
	
	public int deleteAll(LinkedHashMap map) {
		int i=assembleMapper.deleteAll(map);
		System.out.println("删除了："+String.valueOf(i)+"行");
		return i;
	}
	
	public int alterTableName(LinkedHashMap map) {
		int i=tableNameMapper.alterTableName(map);
		System.out.println("------------"+"更新状态："+String.valueOf(i)+"------------");
		return i;
	}
	
	public int alterColumnName(LinkedHashMap map) {
		int i=columnNameMapper.alterColumnName(map);
		System.out.println("------------"+"更新状态："+String.valueOf(i)+"------------");
		return i;
	}
}
