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

	private static double dist(Point p1, Point p2) { // �� ��ǥ������ �Ÿ��� ���ϴ� �޼ҵ�
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	private static double bruteForce(List<Point> a, int x, int y) { // ���� Ž������ ���� ����� �Ÿ� ã��
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
			// ��ǥ ���� 3���ϸ� Brute force�� x���� y���� ���� ����� �� �� ������ �Ÿ��� ã�´�.
			return bruteForce(a, x, y);
		}
		int mid = (x + y) / 2;
		double left = closest(a, x, mid);
		double right = closest(a, mid + 1, y);
		double min = Math.min(left, right);
		List<Point> p = new ArrayList<>(); // ���ʰ� ������ ������ ������ ������ List
		for (int i = x; i <= y; i++) {
			//n/2�������� x��ǥ �����κ���  min�̳��� �ִ� ��ǥ�� �и�
			double d = a.get(i).x - a.get(mid).x;
			if (d * d < min) {
				p.add(a.get(i));
			}
		}
		Collections.sort(p, new PointComparator()); // y��ǥ������ ����
		int m = p.size();
		for (int i = 0; i < m - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				double k = p.get(j).y - p.get(i).y;
				if (k * k < min) {
					double d = dist(p.get(i), p.get(j));
					if (d < min) {
						min = d;
					}
				} else { // y��ǥ ������ �����߱� ������ ���� ���� ����� ���� min���� ũ�� �� ���ʿ䰡 ����.
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
			File file = new File("C:\\Users\\shs72\\Desktop\\�����\\2-2\\�˰���\\data05_closest.txt");
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

class PointComparator implements Comparator<Point> { // y��ǥ ������ �����ϱ� ���� Comparator

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
