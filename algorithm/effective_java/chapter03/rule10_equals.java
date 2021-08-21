package chapter03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class rule10_equals {

    public static final class rule10_equals_class {
        private final int x;
        private final int y;
        private final int z;
        private final double time;
        private final int hash;

        private rule10_equals_class() {
            this.time = (this.x = (this.y = (this.z = 0))) * 0.0f;
            this.hash = 0;
            throw new AssertionError("should not use default");
        }

        private rule10_equals_class(int x, int y, int z, double time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
            this.hash = this.hashCode();
        }

        public static rule10_equals_class FOURTH(int x, int y, int z, double time) {
            return new rule10_equals_class(x, y, z, time);
        }

        private int hash() {
            return ((((31 + x) * 31 + y) * 31 + z)) * 31 + Double.hashCode(time);
        }

        public int hashCode() {
            return this.hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (!(obj instanceof rule10_equals_class)) { // 好想用模式匹配语法
                return false;
            }
            final var objc = (rule10_equals_class) obj;
            return (objc.x == this.x) &&
                    (objc.y == this.y) &&
                    (objc.z == this.z) && Double.compare(objc.time, this.time) == 0;
        }
    }

    @Test
    public void test_自反性() {
        final var obj1 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        Assertions.assertEquals(obj1, obj1);
        Assertions.assertEquals(obj1.hashCode(), obj1.hashCode());
    }

    @Test
    public void test_null值() {
        final var obj1 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        Assertions.assertNotEquals(null, obj1);
        Assertions.assertNotEquals(obj1, null);
    }

    @Test
    public void test_对称性() {
        final var obj1 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        final var obj2 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj2, obj1);
        Assertions.assertEquals(obj2.hashCode(), obj1.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    public void test_传递性() {
        final var obj1 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        final var obj2 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        final var obj3 = rule10_equals_class.FOURTH(1, 1, 4, 514.0d);
        Assertions.assertEquals(obj1, obj2);
        Assertions.assertEquals(obj1.hashCode(), obj2.hashCode());
        Assertions.assertEquals(obj2, obj3);
        Assertions.assertEquals(obj2.hashCode(), obj3.hashCode());
        Assertions.assertEquals(obj1, obj3);
        Assertions.assertEquals(obj1.hashCode(), obj3.hashCode());
    }
}
