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
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data06_heap.txt");
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
		System.out.println("1. 작업추가  2. 최댓값  3. 최대 우선순위 작업 처리");
		System.out.println("4. 원소 키값 증가          5. 작업 제거        6.종료");
		System.out.println("-------------------------------------");
		while (true) {
			System.out.println("-------------------------------------");
			System.out.print("수행하고싶은 작업을 입력하세요 : ");
			int n = input.nextInt();
			if (n == 1) {
				System.out.print("추가하고 싶은 작업을 입력하시오 : ");
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
				System.out.println("최댓값 : "+ max(list));
			}
			else if (n == 3) {
				System.out.println("최댓값 : "+ extract_max(list));
				printheap(list);
			}
			else if (n == 4) {
				System.out.print("바꾸고 싶은 노드의 위치를 입력하세요 : ");
				int index = input.nextInt();
				System.out.print("바꿀 값을 입력하세요 : ");
				int new_num = input.nextInt();
				icrease_key(list,index-1,new_num);
				printheap(list);
			}
			else if (n == 5) {
				System.out.print("제거하고싶은 작업의 위치를 입력하세요 : ");
				int num = input.nextInt();
				delete(list,num-1);
				printheap(list);
			}
			else if (n == 6) {
				System.out.print("프로그램을 종료합니다.");
				break;
			}
		}
	}
	private static void printheap(ArrayList<Node> list) {
		//현재 우선순위 큐의 목록을 출력
		System.out.println("****현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다****");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%3d : %4d, %s\n",i+1,list.get(i).y,list.get(i).x);

		}
	}
	private static void insert(ArrayList<Node> list, Node t) {
		//우선순위 큐에 새로운 작업을 삽입
		list.add(t);
		build_max_heap(list);
	}

	private static int max(ArrayList<Node> list) {
		//우선순위 큐의 최댓값을 return
		return list.get(0).y;
	}

	private static int extract_max(ArrayList<Node> list) {
		//우선순위 큐의 최대값을 return하고, 최대값을 삭제
		int max = max(list);
		delete(list, 0);
		build_max_heap(list);
		return max;
	}

	private static void icrease_key(ArrayList<Node> list, int num, int key) {
		//인덱스를 입력받아 그 인덱스의 키 값을 새로운 키 값으로 치환
		Node t = new Node(list.get(num).x, key);
		list.set(num, t);
		build_max_heap(list);
	}

	private static void delete(ArrayList<Node> list, int num) {
		//인덱스를 입력받아 인덱스 번째의 값을 삭제
		list.remove(num);
		build_max_heap(list);
	}

	private static void build_max_heap(ArrayList<Node> list) {
		for (int i = list.size() / 2; i >= 0; i--) {
			max_heapify(list, i);
		}
	}

	private static void max_heapify(ArrayList<Node> list, int num) {
		//0번째부터 시작하기 때문에 자식노드는 num*2+1, num*2+2가 된다
		int left_child = num * 2 + 1;
		int right_child = (num * 2) + 2;
		int largest;
		if (left_child < list.size() && list.get(left_child).y > list.get(num).y) {
			//왼쪽 자식노드가 부모노드보다 더 큰 경우
			largest = left_child;
		} else
			largest = num;
		if (right_child < list.size() && list.get(right_child).y > list.get(largest).y) {
			//오른쪽 자식노드가 부모노드보다 더 큰 경우 
			largest = right_child;
		}
		if (largest != num) {
			//자식노드가 부모노드보다 더 큰 경우
			Node t = list.get(largest);
			list.set(largest, list.get(num));
			list.set(num, t);
			max_heapify(list, largest);
		}

	}

	static class Node {
		//x = 작업명, y = 키 값
		String x;
		int y;

		public Node(String x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
