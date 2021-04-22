import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static BufferedReader bufReader;

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data05_inversion.txt");
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
		System.out.print(Sort_and_Count(list, 0, list.size() - 1));


	}

	private static int Sort_and_Count(ArrayList<Integer> arr, int left, int right) {
		if (left >= right)
			return 0;
		int mid = (right + left) / 2; // 리스트 균등 분할
		int leftcount = Sort_and_Count(arr, left, mid); // 앞쪽 부분 리스트 정렬 및 카운트
		int rightcount = Sort_and_Count(arr, mid + 1, right); // 뒤쪽 부분 리스트 정렬 및 카운트
		int mergecount = Merge_and_Count(arr, left, right, mid); // 2개의 부분 배열 합병 및 카운트
		return leftcount + rightcount + mergecount;
	}

	private static int Merge_and_Count(ArrayList<Integer> arr, int left, int right, int mid) {
		int inversion_count = 0;
		int indexA = left, indexB = mid + 1, index = left;
		int[] b = new int[arr.size()];

		while (indexA <= mid && indexB <= right) {
			if (arr.get(indexA) > arr.get(indexB)) {
				inversion_count = inversion_count + mid - indexA + 1;
				b[index] = arr.get(indexB);
				indexB++;
			} else {
				b[index] = arr.get(indexA);
				indexA++;
			}
			index++;

		} 
		if (indexA > mid) {
			for (int i = indexB; i <= right; ++i, ++index)
				b[index] = arr.get(i);
		} else {
			for (int i = indexA; i <= mid; ++i, ++index)
				b[index] = arr.get(i);
		}
		for (int i = left; i <= right; ++i)
			arr.set(i, b[i]);
		return inversion_count;
	}

}
