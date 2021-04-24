
public class Main {

	public static void main(String[] args) {
		int k = 4;
		char Cache[] = { 'A', 'B', 'C', 'D' };
		char Requests[] = {'C','A','F','G','G','D','A','B','F','D'};
		int cnt = 0;
		for (int i = 0; i <= k; i++) {
			cnt = 0;
			//Request가 Cache에 있는지 확인하는 변수 1이면 있고, 0이면 없다.
			for (int j = 0; j <= k - 1; j++) {
				if (Requests[i] == Cache[j]) {
					//Request가 Cache에 있는지 확인
					cnt++;
					break;
				}
			}
			if (cnt == 0) {
				//Request가 Cache에 없는 경우
				int max = 0, count = 0, a,b;
				char evcit;
				for (int j = 0; j <= k - 1; j++) {
					a = 0;
					//Cache의 값이 Requests에 있는지 확인하는 변수 1이면 있고, 0이면 없다.
					for (int l = i+1; l <= Requests.length - 1; l++) {
						if (Cache[j] == Requests[l]) {
							//Cache에 있는 값이 다음에 언제 호출되는지 확인
							a++;
							b = 0;
							/*Cache에 있는 값이 다음에 언제 호출되는지 확인하는 과정에서
							같은 값이 비교하는 Requests 범위내에 있다면 먼저있는 값을 취하기 위해 
							같은 값이 있는지 확인하는 변수*/
							for(int o = l-1;o>= i+1;o--) {
								if(Requests[o] == Requests[l])
									//Request 비교하는 범위 내에 같은 값이 있는 경우
									b++;
							}
							if (l > max && b == 0) {
								//가장 먼 거리에 있는 Cache의 위치를 구함
								max = l;
								count = j;
							}
						}
					}
					if (a == 0) {
						//Cache에 있는 값이 Requests에 없는 경우
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
				// Request가 Cache에 있는 경우
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

