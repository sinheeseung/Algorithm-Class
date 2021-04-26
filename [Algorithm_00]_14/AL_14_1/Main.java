import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Main {

   static int N;
private static BufferedReader bufReader;

   static void stableMatch(String prefer[][]) {
      int wPartner[] = new int[N];
      //������ ��Ʈ�ʸ� �����ϴ� �迭
      boolean mFree[] = new boolean[N];
      //���ڰ� ��Ʈ�ʰ� �ִ��� ������ �迭
      Arrays.fill(wPartner, -1);
      //�ʱ�ȭ��Ŵ
      int count = N;
      //��Ʈ�ʰ� ���� ������ ��
      while (count > 0) {
    	  //��Ʈ�ʰ� ���� ���ڰ� �ִ� ����
         int m;
         for (m = 0; m < N; m++)
            if (mFree[m] == false)
               // M��° ���ڰ� ��Ī���� ���� ���
               break;
         for (int i = 1; i <= N && mFree[m] == false; i++) {
            int w = Integer.parseInt(prefer[m][i].substring(prefer[m][i].length() - 1));
            // m��° ������ i��° ��ȣ ���� ����
            if (wPartner[w-1] == -1) {
               // ���ڰ� ��Ī�Ǿ����� ���� ���
               wPartner[w-1] = m;
               mFree[m] = true;
               count--;
            } else {
               int prefer1 = 0, prefer2 = 0;
               // ��Ī�Ǿ��ִ� ���
               int m1 = wPartner[w-1];
               // m1 = ���ڿ��� ��Ī�Ǿ��ִ� ����
               for (int j = 1; j <= N; j++) {
                  if (Integer.parseInt(prefer[w+N-1][j].substring(prefer[w+N-1][j].length() - 1)) == m1+1)
                	  //�̹� ��Ī�Ǿ� �ִ� ���ڿ� ���� ������ �켱����
                     prefer1 = j;
                  else if (Integer.parseInt(prefer[w+N-1][j].substring(prefer[w+N-1][j].length() - 1)) == m+1)
                	  //���� ��Ī�� ���ϴ� ���ڿ� ���� ������ �켱����
                     prefer2 = j;
               }
               if (prefer2 < prefer1) {
            	   //���� ���ڿ� ���� ������ �켱������ �� �������
                  wPartner[w-1] = m;
                  mFree[m] = true;
                  mFree[m1] = false;
               }
            }
         }
      }
      System.out.println("Man Woman");
      for (int i = 0; i < N; i++) {
         System.out.print(" ");
         System.out.println( wPartner[i]+1  + "     " +(i+1));
      }
   }

   // Driver Code
   public static void main(String[] args) {
      String prefer[][] = new String[10][10];
      int i=0,j=0;
      try {
         File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data14.txt");
         FileReader filereader = new FileReader(file);
         bufReader = new BufferedReader(filereader);
         String line;
         i=0; 
         while ((line = bufReader.readLine()) != null) {
            j=0;
            StringTokenizer parser = new StringTokenizer(line, " ");
            while (parser.hasMoreTokens()) {
               String word = parser.nextToken();
               prefer[i][j] = word;
               j++;
            }
            i++;
         }
      } catch (IOException e) {
         System.out.println(e);
      }
      N = j-1;
      //������ �� == ������ ��
      stableMatch(prefer);
   }
}