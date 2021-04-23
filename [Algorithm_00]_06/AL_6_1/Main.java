import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {

	private static BufferedReader bufReader;
	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		ArrayList<Node> list = new ArrayList<>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data06_heap.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ",");
				String word = parser.nextToken();
				int num = Integer.parseInt(word);
				line = parser.nextToken();
				list.add(new Node(line, num));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		build_max_heap(list);
		printheap(list);
		System.out.println("-------------------------------------");
		System.out.println("1. �۾��߰�  2. �ִ�  3. �ִ� �켱���� �۾� ó��");
		System.out.println("4. ���� Ű�� ����          5. �۾� ����        6.����");
		System.out.println("-------------------------------------");
		while (true) {
			System.out.println("-------------------------------------");
			System.out.print("�����ϰ���� �۾��� �Է��ϼ��� : ");
			int n = input.nextInt();
			if (n == 1) {
				System.out.print("�߰��ϰ� ���� �۾��� �Է��Ͻÿ� : ");
				String line = input.nextLine();
				line = input.nextLine();
				StringTokenizer parser = new StringTokenizer(line, ",");
				String word = parser.nextToken();
				int num = Integer.parseInt(word);
				line = parser.nextToken();
				Node t = new Node(line,num);
				insert(list,t);
				printheap(list);
			}
			else if (n == 2) {
				System.out.println("�ִ� : "+ max(list));
			}
			else if (n == 3) {
				System.out.println("�ִ� : "+ extract_max(list));
				printheap(list);
			}
			else if (n == 4) {
				System.out.print("�ٲٰ� ���� ����� ��ġ�� �Է��ϼ��� : ");
				int index = input.nextInt();
				System.out.print("�ٲ� ���� �Է��ϼ��� : ");
				int new_num = input.nextInt();
				icrease_key(list,index-1,new_num);
				printheap(list);
			}
			else if (n == 5) {
				System.out.print("�����ϰ���� �۾��� ��ġ�� �Է��ϼ��� : ");
				int num = input.nextInt();
				delete(list,num-1);
				printheap(list);
			}
			else if (n == 6) {
				System.out.print("���α׷��� �����մϴ�.");
				break;
			}
		}
	}
	private static void printheap(ArrayList<Node> list) {
		//���� �켱���� ť�� ����� ���
		System.out.println("****���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�****");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%3d : %4d, %s\n",i+1,list.get(i).y,list.get(i).x);

		}
	}
	private static void insert(ArrayList<Node> list, Node t) {
		//�켱���� ť�� ���ο� �۾��� ����
		list.add(t);
		build_max_heap(list);
	}

	private static int max(ArrayList<Node> list) {
		//�켱���� ť�� �ִ��� return
		return list.get(0).y;
	}

	private static int extract_max(ArrayList<Node> list) {
		//�켱���� ť�� �ִ밪�� return�ϰ�, �ִ밪�� ����
		int max = max(list);
		delete(list, 0);
		build_max_heap(list);
		return max;
	}

	private static void icrease_key(ArrayList<Node> list, int num, int key) {
		//�ε����� �Է¹޾� �� �ε����� Ű ���� ���ο� Ű ������ ġȯ
		Node t = new Node(list.get(num).x, key);
		list.set(num, t);
		build_max_heap(list);
	}

	private static void delete(ArrayList<Node> list, int num) {
		//�ε����� �Է¹޾� �ε��� ��°�� ���� ����
		list.remove(num);
		build_max_heap(list);
	}

	private static void build_max_heap(ArrayList<Node> list) {
		for (int i = list.size() / 2; i >= 0; i--) {
			max_heapify(list, i);
		}
	}

	private static void max_heapify(ArrayList<Node> list, int num) {
		//0��°���� �����ϱ� ������ �ڽĳ��� num*2+1, num*2+2�� �ȴ�
		int left_child = num * 2 + 1;
		int right_child = (num * 2) + 2;
		int largest;
		if (left_child < list.size() && list.get(left_child).y > list.get(num).y) {
			//���� �ڽĳ�尡 �θ��庸�� �� ū ���
			largest = left_child;
		} else
			largest = num;
		if (right_child < list.size() && list.get(right_child).y > list.get(largest).y) {
			//������ �ڽĳ�尡 �θ��庸�� �� ū ��� 
			largest = right_child;
		}
		if (largest != num) {
			//�ڽĳ�尡 �θ��庸�� �� ū ���
			Node t = list.get(largest);
			list.set(largest, list.get(num));
			list.set(num, t);
			max_heapify(list, largest);
		}

	}

	static class Node {
		//x = �۾���, y = Ű ��
		String x;
		int y;

		public Node(String x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
