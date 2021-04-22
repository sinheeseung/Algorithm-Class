import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {

	private static BufferedReader bufReader;

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\½ÅÈñ½Â\\2-2\\¾Ë°í¸®Áò\\data02.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ",");
				while(parser.hasMoreTokens()) {
					String word = parser.nextToken();
					int num = Integer.parseInt(word);
					list.add(num);
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
			
		}
		catch(IOException e) {
			System.out.println(e);
		}
		insertionsort(list);
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+ " ");
			if(i % 20 == 0) System.out.println("");
		}


	}
	public static void insertionsort(ArrayList<Integer> list) {
		int key,i,j;
		for(i=1;i<list.size();i++) {
			key = list.get(i);
			for(j=i-1;j>=0&&list.get(j)>key;j--) {
				list.set(j+1, list.get(j));
			}
			list.set(j+1, key);
		}
	}
}
