import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class file_producer {
    private static final String ENCODING = "UTF-8";
    private static final String SOURCE_PATH = "./../source/lab_%1$s/";
    private static final String SOURCE_FILE = SOURCE_PATH + "lab_%1$s_%2$s.java";
    private static final String TEST_PATH = "./../test/lab_%1$s/";
    private static final String TEST_FILE = TEST_PATH + "lab_%1$s_%2$s_test.java";
    private static String source_code_template;
    private static String test_code_template;
    private static String file_header_template;
    private static final String GITHUB_USER = "YOUR_GITHUB_NAME";
    private static final String USER = "YOUR_USER_NAME";
    private static final String REPO_NAME = "YOUR_REPO_NAME";
    private static final String YEAR = new SimpleDateFormat("yyyy").format(new Date());
    private static final String CREATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static void main(String[] args) throws IOException {
        source_code_template = read_file("./java_template.txt");
        test_code_template = read_file("java_test_template.txt");
        file_header_template = read_file("./file_header.txt");
        String[] labs = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"};
        String[] problem_order = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
//        String[] labs = {"01"};
//        String[] problem_order = {"A"};
        for (String i : labs) {
            try_mkdir(i);
            for (String j : problem_order) {
                File test_path = new File(String.format(TEST_PATH + "lab_%1$s_%2$s_data", i, j));
                if (!test_path.exists()) {
                    test_path.mkdir();
                }
                String input_file_name=String.format(TEST_PATH + "lab_%1$s_%2$s_data", i, j)+"/01.data.in";
                if (!Files.exists(Paths.get(input_file_name))) {
                    Files.createFile(Paths.get(input_file_name));
                    try (FileWriter fw1 = new FileWriter(new File(input_file_name), true);) {
                        fw1.write("114 514\n");
                    }
                }
                fill_file(i, j);
            }
        }
        System.out.println("produce files finish");
        System.out.printf("%d files is produces", labs.length * problem_order.length * 3);
    }

    private static void fill_file(String lab_number, String problem_order) throws IOException {
        Path path1 = Paths.get(String.format(SOURCE_FILE, lab_number, problem_order));
        Path path2 = Paths.get(String.format(TEST_FILE, lab_number, problem_order));
        if (!Files.exists(path1)) {
            Files.createFile(path1);
        }
        if (!Files.exists(path2)) {
            Files.createFile(path2);
        }
        File source = new File(String.valueOf(path1));
        File test = new File(String.valueOf(path2));
        try (FileWriter fw1 = new FileWriter(source, true);
             FileWriter fw2 = new FileWriter(test, true);
        ) {
            fw1.write(String.format(source_code_template, lab_number, problem_order));
            fw2.write(String.format(test_code_template, lab_number, problem_order));
            fw1.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, YEAR, CREATE_TIME));
            fw2.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, YEAR, CREATE_TIME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void try_mkdir(String lab_number) {
        File source = new File(String.format(SOURCE_PATH, lab_number));
        File test = new File(String.format(TEST_PATH, lab_number));
        if (!source.exists()) {
            source.mkdir();
        }
        if (!test.exists()) {
            test.mkdir();
        }
    }

    private static String read_file(String file_name) {
        File file = new File(file_name);
        // FileInputStream fis;
        byte[] data = new byte[0];
        try (FileInputStream fis = new FileInputStream(file);) {
            data = new byte[(int) file.length()];
            fis.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data, StandardCharsets.UTF_8);
    }
}

/*
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-27 10:41:58
 * @LastEditors: nanoseeds
 * @LastEditTime: 2020-07-27 14:11:28
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