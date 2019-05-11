package fire;

import java.util.ArrayList;

public class FireAlgorithm {

	public static int fire(MyVector[] tree) {
		int n = tree.length;
		MyVector leaves = new MyVector(n);
		int radius = 0, diameter = 0;
		int[] degrees = new int[n];
		// initialization, n - number of vertices
		for (int i = 0; i < n; i++) {
			degrees[i] = tree[i].size();
			if (degrees[i] == 1) {
				leaves.add(i);
			}
		}
		// calculations: find Centers, Radius and Diameter
		int vertex = 0, leaf = 0;
		while (n > 2) {
			MyVector temp = new MyVector(n);
			for (int i = 0; i < leaves.size(); i++) {
				leaf = leaves.get(i);
				degrees[leaf] = 0;
				for (int j = 0; j < tree[leaf].size(); j++) {
					vertex = tree[leaf].get(j);
					if (degrees[vertex] > 0) {
						degrees[vertex]--;
						if (degrees[vertex] == 1) {
							temp.add(vertex);
						}
					}
				}
				n--;
			}
			leaves = temp;
			radius++;
		}
		if (leaves.size() == 2) {
			radius++;
			diameter = radius * 2 - 1;
		} else
			diameter = radius * 2;
		System.out.println("radius = " + radius + ", diameter = " + diameter + ", centers: " + leaves.toString());
		return diameter;
	}
	
	public static void main(String[] args) {
		fire(InitTrees.initTree1());
		fire(InitTrees.initTree2());
		fire(InitTrees.initTree3());
		fire(InitTrees.initTree4());
		fire(InitTrees.initTree5());
	}

}
