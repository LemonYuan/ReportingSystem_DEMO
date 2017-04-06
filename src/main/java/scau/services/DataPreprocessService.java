package scau.services;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scau.mappers.AssembleMapper;

@Service
public class DataPreprocessService {
	@Autowired
	AssembleMapper assembleMapper;

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
}
