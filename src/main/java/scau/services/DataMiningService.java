package scau.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scau.mappers.AssembleMapper;
import scau.tools.CART.CARTTool;
import scau.tools.CBA.CBATool;
import scau.tools.EM.EMTool;
import scau.tools.ID3.ID3Tool;
import scau.tools.Kmeans.KMeansTool;

@Service
public class DataMiningService {
	@Autowired
	AssembleMapper assembleMapper;
    
	public String Kmeans(LinkedHashMap map,String url) {
		List<LinkedHashMap> result = assembleMapper.multiParamQuery(map);
		List list = (List) map.get("columns");
		File inputfile = new File(url+"/input.txt");
		File outputfile = new File(url+"/output.txt");
		recreateFile(inputfile);
		recreateFile(outputfile);
		PrintStream ps = System.out; 
		System.out.println(url);
		setSystemOutStream(outputfile);
		FileWriter fw;
		try {
			fw = new FileWriter(inputfile);
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int i = 0; i < result.size(); i++) {
				temp_map = result.get(i);
				Iterator iter = temp_map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry entry = (Entry) iter.next();
					try {
						fw.write(entry.getValue().toString()+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					fw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw.close();
			String filePath = url+"/input.txt";
			int classNum = 5;
			KMeansTool tool = new KMeansTool(filePath, classNum);
			tool.kMeansClustering();
			closeSystemOutStream(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String ID3(LinkedHashMap map,String url){
		List<LinkedHashMap> result = assembleMapper.multiParamQuery(map);
		List list = (List) map.get("columns");
		File inputfile = new File(url+"/input.txt");
		File outputfile = new File(url+"/output.txt");
		recreateFile(inputfile);
		recreateFile(outputfile);
		PrintStream ps = System.out; 
		System.out.println(url);
		setSystemOutStream(outputfile);
		try {
			
			FileWriter fw=new FileWriter(inputfile);
			fw.write("index ");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i).toString()+" ");
			}
			fw.write("\r\n");
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int i = 0; i < result.size(); i++) {
				fw.write(String.valueOf(i+1)+" ");
				temp_map = result.get(i);
				Iterator iter = temp_map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry entry = (Entry) iter.next();
					try {
						fw.write(entry.getValue().toString()+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					fw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw.close();
			String filePath = url+"/input.txt";
			ID3Tool tool = new ID3Tool(filePath);
			tool.startBuildingTree(true);
			closeSystemOutStream(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String CART(LinkedHashMap map,String url){
		List<LinkedHashMap> result = assembleMapper.multiParamQuery(map);
		List list = (List) map.get("columns");
		File inputfile = new File(url+"/input.txt");
		File outputfile = new File(url+"/output.txt");
		PrintStream ps = System.out; 
		recreateFile(inputfile);
		recreateFile(outputfile);
		System.out.println(url);
		setSystemOutStream(outputfile);
		try {
			FileWriter fw=new FileWriter(inputfile);
			fw.write("index ");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i).toString()+" ");
			}
			fw.write("\r\n");
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int i = 0; i < result.size(); i++) {
				fw.write(String.valueOf(i+1)+" ");
				temp_map = result.get(i);
				Iterator iter = temp_map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry entry = (Entry) iter.next();
					try {
						fw.write(entry.getValue().toString()+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					fw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw.close();
			String filePath = url+"/input.txt";
			CARTTool tool = new CARTTool(filePath);
			tool.startBuildingTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeSystemOutStream(ps);
		return null;
	}
	public String CBA(LinkedHashMap map,String url){
		List<LinkedHashMap> result = assembleMapper.multiParamQuery(map);
		List list = (List) map.get("columns");
		String detected=(String) map.get("detected");
		File inputfile = new File(url+"/input.txt");
		File outputfile = new File(url+"/output.txt");
		PrintStream ps = System.out; 
		recreateFile(inputfile);
		recreateFile(outputfile);
		System.out.println(url);
		setSystemOutStream(outputfile);
		try {
			FileWriter fw=new FileWriter(inputfile);
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i).toString()+" ");
			}
			fw.write("\r\n");
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int i = 0; i < result.size(); i++) {
				temp_map = result.get(i);
				Iterator iter = temp_map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry entry = (Entry) iter.next();
					try {
						fw.write(entry.getValue().toString()+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					fw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw.close();
			String filePath = url+"/input.txt";
			String classification = null;
			//最小支持度阈值率
			double minSupportRate = 0.2;
			//最小置信度阈值
			double minConf = 0.7;
			CBATool tool = new CBATool(filePath, minSupportRate, minConf);
			classification = tool.CBAJudge(detected);
			System.out.println(MessageFormat.format("{0}的关联分类结果为{1}", detected, classification));
			closeSystemOutStream(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String EM(LinkedHashMap map,String url) {
		List<LinkedHashMap> result = assembleMapper.multiParamQuery(map);
		System.out.println(map.get("columns").toString());
		List list = (List) map.get("columns");
		File inputfile = new File(url+"/input.txt");
		File outputfile = new File(url+"/output.txt");
		PrintStream ps = System.out; 
		recreateFile(inputfile);
		recreateFile(outputfile);
		System.out.println(url);
		setSystemOutStream(outputfile);
		FileWriter fw;
		try {
			fw = new FileWriter(inputfile);
			LinkedHashMap temp_map = new LinkedHashMap();
			for (int i = 0; i < result.size(); i++) {
				temp_map = result.get(i);
				Iterator iter = temp_map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry entry = (Entry) iter.next();
					try {
						fw.write(entry.getValue().toString()+" ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					fw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fw.close();
			String filePath = url+"/input.txt";
			//聚类中心数量设定
			int classNum = 5;
			EMTool tool = new EMTool(filePath);
			tool.readDataFile();
			tool.exceptMaxStep();
			closeSystemOutStream(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
      public void setSystemOutStream(File outputfile){
    	  try { 
    	        PrintStream ps = new PrintStream(outputfile,"utf-8"); 
    	        System.setOut(ps); 
    	        } catch (IOException e) { 
    	            e.printStackTrace(); 
    	        } 
      }
      public void closeSystemOutStream( PrintStream ps){
    	  try { 
  	        System.setOut(ps); 
  	        } catch (Exception e) { 
  	            e.printStackTrace(); 
  	        } 
      }
      public void recreateFile(File file){
    		try {
    			if (!file.exists()) {
    				file.createNewFile();
    			}
    			else{
    				file.delete();
    				file.createNewFile();
    				}
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
      }
}
