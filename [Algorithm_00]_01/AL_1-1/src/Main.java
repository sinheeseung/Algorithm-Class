import java.util.Scanner;

public class Main {
	private static Scanner input;

	public static void main(String[] args) {
		int n;
		//입력되는 정수의 갯수
		int arr[] = new int[100];
		//정수를 오름차순으로 정렬할 배열
		input = new Scanner(System.in);
		System.out.print("N : ");
		n = input.nextInt();
		System.out.print("Input : ");
		for (int i = 0; i < n; i++) {
			arr[i] = input.nextInt();
		}
		insertionsort(arr, n);
		//삽입정렬 실행
		System.out.println("최솟값 : " + arr[0]);
		System.out.println("최댓값 : " + arr[n-1]);
	}
	public static void insertionsort(int arr[], int num) {
		//삽입정렬을 통해 입력된 값을 오름차순으로 정렬
		int key, i,j;
		for(i=1;i<num;i++) {
			key = arr[i];
			for(j=i-1;j>=0&&arr[j]>key;j--) {
				arr[j+1] = arr[j];
			}
			arr[j+1] = key;
		}
	}

}


