---
SPDX-License-Identifier: CC-BY-NC-SA-4.0
---

# lab_3_1141

分析一下下面的用例

``` log
3
3 4 0
3 4 1
3 3 1
```

## 3 4 0

注意到`numbered (m + 1)th`,可见是从1开始计数的.

three node, destory 4 times, earty is fst

+ 0 1 2
    + 0 1 2 0 1
    + 1 is destory
    + 2 0
+ 2 0
    + 2 0 2 0 2
    + 2 is destory
    + 0
+ 0
    + 0 0 0 0 0
    + 0 is destory

0 is the last.

编号从0开始, 但是计数1开始记到m+1, 麻烦.

## 3 4 1

过程如上, 1是倒数第二个, 不是.

### 3 3 1

+ 0 1 2
    + 0 1 2 0
    + 0 is destory
    + 1 2
+ 1 2
    + 1 2 1 2
    + 2 is destory
    + 1
+ 1
    + 1 1 1 1 1
    + 1 is destory

这里可以发现, 需要把下一个计数的作为第一个, 才能推理通顺.

PS: 需要注意到, 并不是只进行math.min(n,m)次, 而是准确的进行n次, 每次1个.

## 约瑟夫环问题

这个问题其实是约瑟夫环问题, 数学证明之后可以比较简单的解决.
