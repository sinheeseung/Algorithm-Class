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
	public int Vector; // ������ ��
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
		this.edge[v].add(w); // ������ �߰��մϴ�.
	}

	void BFS(int s) {
		Queue<Node> queue = new LinkedList<Node>();
		for (int i = 0; i < g_v.length ; i++) {
			if (i != s) {
				//�������� S�� ������ ��� ������ COLOR �Ÿ� ������ �ʱ�ȭ
				g_v[i].color = WHITE;
				g_v[i].d = Integer.MAX_VALUE;
				g_v[i].pie = null;
			}
		}
		g_v[s].color = GRAY;
		g_v[s].d = 0;
		g_v[s].pie = g_v[s];
		//���� ������ COLOR, �Ÿ�, ������ �ʱ�ȭ
		queue.add(g_v[s]);
		//ť�� ���� ���� �߰�
		while (queue.size() != 0) {
			Node u = queue.poll();
			//ť���� �ϳ��� ����
			for (int v : edge[u.index]) {
				//������ ��忡�� ������ ��� ���� �湮
				if (g_v[v].color == WHITE) {
					//������ ������ �ѹ��� �湮 ���� ���� ���¶�� 
					g_v[v].color = GRAY;
					g_v[v].d = u.d + 1;
					g_v[v].pie = u;
					//u�� ���� ������ �湮�����Ƿ� ������ ���� ����
					queue.add(g_v[v]);
				}
			}
			u.color = BLACK;
			//u���� ������ ��� ������ Ȯ�������Ƿ� black
		}
	}

	void print() {
		for (Node u : g_v) {
			System.out.printf("%c�� �θ����� : %c  ��� :  %d\n",u.index+114, u.pie.index + 114, u.d);
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
		graph.BFS(1);
		//���� s���� Ž������
		graph.print();

	}
}