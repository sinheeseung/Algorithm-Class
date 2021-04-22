import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static double dist(Point p1, Point p2) { // 두 좌표사이의 거리를 구하는 메소드
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	private static double bruteForce(List<Point> a, int x, int y) { // 완전 탐색으로 가장 가까운 거리 찾기
		double min = -1;
		for (int i = x; i <= y - 1; i++) {
			for (int j = i + 1; j <= y; j++) {
				double k = dist(a.get(i), a.get(j));
				if (min == -1 || min > k)
					min = k;
			}
		}
		return min;
	}

	private static double closest(List<Point> a, int x, int y) {
		int n = y - x + 1;
		if (n <= 3) { 
			// 좌표 수가 3이하면 Brute force로 x부터 y까지 가장 가까운 두 점 사이의 거리를 찾는다.
			return bruteForce(a, x, y);
		}
		int mid = (x + y) / 2;
		double left = closest(a, x, mid);
		double right = closest(a, mid + 1, y);
		double min = Math.min(left, right);
		List<Point> p = new ArrayList<>(); // 왼쪽과 오른쪽 사이의 점들을 저장할 List
		for (int i = x; i <= y; i++) {
			//n/2지점에서 x좌표 값으로부터  min이내에 있는 좌표만 분리
			double d = a.get(i).x - a.get(mid).x;
			if (d * d < min) {
				p.add(a.get(i));
			}
		}
		Collections.sort(p, new PointComparator()); // y좌표순으로 정렬
		int m = p.size();
		for (int i = 0; i < m - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				double k = p.get(j).y - p.get(i).y;
				if (k * k < min) {
					double d = dist(p.get(i), p.get(j));
					if (d < min) {
						min = d;
					}
				} else { // y좌표 순으로 정렬했기 때문에 앞의 가장 가까운 점이 min보다 크면 더 볼필요가 없다.
					break;
				}
			}
		}
		return Math.sqrt(min);
	}
	private static BufferedReader bufReader;
	public static void main(String[] args) throws IOException {
		// int n = Integer.parseInt(reader.readLine());
		List<Point> a = new ArrayList<>();
		try {
			File file = new File("C:\\Users\\shs72\\Desktop\\신희승\\2-2\\알고리즘\\data05_closest.txt");
			FileReader filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				StringTokenizer parser = new StringTokenizer(line, ",");
				String word = parser.nextToken();
				double num1 = Double.parseDouble(word);
				word = parser.nextToken();
				double num2 = Double.parseDouble(word);
				a.add(new Point(num1, num2));

			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		 System.out.println(closest(a, 0, a.size()-1));
	}

}

class PointComparator implements Comparator<Point> { // y좌표 순으로 정렬하기 위한 Comparator

	@Override
	public int compare(Point p1, Point p2) {
		return (int) (p1.y - p2.y);
	}

}

class Point implements Comparable<Point> {
	double x;
	double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point p) {
		double r = this.x - p.x;
		if (r == 0)
			r = this.y - p.y;
		return (int) r;
	}
}
