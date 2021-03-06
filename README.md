# 알고리즘 공부하면서 배운 kotlin collection 및 util function

## Collection

### 1. chunked
``` kotlin
val food = listOf("당근", "배", "사과", "포도", "체리")
val exam1 = food.chunked(2)
val exam2 = food.chunked(2) { it.last() }
val exam3 = food.windowed(3)
val exam4 = food.windowed(3, partialWindows = true) {
    it.reversed()
}
```
 - 결과 값  
exam1 = [당근, 배], [사과, 포도], [체리]  
exam2 = [배, 포도, 체리, 체리]  
exam3 = [당근, 배, 사과], [배, 사과, 포도], [사과, 포도, 체리]  
exam4 = [사과, 배, 당근], [포도, 사과, 배], [체리, 포도, 사과], [체리, 포도], [체리]  

### 2. map
 - 조건에 따라 list를 return 
``` kotlin
val intArray = intArrayOf(1,2,3,4,5)
val mapArray = intArray.map { "${it}번 인덱스" }
```
 - 결과 값  
mapArray = [1번 인덱스, 2번 인덱스, 3번 인덱스, 4번 인덱스, 5번 인덱스]

### 3. groupBy
``` kotlin
val words = listOf("a", "abc", "ab", "def", "abcd")
val byLength = words.groupBy { it.length }
```
 - 결과 값  
byLength.keys = [1, 3, 2, 4]  
byLength.values = [[a], [abc, def], [ab], [abcd]]

### 4. flatMap
``` kotlin
val strings = listOf("abc", "def")
println(strings.flatMap { it.toList() })
```
 - 결과 값  
 list("a", "b", "c", "d", "e", "f")

### 5. contentDeepToString 
``` kotlin
val matrix = arrayOf(
    intArrayOf(3, 7, 9),
    intArrayOf(0, 1, 0),
    intArrayOf(2, 4, 8)
)
println(matrix.contentDeepToString())
```
 - 결과 값  
 [[3, 7, 9], [0, 1, 0], [2, 4, 8]]  

### 6. joinToString
``` kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)
println(numbers.joinToString()) // 1, 2, 3, 4, 5, 6
println(numbers.joinToString(prefix = "[", postfix = "]")) // [1, 2, 3, 4, 5, 6]
println(numbers.joinToString(prefix = "<", postfix = ">", separator = "•")) // <1•2•3•4•5•6>
```
 
## Function
### 1. 소수 판별
``` kotlin
private fun isPrime(n: Int): Boolean {
    if (n == 0 || n == 1) return false
    var i = 2
    while (i * i <= n) { if (n % i++ == 0) return false }
    return true
}
```

### 2. 순열
``` kotlin
private fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el ): List<List<T>> {
    return if(sub.isEmpty()) listOf(fin)
    else sub.flatMap { permutation(el, fin + it, sub - it) }
}
```

### 3. 계승(팩토리얼)
``` kotlin
private tailrec fun factorial(n: Int, acc: Int = 1): Int =
    if (n <= 0) acc else factorial(n-1, n*acc)
```
