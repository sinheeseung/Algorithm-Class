import java.util.Scanner;
public class Main {
	private static Scanner input;
	private static long fibonacci(int n) {
		if(n < 2)
			return n;
		else
			return fibonacci(n-1) + fibonacci(n-2);
	}
	private static long[][] multi(long a[][], long b[][]){
		//행렬의 곱을 계산
		long arr[][] = new long[2][2];
		arr[0][0] = a[0][0]*b[0][0]+a[0][1]*b[1][0];
		arr[0][1] = a[0][0]*b[0][1]+a[0][1]*b[1][1];
		arr[1][0] = a[1][0]*b[0][0]+a[1][1]*b[1][0];
		arr[1][1] = a[1][0]*b[0][1]+a[1][1]*b[1][1];
		return arr;
	}
	private static long[][] pow(long arr[][], int n){
		//지수의 홀짝에 따라 각각 다른 값을 return
		if(n > 1) {
			arr = pow(arr,n/2);
			arr = multi(arr,arr);
			if(n % 2 == 1) {
				//홀수인 경우 a를 한번 더 곱해줌
				long a[][] = new long[2][2];
				a[0][0] = 1; a[0][1] = 1; 
				a[1][0] = 1; a[1][1] = 0;
				arr = multi(arr,a);
			}
		}
		return arr;
	}
	private static long RecursiveSquaring(int n) {
		//{1,1}{1,0} 행렬을 n제곱하면 {f(n+1),f(n)}{f(n),f(n-1)}값이 나오는것을
		//이용하여 구현
		if(n < 2)
			return n;
		else {
			long arr[][] = new long[2][2];
			arr[0][0] = 1; arr[0][1] = 1; 
			arr[1][0] = 1; arr[1][1] = 0;
			return pow(arr,n)[0][1];

		}
	}
	private static long arr(int n) {
		//이전 2개의 배열 값을 이용하여 계산
		long arr[] = new long[3];
		arr[0] = 0; arr[1] = 1;
		if(n < 2) {
			return arr[n];
		}
		else {
			for(int i=2;i<=n;i++) {
				arr[2] = arr[0]+arr[1];
		        arr[0] = arr[1];
		        arr[1] = arr[2];
			}
		}
		return arr[2];
	}
	public static void main(String[] args) {
		input = new Scanner(System.in);
		int a,n;
		System.out.println("1. recursive");
		System.out.println("2. bottom-up");
		System.out.println("3. recursive squaring");
		a = input.nextInt();
		System.out.println("n값을 입력하시오.");
		n = input.nextInt();
		if(a == 1) {
			for(int i=0;i<=n;i++) {
				long start = System.currentTimeMillis();
				System.out.printf("f<%2d> = %12d   ",i,(fibonacci(i)));
				long end = System.currentTimeMillis();
				long time = (end - start);
				System.out.printf("%18f sec\n",(float)time/1000);				
			}
		}
		else if(a == 2) {
			for(int i=0;i<=n;i++) {
				long start = System.currentTimeMillis();
				System.out.printf("f<%2d> = %12d   ",i,(arr(i)));
				long end = System.currentTimeMillis();
				long time = (end - start);
				System.out.printf("%18f sec\n",(float)time/1000);				
			}	
		}
		else if(a == 3) {
			for(int i=0;i<=n;i++) {
				long start = System.currentTimeMillis();
				System.out.printf("f<%2d> = %12d   ",i,(RecursiveSquaring(i)));
				long end = System.currentTimeMillis();
				long time = (end - start);
				System.out.printf("%18f sec\n",(float)time/1000);				
			}
		}

	}
	

}
