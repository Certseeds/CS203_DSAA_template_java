import java.io.InvalidObjectException;

public final class StandardClassTest {
    static final class StandardClass {
        private long l = 100;

        private StandardClass() {
            throw new RuntimeException("should not use default init func");
        }

        private StandardClass(long l) {
            this.l = l;
        }

        private void readObjectNoDate() throws InvalidObjectException {
            throw new InvalidObjectException("Stream data required");
        }

        public static StandardClass LONG(long l) {
            return new StandardClass(l);
        }

        @Override
        @Deprecated
        final protected void finalize() {
            throw new RuntimeException("should not use any finalize method");
        }
    }
}
