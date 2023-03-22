// SPDX-License-Identifier: MIT

import org.junit.jupiter.api.*;
import tests.Pair;
import tests.Redirect;

import java.io.IOException;
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
        redirect.set_path("01.data.in");
        Main.cal(Main.read());
    }

    @Test
    public void test_02() throws IOException {
        redirect.set_path("01.data.in", "01.test.out");
        Main.main(init_String);
        Pair<String, String> p = redirect.compare_double("01.data.out", "01.test.out");
        Assertions.assertEquals(p.getFirst()
                .length(),
            p.getSecond()
                .length());
        Assertions.assertEquals(p.getFirst(), p.getSecond());
    }

    @AfterEach
    public void last() throws IOException {
        redirect.close();
    }
}
