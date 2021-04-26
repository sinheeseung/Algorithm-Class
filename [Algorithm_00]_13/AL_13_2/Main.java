
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
	public int Vector; // ������ ��
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
		this.edge[v].add(w); // ������ �߰��մϴ�.
	}

	void DFS() {
		for (Node u : g_v) {
			//��� ������ color�� �θ� �ʱ�ȭ
			u.color = WHITE; // 0 white
		    u.pie = null;
		}
		time = 0;
		for (Node u : g_v) {
			if (u.color == WHITE) {
				//��� ������ ���Ͽ� ������ color�� white�� DfsVisitȣ��
				u.pie = u;
				DfsVisit(u);
			}
		}

	}

	void DfsVisit(Node u) {
		this.time++;
		u.time_d = this.time;
		u.color = GRAY; 
		//������ �湮�Ǿ����Ƿ� ���� ����
		for (int v : edge[u.index]) {
			//���� �������� ������ ��� ������ ���Ͽ�
			if (g_v[v].color == WHITE) {
				//���� ������ ���� �湮���� ���� ���
				g_v[v].pie = u;
				DfsVisit(g_v[v]);
				//���� �켱 Ž���̹Ƿ� ���ȣ��
			}
		}

		u.color = BLACK;
		this.time++;
		u.time_f = this.time;
		//���� �������� ������ ��� ������ �湮�Ͽ����Ƿ� ���� ����
		//back tracking
	}

	void print() {
		for (Node u : g_v) {
			System.out.printf("%c��� => Ž�� ���� �ð�: %2d, Ž�� ����ð� :  %2d,  �θ���  : %2c\n",u.index+114,u.time_d ,u.time_f, u.pie.index+114) ;
		}
	}

}

public class Main {
	public static void main(String args[]) {
		// ��Ʈ ��忡�� �����ؼ� ������ ��带 ���� �ƻ��մϴ�.
		Graph graph = null;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data13.txt");
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