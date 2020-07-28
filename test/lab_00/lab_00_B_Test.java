package lab_00;

import include.Pair;
import include.Redirect;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class lab_00_B_Test {

    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "test/lab_00/lab_00_B_data/";
    private static long begin_time;
    private static InputStream System_in;
    private static PrintStream System_out;
    private static Random random;
    private static Redirect redirect;

    @BeforeAll
    public static void init() {
        begin_time = System.currentTimeMillis();
        System_in = System.in;
        System_out = System.out;
        random = new Random();
        redirect = new Redirect(DATA_PATH);
    }

    @Order(0)
    @Test
    public void test_0() {
        for (int i = 0; i < 114; i++) {
            int x = random.nextInt(0x7777);
            assertEquals(lab_00_B.brute_force(x), lab_00_B.cal(x));
        }
    }

    @Order(1)
    @Test
    public void test_2() throws IOException {
        assertEquals(1, lab_00_B.cal(1));

        assertEquals(4, lab_00_B.cal(2));

        assertEquals(38 * 115 * 58, lab_00_B.cal(114));
    }

    @Order(2)
    @Test
    public void test_3() throws IOException {
        for (int i = 1; i <= 3; i++) {
            redirect.set_path(String.format("0%d.data.in", i), String.format("0%d.test.out", i));
            lab_00_B.output(lab_00_B.cal_warpper(lab_00_B.read()));
            Pair<String, String> p = redirect.compare_double(String.format("0%d.data.out", i), String.format("0%d.test.out", i));
            assertEquals(p.getFirst().length(), p.getSecond().length());
            assertEquals(p.getFirst(), p.getSecond());
        }
        for (int i = 1; i <= 3; i++) {
            redirect.set_path(String.format("0%d.data.in", i), String.format("0%d.test.out", i));
            lab_00_B.output(lab_00_B.cal_warpper(lab_00_B.reader()));
            Pair<String, String> p = redirect.compare_double(String.format("0%d.data.out", i), String.format("0%d.test.out", i));
            assertEquals(p.getFirst().length(), p.getSecond().length());
            assertEquals(p.getFirst(), p.getSecond());
        }
    }

    @AfterAll
    public static void last_one() {
        System.setIn(System_in);
        System.setOut(System_out);
        System.out.println(String.format("cost %d ms", System.currentTimeMillis() - begin_time));
    }
}
/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: sunzhongxia
 * @Date: 2020-07-28 12:14:23
 * @LastEditors: sunzhongxia
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_template_java 

Copyright (C) 2020  sunzhongxia

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
