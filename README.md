# Algorithms, Part 1 by 프린스턴 대학교
## 이 강좌에 대하여
This course covers the essential information that every serious programmer needs to know about algorithms and data structures, with emphasis on applications and scientific performance analysis of Java implementations. Part I covers elementary data structures, sorting, and searching algorithms. Part II focuses on graph- and string-processing algorithms.
## 강사
- Kevin Wayne, Phillip Y. Goldman '86 Senior Lecturer
- Robert Sedgewick, William O. Baker *39 Professor of Computer Science
## Week 1
### Union-Find
#### 1. dynamic connectivity 

is connected to는 Reflexive 하고 Symmetric, Transitive 하다. 편의에 따라 객체의 이름을 0 ~ N-1로 하는 것은 좋지 않다. Union-Find를 사용하는 것이 좋다.

#### 2. quick find

배열 id[]를 만들어 연결되어 있는 객체는 같은 아이디로 설정한다. 
Quick-find는 매우 느리다. 

|algorithm|initialize|union|find|
|:---:|:---:|:---:|:---:|
|quick-find|N|N|1|
N개의 객체에 N개의 Union 명령을 실행하는데 N^2(quadratic)의 시간이 든다. 
#### 3. quick union

트리 형태로 구성해 root를 만든다. 연결되어 있으면 같은 root를 가르키는 형태.

|algorithm|initialize|union|find|
|:---:|:---:|:---:|:---:|
|quick-find	|N	|N	|1|
|quick-union|	N|	N|	N|
Quick-union 또한 느리다. 

**Quick-find의 결함**
- Union이 오래 걸린다. (N 배열을 접근)
- Tree가 flat한데 flat하는데 드는 시간이 크다.

**Quick-union의 결함**
- 트리가 커질 수 있다.
- Find가 오래 걸린다. 
#### 4. improvements
Weighted quick-union
- quick-union의 트리가 커지는 것을 방지한다.

|algorithm|	initialize|	union|	connected|
|:---:|:---:|:---:|:---:|
|quick-find|	N|	N|	1|
|quick-union|	N|	N	|N|
|weighted QU|	N	|lg N	|lg N|

#### 5. applications
우리가 공부한 Weighted QU은 다양한 분야에서 사용된다. 이를 통해, 우리는 과학에서 유명한Percolation 문제를 풀어볼 수 있다.
- Programming Assignment: Percolation

### Analysis of Algorithms

#### 1. introduction
Algorithm을 비교하는 방법
Scientific method
	Observation

#### 2. observations
Experimental algorithmics
Good. Easier and cheaper than other sciences.
Bad. Difficult to get precise measurement.
#### 3. mathematical models
Alan Turing이 말하길 가장 큰 연산만 고려하면 된다고 하였다.
#### 4. order-of-growth classifications
![](https://4.bp.blogspot.com/-lSgfMcSfxPk/VfBecnA7ntI/AAAAAAAAATI/ozjV6p9vuHo/s1600/orderofgrowth.png)
#### 5. theory of algorithms
Best case. Lower bound on cost.

Worst case. Upper bound on cost.

Average case. “Expected” cost.

|notation|	provides|	example|	used to|
|:---:|:---:|:---:|:---:|
|Tilde|	leading term|	~$10N^2$	|provide approximate model|
|Big Theta	|asymptotic order of growth|	$\Theta(N^2)$|	classify algorithms|
|Big Oh|	$\Theta(N^2)$ and smaller|	$O(N^2)$|	develop upper bounds|
|Big Omega|	$\Theta(N^2)$ and larger|	$\Omega(N^2)$	|develop lower bounds|
#### 6. memory
Total memory usage for a data type value:
- Primitive type: 4 bytes for int, 8 bytes for double, …
- Object reference: 8 bytes.
- Array: 24 bytes + memory for each array entry.
- Object: 16 bytes + memory for each instance variable + 8 bytes if inner class (for pointer to enclosing class).
- Padding: round up to multiple of 8 bytes.

Shallow memory usage: Don't count referenced objects.

Deep memory usage: If array entry or instance variable is a reference,
add memory (recursively) for referenced object.
#### 7. summary
Empirical analysis.
- Execute program to perform experiments.
- Assume power law and formulate a hypothesis for running time.
- Model enables us to make predictions.

Mathematical analysis.
- Analyze algorithm to count frequency of operations.
- Use tilde notation to simplify analysis.
- Model enables us to explain behavior.

Scientific method.
- Mathematical model is independent of a particular system;
applies to machines not yet built.
- Empirical analysis is necessary to validate mathematical models
and to make predictions.
## Week2
### Stacks and Queues
#### 1. stacks
API. Stack of string data type

|public class StackOfStrings| | |
|---:|---|---|
||StackOfStrings() |create an empty stack
|void	|push(String item)	|insert a new string onto stack|
|String	|pop()	|remove and return the string most recently added|
|Boolean	|isEmpty()	|is the stack empty?|
|int	|size()	|number of strings on the stack|

**Linked-list**
~~~
private class Node	
{	
    String item;	
    Node next;	
}	
~~~        
      16 bytes (object overhead)
      8 bytes (inner class extra overhead)
      8 bytes (reference to String)
      8 bytes (reference to Node)
    +___________________________________
      40 bytes per stack node

~$40N$bytes 

**Array**

Stack overflows when $N$ exceeds capacity.
#### 2. resizing arrays
If array is full, create a new array of twice the size

If we want to shrink array, it’s too expensive. we shrink the array, when 
array is one-half full.

**Linked-list implementation.**
- Every operation takes constant time in the worst case.
- Uses extra time and space to deal with the links.

**Resizing-array implementation.**
- Every operation takes constant amortized time.
- Less wasted space.
#### 3. queues
Queue API
public class QueueOfStrings|||
------------:|:---:|---
||QueueOfString|	*create an empty queue*
void	|enqueue(String item)|	*insert a new string onto queue*
String|	dequeue()	|*remove and return the string least recently added*
Boolean|	isEmpty()|	*is the queue empty?*
int	|size()	|*number of strings on the queue*

#### 4. generics
다양한 타입의 item을 취급하고 싶음.

**방법1.** 타입마다 다른 클래스를 만듬.

**방법2.** object 타입 스택을 만듬.

**방법3.** Java generics.

**Wrapper type.** Each primitive type has a wrapper object type.

**Autoboxing.** Automatic cast between a primitive type and its wrapper.
#### 5. iterators
Iterable: Has a method that returns an Iterator.

Iterator: Has methods ```hasNext()``` and ```next()```.
#### 6. applications
### Elementary Sorts
#### 1. rules of the game
Using Interfaces, I can sort any type of data.

Total order
- Antisymmetry: if v ≤ w and w ≤ v, then v = w.
- Transitivity: if v ≤ w and w ≤ x, then v ≤ x.
- Totality: either v ≤ w or w ≤ v or both
#### 2. selection sort
**logic.**
- In iteration $i$, find index $min$ of smallest remaining entry.
- Swap $a[i]$ and $a[min]$.

**Running time insensitive to input.** Quadratic time, even if input is sorted.

**Data movement is minimal.** Linear number of exchanges.
#### 3. insertion sort
**logic.**
- In iteration $i$, swap $a[i]$ with each larger entry to its left.

**Best case.** If the array is in ascending order, insertion sort makes $N- 1$ compares and 0 exchanges.

**Worst case.** If the array is in descending order (and no duplicates), insertion sort makes ~ ${1\over2}N^2$ compares and ~ ${1\over2}N^2$ exchanges.

**<u>If array is partially-sorted, insertion sort runs in linear time.</u>**
#### 4. shellsort
Idea from *h*-sorting

decrease values of *h*.

using Insertion sort, with stride length *h*.

**<u>A *g*-sorted array remains *g*-sorted after *h*-sorting it.</u>**
#### 5. shuffling
shuffling is important. 

**knuth suffle**
- In iteration $i$, pick integer $r$ between $0$ and $i$ uniformly at random.
- Swap $a[i]$ and $a[r]$.
#### 6. convex hull
The convex hull of a set of N points is the smallest perimeter fence

enclosing the points.

**CCW.** Given three points a, b, and c, is a → b→ c a counterclockwise turn?
- Determinant (or cross product) gives 2x signed area of planar triangle.
- If signed area > 0, then a→ b→ c is counterclockwise.
- If signed area < 0, then a→ b→ c is clockwise.
- If signed area = 0, then a→ b→ c are collinear. 
## Week3

## Week4

## Week5

## Week6
