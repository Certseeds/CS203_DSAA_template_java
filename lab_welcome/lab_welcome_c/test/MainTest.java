// SPDX-License-Identifier: MIT

import org.junit.jupiter.api.*;
import tests.Pair;
import tests.Quaternion;
import tests.Redirect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public final class MainTest {
    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "resources/";
    private static final long begin_time = System.currentTimeMillis();
    private static final Random random = new Random();
    private Redirect redirect;

    @AfterAll
    public static void last_one() throws IOException {
        System.out.printf("cost %d ms%n", System.currentTimeMillis() - begin_time);
    }

    @BeforeEach
    public void init() {
        redirect = new Redirect(DATA_PATH);
    }

    @Test
    public void test_01() throws IOException {
        // 测试
        redirect.set_path("01.data.in");
        Assertions.assertFalse(Main.cal(Main.read()));

        redirect.set_path("02.data.in");
        Assertions.assertTrue(Main.cal(Main.read()));

        redirect.set_path("03.data.in");
        Assertions.assertTrue(Main.cal(Main.read()));

        redirect.set_path("04.data.in");
        Assertions.assertFalse(Main.cal(Main.read()));

        redirect.set_path("05.data.in");
        Assertions.assertFalse(Main.cal(Main.read()));
    }

    @Test
    public void test_02() throws FileNotFoundException {
        final String[] strs = {"01.data.in", "02.data.in", "03.data.in", "04.data.in", "05.data.in"};
        final List<Boolean> result = List.of(false, true, true, false, false);
        for (int i = 0; i < 5; i++) {
            redirect.set_path(strs[i]);
            Assertions.assertEquals(Main.cal(Main.read()), result.get(i));
        }
    }

    @Test
    public void test_03() throws FileNotFoundException {
        // java内部没pair,凑合拿这个当pair吧.
        final List<Pair<String, Boolean>> results =
            List.of(new Pair<>("01.data.in", false), new Pair<>("02.data.in", true), new Pair<>("03.data.in", true),
                new Pair<>("04.data.in", false), new Pair<>("05.data.in", false));
        for (var me : results) {
            redirect.set_path(me.getFirst());
            Assertions.assertEquals(Main.cal(Main.read()), me.getSecond());
        }
    }

    @Order(4)
    @Test
    public void test_04() throws IOException {
        // java内部没pair,凑合拿这个当pair吧.
        final List<Quaternion<String, String, String, Boolean>> results =
            List.of(new Quaternion<>("01.data.in", "01.data.out", "01.test.out", false),
                new Quaternion<>("02.data.in", "02.data.out", "02.test.out", true),
                new Quaternion<>("03.data.in", "03.data.out", "03.test.out", true),
                new Quaternion<>("04.data.in", "04.data.out", "04.test.out", false),
                new Quaternion<>("05.data.in", "05.data.out", "05.test.out", false));
        for (Quaternion<String, String, String, Boolean> q : results) {
            redirect.set_path(q.getFirst(), q.getThird());
            Main.main(init_String);
            Pair<String, String> p = redirect.compare_double(q.getSecond(), q.getThird());
            Assertions.assertEquals(p.getFirst()
                    .length(),
                p.getSecond()
                    .length());
            Assertions.assertEquals(p.getFirst(), p.getSecond());
        }
    }

    @AfterEach
    public void last() throws IOException {
        redirect.close();
    }
}
