# 2020NaverRecruitment_1_02

## 2020 Naver Recruitment  온라인 코딩테스트 (9/22) 02번 문제

> 문제 출처: 네이버

> 코딩테스트 플랫폼: https://programmers.co.kr/

### 1. 문제설명

input으로 int n 이 들어온다. 연속하는 자연수 두 개 이상을 곱해서 만들 수 있는 자연수를 크기순으로 나열한 수열에서 n번째 숫자를 return하는 문제.

* 수열: 2 6 12 20 24 30 42 56 50 72 ...

* 제한사항
     >  n은 1이상 1,000,000 이하의 자연수, 정답은 10^12 이하의 경우만 주어진다

### 2. 풀이

이중for문으로 연속하는 자연수 두 개 이상을 곱해서 만들 수 있는 자연수를 생성하여 수열을 만든다. 중복된 숫자가 만들어 질 수 있으므로 생성된 숫자는 `HashSet<Integer>`에 저장한다.

```java
for (int i = 1; i < Math.sqrt(MAX); i++) {
  long num = i;
  for (int j = i + 1; j < Math.sqrt(MAX); j++) {
    num *= j;
    if (num > MAX)
      break;

    set.add(num);
  }
}
```

i를 `1`부터 `Math.sqrt(MAX) - 1`까지 반복한 이유는 i가 `Math.sqrt(MAX)` 이후로는 `Math.sqrt(MAX) * (Math.sqrt(MAX) + 1)` 가 `MAX`값을 초과하기 때문이다.

이렇게 생성된 set을 오름차순으로 정렬하기위해 `PriorityQueue<Integer>`를 이용한다. 이후 n번째 요소를 꺼내 return하여 해결.

### 3. 개선 가능한 점

위 알고리즘은 i를 `Math.sqrt(MAX)`까지 돌리며 수열을 생성한다. 하지만 n값이 작다면 꼭 `Math.sqrt(MAX)`까지 루프를 돌지 않아도 이전에 만들어진 수열만으로 정답이 나온다.

`n=1 i=1, 1*2, 1*2*3, 1*2*3*4, ..., return 1*2`

`n=2 i=2, 2*3, 2*3*4, 2*3*4*5, ..., return 1*2*3`

`n=3 i=3, 3*4, 3*4*5, 3*4*5*6, ..., return 1*2*3*4`

n번째 수를 찾으려한다면 i를 n까지만 반복하며 찾아도 충분히 n번째의 수가 만들어 진다는 것을 알 수 있다.

`i=n, n*(n+1), ...` 가 생성되고 이때 수열의 n번째 값은 i=n에서 만든 에 포함되지 않는다. 

```java
// i의 lastValue: (Math.sqrt(MAX)) => (n)
for (int i = 1; i <= n; i++) {
  long num = i;
  // j의 Condition: (j < Math.sqrt(MAX)) => (j <= Math.sqrt(MAX))
  for (int j = i + 1; j <= Math.sqrt(MAX); j++) {
    num *= j;
    if (num > MAX)
      break;

    set.add(num);
  }
}
```

