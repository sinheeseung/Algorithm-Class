
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

	public static void BellmanFord(Graph graph, int start, int end) {
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];
		int pre[] = new int[V];
		int distance = 0;

		for (int i = 0; i < V; ++i) {
			// start로 부터 모든 정점의 거리를 무한대로 초기화
			dist[i] = Integer.MAX_VALUE;
			pre[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		pre[start] = 0;
		for (int i = 1; i < V; i++) {
			// v-1번 반복하여 각 정점까지의 최단거리를 계산
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < pre[v]) {
					// pre[v] > dist[u] + 간선 uv의 가중치 라면 더 짧은 경로가 있다는 뜻이므로
					// pre[v]를 dist[u]+ 간선 uv의 가중치로 갱신한다.
					pre[v] = dist[u] + weight;
				}
			}
			for (int j = 0; j < V; j++) {
				// 최단거리를 구하는 과정에서 dist의 값을 바로 바꾸게 되면
				// 바뀐 dist의 값이 같은 i일때 사용 될 수 있기 때문에
				// pre라는 dist와 같은 값을 가지는 배열을 만들어 pre에 저장함
				dist[j] = pre[j];
			}
			System.out.println(i + "개의 간선으로 갈 수 있는 최단거리");
			for (int j = 0; j < V; j++) {
			// 각 정점까지 필요한 최단거리 출력
			System.out.println("정점 :" + j + "    거리 : " + dist[j]);
		}
		System.out.println();

		}
		distance = dist[end];
		//end 정점까지 필요한 최단거리 저장
		for (int j = 0; j < E; ++j) {
			// v-1번의 반복을 통해 최단 거리를 구한 시점에서
			// 모든 간선에 대하여 최단거리를 구하는 작업을 한 번 더 반복 하였을 때
			// 각 정점에 대한 최단 경로가 바뀌었다면 그 정점에는 음수 싸이클이 있는것이다.
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < pre[v]) 
				pre[v] = dist[u] + weight;
			for (int k = 0; k < V; k++) 
				dist[k] = pre[k];
		}
		System.out.println(V + "개의 간선으로 갈 수 있는 최단거리");
		for (int j = 0; j < V; j++) 
		// 각 정점까지 필요한 최단거리 출력
		System.out.println("정점 :" + j + "    거리 : " + dist[j]);
		System.out.println();
		if (distance == dist[end])
			//최단거리의 값이 바뀌지 않은 경우 
			System.out.println(distance);
		else
			//음수 사이클이 포함되어 최단거리의 값이 바뀐 경우 
			System.out.println("정정 '"+end + "' 까지의 최단거리에 음수싸이클이 포함되어 있습니다.");

	}

	public static void main(String[] args) {
		Graph graph = null;
		int M = 0, N = 0, start=0,end=0;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data12_bellman.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			StringTokenizer parser = new StringTokenizer(line, " ");
			N = Integer.parseInt(parser.nextToken());
			line = bufReader.readLine();
			parser = new StringTokenizer(line, " ");
			start = Integer.parseInt(parser.nextToken());
			end = Integer.parseInt(parser.nextToken());
			line = bufReader.readLine();
			parser = new StringTokenizer(line, " ");
			M = Integer.parseInt(parser.nextToken());

			graph = new Graph(N, M);

			for (int i = 0; i < M; i++) {
				parser = new StringTokenizer(bufReader.readLine());
				int src = Integer.parseInt(parser.nextToken());
				int dest = Integer.parseInt(parser.nextToken());
				int weight = Integer.parseInt(parser.nextToken());

				graph.edge[i].src = src;
				graph.edge[i].dest = dest;
				graph.edge[i].weight = weight;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		BellmanFord(graph, start,end);
	}

}

class Graph {
	class Edge {
		int src, dest, weight;

		Edge() {
			src = dest = weight = 0;
		}
	};

	int V, E;
	Edge edge[];

	Graph(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}
}