import java.util.Scanner;
public class Main {
	
	private static Scanner input;
	private static int Powering(int a, int n) {
		if(n == 0)
			//0������ 1
			return 1;
		else if(n == 1) {
			return a;
		}
		else if(n % 2 == 0) {
			//¦���� ���
			return Powering(a,n/2) * Powering(a,n/2);
		}
		else
			//Ȧ���� ���
			return Powering(a,(n-1)/2) * Powering(a,(n-1)/2) * a;

	}
	public static void main(String[] args) {
		input = new Scanner(System.in);
		int a,b;
		System.out.print("������ ���ڸ� �Է��ϼ��� : ");
		a = input.nextInt();
		System.out.print("������ �Է��ϼ��� : ");
		b = input.nextInt();
		System.out.println(Powering(a,b));
	}

}

