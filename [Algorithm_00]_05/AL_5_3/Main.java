import java.util.Random;
import java.util.Scanner;
public class Main {
	private static Scanner input;
	static void printPoly(int[] a, int length) {
		for (int z = length-1; z >= 0; z--) {
			if(z < length-1 && a[z] > 0) System.out.print("+");
			if(a[z] != 0) {
				System.out.print(a[z]);
				if (z > 0)
					System.out.print("*X^" + z);
			}
			System.out.println();
		}
	}
	public static int[] polyMult(int[] p, int[] q, int num) {
		if (num == 1) {
			int[] temp = { p[0] * q[0] };
			return temp;
		}
		int mid = num / 2;

		int[] p_Left = new int[mid];
		int[] q_Left = new int[mid];
		int[] p_Right = new int[mid];
		int[] q_Right = new int[mid];
		for (int i = 0; i < mid; i++) {
			p_Left[i] = p[i + mid];
			q_Left[i] = q[i + mid];

			p_Right[i] = p[i];
			q_Right[i] = q[i];
		}
		//n항의 다항식을 n/2로 나눠줌
		int[] RightPQ = polyMult(p_Right, q_Right, mid);
		//나눠진 부분 중 차수가 낮은 부분의 곱
        int [ ] RightP_LeftQ = polyMult(p_Right,q_Left,mid);
        int [ ] RightQ_LeftP = polyMult(p_Left,q_Right,mid);
        //나눠진 부분 중 차수가 낮은 부분과 높은 부분의 곱
        int [ ] LeftPQ = polyMult(p_Left,q_Left,mid);
        //나줘진 부분 중 차수가 높은 부분의 곱
        int [ ] pq = new int[(2 * num)-1];        
        
       for (int i = 0; i < num-1; i++) 
        {
            pq[i] += RightPQ[i];
            pq[i+mid] += RightP_LeftQ[i] + RightQ_LeftP[i];
            pq[i+(2*mid)] += LeftPQ[i];
        }
        return pq;
    }
	public static int[] Karatsuba(int[] p, int[] q, int num) {
		if (num == 1) {
			int[] temp = { p[0] * q[0] };
			return temp;
		}
		int mid = num / 2;
		//_Left에는 차수 높은 애들
		int[] p_Left = new int[mid];
		int[] q_Left = new int[mid];
		int[] p_Right = new int[mid];
		int[] q_Right = new int[mid];

		for (int i = 0; i < mid; i++) {
			p_Left[i] = p[i + mid];
			q_Left[i] = q[i + mid];

			p_Right[i] = p[i];
			q_Right[i] = q[i];
		}
		//n항의 다항식을 n/2로 나눠줌
		int[] RightPQ = Karatsuba(p_Right, q_Right, mid);
		//나눠진 부분 중 차수가 낮은 부분의 곱
        int [ ] LeftPQ = Karatsuba(p_Left,q_Left,mid);
        //나눠진 부분 중 차수가 낮은 부분과 높은 부분의 곱
        int[] midPQ = sub(sub(Karatsuba(add(p_Right,p_Left,mid),add(q_Right,q_Left,mid),mid),RightPQ, mid),LeftPQ,mid);
        //나줘진 부분 중 차수가 높은 부분의 곱
        int [ ] pq = new int[(2 * num)-1];        
        
       for (int i = 0; i < num-1; i++) 
        {
            pq[i] += RightPQ[i];
            pq[i+mid] += midPQ[i];
            pq[i+(2*mid)] += LeftPQ[i];
        }        
        return pq;
    }
	private static int[] sub(int[] p, int[] q, int d) {
		int[] arr= new int[p.length];
		for(int i=0;i<d;i++) {
			arr[i] = p[i] - q[i];
		}
		return arr;	
	}
	private static int[] add(int[] p, int[] q, int d) {
		int[] arr= new int[p.length];
		for(int i=0;i<d;i++) {
			arr[i] = p[i] + q[i];
		}
		return arr;	
	}
    public static void main(String[] args) 
    {
        int n = 8;
        int[] p = new int[n];
        int[] q = new int[n];
        int[] pq = new int[(2*n)-1];       
        Random randomGenerator = new Random();
       input = new Scanner(System.in);   

       for(int i=0 ; i<n ; i++)
       {   
           p[i] = randomGenerator.nextInt()%10;           

           q[i] = randomGenerator.nextInt()%10;
       }
       System.out.println("1. Polynimial Multiplication");
       System.out.println("2. Karatsuba");
       int a= input.nextInt();
       System.out.println("------------------------------------");
       System.out.println("first polynomial");
       System.out.println("------------------------------------");
       printPoly(p, p.length);
       
       System.out.println("------------------------------------");
       System.out.println("second polynomial");
       System.out.println("------------------------------------");
       printPoly(q, q.length);
       System.out.println("------------------------------------");
       System.out.println("multiplication");
       if(a == 1)
    	   pq = polyMult(p,q,n);
       else     pq = Karatsuba(p,q,n);
       printPoly(pq, pq.length);
        
    }
}