package tests;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public final class Redirect implements Closeable {
    private final String data_path;
    private FileInputStream fis;
    private PrintStream ps;
    private final PrintStream ori_out = System.out;
    private final InputStream in_ori = System.in;

    public Redirect(String data_path) {
        this.data_path = data_path;
    }

    public void set_path(String input_path) throws FileNotFoundException {
        set_path(input_path, "");
    }

    public void set_path(String input_path, String output_path) throws FileNotFoundException {
        input(input_path);
        if (!"".equals(output_path)) {
            output(output_path);
        }
    }

    public void input(String input_path) throws FileNotFoundException {
        fis = new FileInputStream(data_path + input_path);
        System.setIn(fis);
        // 重定向标准输入流到FileInputStream
    }

    public void output(String output_path) throws FileNotFoundException {
        ps = new PrintStream(new FileOutputStream(data_path + output_path));
        System.setOut(ps);
    }

    public void close() throws IOException {
        System.setIn(in_ori);
        System.setOut(ori_out);
    }

    public Pair<String, String> compare_double(String expected, String actual) throws IOException {
        byte[] file1Bytes = Files.readAllBytes(Paths.get(data_path + expected));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(data_path + actual));
        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
        return new Pair<>(file1, file2);
    }

    public String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        byte[] buffer = new byte[8192];
        int len;
        try (FileInputStream in = new FileInputStream(file);) {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}