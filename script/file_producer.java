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
    private static final String SOURCE_PATH = "./../source/lab_%1$s/";
    private static final String SOURCE_FILE = SOURCE_PATH + "lab_%1$s_%2$s.java";
    private static final String TEST_PATH = "./../test/lab_%1$s/";
    private static final String TEST_FILE = TEST_PATH + "lab_%1$s_%2$s_Test.java";
    private static final String TEST_DATA_PATH = TEST_PATH + "lab_%1$s_%2$s_data";
    private static String source_code_template;
    private static String test_code_template;
    private static String file_header_template;
    private static final String GITHUB_USER = "YOUR_GITHUB_NAME";
    private static final String USER = "YOUR_USER_NAME";
    private static final String REPO_NAME = "YOUR_REPO_NAME";
    private static final String YEAR = new SimpleDateFormat("yyyy").format(new Date());
    private static final String CREATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private static final String[] labs =
            {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"};
    private static final String[] problem_order = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    //    private static final String[] labs = {"00"};
//    private static final String[] problem_order = {"E"};
    private static final String[] test_datas = {"01", "02", "03"};

    public static void main(String[] args) throws IOException {
        source_code_template = read_file("./java_template.txt");
        test_code_template = read_file("./java_test_template.txt");
        file_header_template = read_file("./file_header.txt");
        for (String i : labs) {
            try_mkdir(i);
            for (String j : problem_order) {
                //try_make_data_dir(i, j);
                fill_file(i, j);
            }
        }
        System.out.println("produce files finish");
        System.out.printf("%d files is produces", labs.length * problem_order.length * 2);
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
             FileWriter fw2 = new FileWriter(test, true);) {
            fw1.write(String.format(source_code_template, lab_number, problem_order));
            fw2.write(String.format(test_code_template, lab_number, problem_order));
            fw1.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, CREATE_TIME, YEAR));
            fw2.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, CREATE_TIME, YEAR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void try_make_data_dir(String lab_number, String problem_order) throws IOException {
        File test_path = new File(String.format(TEST_DATA_PATH, lab_number, problem_order));
        if (!test_path.exists()) {
            test_path.mkdir();
        }
        for (String num : test_datas) {
            String input_file_name = String.format(TEST_DATA_PATH, lab_number, problem_order) +
                    String.format("/%s.data.in", num);
            if (!Files.exists(Paths.get(input_file_name))) {
                Files.createFile(Paths.get(input_file_name));
                try (FileWriter fw1 = new FileWriter(new File(input_file_name), true);) {
                    fw1.write("114 514\n");
                }
            }
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

/**
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