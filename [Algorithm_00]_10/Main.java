
public class Main {

	public static void main(String[] args) {
		int k = 4;
		char Cache[] = { 'A', 'B', 'C', 'D' };
		char Requests[] = {'C','A','F','G','G','D','A','B','F','D'};
		int cnt = 0;
		for (int i = 0; i <= k; i++) {
			cnt = 0;
			//Request�� Cache�� �ִ��� Ȯ���ϴ� ���� 1�̸� �ְ�, 0�̸� ����.
			for (int j = 0; j <= k - 1; j++) {
				if (Requests[i] == Cache[j]) {
					//Request�� Cache�� �ִ��� Ȯ��
					cnt++;
					break;
				}
			}
			if (cnt == 0) {
				//Request�� Cache�� ���� ���
				int max = 0, count = 0, a,b;
				char evcit;
				for (int j = 0; j <= k - 1; j++) {
					a = 0;
					//Cache�� ���� Requests�� �ִ��� Ȯ���ϴ� ���� 1�̸� �ְ�, 0�̸� ����.
					for (int l = i+1; l <= Requests.length - 1; l++) {
						if (Cache[j] == Requests[l]) {
							//Cache�� �ִ� ���� ������ ���� ȣ��Ǵ��� Ȯ��
							a++;
							b = 0;
							/*Cache�� �ִ� ���� ������ ���� ȣ��Ǵ��� Ȯ���ϴ� ��������
							���� ���� ���ϴ� Requests �������� �ִٸ� �����ִ� ���� ���ϱ� ���� 
							���� ���� �ִ��� Ȯ���ϴ� ����*/
							for(int o = l-1;o>= i+1;o--) {
								if(Requests[o] == Requests[l])
									//Request ���ϴ� ���� ���� ���� ���� �ִ� ���
									b++;
							}
							if (l > max && b == 0) {
								//���� �� �Ÿ��� �ִ� Cache�� ��ġ�� ����
								max = l;
								count = j;
							}
						}
					}
					if (a == 0) {
						//Cache�� �ִ� ���� Requests�� ���� ���
						count = j;
						break;
					}
				}
				evcit = Cache[count];
				Cache[count] = Requests[i];
				System.out.printf("Schedule  %d\n", i);
				System.out.printf("Cache = ");
				for (int j = 0; j <= Cache.length - 1; j++) {
					System.out.print(Cache[j]);
				}
				System.out.printf("\nEvict = %c \n", evcit);

			} else {
				// Request�� Cache�� �ִ� ���
				System.out.printf("Schedule  %d\n", i);
				System.out.printf("Cache = ");
				for (int j = 0; j <= Cache.length - 1; j++) {
					System.out.print(Cache[j]);
				}
				System.out.println();
			}
		}

	}

}

