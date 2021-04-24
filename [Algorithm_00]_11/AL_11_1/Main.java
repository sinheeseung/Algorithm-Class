import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bufReader;
	static List<Edge>[] adj = null;
	static int node_count = 0;
	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight + "";
		}

	}

	public static void main(String[] args) {

		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data11_dijkstra.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			StringTokenizer parser = new StringTokenizer(line, ",");
			while (parser.hasMoreTokens()) {
				String word = parser.nextToken();
				node_count++;
			}
			adj = new ArrayList[node_count];
			for (int i = 0; i < node_count; i++) {
				adj[i] = new ArrayList<>();
			}
			while ((line = bufReader.readLine()) != null) {
				parser = new StringTokenizer(line, ",");
				String temp_start = parser.nextToken();
				String temp_end = parser.nextToken();
				String temp_dist = parser.nextToken();
				adj[Integer.parseInt(temp_start) - 1]
						.add(new Edge(Integer.parseInt(temp_end) - 1, Integer.parseInt(temp_dist)));

			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		dijkstra();
	}
	public static void dijkstra(){
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge> S = new ArrayList<>();
		boolean[] check = new boolean[node_count];
		Edge[] D = new Edge[node_count];

		// 0번에서 시작
		for (int i = 0; i < node_count; i++) {
			// 모든 정점들을 큐에 넣는다
			if (i == 0) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
				//무한대로 초기화
			}
			pq.add(D[i]);
		}

		int j = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			System.out.println("------------------------------------------------");
			System.out.printf("S[%d] : d[%c] = %d\n", j++, edge.v + 65, edge.weight);
			System.out.println("------------------------------------------------");
			int[][] post_num = new int[pq.size()][2];
			int i = 0;
			for (Edge out : pq) {
				//이전 큐 값 저장
				for (int k = 0; k < 2; k++) {
					if (k == 0) {
						post_num[i][k] = out.v;
					} else {
						post_num[i][k] = out.weight;
					}
				}
				i++;
			}
			for (Edge next : adj[edge.v]) {
				if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
					// check되지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
					D[next.v].weight = D[edge.v].weight + next.weight;
					// decrease key
					// 재 정렬을 합니다.
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
				S.add(D[next.v]);
			}
			i = 0;
			for (Edge out : pq) {
				//큐에 있는 값을 순서대로 출력
				for (int k = 0; k < post_num.length; k++) {
					if (post_num[k][0] == out.v) {
						if (post_num[k][1] == out.weight) {
							System.out.printf("Q[%d] : d[%c] = %d\n", k, out.v + 65, out.weight);
						} else {
							System.out.printf("Q[%d] : d[%c] = %d  -> d[%c] = %d\n", k, out.v + 65, post_num[k][1],
									out.v + 65, out.weight);
						}
						i++;
					}
				}
			}
			System.out.println();
			check[edge.v] = true;
		}

	}

}