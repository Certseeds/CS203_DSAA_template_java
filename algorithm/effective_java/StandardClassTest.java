import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;

public final class StandardClassTest {
    static final class StandardClass implements ObjectInputValidation {
        private long l = 100;

        private StandardClass() {
            throw new RuntimeException("should not use default init func");
        }

        private StandardClass(long l) throws InvalidObjectException {
            this.l = l;
            this.validateObject();
        }

        public static StandardClass LONG(long l) throws InvalidObjectException {
            return new StandardClass(l);
        }

        @Override
        @Deprecated
        final protected void finalize() {
            throw new RuntimeException("should not use any finalize method");
        }

        @Override
        public void validateObject() throws InvalidObjectException {
            // 有一说一,这个方法独立出来之后,构造函数也该调用
            if (0L == l) {
                throw new InvalidObjectException("invalid Object");
            }
        }

    }
}
