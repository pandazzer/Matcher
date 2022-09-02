package team;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class MainTest {
    static String expected1;
    static String expected2;
    static String expected3;
    static String example1;
    static String example2;
    static String example3;

    @BeforeAll
    public static void createTxt(){
        example1 = """
                4
                гвоздь
                шуруп
                краска синяя
                ведро для воды
                3
                краска
                корыто для воды
                шуруп 3х1.5""";
        example2 = """
                1
                Бетон с присадкой
                1
                Цемент""";

        example3 = """
                1
                Бетон с присадкой
                2
                присадка для бетона
                доставка""";
        expected1 = """
                гвоздь:?
                шуруп:шуруп 3х1.5
                краска синяя:краска
                ведро для воды:корыто для воды""";
        expected2 = "Бетон с присадкой:Цемент";
        expected3 = "Бетон с присадкой:присадка для бетона\n" +
                "доставка:?";

    }

    @org.junit.jupiter.api.Test
    void example1() {
        writeFile(example1);
        Main.main(null);
        String actual = readFile();
        Assertions.assertEquals(expected1, actual);
    }
    @org.junit.jupiter.api.Test
    void example2() {
        writeFile(example2);
        Main.main(null);
        String actual = readFile();
        Assertions.assertEquals(expected2, actual);
    }

    @org.junit.jupiter.api.Test
    void example3() {
        writeFile(example3);
        Main.main(null);
        String actual = readFile();
        Assertions.assertEquals(expected3, actual);
    }
    private void writeFile(String line){
        try {
            FileWriter fileWriter = new FileWriter("input.txt");
            fileWriter.write(line);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String readFile(){
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("output.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext()){
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNext()){
                stringBuilder.append("\n");
            }
        }
        scanner.close();
        return stringBuilder.toString();
    }
}