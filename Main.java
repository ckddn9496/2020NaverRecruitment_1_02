import java.util.HashSet;
import java.util.PriorityQueue;

/* 2020 Naver 개발자 채용 코딩테스트 2번
연속하는 자연수 두 개 이상을 곱해서 만들 수 있는 자연수를 크기 순으로 나열한 수열이 있습니다. 다음은 수열의 예시입니다.

2, 6, 12, 20, 24, 30, 42, 56, 60, 72 ...
예를 들어 2는 두 연속하는 자연수 1과 2를 곱해서 만들 수 있습니다. 마찬가지로 6 = 2 x 3 또는 6 = 1 x 2 x 3이며, 20 = 4 x 5, 60 = 3 x 4 x 5와 같이 연속하는 자연수를 두 개 이상 곱해서 만들 수 있습니다.

자연수 n이 매개변수로 주어질 때, 위 수열에서 n 번째 숫자를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 1 이상 1,000,000 이하인 자연수입니다.
정답이 1012 이하인 경우만 입력으로 주어집니다.
 * */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1; // return 2
//		int n = 2; // return 6
//		int n = 4; // return 20
//		int n = 9; // return 60
		System.out.println((new Solution().solution(n)));
	}

}
class Solution {
	public final static long MAX = (long) Math.pow(10, 12);
	public long solution(int n) {
		HashSet<Long> set = new HashSet<>();
		PriorityQueue<Long> heap = new PriorityQueue<>();
		long answer = 0;
//		for (int i = 1; i < Math.sqrt(MAX); i++) {
		for (int i = 1; i <= n; i++) {
			long num = i;
			for (int j = i + 1; j <= Math.sqrt(MAX); j++) {
				num *= j;
				if (num > MAX)
					break;
				
				set.add(num);
			}
		}
		heap.addAll(set);
		int count = 0;
		while (count < n - 1) {
			heap.poll();
			count ++;
		}
		return heap.poll();
		
	}
}