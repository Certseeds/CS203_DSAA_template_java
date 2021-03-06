import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class lab_00_D {
    private static final Map.Entry<Integer, String>[] spis = new Map.Entry[]{
            new AbstractMap.SimpleImmutableEntry<>(2, "+---+"),
            new AbstractMap.SimpleImmutableEntry<>(1, "/   /|"),
            new AbstractMap.SimpleImmutableEntry<>(0, "+---+ |"),
            new AbstractMap.SimpleImmutableEntry<>(0, "|   | +"),
            new AbstractMap.SimpleImmutableEntry<>(0, "|   |/"),
            new AbstractMap.SimpleImmutableEntry<>(0, "+---+")};

    public static int[][] read() {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] will_return = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                will_return[i][j] = input.nextInt();
            }
        }
        return will_return;
    }

    public static int[][] reader() throws IOException {
        Reader_00_D input = new Reader_00_D();
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] will_return = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                will_return[i][j] = input.nextInt();
            }
        }
        return will_return;
    }


    public static void print(char[][] map, int a, int b) {
        for (int i = 0; i < spis.length; i++) {
            for (int j = 0; j < spis[i].getValue().length(); j++) {
                map[a - 1 + i][b + j + spis[i].getKey()] = spis[i].getValue().charAt(j);
            }
        }
    }

    public static void cal(int[][] hi) {
        char[][] out = new char[302][301];
        Arrays.stream(out).forEach(a -> Arrays.fill(a, '.'));
        int m = hi.length - 1;
        int n = hi[0].length - 1;
        int wide = 4 * n + 2 * m + 1;
        int h = -0x3f3f;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                h = Math.max(h, hi[i][j] * 3 + 2 * (m - i + 1) + 1);
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < hi[i][j]; k++) {
                    int x = h - 3 * (k + 2) - 2 * (m - i) + 1;
                    int y = 4 * j + 2 * (m - i - 1) - 1;
                    print(out, x, y);
                }
            }
        }
        output(out, h, wide);
    }

    public static void main(String[] args) {
        cal(read());
    }

    public static void output(char[][] map, int high, int wide) {
        for (int i = 0; i < high; i++) {
            for (int j = 1; j <= wide; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
    }
}

class Reader_00_D {
    private final int BUFFER_SIZE = 1 << 16;
    private final DataInputStream dis;
    private final byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader_00_D() {
        dis = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader_00_D(String file_name) throws IOException {
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
        }
        while ((c = read()) >= '0' && c <= '9');
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
        }
        while ((c = read()) >= '0' && c <= '9');
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
        if (dis == null) {
            return;
        }
        dis.close();
    }
}
/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_templalte_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-28 22:44:46
 * @LastEditors: nanoseeds
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_templalte_java 

Copyright (C) 2020  nanoseeds

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
