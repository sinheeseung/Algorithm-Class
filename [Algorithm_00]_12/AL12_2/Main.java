
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
			// start�� ���� ��� ������ �Ÿ��� ���Ѵ�� �ʱ�ȭ
			dist[i] = Integer.MAX_VALUE;
			pre[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		pre[start] = 0;
		for (int i = 1; i < V; i++) {
			// v-1�� �ݺ��Ͽ� �� ���������� �ִܰŸ��� ���
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < pre[v]) {
					// pre[v] > dist[u] + ���� uv�� ����ġ ��� �� ª�� ��ΰ� �ִٴ� ���̹Ƿ�
					// pre[v]�� dist[u]+ ���� uv�� ����ġ�� �����Ѵ�.
					pre[v] = dist[u] + weight;
				}
			}
			for (int j = 0; j < V; j++) {
				// �ִܰŸ��� ���ϴ� �������� dist�� ���� �ٷ� �ٲٰ� �Ǹ�
				// �ٲ� dist�� ���� ���� i�϶� ��� �� �� �ֱ� ������
				// pre��� dist�� ���� ���� ������ �迭�� ����� pre�� ������
				dist[j] = pre[j];
			}
			System.out.println(i + "���� �������� �� �� �ִ� �ִܰŸ�");
			for (int j = 0; j < V; j++) {
			// �� �������� �ʿ��� �ִܰŸ� ���
			System.out.println("���� :" + j + "    �Ÿ� : " + dist[j]);
		}
		System.out.println();

		}
		distance = dist[end];
		//end �������� �ʿ��� �ִܰŸ� ����
		for (int j = 0; j < E; ++j) {
			// v-1���� �ݺ��� ���� �ִ� �Ÿ��� ���� ��������
			// ��� ������ ���Ͽ� �ִܰŸ��� ���ϴ� �۾��� �� �� �� �ݺ� �Ͽ��� ��
			// �� ������ ���� �ִ� ��ΰ� �ٲ���ٸ� �� �������� ���� ����Ŭ�� �ִ°��̴�.
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < pre[v]) 
				pre[v] = dist[u] + weight;
			for (int k = 0; k < V; k++) 
				dist[k] = pre[k];
		}
		System.out.println(V + "���� �������� �� �� �ִ� �ִܰŸ�");
		for (int j = 0; j < V; j++) 
		// �� �������� �ʿ��� �ִܰŸ� ���
		System.out.println("���� :" + j + "    �Ÿ� : " + dist[j]);
		System.out.println();
		if (distance == dist[end])
			//�ִܰŸ��� ���� �ٲ��� ���� ��� 
			System.out.println(distance);
		else
			//���� ����Ŭ�� ���ԵǾ� �ִܰŸ��� ���� �ٲ� ��� 
			System.out.println("���� '"+end + "' ������ �ִܰŸ��� ��������Ŭ�� ���ԵǾ� �ֽ��ϴ�.");

	}

	public static void main(String[] args) {
		Graph graph = null;
		int M = 0, N = 0, start=0,end=0;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data12_bellman.txt");
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