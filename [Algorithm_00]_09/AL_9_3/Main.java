
public class Main {
	private static void getMinimumPenalty(String x, String y) {
		int xlen = x.length();
		int ylen = y.length();
		int[][] h = new int[xlen + 1][ylen + 1];
		int r1 = 1, r2 = -1, r3 = -2;
		// r1 = ���ڰ� �������
		// r2 = ���ڰ� �ٸ����
		// r3 = ������ �ϳ��� �����ΰ��
		for (int j = 0; j < ylen + 1; j++) {
			h[0][j] = r3 * j;
		}
		for (int i = 0; i < xlen + 1; i++) {
			h[i][0] = r3 * i;
		}

		for (int i = 1; i < xlen + 1; i++) {
			for (int j = 1; j < ylen + 1; j++) {
				int s;
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					s = r1;
					// ���ڰ� �������
				} else {
					s = r2;
					// ���ڰ� �ٸ����
				}
				h[i][j] = Math.max(h[i - 1][j] + r3, Math.max(h[i - 1][j - 1] + s, h[i][j - 1] + r3));
			}
		}
		System.out.println("score matrix:");
		for (int i = 0; i < xlen + 1; i++) {
			for (int j = 0; j < ylen + 1; j++) {
				System.out.printf("%4d", h[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String gene1 = "acagaagta";
		String gene2 = "actgagttaa";
		getMinimumPenalty(gene1, gene2);
	}
}
