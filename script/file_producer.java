import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public final class file_producer {

    private static final String LAB_PATH = "./../lab%1$s/";
    private static final String SOURCE_PATH = LAB_PATH + "src/";
    private static final String TEST_PATH = LAB_PATH + "test/";
    private static final String TESTCASE_PATH = LAB_PATH;
    private static final String SOURCE_FILE = SOURCE_PATH + "lab_%1$s_%2$s.java";
    private static final String TEST_FILE = TEST_PATH + "lab_%1$s_%2$s_Test.java";
    private static final String SUB_POM_FILE = LAB_PATH + "pom.xml";
    private static final String PARENT_POM_FILE = "./../pom.xml";
    private static final String TEST_DATA_PATH = TESTCASE_PATH + "lab_%1$s_%2$s_data";
    private static String source_code_template, test_code_template, sub_pom_template, parent_pom, file_header_template;
    private static final String GITHUB_USER = "YOUR_GITHUB_NAME", USER = "YOUR_USER_NAME", REPO_NAME = "YOUR_REPO_NAME",
            YEAR = new SimpleDateFormat("yyyy").format(new Date()),
            CREATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private static final List<String>
            labs = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"),
            problem_order = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"),
    // labs = List.of("00"),
    // problem_order = List.of("E");
    test_datas = List.of("01");

    public static void main(String[] args) throws IOException {
        source_code_template = read_file("./java_template.txt");
        test_code_template = read_file("./java_test_template.txt");
        sub_pom_template = read_file("./subpom.xml");
        parent_pom = read_file("./parentpom.xml");
        file_header_template = read_file("./file_header.txt");

        for (var i : labs) {
            try_mkdir(i);
            for (String j : problem_order) {
                // try_make_data_dir(i, j);
                fill_file(i, j);
                try_make_data_dir(i, j);
            }
        }
        System.out.println("produce files finish");
        System.out.printf("%d files is produces", labs.size() * problem_order.size() * 2);
    }

    private static void fill_file(String lab_number, String problem_order) throws IOException {
        final var path1 = Paths.get(String.format(SOURCE_FILE, lab_number, problem_order));
        final var path2 = Paths.get(String.format(TEST_FILE, lab_number, problem_order));
        if (!Files.exists(path1)) {
            Files.createFile(path1);
        }
        if (!Files.exists(path2)) {
            Files.createFile(path2);
        }
        final var subPomPath = Paths.get(String.format(SUB_POM_FILE, lab_number));
        final var parentPomPath = Paths.get(PARENT_POM_FILE);
        final var source = new File(String.valueOf(path1));
        final var test = new File(String.valueOf(path2));
        final var subPom = new File(String.valueOf(subPomPath));
        final var parentPom = new File(String.valueOf(parentPomPath));
        try (var sourceWriter = new OutputStreamWriter(new FileOutputStream(source, true), StandardCharsets.UTF_8);
             var testWriter = new OutputStreamWriter(new FileOutputStream(test, true), StandardCharsets.UTF_8);
             var subPomWriter = new OutputStreamWriter(new FileOutputStream(subPom, true), StandardCharsets.UTF_8);
             var parentPomWriter = new OutputStreamWriter(new FileOutputStream(parentPom))) {
            sourceWriter.write(String.format(source_code_template, lab_number, problem_order));
            testWriter.write(String.format(test_code_template, lab_number, problem_order));
            subPomWriter.write(String.format(sub_pom_template, lab_number));
            parentPomWriter.write(parent_pom);
            sourceWriter.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, CREATE_TIME, YEAR));
            testWriter.write(String.format(file_header_template, GITHUB_USER, USER, REPO_NAME, CREATE_TIME, YEAR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void try_make_data_dir(String lab_number, String problem_order) throws IOException {
        final var test_path = new File(String.format(TEST_DATA_PATH, lab_number, problem_order));
        if (!test_path.exists()) {
            test_path.mkdir();
        }
        final Consumer<String[]> lambda = (var strs) -> {
            try {
                String input_file_name =
                        String.format(TEST_DATA_PATH, lab_number, problem_order) + String.format(strs[0], strs[1]);
                if (!Files.exists(Paths.get(input_file_name))) {
                    Files.createFile(Paths.get(input_file_name));
                }
                try (var fw1 = new OutputStreamWriter(new FileOutputStream(input_file_name), StandardCharsets.UTF_8);) {
                    fw1.write(String.format("%s\n", strs[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        for (var num : test_datas) {
            lambda.accept(new String[]{"/%s.data.in", num, "114 514"});
            lambda.accept(new String[]{"/%s.data.out", num, "628"});
        }

    }

    private static void try_mkdir(String lab_number) {
        final Consumer<String> lambda = (String str) -> {
            File lab = new File(String.format(str, lab_number));
            if (!lab.exists()) {
                lab.mkdir();
            }
        };
        lambda.accept(LAB_PATH);
        lambda.accept(SOURCE_PATH);
        lambda.accept(TEST_PATH);
        lambda.accept(TESTCASE_PATH);
    }

    private static String read_file(String file_name) {
        final var file = new File(file_name);
        int n = 0;
        // FileInputStream fis;
        char[] data = new char[0];
        try (BufferedReader fis =
                     new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));) {
            data = new char[(int) file.length()];
            n = fis.read(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data, 0, n);
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

Copyright (C) 2020-2021 nanoseeds

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