package scau.tools.RounhSets;

import scau.tools.RounhSets.RoughSetsTool;

public class Client {
	public static void main(String[] args){
		String filePath = "C:\\Users\\asus\\Desktop\\input.txt";
		
		RoughSetsTool tool = new RoughSetsTool(filePath);
		tool.findingReduct();
	}
}
