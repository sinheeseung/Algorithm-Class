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
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data07_3.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = bufReader.readLine();
			m = Integer.parseInt(line);
			//���ڿ� ���� �Է�
			line = bufReader.readLine();
			//���ڿ� �Է�
			X = new char[m+1];
			for(int i=1;i<=m;i++) {
				X[i] = line.charAt(i-1);
			}
			line = bufReader.readLine();
			n = Integer.parseInt(line);

			//���ڿ� ���� �Է�
			line = bufReader.readLine();
			//���ڿ� �Է�
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
		//LCS�� ������Ϸ� ����
	}
	private static void LCS_Length(char[] X, char[] Y) {
		int m = X.length;
		int n = Y.length;
		int[][] c = new int [m][n];
		int[][] b = new int [m][n];
		for(int i=1;i<m;i++) {
			//c�� [i][0]�� 0���� ä��
			c[i][0] = 0;
		}
		for(int j=0;j<n;j++) {
			//c�� [0][j]�� 0���� ä��
			c[0][j] = 0;
		}
		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				if(X[i] == Y[j]) {
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 1;
					//1�� ��� ���� ���� ����Ų��.
				}
				/*�� ���ڰ� �ٸ� ��� �����̳� ���� �ִ� ���� �� ū ���� ����Ű��
				���� ���� ������ �ȴ�.*/
				else if(c[i-1][j] >= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = 2;
					//2�� ��� ������ ����Ų��.
				}
				else {
					c[i][j] = c[i][j-1];
					b[i][j] = 3;
					//3�� ��� ���� ����Ų��.
				}
			}
		}

		Print_LCS(b,X,m-1,n-1);
	}

	private static void Print_LCS(int[][] b, char[] X, int i,int j) {
		if(i ==0 || j == 0) return;
		if(b[i][j] == 1) {
			Print_LCS(b,X,i-1,j-1);
			//ȭ��ǥ�� ���� �̵�
			list.add(X[i]);
			//���� ���� ����Ű�� ȭ��ǥ�� ���´ٸ� �� ��ġ�� �ִ� ���ڸ� ����
		}
		else if(b[i][j] ==2) {
			Print_LCS(b,X,i-1,j);
			//ȭ��ǥ�� ���� �̵�
		}
		else Print_LCS(b,X,i,j-1);
		//ȭ��ǥ�� ���� �̵�

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
