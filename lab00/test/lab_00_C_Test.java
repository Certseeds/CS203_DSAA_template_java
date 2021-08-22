import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public final class lab_00_C_Test {
    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "lab00/lab_00_C_data/";
    private static final long begin_time = System.currentTimeMillis();
    private static final Random random = new Random();
    private static Redirect redirect;

    @BeforeAll
    public static void init() {
        redirect = new Redirect(DATA_PATH);
    }

    @AfterAll
    public static void last_one() throws IOException {
        redirect.close();
        System.out.printf("cost %d ms%n", System.currentTimeMillis() - begin_time);
    }

    @Order(1)
    @Test
    public void test_01() throws IOException {
        // 测试
        redirect.set_path("01.data.in");
        assertFalse(lab_00_C.cal(lab_00_C.read()));

        redirect.set_path("02.data.in");
        assertTrue(lab_00_C.cal(lab_00_C.read()));

        redirect.set_path("03.data.in");
        assertTrue(lab_00_C.cal(lab_00_C.read()));

        redirect.set_path("04.data.in");
        assertFalse(lab_00_C.cal(lab_00_C.read()));

        redirect.set_path("05.data.in");
        assertFalse(lab_00_C.cal(lab_00_C.read()));
    }

    @Order(2)
    @Test
    public void test_02() throws FileNotFoundException {
        final String[] strs = {"01.data.in", "02.data.in", "03.data.in", "04.data.in", "05.data.in"};
        boolean[] result = {false, true, true, false, false};
        for (int i = 0; i < 5; i++) {
            redirect.set_path(strs[i]);
            assertEquals(lab_00_C.cal(lab_00_C.read()), result[i]);
        }
    }

    @Order(3)
    @Test
    public void test_03() throws FileNotFoundException {
        // java内部没pair,凑合拿这个当pair吧.
        final Pair<String, Boolean>[] results =
            new Pair[] {new Pair<>("01.data.in", false), new Pair<>("02.data.in", true), new Pair<>("03.data.in", true),
                new Pair<>("04.data.in", false), new Pair<>("05.data.in", false)};
        for (var me : results) {
            redirect.set_path(me.getFirst());
            assertEquals(lab_00_C.cal(lab_00_C.read()), me.getSecond());
        }
    }

    @Order(4)
    @Test
    public void test_04() throws IOException {
        // java内部没pair,凑合拿这个当pair吧.
        final Quaternion<String, String, String, Boolean>[] results =
            new Quaternion[] {new Quaternion<>("01.data.in", "01.data.out", "01.test.out", false),
                new Quaternion<>("02.data.in", "02.data.out", "02.test.out", true),
                new Quaternion<>("03.data.in", "03.data.out", "03.test.out", true),
                new Quaternion<>("04.data.in", "04.data.out", "04.test.out", false),
                new Quaternion<>("05.data.in", "05.data.out", "05.test.out", false)};
        for (Quaternion<String, String, String, Boolean> q : results) {
            redirect.set_path(q.getFirst(), q.getThird());
            lab_00_C.main(init_String);
            Pair<String, String> p = redirect.compare_double(q.getSecond(), q.getThird());
            assertEquals(p.getFirst()
                          .length(),
                p.getSecond()
                 .length());
            assertEquals(p.getFirst(), p.getSecond());
        }
    }
}
/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-28 13:46:33
 * @LastEditors: nanoseeds
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_template_java 

Copyright (C) 2020-2021 nanoseeds

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
