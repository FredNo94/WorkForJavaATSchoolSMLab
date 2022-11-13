package clientapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static int[] createdArray;
    public static int[] sortedArray;
    public static int maxValue;
    public static int minValue;
    public static int sumValue = 0;
    public static int avgValue;
    public static int avgValueVarTwo;
    public static int userSizeArray;


    public static void main(String[] args) throws IOException {
        requestArraySize();
        createdArray = generateArray(userSizeArray);
        fillingArray();
        calculationValue();
        createFile();
    }

    public static void requestArraySize() {
        System.out.println("Введите размер массива: ");
        userSizeArray = scan.nextInt();
    }

    public static void calculationValue() {
        maxValue = findMaxValue();
        minValue = findMinValue();
        sumValue = findSumValues();
        avgValue = sumValue / createdArray.length;
        avgValueVarTwo = avgValueVarTwo(); //Значение которое стоит посередине массива
    }

    public static int[] generateArray(int sizeArray) {
        int newArray[] = new int[sizeArray];
        return newArray;
    }

    public static void fillingArray() {
        for (int i = 0; i < createdArray.length; i++) {
            System.out.println(String.format("Ввведите %d элемент", (i + 1)));
            createdArray[i] = scan.nextInt();
        }
    }

    public static int avgValueVarTwo() {
        sortedArray = createdArray.clone();
        int firstElm;
        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = 0; j < sortedArray.length - 1; j++) {
                if (sortedArray[j + 1] < sortedArray[j]) {
                    firstElm = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = firstElm;
                }
            }
        }
        return sortedArray[sortedArray.length / 2];
    }

    public static int findMaxValue() {
        maxValue = createdArray[0];
        ;
        for (int i = 0; i < createdArray.length - 1; i++) {
            if (maxValue < createdArray[i + 1]) {
                maxValue = createdArray[i + 1];
            }
        }
        return maxValue;
    }

    public static int findMinValue() {
        minValue = createdArray[0];
        for (int i = 0; i < createdArray.length - 1; i++) {
            if (minValue > createdArray[i + 1]) {
                minValue = createdArray[i + 1];
            }
        }
        return minValue;
    }

    public static int findSumValues() {
        for (int i = 0; i < createdArray.length; i++) {
            sumValue += createdArray[i];
        }
        return sumValue;
    }

    public static void createFile() throws IOException {
        FileWriter writer = new FileWriter("array.txt", StandardCharsets.UTF_8, true);
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.printf("\n Массив: %s \n ", Arrays.toString(createdArray));
        printWriter.printf("Максимальное число в массиве: %d \n ", maxValue);
        printWriter.printf("Минимальное число в массиве: %d \n ", minValue);
        printWriter.printf("Сумма чисел в массиве: %d \n ", sumValue);
        printWriter.printf("Среднее зачение(расчетное) в массиве: %d \n ", avgValue);
        printWriter.printf("Среднее зачение в массиве: %d", avgValueVarTwo);
        writer.close();
    }

}
