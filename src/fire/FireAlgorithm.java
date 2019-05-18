package fire;


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
		int n = 7;
		MyVector[] tree = new MyVector[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new MyVector(n);
		}
		tree[0].add(1);
		tree[1].add(0);
		tree[1].add(2);
		tree[1].add(4);
		tree[2].add(1);
		tree[2].add(3);
		tree[3].add(2);
		tree[4].add(1);
		tree[4].add(5);
		tree[5].add(4);
		tree[5].add(6);
		tree[6].add(5);
		fire(tree);// radius = 3 , diameter = 5 , centers = (1,4)

		n = 5;
		MyVector[] tree1 = new MyVector[n];
		for (int i = 0; i < n; i++) {
			tree1[i] = new MyVector(n);
		}
		tree1[0].add(1);
		tree1[1].add(0);
		tree1[1].add(2);
		tree1[2].add(1);
		tree1[2].add(3);
		tree1[3].add(2);
		tree1[3].add(4);
		tree1[4].add(3);// 0->1->2->3->4
		fire(tree1);// radius = 2 , diameter = 4 , center = 2

	}

}
