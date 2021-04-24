import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	private static Scanner input;
	private static BufferedReader bufReader;

	private static void knapSack(int W,int A[], int wt[], int val[], int n) {
		int i, w;
		int K[][] = new int[n + 1][W + 1];

		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0)
					//ù ���� ���� ���� �ʱ�ȭ ���ش�.
					K[i][w] = 0;
				else if (wt[i - 1] <= w)
					//i��° ������ ���ԵǴ� ���
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
				else
					//i��° ������ ���Ե��� �ʴ� ���
					K[i][w] = K[i - 1][w];
			}
		}
		int max = K[n][W];
		//�賶�� �ִ� ���� ��ġ�� ����
		for(i=0;i<=n;i++) {
			for(w=0;w<=W;w++) {
				System.out.printf("%4d",K[i][w]);
			}
			System.out.println();
		}
		System.out.print("Val : ");
		System.out.println(max);
		System.out.print("Items : ");

		w = W;
		for (i = n; i > 0 && max > 0; i--) {

			if (max == K[i - 1][w])
				continue;
			else {
				// ������ ���ԵǾ� �ִ°��
				System.out.print(A[i - 1] + " ");
				max = max - val[i - 1];
				w = w - wt[i - 1];
				//���濡�� ������ ���´ٰ� �����ϰ� ���� ��ġ�� �� �հ�, ���Ը� ����
			}
		}
		System.out.println();
	}

	// Driver code
	public static void main(String args[]) {

		int val[] = new int[100];
		int wt[] = new int[100];
		int A[] = new int[100];
		int n=0,W=0;
		input = new Scanner(System.in);
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data09_knapsack.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ", ");
				String word = parser.nextToken();
				int num = Integer.parseInt(word);
				A[n] = num;
				word = parser.nextToken();
				num = Integer.parseInt(word);
				val[n] = num;
				word = parser.nextToken();
				num = Integer.parseInt(word);
				wt[n] = num;
				n++;
			}


		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.print("������ ���Ը� �Է��ϼ���(0~50) : ");
		W = input.nextInt();
		while(W >=0 && W<=50) {
			knapSack(W, A,wt, val, n);
			System.out.print("������ ���Ը� �Է��ϼ���(0~50) : ");
			W = input.nextInt();
		}
		System.out.print("���α׷��� �����Ͽ����ϴ�.");
	}

}
