import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class lab_00_A_Test {
    private static final String[] init_String = new String[0];
    private static final String DATA_PATH = "lab00/lab_00_A_data/";
    private static final long begin_time = System.currentTimeMillis();
    private static final Random random = new Random();
    private static Redirect redirect;

    @BeforeAll
    public static void init() {
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
    public static void last_one() throws IOException {
        redirect.close();
        System.out.printf("cost %d ms%n", System.currentTimeMillis() - begin_time);
    }
}
