import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.Pair;
import tests.Redirect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Random;

public final class MainTest {
    private static final String DATA_PATH = "resources/";
    private static final long begin_time = System.currentTimeMillis();
    private static final Random random = new Random();
    private Redirect redirect;

    @AfterAll
    public static void last_one() {
        System.out.printf("cost %d ms%n", System.currentTimeMillis() - begin_time);
    }

    @BeforeEach
    public void init() {
        redirect = new Redirect(DATA_PATH);
    }

    @Test
    public void test_0() {
        for (int i = 0; i < 114; i++) {
            int x = random.nextInt(0x3f3f3f3f);
            int y = random.nextInt(0x3f3f3f3f);
            assertEquals(x + y, Main.cal(new int[]{x, y}));
        }
    }

    @Test
    public void test_2() throws IOException {
        redirect.set_path("01.data.in", "01.test.out");
        Main.output(Main.cal(Main.read()));
        Pair<String, String> p = redirect.compare_double("01.data.out", "01.test.out");
        assertEquals(p.getFirst().length(), p.getSecond().length());
        assertEquals(p.getFirst(), p.getSecond());

        redirect.set_path("01.data.in", "01.test.out");
        Main.output(Main.cal(Main.reader()));
        p = redirect.compare_double("01.data.out", "01.test.out");
        assertEquals(p.getFirst().length(), p.getSecond().length());
        assertEquals(p.getFirst(), p.getSecond());
    }

    @Test
    public void test_3() throws IOException {
        redirect.set_path("01.data.in");
        assertEquals(628, Main.cal(Main.read()));

        redirect.set_path("01.data.in");
        assertEquals(628, Main.cal(Main.reader()));

    }
}
