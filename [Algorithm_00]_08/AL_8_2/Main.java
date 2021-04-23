import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bufReader;

	public static void main(String[] args) {
		int m = 0;
		double[] p = null;
		int[] A = null;
		int[] q = null;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data08_optimal_bst.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			m = Integer.parseInt(line);
			// 문자열 길이 입력
			p = new double[m];
			A = new int[m];
			q = new int[m];
			int i = 0;
			line = bufReader.readLine();
			StringTokenizer parser = new StringTokenizer(line, ", ");
			while (parser.hasMoreTokens()) {
				String word = parser.nextToken();
				int num = Integer.parseInt(word);
				A[i] = num;
				i++;
			}
			i = 0;
			line = bufReader.readLine();
			parser = new StringTokenizer(line, ", ");
			while (parser.hasMoreTokens()) {
				String word = parser.nextToken();
				double num = Double.parseDouble(word);
				p[i] = num;
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} 
		for(int i =0; i<m;i++) {
			//dummy key값 생성
			q[i] = 0;
		}
		optimal_bst(p, q, m);
	}

	public static void optimal_bst(double[] p, int[] q, int n) {
		double[][] e = new double[n + 2][n + 1];
		double[][] w = new double[n + 2][n + 1];
		int[][] root = new int[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			e[i][i - 1] = p[i - 1];
			w[i][i - 1] = p[i - 1];
		}

		for (int l = 1; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				e[i][j] = Double.POSITIVE_INFINITY;
				w[i][j] = w[i][j - 1] + p[j-1] + q[j-1];
				//(i~j)의 키를 가진 서브 트리의 모든 확률의 합
				for (int r = i; r <= j; r++) {
					//r이 root인 경우
					double t = e[i][r - 1] + e[r + 1][j] + w[i][j];
					//r이 root일 때 가지는 검색 비용
					if (t < e[i][j]) 
					//검색 비용이 이전의 비용보다 큰 경우
					{
						e[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
		System.out.println("e");
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%.2f ",e[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
		System.out.println("w");
		for (int i = 1; i < n + 2; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%.2f ",w[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
		System.out.println("root");
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				System.out.print(root[i][j] + " ");
			}
			System.out.println();
		}
	}


}