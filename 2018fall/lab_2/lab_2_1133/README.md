---
SPDX-License-Identifier: CC-BY-NC-SA-4.0
---

# lab_2_1133

0. 注意输入, 用了`hasNext()`之后, 直接调试会卡住, 需要从文件读入才行, 建议使用MainTest来调试.
1. 此处注意, 输出的第二种情况, 要求输出的是第一个不可行的index, 不是全部后续index

## 解法1

读完题目, 每一个订单对应的是对一个区间做修改，所以我们可以用线段树来维护这个区间的修改。

我们需要的操作有
1. 更新某个位置上的值, 用于初始化
2. 更新一个区间的所有值, 用于每一个操作
3. 获取一个范围的最小值， 用于判断到最后是否可行.

把从sj到tj天订dj个房间, 翻译成: 给sj到tj的区间内, 每个值都减少dj.
之后每次检查区间最小值是否小于0,小于0则不可行.

## 解法2

二分+前缀和, 二分的是最后一个订单的index, 然后用前缀和来判断是否可行.