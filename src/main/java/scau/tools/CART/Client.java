package scau.tools.CART;

public class Client {
	public static void main(String[] args){
		String filePath = "src\\main\\resources\\input.txt";
		
		CARTTool tool = new CARTTool(filePath);
		
		tool.startBuildingTree();
	}
}