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
	public static void main(String[] args) throws IOException {
		input = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data07_1.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ", ");
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken();
					int num = Integer.parseInt(word);
					list.add(num);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.print("x���� �Է��Ͻÿ� : ");
		int x = input.nextInt();
		System.out.println(Binary_Search(list, x));
		
	}
	private static int Binary_Search(ArrayList<Integer> A, int x) {
		int start = 0;
		int end = A.size()-1;
		int mid; 
		while(start +1 < end) {
			mid = (start + end) / 2;
			if(start <= mid) {
				if(A.get(mid) == x) {
					return mid+1;
				}
				else if(A.get(mid) > x) {
					//x���� mid�� ���ʿ� ��ġ�Ѵ�.
					end = mid;
				}
				else start = mid;
				//x���� mid�� �����ʿ� ��ġ�Ѵ�.
			}
		}
		return 0;
	}

}