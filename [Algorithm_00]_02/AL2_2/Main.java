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
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data02.txt");
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
		mergeSort(list,0,list.size()-1);
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+ " ");
			if(i % 20 == 0) System.out.println("");
		}


	}
    private static void mergeSort(ArrayList<Integer> list, int left, int right) {

        if(left < right) {
            int mid = (left + right) / 2; // 리스트를 균등 분할
            mergeSort(list,left,mid); // 앞쪽 부분 라스트 정렬
            mergeSort(list,mid+1,right); // 뒤쪽 부분 리스트 정렬

            merge(list,left,mid,right); // 정렬된 2개의 부분 배열을 합병
        }
    }

    private static void merge(ArrayList<Integer> list, int left, int mid, int right) {

        int lidx = left;
        int ridx = mid+1;
        int[] b = new int[list.size()];
        int bidx = left;
        //분할된 list의 합병
        while(lidx <= mid && ridx <= right) {

            if(list.get(lidx) <= list.get(ridx)){
                b[bidx] = list.get(lidx);
                lidx += 1; bidx +=1;
            }
            else {
                b[bidx] = list.get(ridx);
                ridx += 1; bidx +=1;
            }
        }
        //남아있는 값 복사
        if(lidx > mid) {
            for(int i = ridx; i<= right; ++i,++bidx)
                b[bidx] = list.get(i);
        }
        else {
            for(int i = lidx; i<= mid; ++i, ++bidx)
                b[bidx] =list.get(i);
        }
        //임시배열의 리스트를 배열 list로 복사
        for(int i=left; i<=right; ++i)
        	list.set(i, b[i]); 
    }
}