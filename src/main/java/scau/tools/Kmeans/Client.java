package scau.tools.Kmeans;


/**
 * K-means（K均值）算法调用类
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "src\\main\\resources\\input.txt";
		//聚类中心数量设定
		int classNum = 5;
		
		KMeansTool tool = new KMeansTool(filePath, classNum);
		tool.kMeansClustering();
	}
}