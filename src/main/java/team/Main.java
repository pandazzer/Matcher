package team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        File input = new File("input.txt");
        File output = new File("output.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int n = scanner.nextInt();
        List<String> listOne = new ArrayList<>();
        List<String> listTwo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            if (!str.isEmpty()){
                listOne.add(str);
            }else i--;

        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            String str = scanner.nextLine();
            if (!str.isEmpty()){
                listTwo.add(str);
            }else i--;
        }

        scanner.close();
        List<String> result = main.getList(listOne, listTwo);

        try {
            FileWriter fileWriter = new FileWriter(output);
            for (String resStr : result){
                fileWriter.write(resStr);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    List<String> getList(List<String> array1, List<String> array2){
        List<String> mappedList = new ArrayList<>();
        List<String> balance = new ArrayList<>(array2);
        for (String str1 : array1) {
            List<String> wordOne = List.of(str1.split(" "));
            int maxEqualsRate = 0;
            String resWord2 = "?";
            for (String str2 : array2){
                List<String> wordTwo = List.of(str2.split(" "));
                int equalsRate = equalsRate(wordOne, wordTwo);
                if (equalsRate > maxEqualsRate){
                    maxEqualsRate = equalsRate;
                    resWord2 = str2;
                    balance.remove(str2);
                }
            }
            mappedList.add(str1 + ":" + resWord2 + "\n");
        }
        List<String> result = new ArrayList<>();
        for (String resultStr : mappedList){
            if (balance.size() > 0 && resultStr.contains("?")){
                result.add(resultStr.replace("?", balance.get(0)));
                balance.remove(0);
            } else {
                result.add(resultStr);
            }
        }
        while (balance.size() > 0){
            result.add(balance.get(0) + ":?");
            balance.remove(0);
        }
        return result;
    }

    private int equalsRate(List<String> array1, List<String> array2){
        int result = 0;
        if (array1.size() > array2.size()){
            for (String str: array1){
                if (array2.contains(str)){
                    result++;
                }
            }
        } else {
            for (String str : array2){
                if (array1.contains(str)) {
                    result++;
                }
            }
        }
        return result;
    }
}
