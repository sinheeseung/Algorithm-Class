import java.util.*;
import java.io.*;
 
public class Main {
    static Graph graph;
    static int V = 0;
	private static BufferedReader bufReader;

    public static void Swap(int n1, int n2) {
        int temp = n1;
        n1 = n2;
        n2 = temp;
    }
 
    public static void Union(int u, int v) {
    	//u�� ���� Ʈ���� v�� ���� Ʈ���� ��ġ�� �Լ�
        int uR = Find(u);
        int vR = Find(v);
 
        if (uR == vR)
            return;
 
        if (graph.subset[uR].rank > graph.subset[vR].rank)
        	//�׻� ���̰� �� ���� Ʈ���� �� ���� Ʈ���ؿ� ����������ν� Ʈ���� ���̰� �������� ��Ȳ�� �����ϴ� ���̴�
            Swap(uR, vR);
 
        graph.subset[uR].parent = vR;
 
        if (graph.subset[uR].rank == graph.subset[vR].rank)
            graph.subset[vR].rank++;
    }
 
    public static int Find(int u) {
    	//u�� ���� Ʈ���� ��Ʈ����� ��ȣ�� ��ȯ�ϴ� �Լ�
        if (graph.subset[u].parent == u)
            return u;
        else
            return graph.subset[u].parent = Find(graph.subset[u].parent);
    }
 
    public static void Kruskal() {
    
        Collections.sort(graph.edgelist);
        //������ ������������ ����
        Edge[] result = new Edge[graph.N];
        int mst = 0, idx = 1;
    
        for (int i = 0; i < graph.edgelist.size(); i++) {
            Edge edge = graph.edgelist.get(i);
            if (Find(edge.x) != Find(edge.y)) {
            	// ������ �� �� ������ ��Ʈ��尡 �ٸ����
                Union(edge.x, edge.y);
                // �� ������ �ٸ� �κ����� Ʈ���� �����Ƿ� ��ģ��
                mst += edge.cost;
                //������ ����ġ�� �����ش�.
                result[idx++] = edge;
            }
        }
    
        for (int i = 1; i < idx; i++)
			System.out.printf("w<%c,%c> = %d\n", result[i].x + 96, result[i].y + 96, result[i].cost);

		System.out.println();
		System.out.println("w<MST> = "+ mst);
    }
 
    public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data11_mst.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			StringTokenizer parser = new StringTokenizer(line, ",");
			while (parser.hasMoreTokens()) {
				String word = parser.nextToken();
				V++;
			}

			graph = new Graph(V);
			while ((line = bufReader.readLine()) != null) {
				parser = new StringTokenizer(line, ",");
				String word = parser.nextToken();
				int from = word.charAt(0);
				word = parser.nextToken();
				int to = word.charAt(0);
				word = parser.nextToken();
				int cost = Integer.parseInt(word);
				graph.AddEdge(from-96, to-96, cost);
				//���ڿ� �Է��� �ƽ�Ű �ڵ�� ��ȯ�Ͽ� ���ڷ� ����

			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
        Kruskal();
    }
 
}
 
class Graph {
    int N;                 // ������ ����
    List<Edge> edgelist; // ���� ����Ʈ 
    Subset[] subset;     // �� ������ ���� Ʈ�� �κ����� 
 
    public Graph(int size) {
        this.N = size + 1;
        edgelist = new ArrayList<>();
        subset = new Subset[this.N];
 
        for (int i = 1; i < this.N; i++)
            subset[i] = new Subset(i, 0); // �ʱ⿡�� �κ����տ� ���� ������
    }
 
    public void AddEdge(int x, int y, int cost) {
        edgelist.add(new Edge(x, y, cost));
    }
}
 
class Edge implements Comparable<Edge> {
    int x, y, cost;
 
    public Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
 
    @Override // ����ġ�� �������� �������� ������ ���� �Լ� �������̵�
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
 
}
 
class Subset {
    int parent, rank;
 
    public Subset(int parent, int rank) {
        this.parent = parent; // ��Ʈ���
        this.rank = rank;      // Ʈ������
    }
}