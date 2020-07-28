package lab_00;

import include.Reader;

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
        Reader input = new Reader();
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
