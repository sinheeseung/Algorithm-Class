package AL_4_3_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {

	private static Scanner input;
	private static BufferedReader bufReader;

	public static void main(String[] args) {
		int A[][] = new int[64][64];
		int B[][] = new int[64][64];
		int C[][] = new int[64][64];
		int cnt = 0, a = 0, size= 0,n;
		long time=0;
		input = new Scanner(System.in);
		System.out.println("방법");
		System.out.println("1. Divide and Conquer");
		System.out.println("2. Strassen");
		n = input.nextInt();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data04.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			while (true) {
				line = bufReader.readLine();
				StringTokenizer parser = new StringTokenizer(line, " ");
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken();
					int num = Integer.parseInt(word);
					A[a][cnt] = num;
					cnt++;
				}
				a++;
				size = cnt;
				cnt = 0;
				if (a >= size)
					break;
			}
			line = bufReader.readLine();
			line = bufReader.readLine();
			cnt = 0;
			a = 0;
			while (true) {
				line = bufReader.readLine();
				StringTokenizer parser_b = new StringTokenizer(line, " ");
				while (parser_b.hasMoreTokens()) {
					String word = parser_b.nextToken();
					int num = Integer.parseInt(word);
					B[a][cnt] = num;
					cnt++;
				}
				a++;
				size = cnt;
				cnt = 0;
				if (a >= size)
					break;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);

		} catch (IOException e) {
			System.out.println(e);
		}
		if(n == 1) {
			long start = System.currentTimeMillis();
			C = Multi(A, B);
			long end = System.currentTimeMillis();
			time = (end - start);
		}
		else {
			long start = System.currentTimeMillis();
			C = strassen(A, B);
			long end = System.currentTimeMillis();
		    time = (end - start);
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("%f   sec\n",(float)time/1000);
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("%5d", C[i][j]);
			}
			System.out.println();
		}

	}

	public static int[][] Multi(int[][] A, int[][] B) {
		//Divide and Conquer
		int n = A.length;
		int[][] result = new int[n][n];

		if (n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];

			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			divide(A, A11, 0, 0);
			divide(A, A12, 0, n / 2);
			divide(A, A21, n / 2, 0);
			divide(A, A22, n / 2, n / 2);

			divide(B, B11, 0, 0);
			divide(B, B12, 0, n / 2);
			divide(B, B21, n / 2, 0);
			divide(B, B22, n / 2, n / 2);
			
			int[][] C11 = add(Multi(A11,B11),Multi(A12,B21)); //C11
			int[][] C12 = add(Multi(A11,B12),Multi(A12,B22)); //C12
			int[][] C21 = add(Multi(A21,B11),Multi(A22,B21)); //C21
			int[][] C22 = add(Multi(A21,B12),Multi(A22,B22)); //C22

			combine(C11,C12,C21,C22,result);
		}
		return result;
	}
	public static int[][] strassen(int[][] A, int[][] B) {
		int n = A.length;
		int[][] result = new int[n][n];

		if (n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];

			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			divide(A, A11, 0, 0);
			divide(A, A12, 0, n / 2);
			divide(A, A21, n / 2, 0);
			divide(A, A22, n / 2, n / 2);

			divide(B, B11, 0, 0);
			divide(B, B12, 0, n / 2);
			divide(B, B21, n / 2, 0);
			divide(B, B22, n / 2, n / 2);

			int[][] P1 = strassen(add(A11, A22), add(B11, B22));// p1=(a11+a11)(b11+b22)
			int[][] P2 = strassen(add(A21, A22), B11); // p2=(a21+a22)b11
			int[][] P3 = strassen(A11, sub(B12, B22)); // p3=a11(b12-b22)
			int[][] P4 = strassen(A22, sub(B21, B11));// p4=a22(b21-b11)
			int[][] P5 = strassen(add(A11, A12), B22);// p5=(a11+a12)b22
			int[][] P6 = strassen(sub(A21, A11), add(B11, B12)); // p6=(a21-a11)(b11+b12)
			int[][] P7 = strassen(sub(A12, A22), add(B21, B22)); // p7=(a12-a22)(a21+b22)

			int[][] C11 = add(sub(add(P1, P4), P5), P7); // c11 = p1 + p4 - p5 + p7
			int[][] C12 = add(P3, P5);// c12 = p3 + p5
			int[][] C21 = add(P2, P4);// c21 = p2 + p4
			int[][] C22 = add(sub(add(P1, P3), P2), P6);// c22 = p1 + p3 - p2 + p6

			combine(C11,C12,C21,C22,result);
		}
		return result;
	}

	private static void combine(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int[][] result) {
		//c11,c12,c21,c22 4가지 행렬을 하나의 행렬로 병합
		int n = c11.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = c11[i][j]; // 11
				result[i][j + n] = c12[i][j]; // 12
				result[i + n][j] = c21[i][j]; // 21
				result[i + n][j + n] = c22[i][j]; // 22
			}
		}
	}
	public static int[][] add(int[][] A, int[][] B) {
		//행렬의 덧셈을 계산
		int n = A.length;
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = A[i][j] + B[i][j];
		return result;
	}
	public static int[][] sub(int[][] A, int[][] B) {
		//행렬의 뺄셈을 계산
		int n = A.length;
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = A[i][j] - B[i][j];
		return result;
	}
	public static void divide(int[][] o, int[][] d, int row, int col) {
		//n*n행렬을 (n/2)*(n/2)행렬로 나누는데 사용
		for (int i1 = 0, i2 = row; i1 < d.length; i1++, i2++)
			for (int j1 = 0, j2 = col; j1 < d.length; j1++, j2++) {
				d[i1][j1] = o[i2][j2];
			}
	}

}
