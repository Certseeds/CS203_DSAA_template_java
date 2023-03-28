// SPDX-License-Identifier: AGPL-3.0-or-later

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public final class Main {
    public static long[] read() {
        final var input = new Scanner(System.in);
        final int test_number = input.nextInt();
        final long[] will_return = new long[test_number];
        for (int i = 0; i < test_number; i++) {
            will_return[i] = input.nextInt();
        }
        return will_return;
    }

    public static long[] reader() throws IOException {
        try (final var Reader = new Reader();) {
            final int test_number = Reader.nextInt();
            final long[] will_return = new long[test_number];
            for (int i = 0; i < will_return.length; i++) {
                will_return[i] = Reader.nextInt();
            }
            return will_return;
        }
    }

    static long[] cal_warpper(long[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = cal(nums[i]);
        }
        return nums;
    }

    static long cal(long data) {
        final long will_return = data * (data + 1) * (data + 2) / 6;
        return will_return;
    }

    static long brute_force(long data) {
        long will_return = 0;
        for (long i = 1; i <= data; ++i) {
            will_return += i * (i + 1) / 2;
        }
        return will_return;
    }

    static void output(long[] nums) {
        for (long num : nums) {
            System.out.print(num);
            System.out.print('\n');
        }
    }

    public static void main(String[] args) {
        final var input = read();
        final var output = cal_warpper(input);
        output(output);
    }

    /**
     * Read helper, make it faster
     */
    private static final class Reader implements AutoCloseable {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            dis = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String nextLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        // this function eat the lines last '\r\n','\n'
        // so after the after not need a readLine() to make next Readline can read a line
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {

            dis.close();
        }
    }
}
