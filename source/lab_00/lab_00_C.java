package lab_00;

import include.Reader;

import java.io.IOException;
import java.util.Scanner;

public class lab_00_C {
    private enum COLOR {
        uncolor, red, black
    }

    public static long[] read() {
        Scanner input = new Scanner(System.in);
        int test_number = 0;
        test_number = input.nextInt();
        long[] will_return = new long[test_number];
        for (int i = 0; i < will_return.length; i++) {
            will_return[i] = input.nextInt();
        }
        return will_return;
    }

    public static long[] reader() throws IOException {
        Reader input = new Reader();
        int test_number = input.nextInt();
        long[] will_return = new long[test_number];
        for (int i = 0; i < will_return.length; i++) {
            will_return[i] = input.nextInt();
        }
        return will_return;
    }

}
/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-28 11:07:58
 * @LastEditors: nanoseeds
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_template_java 

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
