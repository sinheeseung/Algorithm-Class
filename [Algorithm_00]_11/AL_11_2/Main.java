import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int V = 0, min = 0;
	static Graph graph;
	static boolean[] visited;
	static ArrayList<Edge> mst;
	private static BufferedReader bufReader;

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
			visited = new boolean[V + 1];
			mst = new ArrayList<>();
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

			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		Prim();
		for (Edge edge : mst) {
			System.out.printf("w<%c,%c> = %d\n", edge.from + 96, edge.to + 96, edge.cost);
		}
		System.out.println();
		System.out.println("w<MST> = "+ min);
	}

	public static void Prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		// ����ġ�� ���� ����� ������ ������ �켱����
		Queue<Integer> queue = new LinkedList<>(); 
		// �����湮 ������ ó���� ���� ť
		queue.add(1); // ���� 1���� ����
		while (!queue.isEmpty()) {
			int from = queue.poll();
			visited[from] = true;

			for (Edge edge : graph.edge[from]) { // ���� ���� from�� ����� ���� ��
				if (!visited[edge.to]) { // ���� ���� to�� �湮���� �ʾҴٸ�
					pq.add(edge); // �켱���� ť�� ������ �߰��Ѵ�.
				}
			}

			while (!pq.isEmpty()) {
				Edge edge = pq.poll(); // ����ġ�� ���� ���� ������ ���� ���̸�,
				if (!visited[edge.to]) { // ������ ����� ���� to�� �湮�� ���� ���ٸ�,
					queue.add(edge.to); // ť�� �����Ͽ� ������ �湮�Ѵ�.
					visited[edge.to] = true; // �湮�ߴ� ������ �ٽ� �湮���� �ʵ��� �湮ǥ��.
					mst.add(edge); // �ּ� ���д� Ʈ���� �����ϴ� ���� �߰�.
					min += edge.cost; // �� �ּ� ����ġ ���� ���ϱ� ���� ����.
					break;
				}
			}
		}
	}
}

class Graph {
	List<Edge>[] edge;

	public Graph(int V) {
		edge = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++)
			edge[i] = new ArrayList<>();
	}

	// ����� �׷���
	public void AddEdge(int from, int to, int cost) {
		edge[from].add(new Edge(from, to, cost));
		edge[to].add(new Edge(to, from, cost));
	}
}

class Edge implements Comparable<Edge> {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override // �켱���� ť�� ����ϱ� ���� �Լ� �������̵�
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}