package lab_00;

import java.io.IOException;

import include.Reader;
import include.Reader_2;
import include.Reader_3;

public class lab_00_A {
    public static int[] read() throws IOException {
        int will_return[] = new int[2];
        Reader.init(System.in);
        will_return[0] = Reader.nextInt();
        will_return[1] = Reader.nextInt();
        return will_return;
    }
    public static int[] read2() throws IOException {
        int will_return[] = new int[2];
        Reader_2 Reader = new Reader_2();
        will_return[0] = Reader.nextInt();
        will_return[1] = Reader.nextInt();
        return will_return;
    }

    public static int[] read3() throws IOException {
        int will_return[] = new int[2];
        Reader_3.init(System.in);
        will_return[0] = Reader_3.nextInt();
        will_return[1] = Reader_3.nextInt();
        return will_return;
    }

    public static int cal(int[] nums) {
        assert (nums.length == 2);
        return nums[0] + nums[1];
    }

    public static void main(String[] args) throws IOException {
        int datas[] = read2();
        int result = cal(datas);
        output(result);
    }

    public static void output(int number) {
        System.out.println(number);
    }

}

/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_templalte_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-26 21:48:48
 * @LastEditors: nanoseeds
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_templalte_java

Copyright (C) 2020  nanoseeds

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
