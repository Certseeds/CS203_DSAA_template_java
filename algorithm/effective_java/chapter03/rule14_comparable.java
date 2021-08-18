package chapter03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public final class rule14_comparable {
    // 排序,很神奇吧
    @Test
    public void test() {
        // final只限制了不能再给comparableList赋值
        // 数组中元素仍可以发生变化
        final var comparableList = new showComparable[]{
                showComparable.newCompare(1, 1),
                showComparable.newCompare(4, 5),
                showComparable.newCompare(1, 4),
                showComparable.newCompare(1, 9),
                showComparable.newCompare(1, 9),
                showComparable.newCompare(8, 1),
                showComparable.newCompare(0, 4),
        };
        System.out.println(Arrays.toString(comparableList));
        Arrays.sort(comparableList);
        System.out.println(Arrays.toString(comparableList));
        System.out.println(List.of(comparableList));
    }

    private final static class showComparable implements Comparable<showComparable> {
        private final int x;
        private final int y;

        private showComparable() {
            throw new RuntimeException("should not use default");
        }

        private showComparable(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static showComparable newCompare(int x, int y) {
            return new showComparable(x, y);
        }


        /**
         * @param o : a object
         * @return: 如果this比o x更大,或者x想等情况下y更大,则返回1
         * 若x,y相同,返回0,
         * 否则返回-1
         */
        //@Override
        public int compareTo(showComparable o) {
            // 自反性, 可以保证
            // 对称性, 可以保证
            // 可传递, 可以保证
            // obj1.compareTo(obj2) == 0 确保内部每一个元素进行compareTo都相同,可以保证
            // (obj1.compareTo(obj2) == 0) == (obj1.equals(obj2)) 可以保证
            // int,long这种整型可以用> <
            // float,double就得用Float.compare,Double.compare了
            // 整形用Integer.compare显得傻傻的,很不舒服
            if (this.x > o.x || (this.x == o.x && this.y > o.y)) {
                return 1;
            } else if (this.x == o.x && this.y == o.y) {
                return 0;
            }
            return -1;
        }
        private static final Comparator<showComparable> OLD_COMPARABLE_COMPARATOR = Comparator
                .comparingInt((showComparable o) -> o.x) // 需要写类型,不优雅
                .thenComparing(o -> o.y);
        private static final Comparator<showComparable> COMPARABLE_COMPARATOR = Comparator
                .<showComparable>comparingInt(o -> o.x) // 这样剥离类型更优雅
                .thenComparing(o -> o.y);

        //@Override
        public int compareTo2(showComparable o) {
            // 自反性, 可以保证
            // 对称性, 可以保证
            // 可传递, 可以保证
            // obj1.compareTo(obj2) == 0 确保内部每一个元素进行compareTo都相同,可以保证
            // (obj1.compareTo(obj2) == 0) == (obj1.equals(obj2)) 可以保证
            // 主要用在那些想要自定义比较,但是无法重写要排序的类的地方,传一个比较函数过去而不需要对类本身进行修改
            // 反正OJ内的代码都是自己写的,这个应该用不到
            return COMPARABLE_COMPARATOR.compare(this, o);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", showComparable.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("y=" + y)
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final showComparable that = (showComparable) o;
            if (x != that.x) {
                return false;
            }
            return y == that.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}

