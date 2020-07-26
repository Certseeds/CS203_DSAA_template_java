package lab_00;

import include.Redirect;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Random;

public class lab_00_A_test {
    private static final String[] init_String = new String[0];
    private static Random random;
    private static Redirect redirect;
    private static final String data_path = "test\\lab_00\\lab_00_A\\";

    @BeforeAll
    public static void init() {
        random = new Random();
        redirect = new Redirect(data_path);
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
        redirect.input("01.data.in");
        redirect.output("01.test.out");
        lab_00_A.output(lab_00_A.cal(lab_00_A.read()));
        assertTrue(redirect.compare_double("01.test.out", "01.data.out"));
        redirect.input("01.data.in");
        redirect.output("01.test.out");
        lab_00_A.output(lab_00_A.cal(lab_00_A.read2()));
        assertTrue(redirect.compare_double("01.test.out", "01.data.out"));
        redirect.input("01.data.in");
        redirect.output("01.test.out");
        lab_00_A.output(lab_00_A.cal(lab_00_A.read3()));
        assertTrue(redirect.compare_double("01.test.out", "01.data.out"));
    }
}

