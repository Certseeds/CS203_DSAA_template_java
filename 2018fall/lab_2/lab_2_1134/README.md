---
SPDX-License-Identifier: CC-BY-NC-SA-4.0
---

# lab_2_1134

## 从直观上来看,

1. 在取值最小, 取值最大时, 取值稍微向中间靠拢一些, 结果就会变小.
2. 在取值正好在最适合的值时, 向左或向右都会导致结果变大.

## 从函数的角度来看下

表达式f(p) = ∑ (|p-xi|)^3*w, 每一个都可以认为是一个在实数域上只有xi一个零点, 左右在xi上对称的三次函数.

在函数图像上, 这种情况比较类似于二次函数, 如果都是二次函数的话, 求导之后会发现导函数递增, 会在某点为零, 先为负数再为正数, 说明函数先减后增.

不用这个方式来思考的话, 多个三次函数之和, 在两个函数分别对应的零点之间移动时, 由于右侧的复数个三次函数在下降, 因此总和仍然下降. 直到到达最低点.
