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
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data11_mst.txt");
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
		// 가중치가 낮은 순대로 간선을 정렬할 우선순위
		Queue<Integer> queue = new LinkedList<>(); 
		// 정점방문 스케줄 처리를 위한 큐
		queue.add(1); // 정점 1에서 시작
		while (!queue.isEmpty()) {
			int from = queue.poll();
			visited[from] = true;

			for (Edge edge : graph.edge[from]) { // 현재 정점 from과 연결된 간선 중
				if (!visited[edge.to]) { // 아직 정점 to를 방문하지 않았다면
					pq.add(edge); // 우선순위 큐에 간선을 추가한다.
				}
			}

			while (!pq.isEmpty()) {
				Edge edge = pq.poll(); // 가중치가 가장 적은 간선이 나올 것이며,
				if (!visited[edge.to]) { // 간선이 연결된 정점 to를 방문한 적이 없다면,
					queue.add(edge.to); // 큐에 삽입하여 다음에 방문한다.
					visited[edge.to] = true; // 방문했던 정점을 다시 방문하지 않도록 방문표시.
					mst.add(edge); // 최소 스패닝 트리를 구성하는 간선 추가.
					min += edge.cost; // 총 최소 가중치 합을 구하기 위해 덧셈.
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

	// 양방향 그래프
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

	@Override // 우선순위 큐를 사용하기 위한 함수 오버라이딩
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}