
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {9,1,3,7,5,6,10};
		int[] LIS = new int[9];
		int max = 0;
		int cnt=0;
		// �����ؾ� �ϴ� �迭
		for (int i = 0; i < A.length; i++)
			LIS[i] = 1;
		for (int i = 0; i < A.length; i++) {
			int partial_max = 1;
			// ó�� �񱳸� �ؾ��ϴ� ��
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i]) {
					// A[i]�� �ִ밪���� ������ ���� ������ ������ �ִ밪�� ����
					partial_max = Math.max(partial_max, LIS[j] + 1);
				}
				LIS[i] = partial_max;
				if(partial_max > max) {
					max = partial_max;
					//���� �� ���������� ����
					cnt = i;
					//���� �� ���������� �ִ��� �ε���
				}
			}
		}

		int[] k = new int[max];
		//���������� ���̰� �ְ� �Ǵ� �������� �迭
		k[max-1] = A[cnt];
		//�ִ� �迭�� �������� ����
		int b = max-1;
		System.out.print("LSA���ڿ��� : ");
		for(int i = cnt-1; i>=0;i--) {
			if(LIS[i] == b && A[i] < k[b]) {
				//�ִ� ������ ���������� ���� ���Ҹ� ã�����
				k[b-1] = A[i];
				b--;
			}
			if(b <= 0) break;
		}
		for(int i=0;i<k.length;i++) {
			if(i == k.length-1) {
				System.out.print(k[i]);
			}
			else System.out.print(k[i] + ", ");
		}

		System.out.printf("\nLSA���ڿ��� ���̴�  : %d", max);

	}

}