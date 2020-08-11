package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int countX;
        int numS;
        int type;
        int flag;
        int numberRow;
        int numberColumn;
        int counter = 0;

        ArrayList<ArrayList<Integer>> listForAnswer = new ArrayList<>();


        Scanner p = new Scanner(System.in);

        System.out.print("Enter count x: ");
        countX = p.nextInt();

        System.out.print("Enter count S: ");
        numS = p.nextInt();

        double[] tempArr = new double[numS + 1];

/*
        double[][] arr = new double[numS + 1][numS + countX + 2];

        System.out.println("Enter string Z");
        for (int i = 0; i < numS + countX + 2; i++) {
            arr[0][i] = p.nextInt();
        }

        for (int i = 1; i <= numS; i++) {
            System.out.println("Enter string S" + i);
            for (int j = 0; j < numS + countX + 2; j++) {
                arr[i][j] = p.nextInt();
            }

        }

        double[][] arr = {
                {1, -5, -4, 0, 0, 0, 0, 0},
                {0, 6, 4, 1, 0, 0, 0, 24},
                {0, 1, 2, 0, 1, 0, 0, 6},
                {0, -1, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 2}};

 */

        double[][] arr = {
                {1, -1, -4, 0, 0, 0, 0},
                {0, 5, 1, 1, 0, 0, 26},
                {0, 7, 2, 0, 1, 0, 40},
                {0, 6, 18, 0, 0, 1, 10}};


        System.out.println("");
        printArr(arr);

        System.out.println("Is this the problem of finding the maximum (1) or minimum (-1)? ");
        do {
            type = p.nextInt();
            if (type == 0 || type > 1 || type < -1)
                System.out.println("Enter correct number");
        } while (type == 0 || type > 1 || type < -1);


        flag = check(arr, countX, type);


        while (flag != 0) {

            listForAnswer.add(new ArrayList<>());
            numberColumn = searchColumn(arr, countX, type);
            System.out.println("Column = " + numberColumn);

            listForAnswer.get(counter).add(numberColumn);

            numberRow = searchRow(arr, numS, type, numberColumn);
            System.out.println("Row = " + numberRow);
            listForAnswer.get(counter).add(numberRow);


            for (int i = 0; i < arr.length; i++) {
                tempArr[i] = arr[i][numberColumn];
            }

            arr = simplexMethod(arr, numberRow, numberColumn, tempArr);

            printArr(arr);
            flag = check(arr, countX, type);

            counter++;
        }

        for (ArrayList<Integer> integers : listForAnswer) {
            System.out.printf("X" + integers.get(0) + " = " + "%.2f", arr[integers.get(1)][arr[0].length - 1]);
            System.out.println("");
        }

    }

    //=========================================================


    private static void printArr(double[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf(" " + "%.2f", arr[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static int searchColumn(double[][] arr, int numX, int type) {
        double max = arr[0][1];
        int numberColumn = 1;
        for (int i = 1; i <= numX; i++) {
            if ((arr[0][i] * type) < max * type) {
                max = arr[0][i];
                numberColumn = i;
            }
        }
        return numberColumn;
    }

    private static int searchRow(double[][] arr, int numS, int type, int numberColumn) {
        int t = arr[1].length - 1;
        double minMax = arr[1][t] / arr[1][numberColumn];
        int numberRow = 1;
        for (int i = 1; i <= numS; i++) {
            if (arr[i][numberColumn] > 0) {
                if (type == 1) {
                    if ((arr[i][t] / arr[i][numberColumn]) < minMax && (arr[i][t] / arr[i][numberColumn]) > 0) {
                        minMax = arr[i][t] / arr[i][numberColumn];
                        numberRow = i;
                    }
                } else if (type == -1) {
                    if ((arr[i][t] / arr[i][numberColumn]) > minMax && (arr[i][t] / arr[i][numberColumn]) > 0) {
                        minMax = arr[i][t] / arr[i][numberColumn];
                        numberRow = i;
                    }
                }


            }
        }
        return numberRow;
    }

    private static double[][] simplexMethod(double[][] arr, int numberRow, int numberColumn, double[] tempArr) {


        double temp = arr[numberRow][numberColumn];
        for (int i = 0; i < arr[numberRow].length; i++) {
            arr[numberRow][i] = arr[numberRow][i] / temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if (i != numberRow) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr[i][j] - tempArr[i] * arr[numberRow][j];
                }
            }
        }
        return arr;
    }


    private static int check(double[][] arr, int countX, int type) {
        int flag = 0;
        for (int i = 1; i <= countX; i++) {

            if (arr[0][i] * type < 0) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

}