// SPDX-License-Identifier: MIT

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import tests.Pair;
import tests.Redirect;

import java.io.IOException;

@Slf4j
public final class MainTest {
    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "resources/";
    private static final long begin_time = System.currentTimeMillis();

    @AfterAll
    public static void last_one() throws IOException {
        log.info("cost {} ms\n", System.currentTimeMillis() - begin_time);
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        log.info("{} begin", testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        log.info("{} end", testInfo.getDisplayName());
    }

    @Test
    public void test_01() throws IOException {
        try (Redirect redirect = Redirect.from(DATA_PATH, "01.data.in", "")) {
            Main.cal(Main.read());
        }
    }

    @Test
    public void test_02() throws IOException {
        try (Redirect redirect = Redirect.from(DATA_PATH, "01.data.in", "01.test.out")) {
            Main.main(init_String);
            final Pair<String, String> p = redirect.compare_double("01.data.out", "01.test.out");
            Assertions.assertEquals(p.getFirst().length(), p.getSecond().length());
            Assertions.assertEquals(p.getFirst(), p.getSecond());
        }
    }

}
