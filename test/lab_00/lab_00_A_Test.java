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

public class lab_00_A_Test {
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
        assertEquals(0xFFFFFFFF, lab_00_A.cal(new int[] { 0xFFFFFFFE, 1 }));
        assertEquals(0x00000001, lab_00_A.cal(new int[] { 0x00000000, 1 }));
        assertEquals(0xFFFFFFF0, lab_00_A.cal(new int[] { 0xFFFFFFEF, 1 }));
    }

    @Order(1)
    @Test
    public void test_1() {
        assertEquals(0xFFFFFFFE, lab_00_A.cal(new int[] { 0xFFFFFFFF, -1 }));
        assertEquals(0xFFFFFFFE, lab_00_A.cal(new int[] { 0xFFFFFFFF, -1 }));
        assertEquals(0xFFFFFFEE, lab_00_A.cal(new int[] { 0xFFFFFFEF, -1 }));
        assertEquals(0x12345678 << 1, lab_00_A.cal(new int[] { 0x12345678, 0x12345678 }));
    }

    @Order(2)
    @Test
    public void test_2() {
        for (int i = 0; i < 114; i++) {
            int x = random.nextInt(0x3f3f3f3f);
            int y = random.nextInt(0x3f3f3f3f);
            assertEquals(x + y, lab_00_A.cal(new int[] { x, y }));
        }
    }

    @Order(3)
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
