import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bufReader;

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data06.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ",");
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
		quicksort(list, 0, list.size() - 1);
		
	}

	private static void quicksort(ArrayList<Integer> A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quicksort(A, p, q - 1);
			quicksort(A, q + 1, r);
		}
	}

	private static void swap(ArrayList<Integer> A, int a, int b) {
		int t = A.get(a);
		A.set(a, A.get(b));
		A.set(b, t);

	}

	private static int partition(ArrayList<Integer> A, int p, int r) {
		// x�� pivot������ �Ѵ�.
		int x = A.get(r);
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (A.get(j) <= x) {
				// x���� ���� ������ x�� ���ʿ� ��ġ��Ŵ
				i++;
				int t = A.get(i);
				A.set(i, A.get(j));
				A.set(j, t);
			}
		}
		i++;
		swap(A, i, r);
		// r�� i�� ��ġ�� �ٲ����� r�� �������� ���ʿ��� r���� ��������
		// �����ʿ��� r���� ū ���� ���Ե�
		return i;

	}

}
