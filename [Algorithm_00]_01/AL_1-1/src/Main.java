import java.util.Scanner;

public class Main {
	private static Scanner input;

	public static void main(String[] args) {
		int n;
		//�ԷµǴ� ������ ����
		int arr[] = new int[100];
		//������ ������������ ������ �迭
		input = new Scanner(System.in);
		System.out.print("N : ");
		n = input.nextInt();
		System.out.print("Input : ");
		for (int i = 0; i < n; i++) {
			arr[i] = input.nextInt();
		}
		insertionsort(arr, n);
		//�������� ����
		System.out.println("�ּڰ� : " + arr[0]);
		System.out.println("�ִ� : " + arr[n-1]);
	}
	public static void insertionsort(int arr[], int num) {
		//���������� ���� �Էµ� ���� ������������ ����
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


