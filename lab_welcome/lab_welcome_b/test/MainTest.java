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
        Assertions.assertNotNull(redirect);
    }

    @Test
    public void test_0() {
        for (int i = 0; i < 114; i++) {
            int x = random.nextInt(0x7777);
            Assertions.assertEquals(Main.brute_force(x), Main.cal(x));
        }
    }

    @Test
    public void test_2() throws IOException {
        Assertions.assertEquals(1, Main.cal(1));

        Assertions.assertEquals(4, Main.cal(2));

        Assertions.assertEquals(38 * 115 * 58, Main.cal(114));
    }

    @Test
    public void test_3() throws IOException {
        for (int i = 1; i <= 3; i++) {
            redirect.set_path(String.format("0%d.data.in", i), String.format("0%d.test.out", i));
            Main.output(Main.cal_warpper(Main.read()));
            Pair<String, String> p =
                redirect.compare_double(String.format("0%d.data.out", i), String.format("0%d.test.out", i));
            Assertions.assertEquals(p.getFirst()
                                     .length(),
                p.getSecond()
                 .length());
            Assertions.assertEquals(p.getFirst(), p.getSecond());
        }
        for (int i = 1; i <= 3; i++) {
            redirect.set_path(String.format("0%d.data.in", i), String.format("0%d.test.out", i));
            Main.output(Main.cal_warpper(Main.reader()));
            Pair<String, String> p =
                redirect.compare_double(String.format("0%d.data.out", i), String.format("0%d.test.out", i));
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