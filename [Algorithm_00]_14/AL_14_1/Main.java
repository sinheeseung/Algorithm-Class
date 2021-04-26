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
      //여성의 파트너를 저장하는 배열
      boolean mFree[] = new boolean[N];
      //남자가 파트너가 있는지 저장한 배열
      Arrays.fill(wPartner, -1);
      //초기화시킴
      int count = N;
      //파트너가 없는 남자의 수
      while (count > 0) {
    	  //파트너가 없는 남자가 있는 동안
         int m;
         for (m = 0; m < N; m++)
            if (mFree[m] == false)
               // M번째 남자가 매칭되지 않은 경우
               break;
         for (int i = 1; i <= N && mFree[m] == false; i++) {
            int w = Integer.parseInt(prefer[m][i].substring(prefer[m][i].length() - 1));
            // m번째 남자의 i번째 선호 순위 여자
            if (wPartner[w-1] == -1) {
               // 여자가 매칭되어있지 않은 경우
               wPartner[w-1] = m;
               mFree[m] = true;
               count--;
            } else {
               int prefer1 = 0, prefer2 = 0;
               // 매칭되어있는 경우
               int m1 = wPartner[w-1];
               // m1 = 여자에게 매칭되어있는 남자
               for (int j = 1; j <= N; j++) {
                  if (Integer.parseInt(prefer[w+N-1][j].substring(prefer[w+N-1][j].length() - 1)) == m1+1)
                	  //이미 매칭되어 있는 남자에 대한 여자의 우선순위
                     prefer1 = j;
                  else if (Integer.parseInt(prefer[w+N-1][j].substring(prefer[w+N-1][j].length() - 1)) == m+1)
                	  //현재 매칭을 원하는 남자에 대한 여자의 우선순위
                     prefer2 = j;
               }
               if (prefer2 < prefer1) {
            	   //현재 남자에 대한 여자의 우선순위가 더 높은경우
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
         File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data14.txt");
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
      //남성의 수 == 여성의 수
      stableMatch(prefer);
   }
}