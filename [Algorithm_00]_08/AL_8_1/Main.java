import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bufReader;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] A = new int[100][100];
		int i = 0;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data08_matrix_chain.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			i = 0;
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ",");
				int j = 0;
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken();
					int num = Integer.parseInt(word);
					A[i][j] = num;
					j++;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		int[] p = new int[i + 1];
		p[0] = A[0][0];
		p[1] = A[0][1];
		for (int j = 2; j <= i; j++) {
			if (A[j - 2][1] == A[j - 1][0])
				p[j] = A[j - 1][1];
		}
		for (int j = 1; j <= i; j++) {
			System.out.printf("A(%d) = %3d x %3d\n", j, A[j - 1][0], A[j - 1][1]);
		}
		System.out.println("-----------------------------------------");
		martrix_chain_order(p);
	}

	private static void martrix_chain_order(int[] p) {
		int n = p.length;
		int m[][] = new int[n][n];
		int s[][] = new int[n][n];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				//s배열은 -1로 초기화
				s[i][j] = -1;
				if (i == j)
					// i,i인 경우 0을 삽입
					m[i][j] = 0;
				else
					// i,j인 경우 -1을 삽입
					m[i][j] = -1;
			}
		}
		for (int l = 2; l < n; l++) {
			for (int i = 1; i < n - l + 1; i++) {
				int j = i + l - 1;
				if (n == j)
					//범위가 인덱스 안인 경우
					continue;
				m[i][j] = Integer.MAX_VALUE;
				//최소 행렬곱을 구해야 하기 때문에 최대값으로 초기화시킴
				for (int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					//행렬곱에 필요한 곱셈의 수를 계산
					if (q < m[i][j]) {
						//더 작은 곱셈의 수가 계산된 경우
						m[i][j] = q;
						s[i][j] = k - 1;
					}
				}
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				System.out.printf("%6d", m[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------");
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				System.out.printf("%6d", s[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------");
		System.out.printf("Optimal solution : %d\n", m[1][n-1]);
		System.out.print("Optimal parens : ");
		print_optimal_parens(s, 1, n - 1);
	}

	private static void print_optimal_parens(int[][] s, int i, int j) {
		if (i == j) {
			System.out.printf("A[%d]", i);
		} else {
			System.out.print("(");
			print_optimal_parens(s, i, s[i][j] + 1);
			print_optimal_parens(s, s[i][j] + 2, j);
			System.out.print(")");
		}
	}

}
