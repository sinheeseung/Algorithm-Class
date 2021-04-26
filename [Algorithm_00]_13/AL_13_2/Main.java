
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	Node(int i) {
		this.index = i;
	}

	int index;
	int color;
	Node pie;
	int time_d;
	int time_f;

}

class Graph {
	int time = 0;
	public int Vector; // 벡터의 수
	public LinkedList<Integer> edge[];
	Node g_v[];
	public int WHITE=0,GRAY=1,BLACK=2;
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

	void DFS() {
		for (Node u : g_v) {
			//모든 정점의 color와 부모 초기화
			u.color = WHITE; // 0 white
		    u.pie = null;
		}
		time = 0;
		for (Node u : g_v) {
			if (u.color == WHITE) {
				//모든 정점에 대하여 정점의 color가 white면 DfsVisit호출
				u.pie = u;
				DfsVisit(u);
			}
		}

	}

	void DfsVisit(Node u) {
		this.time++;
		u.time_d = this.time;
		u.color = GRAY; 
		//정점이 방문되었으므로 상태 변경
		for (int v : edge[u.index]) {
			//현재 정점에서 인접한 모든 정점에 대하여
			if (g_v[v].color == WHITE) {
				//인접 정점이 아직 방문되지 않은 경우
				g_v[v].pie = u;
				DfsVisit(g_v[v]);
				//깊이 우선 탐색이므로 재귀호출
			}
		}

		u.color = BLACK;
		this.time++;
		u.time_f = this.time;
		//현재 정점에서 인접한 모든 정점을 방문하였으므로 상태 변경
		//back tracking
	}

	void print() {
		for (Node u : g_v) {
			System.out.printf("%c노드 => 탐색 시작 시간: %2d, 탐색 종료시간 :  %2d,  부모노드  : %2c\n",u.index+114,u.time_d ,u.time_f, u.pie.index+114) ;
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
		graph.DFS();
		graph.print();

	}
}