package producer;

public enum constant {
    ;
    public static final String PARENT_POM_FILE = "./pom.xml";
    public static final String LAB_PATH = "./lab_%1$s/";
    public static final String SUB_POM_FILE = LAB_PATH + "pom.xml";
    public static final String LAB_QUESTION_PATH = LAB_PATH + "lab_%1$s_%2$s/";
    public static final String LAB_QUESTION_POM_PATH = LAB_QUESTION_PATH + "pom.xml";
    public static final String LAB_QUESTION_SOURCE_PATH = LAB_QUESTION_PATH + "src/";
    public static final String LAB_QUESTION_TEST_PATH = LAB_QUESTION_PATH + "test/";
    public static final String LAB_QUESTION_TEST_DATA_PATH = LAB_QUESTION_PATH + "resources/";
    public static final String SOURCE_FILE = LAB_QUESTION_SOURCE_PATH + "Main.java";
    public static final String TEST_FILE = LAB_QUESTION_TEST_PATH + "MainTest.java";
    private static final String FILE_HEADER_STR = "// SPDX-License-Identifier: MIT \n";
}
