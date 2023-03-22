// SPDX-License-Identifier: MIT

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public final class Main {
    public static int[] read() {
        final int[] will_return = new int[2];
        final var input = new Scanner(System.in);
        will_return[0] = input.nextInt();
        will_return[1] = input.nextInt();
        return will_return;

    }

    public static int[] reader() throws IOException {
        final int[] will_return = new int[2];
        try (final var input = new Reader();) {
            will_return[0] = input.nextInt();
            will_return[1] = input.nextInt();
        }
        return will_return;
    }

    public static int cal(int[] nums) {
        assert (nums.length == 2);
        return nums[0] + nums[1];
    }

    public static void main(String[] args) throws IOException {
        final int[] datas = reader();
        final int result = cal(datas);
        output(result);
    }

    public static void output(int number) {
        System.out.print(number + '\n');
    }

    // Working program using Reader Class
    // come from https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
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

        @Override
        public void close() throws IOException {
            dis.close();
        }
    }
}
