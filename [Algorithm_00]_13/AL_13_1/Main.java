import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
	Node(int i) {
		this.index = i;
	}

	int d;
	int index;
	int color;
	Node pie;

}

class Graph {
	public int Vector; // 벡터의 수
	public int WHITE=0,GRAY=1,BLACK=2;
	public LinkedList<Integer> edge[];
	Node g_v[];

	Graph(int vector) {
		this.Vector = vector;
		edge = new LinkedList[this.Vector];
		for (int i = 0; i < this.Vector; i++) {
			edge[i] = new LinkedList();
		}
		g_v = new Node[Vector];
		for (int i = 0; i < this.Vector; i++) {
			g_v[i] = new Node(i);
		}
	}

	void add(int v, int w) {
		this.edge[v].add(w); // 간선을 추가합니다.
	}

	void BFS(int s) {
		Queue<Node> queue = new LinkedList<Node>();
		for (int i = 0; i < g_v.length ; i++) {
			if (i != s) {
				//시작정점 S를 제외한 모든 정점의 COLOR 거리 선행지 초기화
				g_v[i].color = WHITE;
				g_v[i].d = Integer.MAX_VALUE;
				g_v[i].pie = null;
			}
		}
		g_v[s].color = GRAY;
		g_v[s].d = 0;
		g_v[s].pie = g_v[s];
		//시작 정점의 COLOR, 거리, 선행자 초기화
		queue.add(g_v[s]);
		//큐에 시작 정점 추가
		while (queue.size() != 0) {
			Node u = queue.poll();
			//큐에서 하나씩 제거
			for (int v : edge[u.index]) {
				//제거한 노드에서 인접한 모든 정점 방문
				if (g_v[v].color == WHITE) {
					//인접한 정점이 한번도 방문 받지 못한 상태라면 
					g_v[v].color = GRAY;
					g_v[v].d = u.d + 1;
					g_v[v].pie = u;
					//u를 통해 정점에 방문했으므로 정점의 상태 변경
					queue.add(g_v[v]);
				}
			}
			u.color = BLACK;
			//u에서 인접한 모든 정점을 확인했으므로 black
		}
	}

	void print() {
		for (Node u : g_v) {
			System.out.printf("%c의 부모정점 : %c  비용 :  %d\n",u.index+114, u.pie.index + 114, u.d);
		}
	}


}

public class Main {
	public static void main(String args[]) {
		// 루트 노드에서 시작해서 인접한 노드를 먼저 탬색합니다.
		Graph graph = null;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data13.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			int n = Integer.parseInt(line);
			graph = new Graph(n);
			int i = 0;
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, " ");
				int j = 0;
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken();
					int num = Integer.parseInt(word);
					if (num == 1) {
						graph.add(i, j);
					}
					j++;
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		graph.BFS(1);
		//정점 s에서 탐색시작
		graph.print();

	}
}