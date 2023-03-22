// SPDX-License-Identifier: MIT

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public final class Main {

    private static final String SUCCESS = "\"PoSSiBLE\"\n";
    // uncolor is the first so it's default when init array.
    private static final String FAIL = "\"lMP0SSlBLE\"\n";

    public static int[][] read() {
        final var input = new Scanner(System.in);
        final int node_number = input.nextInt();
        int[][] will_return = new int[node_number][];
        for (int i = 0; i < node_number; i++) {
            int connects = input.nextInt();
            will_return[i] = new int[connects];
            for (int j = 0; j < connects; j++) {
                will_return[i][j] = input.nextInt();
            }
        }
        return will_return;
    }

    public static int[][] reader() throws IOException {
        try (final var input = new Reader();) {
            final int node_number = input.nextInt();
            final int[][] will_return = new int[node_number][];
            for (int i = 0; i < node_number; i++) {
                final int connects = input.nextInt();
                will_return[i] = new int[connects];
                for (int j = 0; j < connects; j++) {
                    will_return[i][j] = input.nextInt();
                }
            }
            return will_return;
        }
    }

    public static void main(String[] args) {
        final int[][] graph = read();
        final boolean result = cal(graph);
        output(result);
    }

    static boolean cal(int[][] graph) {
        final int node_number = graph.length;
        final var color_vec = new COLOR[node_number];
        Arrays.fill(color_vec, COLOR.uncolor);
        final Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < node_number; i++) {
            if (graph[i].length != 0 && color_vec[i] == COLOR.uncolor) {
                color_vec[i] = COLOR.red;
                que.add(i);
                while (!que.isEmpty()) {
                    final int head = que.remove();
                    final COLOR color_head = color_vec[head];
                    final COLOR next_color = (color_head == COLOR.red) ? COLOR.black : COLOR.red;
                    for (int j : graph[head]) {
                        if (color_vec[j] == COLOR.uncolor) {
                            color_vec[j] = next_color;
                            que.add(j);
                        } else if (color_vec[j] == color_head) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static void output(boolean data) {
        if (data) {
            System.out.print(SUCCESS);
        } else {
            System.out.print(FAIL);
        }
    }

    private enum COLOR {
        uncolor, red, black
    }

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
