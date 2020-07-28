package lab_00;

import include.Redirect;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.util.Random;

public class lab_00_A_test {
    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "test/lab_00/lab_00_A_data/";
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
            int x = random.nextInt(0x3f3f3f3f);
            int y = random.nextInt(0x3f3f3f3f);
            assertEquals(x + y, lab_00_A.cal(new int[]{x, y}));
        }
    }

    @Order(1)
    @Test
    public void test_2() throws IOException {
        redirect.set_path("01.data.in", "01.test.out");
        lab_00_A.output(lab_00_A.cal(lab_00_A.read()));
        assertTrue(redirect.compare_double("01.test.out", "01.data.out"));

        redirect.set_path("01.data.in", "01.test.out");
        lab_00_A.output(lab_00_A.cal(lab_00_A.reader()));
        assertTrue(redirect.compare_double("01.test.out", "01.data.out"));
    }

    @Order(2)
    @Test
    public void test_3() throws IOException {
        // 测试
        redirect.set_path("01.data.in");
        assertEquals(628, lab_00_A.cal(lab_00_A.read()));

        redirect.set_path("01.data.in");
        assertEquals(628, lab_00_A.cal(lab_00_A.reader()));

    }

    @AfterAll
    public static void last_one() {
        System.setIn(System_in);
        System.setOut(System_out);
        System.out.println(String.format("cost %d ms", System.currentTimeMillis() - begin_time));
    }
}

