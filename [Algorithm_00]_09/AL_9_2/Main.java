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
					//첫 번쨰 열과 행을 초기화 해준다.
					K[i][w] = 0;
				else if (wt[i - 1] <= w)
					//i번째 보석이 포함되는 경우
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
				else
					//i번째 보석이 포함되지 않는 경우
					K[i][w] = K[i - 1][w];
			}
		}
		int max = K[n][W];
		//배낭에 있는 물건 가치의 총합
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
				// 물건이 포함되어 있는경우
				System.out.print(A[i - 1] + " ");
				max = max - val[i - 1];
				w = w - wt[i - 1];
				//가방에서 물건을 꺼냈다고 생각하고 물건 가치의 총 합과, 무게를 빼줌
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
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data09_knapsack.txt");
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
		System.out.print("가방의 무게를 입력하세요(0~50) : ");
		W = input.nextInt();
		while(W >=0 && W<=50) {
			knapSack(W, A,wt, val, n);
			System.out.print("가방의 무게를 입력하세요(0~50) : ");
			W = input.nextInt();
		}
		System.out.print("프로그램을 종료하였습니다.");
	}

}
