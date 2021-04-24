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
    	//u가 속한 트리와 v가 속한 트리를 합치는 함수
        int uR = Find(u);
        int vR = Find(v);
 
        if (uR == vR)
            return;
 
        if (graph.subset[uR].rank > graph.subset[vR].rank)
        	//항상 높이가 더 낮은 트리를 더 높은 트리밑에 집어넣음으로써 트리의 높이가 높아지는 상황을 방지하는 것이다
            Swap(uR, vR);
 
        graph.subset[uR].parent = vR;
 
        if (graph.subset[uR].rank == graph.subset[vR].rank)
            graph.subset[vR].rank++;
    }
 
    public static int Find(int u) {
    	//u가 속한 트리의 루트노드의 번호를 반환하는 함수
        if (graph.subset[u].parent == u)
            return u;
        else
            return graph.subset[u].parent = Find(graph.subset[u].parent);
    }
 
    public static void Kruskal() {
    
        Collections.sort(graph.edgelist);
        //간선을 오름차순으로 정렬
        Edge[] result = new Edge[graph.N];
        int mst = 0, idx = 1;
    
        for (int i = 0; i < graph.edgelist.size(); i++) {
            Edge edge = graph.edgelist.get(i);
            if (Find(edge.x) != Find(edge.y)) {
            	// 간선의 양 끝 정점의 루트노드가 다른경우
                Union(edge.x, edge.y);
                // 두 정점은 다른 부분집합 트리에 있으므로 합친다
                mst += edge.cost;
                //간선의 가중치를 더해준다.
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
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data11_mst.txt");
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
				//문자열 입력을 아스키 코드로 변환하여 숫자로 저장

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
    int N;                 // 정점의 갯수
    List<Edge> edgelist; // 간선 리스트 
    Subset[] subset;     // 각 정점이 속한 트리 부분집합 
 
    public Graph(int size) {
        this.N = size + 1;
        edgelist = new ArrayList<>();
        subset = new Subset[this.N];
 
        for (int i = 1; i < this.N; i++)
            subset[i] = new Subset(i, 0); // 초기에는 부분집합에 본인 정점만
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
 
    @Override // 가중치를 기준으로 오름차순 정렬을 위한 함수 오버라이딩
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
 
}
 
class Subset {
    int parent, rank;
 
    public Subset(int parent, int rank) {
        this.parent = parent; // 루트노드
        this.rank = rank;      // 트리높이
    }
}