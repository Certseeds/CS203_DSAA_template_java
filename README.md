<!--
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-15 23:52:04
 * @LastEditors: nanoseeds
 * @LastEditTime: 2021-08-15 18:02:58
 * @License: CC-BY-NC-SA_V4_0 or any later version 
 -->
# CS203_DSAA_template_Java  
# CS203_数据结构与算法分析_代码模板_Java

1. 所有代码(*.cpp,*.hpp,etc)基于MIT协议:限制最少的主流开源协议,
    + 在署名的前提下,
    + 允许他人以任何方式使用,  
    + 同时原作者不承担任何风险.
    + 具体内容请看[`LICENSE_MIT.md`](./LICENSE_MIT.md)

2. 所有其他文件(主要是*.md)基于CC-BY-NC-SA-4.0(或以后版本)协议.
    + 相同方式共享-署名-非商业性使用的知识共享协议4.0或任何以后版本.
    + 署名(BY)-使用到相应内容的其他地方,应该加以注释,保留来源.
    + 非商业性使用(NC)-默认情况下,只要署名,可以在不盈利的情况下使用.(并不是指商业情况不能用,而是需要和原作者沟通)
    + 相同方式共享(SA)-使得协议具有传染性,只要其他内容采用了本repo的内容,就需要在署名的同时,保证其协议也是CC-BY-NC-SA-4.0 or later version.
    + 具体内容请看[`LICENSE_CC_BY_NC_SA_V4_0.md`](./LICENSE_CC_BY_NC_SA_V4_0.md)

3. 本repo的目的:提供一个
    + 较为简单,
    + 开箱即用,
    + 带有测试,
    + 针对CS203_dsaa的lab设计的,基于Java11的代码模板仓库,方便同学们进行课程的学习.
    + 同时会提供一些基本的算法框架.

### 如何使用本repo:

+ 首先请到[release_latest](https://github.com/Certseeds/CS203_DSAA_template_java/releases/latest)下载script_no_need.zip.
+ 将其解压,放到某处.
+ 执行代码和测试:
  + 使用IDEA打开文件夹,配置好Java环境与Maven的基础上,会自动识别`pom.xml`.之后可选择test或源文件进行执行.
  + `lab_${lab_number}/lab_${lab_number}_${ques_number}/src/Main.java`为将要提交的源文件.
  + `lab_${lab_number}/lab_${lab_number}_${ques_number}/test/MainTest.java`为对源文件进行测试的测试文件，目的为方便测试,同时便于分享测试用例.

### 实际场景:

1. A+B: lab_00_A ,测试样例

+ 这个问题较为简单,见[A+B](./lab_welcome/lab_welcome_a/src/Main.java).解决起来不复杂.
+ 虽然手工一个一个输入,然后肉眼观察输出.但是如果我们希望严谨的测试,要100组测试数据,难道每次出新版本都要手动输入100次?</br>
显然,有更好的解决方式:使用测试框架.
+ 在本repo,使用`Junit`测试框架.</br>
比如,我们有四组数据,第一组,第二组测试边界值,第三组使用随机数测试对偶性与正确性,第四组测试几个手动的随机值.</br>
参见[test_for_lab00_A](./lab_welcome/lab_welcome_a/test/MainTest.java).
+ 这样一来,我们只需要每次修改完主文件之后,run `lab_welcome_a/test/MainTest.java`, 对其进行调用,就能验证其在所有的测试用例上的正确性.</br>
测试的结果也会出现在输出中.

2. 文件输入输出重定向 part1:

+ 常见于tree,graph类的问题,debug需要的数据集都比较大,不方便直接写在代码中.
+ 比如[判断二分图](./lab_welcome/lab_welcome_c/src/Main.java),一张图可以有几十上百个node,写在内部占用空间太大.
+ 而在这里,使用`Redirect`对象,便可以省去手动输入的方式.

  ``` java
      Redirect redirect = new Redirect(DATA_PATH); // 设定目录，DATA_PATH在文件里有定义
      redirect.set_path("01.data.in", "01.test.out"); // 重定向输入
      Main.output(Main.cal_warpper(Main.read())); // 执行
      Pair<String, String> p = redirect.compare_double("01.data.out", "01.test.out"); // 获取两个文件中的字符串
      assertEquals(p.getFirst().length(), p.getSecond().length()); // 比较长度
      assertEquals(p.getFirst(), p.getSecond()); // 比较文本
  ```

    只需要准备好输入的数据与结果,就可以从文件中读取,执行后判断结果是否符合预期.   
  + test_1 为最简单的逐个判断,最简单,代码量最大.
  + test_2 则优化了一些,但是还是比较麻烦,for循环还需要了解测试样例的个数.
  + test_3 with tuple 则最优雅,修改起来的难度最小.
  + PS: 此处注意,引用文件的相对路径.
  + PS2: 模版文件中已经将前面`resources/`预制好，只需要填写文件名.

3. 文本输入输出重定向 part2:

+ 一般来说,题目的输出不会太复杂,但是反例也不是没有.:比如专门考输出的[立体图](./lab_welcome/lab_welcome_d/src/Main.java)
+ 这种情况下,使用c++的重定向输出就可以较为方便的对输入进行处理,同时保存输出方便调试.
  ``` java
      redirect.set_path("01.data.in", "01.test.out");
      Main.main(init_String);
      Pair<String, String> p = redirect.compare_double("01.data.out", "01.test.out");
      assertEquals(p.getFirst().length(), p.getSecond().length());
      assertEquals(p.getFirst(), p.getSecond());
  ```
  这样就将标准输出重定向到了01.test.out中,并与01.data.out比对.
  + 这里需要考虑的是，谨慎使用println(),因为println()的输出与平台有关，print("\n")比较方便和data.out比较.

4. 快读.

+ 一般来说,题目不会卡读入.
+ 但是,当数据量上来之后,读取时间不容小看.
+ 所以可以使用每个文件中自带的Reader类来进行快读.

### 为什么要将 `读取` `数据处理` `输出` 分开?

+ 便于理清思路,读完题目之后,不管别的,先把数据读入,输出的函数写好,方便后续写作.
+ 交流代码逻辑的时候不会受到无关逻辑的影响
+ 可以互相分享少量代码而不触及核心逻辑,方便协作.
+ 便于使用测试.
+ 便于使用替换快读与scanner.

### 为什么要选择Java做题

1. SUSTech大一默认用java教学.
2. java对字符串处理有一些预置方法，挺好用。
3. 没了，欢迎补充.

### <del>为什么要选择C++做题.</del>

1. C++是dalao们的选择
直接去
[dalao1](https://acm.sustech.edu.cn/onlinejudge/status.php?user_id=11710724&jresult=4)
[dalao2](https://acm.sustech.edu.cn/onlinejudge/status.php?user_id=11612908&jresult=4)
[dalao3](https://acm.sustech.edu.cn/onlinejudge/status.php?user_id=11712510&jresult=4)
等等dalao的解题页面看看,会发现在排行榜榜首的人,绝大多数题目使用的都是C++.

2. 速度.

+ oj内一般java的最大运行时间都会是c++的2倍,显然是暗示速度之间的差别.
+ 其次,C++可以通过一些魔法操作,比如下文的优化等操作再获取一些时间上的优势.

#### 对数据结构的友好性

DSAA既然内含Data structure,就势必涉及到类似Node,Tree,Graph等等数据结构,这类数据结构使用C++写,比较方便理解.

#### 对算法友好的性能

作者写树和图相关的题目时,最头疼的就是Java的爆栈,有一段时间只要用递归就爆栈,相同算法修改为C++之后问题就消失了.

#### 相关资源的丰富程度

不管怎么说,c++是dalao的选择,所以在网络上搜索题目,得到的大多数答案都是C/C++,java的数量很少.

#### 最后

C++的CS203库:[CS203_DSAA_template](https://github.com/Certseeds/CS203_DSAA_template)

TODO: 介绍使用Cyaron 生成数据.

### 可能遇到的问题

1. 提示找不到Jar包.
  请安装maven作为依赖管理的工具.然后在IDEA中`文件`-`打开`-选择`CS203_DSAA_template_java\pom.xml`-确定-作为项目打开.
2. maven下载包很慢
  需要给maven 换源 (`pom.xml`中已有换源操作)

[![MIT](https://img.shields.io/badge/License-MIT-orange)][MIT_Link]

[![CC BY-NC-SA 4.0](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-orange)][cc_by_nc_sa_4_0]

[![CC BY-SA 4.0][cc_by_nc_sa_4_0_image]][cc_by_nc_sa_4_0]

[MIT_Link]: http://opensource.org/licenses/MIT

[cc_by_nc_sa_4_0]: https://creativecommons.org/licenses/by-nc-sa/4.0/

[cc_by_nc_sa_4_0_image]: https://licensebuttons.net/l/by-nc-sa/4.0/88x31.png
