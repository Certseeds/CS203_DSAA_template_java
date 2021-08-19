package chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class StandardSerializableClassTest {
    static final class StandardSerializableClass implements Serializable, ObjectInputValidation {
        private static final long serialVersionUID = 0x7103328181202L;
        private transient List<Long> l; // use self-defined write and read Object way

        private StandardSerializableClass() {
            throw new RuntimeException("should not use default init func");
        }

        private StandardSerializableClass(List<Long> l) {
            this.l = l;
            // 其实这里也应该有校验
        }

        private void readObjectNoData() throws InvalidObjectException {
            throw new InvalidObjectException("Stream data required");
        }

        public static StandardSerializableClass LONGs(List<Long> l) {
            return new StandardSerializableClass(l);
        }

        @Override
        @Deprecated
        protected void finalize() {
            throw new RuntimeException("should not use any finalize method");
        }

        private Object writeReplace() {
            return this;
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            oos.writeInt(this.l.size());
            for (var i : l) {
                oos.writeLong(i);
            }
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            final var lsize = ois.readInt();
            this.l = new ArrayList<Long>(lsize);
            for (int i = 0; i < lsize; i++) {
                this.l.add(ois.readLong());
            }
            ois.registerValidation(this, 0);
        }

        private Object readResolve() {
            return this;
        }

        @Override
        public void validateObject() throws InvalidObjectException {
            // 有一说一,这个方法独立出来之后,构造函数也该调用
            if (this.l.size() == 0) {
                throw new InvalidObjectException("invalid Object");
            }
        }
    }

    @Test
    public void showLegal() throws IOException, ClassNotFoundException {
        final var byteArray = getObject(List.of());
        try (InputStream is = new ByteArrayInputStream(byteArray);
             ObjectInputStream input = new ObjectInputStream(is)) {
            Assertions.assertThrows(InvalidObjectException.class, () -> {
                final var d = (StandardSerializableClass) input.readObject();
            });
        }
        System.out.println(byteArray.length);
    }

    @Test
    public void showIegella() throws IOException, ClassNotFoundException {
        final var byteArray = getObject(List.of(1L, 2L, 3L));
        try (InputStream is = new ByteArrayInputStream(byteArray);
             ObjectInputStream input = new ObjectInputStream(is)) {
            final var d = (StandardSerializableClass) input.readObject();
        }
        System.out.println(byteArray.length);
    }

    private static byte[] getObject(List<Long> l) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            final var temp = StandardSerializableClass.LONGs(l);
            output.writeObject(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();
    }
}
