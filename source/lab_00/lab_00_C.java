package lab_00;

import include.Reader;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class lab_00_C {
    private enum COLOR {
        uncolor, red, black
    }
    // uncolor is the first so it's default when init array.

    public static int[][] read() {
        Scanner input = new Scanner(System.in);
        int node_number = input.nextInt();
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
        Reader input = new Reader();
        int node_number = input.nextInt();
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

    public static void main(String[] args) {
        int[][] graph = read();
        boolean result = cal(graph);
        output(result);
    }

    static boolean cal(int[][] graph) {
        int node_number = graph.length;
        COLOR[] color_vec = new COLOR[node_number];
        Arrays.fill(color_vec, COLOR.uncolor);
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < node_number; i++) {
            if (graph[i].length != 0 && color_vec[i] == COLOR.uncolor) {
                color_vec[i] = COLOR.red;
                que = new LinkedList<>();
                que.add(i);
                while (!que.isEmpty()) {
                    int head = que.remove();
                    COLOR color_head = color_vec[head];
                    COLOR next_color = (color_head == COLOR.red) ? COLOR.black : COLOR.red;
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
            System.out.println("\"PoSSiBLE\"");
        } else {
            System.out.println("\"lMP0SSlBLE\"");
        }
    }
}
/**
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-28 11:07:58
 * @LastEditors: nanoseeds
 * @LICENSE: MIT
 */
/*
MIT License

CS203_DSAA_template_java 

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
