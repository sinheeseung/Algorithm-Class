import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bufReader;
	public static ArrayList<Character> list = new ArrayList<Character>();
	public static void main(String[] args) throws IOException {
		char X[] = null, Y[]=null;
		int m=0,n=0;
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data07_3.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			m = Integer.parseInt(line);
			//문자열 길이 입력
			line = bufReader.readLine();
			//문자열 입력
			X = new char[m+1];
			for(int i=1;i<=m;i++) {
				X[i] = line.charAt(i-1);
			}
			line = bufReader.readLine();
			n = Integer.parseInt(line);

			//문자열 길이 입력
			line = bufReader.readLine();
			//문자열 입력
			Y = new char[n+1];
			for(int i=1;i<=n;i++) {
				Y[i] = line.charAt(i-1);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		LCS_Length(X,Y);
		text();
		//LCS를 출력파일로 생성
	}
	private static void LCS_Length(char[] X, char[] Y) {
		int m = X.length;
		int n = Y.length;
		int[][] c = new int [m][n];
		int[][] b = new int [m][n];
		for(int i=1;i<m;i++) {
			//c의 [i][0]을 0으로 채움
			c[i][0] = 0;
		}
		for(int j=0;j<n;j++) {
			//c의 [0][j]을 0으로 채움
			c[0][j] = 0;
		}
		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				if(X[i] == Y[j]) {
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 1;
					//1인 경우 좌측 위를 가리킨다.
				}
				/*두 문자가 다른 경우 좌측이나 위에 있는 숫자 중 큰 값을 가리키며
				같은 값을 가지게 된다.*/
				else if(c[i-1][j] >= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = 2;
					//2인 경우 좌측을 가리킨다.
				}
				else {
					c[i][j] = c[i][j-1];
					b[i][j] = 3;
					//3인 경우 위를 가리킨다.
				}
			}
		}

		Print_LCS(b,X,m-1,n-1);
	}

	private static void Print_LCS(int[][] b, char[] X, int i,int j) {
		if(i ==0 || j == 0) return;
		if(b[i][j] == 1) {
			Print_LCS(b,X,i-1,j-1);
			//화살표를 따라 이동
			list.add(X[i]);
			//좌측 위를 가리키는 화살표가 나온다면 그 위치에 있는 문자를 저장
		}
		else if(b[i][j] ==2) {
			Print_LCS(b,X,i-1,j);
			//화살표를 따라 이동
		}
		else Print_LCS(b,X,i,j-1);
		//화살표를 따라 이동

	}

	private static void text() {
		String fileName = "data07.txt";
		File file = new File(fileName);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file,true);
			for(int i=0;i<list.size();i++) {
				writer.write(list.get(i));
			}
			writer.flush();
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


}
