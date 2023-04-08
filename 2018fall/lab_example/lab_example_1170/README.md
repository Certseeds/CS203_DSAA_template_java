---
SPDX-License-Identifier: CC-BY-NC-SA-4.0
---

# lab_example_1170: maximum difference

一眼鉴定为需要O(nlogn)或者O(n)

用数学归纳法的想法来思考一下, 如果只有两个数字, 那无可辩驳, maxdiff = arr[0]-arr[1].

此时, 如果多一个数字arr[2]的话, 由于[0,1]已经明确了, 我们可以把0,1打包成一个phantom-number, 然后和arr[2]比较一下.

[0,1]的可能maxdiff是arr[0]-arr[1], 可能maxValue是max(arr[0],arr[1])

因此, [0,1]和2这两个数字之间的maxdiff可以仿照0,1之间的来求, 只不过现在需要和上一个对比一下.

依次类推, 如果有n(>=2)个数字, 那么需要取maxValue(n-2)次, 取maxdiff(n-1)次, 符合O(n)时间复杂度.
    
