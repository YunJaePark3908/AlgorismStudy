# 알고리즘 공부하면서 배운 kotlin collection 

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
결과 값

exam1 = [당근, 배], [사과, 포도], [체리]

exam2 = [배, 포도, 체리, 체리]

exam3 = [당근, 배, 사과], [배, 사과, 포도], [사과, 포도, 체리]

exam4 = [사과, 배, 당근], [포도, 사과, 배], [체리, 포도, 사과], [체리, 포도], [체리]

### 2. map
 - 조건에 따라 list를 return 

![image](https://user-images.githubusercontent.com/54883589/165867233-edc80013-6df1-49fb-9a5a-96a5cc76d84c.png)

![image](https://user-images.githubusercontent.com/54883589/165867292-1f6c2e32-bd76-46f4-a960-e2fbb82476e8.png)
