// SPDX-License-Identifier: AGPL-3.0-or-later

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        final var Reader = new Reader();
        final int test_number = Reader.nextInt();
        final long[] will_return = new long[test_number];
        for (int i = 0; i < will_return.length; i++) {
            will_return[i] = Reader.nextInt();
        }
        return will_return;
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

    // refactor from https://github.com/Kattis/kattio/blob/master/Kattio.java
    // url: https://raw.githubusercontent.com/Kattis/kattio/master/Kattio.java
    // license: MIT
    private static final class Reader {
        private final BufferedReader br;
        private StringTokenizer st;

        private Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {return Integer.parseInt(next());}

        long nextLong() {return Long.parseLong(next());}

        double nextDouble() {return Double.parseDouble(next());}

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
