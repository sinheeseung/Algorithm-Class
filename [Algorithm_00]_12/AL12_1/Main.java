
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static Map<Character, String> charPrefixHashMap = new HashMap<>();
	static HuffmanNode root;
	static Node root2;
	private static BufferedReader bufReader;

	public static void main(String[] args) {
		String test = null;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data12_huffman.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			test = bufReader.readLine();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Original Text = " + test);
		String s = encode(test);
		export("201702033_encoded.txt", s);
		String encoded=null;
		try {
			File file = new File("C:\\Users\\shs72\\eclipse-workspace\\AL_12_1\\201702033_encoded.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			encoded = bufReader.readLine();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}


		Map<Character, String> Map = new HashMap<>();
		try {
			File file = new File("C:\\Users\\shs72\\eclipse-workspace\\AL_12_1\\201702033_table.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			test = bufReader.readLine();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		test = test.substring(1, test.length() - 2);
		//table입력파일에 있는 {} 제거
		String stArr[] = test.split(", ");
		for (int i = 0; i < stArr.length; i++) {
			StringTokenizer parser = new StringTokenizer(stArr[i], "=");
			String word1 = parser.nextToken();
			String word2 = parser.nextToken();
			Map.put(word1.charAt(0), word2);
		}
		addTree(Map);
		decode(encoded);
	}

	private static HuffmanNode buildTree(Map<Character, Integer> freq) {
		//빈도수를 이용해 tree생성
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		Set<Character> keySet = freq.keySet();
		for (Character c : keySet) {
			HuffmanNode huffmanNode = new HuffmanNode();
			huffmanNode.data = c;
			huffmanNode.frequency = freq.get(c);
			huffmanNode.left = null;
			huffmanNode.right = null;
			priorityQueue.offer(huffmanNode);
			// Queue에 huffmanNode를 삽임
		}
		while (priorityQueue.size() > 1) {
			HuffmanNode x = priorityQueue.peek();
			priorityQueue.poll();

			HuffmanNode y = priorityQueue.peek();
			priorityQueue.poll();
			// 가장 작은 두개의 노드 추출 후 제거
			HuffmanNode sum = new HuffmanNode();
			sum.frequency = x.frequency + y.frequency;
			sum.data = '-';
			sum.left = x;
			sum.right = y;
			// 가장 작은 두개의 노드를 자식으로 가지는 새로운 노드를 생성
			// 이 노드의 빈도수는 두 자식 노드의 빈도수의 합
			priorityQueue.offer(sum);
			// 새로운 노드를 큐에 삽입
		}
		return priorityQueue.poll();
		// 큐에는 하나의 노드(root노드)밖에 없음
	}

	public static void addTree(Map<Character, String> map) {
		//table을 이용해서 tree를 만듬
		root2 = new Node();
		Node temp = root2;
		int i = 0;
		for (Character c : map.keySet()) {
			String encode = map.get(c);
			for (i = 0; i < map.get(c).length() - 1; i++) {
				if (encode.charAt(i) == '0') { 
					// 만약 0이면 왼쪽애 노드생성
					if (temp.left == null) {
						temp.left = new Node();
						temp = temp.left;
					} else {
						temp = temp.left;
					}
				} else if (encode.charAt(i) == '1') {
					// 1이면 오른쪽에 노드생성
					if (temp.right == null) {
						temp.right = new Node();
						temp = temp.right;
					} else {
						temp = temp.right;
					}
				}
			}

			if (encode.charAt(i) == '0') {
				// 마지막이 0이면 현재 문자 왼쪽 노드에 문자를 저장한 노드를 저장합니다.
				Node n = new Node();
				n.data = c;
				temp.left = n;
				temp = root2;
			} else {
				// 마지막이 1이면 현재 문자 오른쪽 노드에 문자를 저장한 노드를 저장합니다.
				Node n = new Node();
				n.data = c;
				temp.right = n;
				temp = root2;
			}
		}
	}

	private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {
		//tree를 통해 prefix노드 생성
		if (node != null) {
			if (node.left == null && node.right == null) {
				charPrefixHashMap.put(node.data, prefix.toString());
			} else {
				prefix.append('0');
				setPrefixCodes(node.left, prefix);
				prefix.deleteCharAt(prefix.length() - 1);

				prefix.append('1');
				setPrefixCodes(node.right, prefix);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
	}

	private static void export(String name, String s) {
		String fileName = name;
		File file = new File(fileName);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String encode(String test) {
		// 각 문자별로 빈도수를 확인
		Map<Character, Integer> freq = new HashMap<>();
		for (int i = 0; i < test.length(); i++) {
			if (!freq.containsKey(test.charAt(i))) {
				// 문자가 freq(map)에 입력되어있지 않은 경우
				freq.put(test.charAt(i), 0);
			}
			freq.put(test.charAt(i), freq.get(test.charAt(i)) + 1);
			// 해당 문자의 개수를 +1
		}
		System.out.println("Character Frequency Map = " + freq);
		root = buildTree(freq);
		setPrefixCodes(root, new StringBuilder());
		System.out.println("Character Prefix Map = " + charPrefixHashMap);
		StringBuilder s = new StringBuilder();
		export("201702033_table.txt", charPrefixHashMap.toString());
		for (int i = 0; i < test.length(); i++) {
			char c = test.charAt(i);
			s.append(charPrefixHashMap.get(c));
		}
		return s.toString();
	}

	private static void decode(String s) {
		//encode된 data를 table을 이용해 decode
		StringBuilder stringBuilder = new StringBuilder();
		Node temp = root2;
		System.out.println("Encoded: " + s);
		for (int i = 0; i < s.length(); i++) {
			int j = Integer.parseInt(String.valueOf(s.charAt(i)));
			if (j == 0) {
				//0이면 왼쪽으로 이동
				temp = temp.left;
				if (temp.left == null && temp.right == null) {
					//tree의 마지막 부분이라면 그 값을 저장
					stringBuilder.append(temp.data);
					temp = root2;
				}
			}
			if (j == 1) {
				temp = temp.right;
				//1이면 오른쪽으로 이동
				if (temp.left == null && temp.right == null) {
					//tree의 마지막 부분이라면 그 값을 저장
					stringBuilder.append(temp.data);
					temp = root2;
				}
			}
		}
		System.out.println("Decoded string is " + stringBuilder.toString());
		export("201702033_decoded.txt", stringBuilder.toString());
	}
}

class Node implements Comparable<Node> {
	int frequency;
	char data;
	Node left, right;

	public int compareTo(Node node) {
		return frequency - node.frequency;
	}
}

class HuffmanNode implements Comparable<HuffmanNode> {
	int frequency;
	char data;
	HuffmanNode left, right;

	public int compareTo(HuffmanNode node) {
		return frequency - node.frequency;
	}
}