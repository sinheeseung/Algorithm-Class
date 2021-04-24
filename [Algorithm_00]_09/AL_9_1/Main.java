
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {9,1,3,7,5,6,10};
		int[] LIS = new int[9];
		int max = 0;
		int cnt=0;
		// 저장해야 하는 배열
		for (int i = 0; i < A.length; i++)
			LIS[i] = 1;
		for (int i = 0; i < A.length; i++) {
			int partial_max = 1;
			// 처음 비교를 해야하는 값
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i]) {
					// A[i]를 최대값으로 가지는 증가 수열의 길이의 최대값을 구함
					partial_max = Math.max(partial_max, LIS[j] + 1);
				}
				LIS[i] = partial_max;
				if(partial_max > max) {
					max = partial_max;
					//가장 긴 증가수열의 갯수
					cnt = i;
					//가장 긴 증가수열의 최댓값의 인덱스
				}
			}
		}

		int[] k = new int[max];
		//증가수열의 길이가 최고가 되는 수열들의 배열
		k[max-1] = A[cnt];
		//최댓값 배열의 마지막에 저장
		int b = max-1;
		System.out.print("LSA문자열은 : ");
		for(int i = cnt-1; i>=0;i--) {
			if(LIS[i] == b && A[i] < k[b]) {
				//최대 길이의 증가수열에 들어가는 원소를 찾은경우
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

		System.out.printf("\nLSA문자열의 길이는  : %d", max);

	}

}