package main.java;

import java.util.Random;
/**
 * @link http://ru.stackoverflow.com/questions/473733/%D0%9A%D0%B0%D0%BA-%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE-%D1%83%D0%BC%D0%BD%D0%BE%D0%B6%D0%B8%D1%82%D1%8C-%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D1%83-%D0%BD%D0%B0-%D0%B4%D1%80%D1%83%D0%B3%D1%83%D1%8E-%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D1%83-%D0%B2-%D0%BD%D0%B5%D1%81%D0%BA%D0%BE%D0%BB%D1%8C%D0%BA%D0%B8%D1%85-%D0%BF%D0%BE%D1%82%D0%BE%D0%BA%D0%B0%D1%85
 */


public class Main {

    final static int FIRST_MATRIX_ROWS = 1000;
    final static int FIRST_MATRIX_COLS = 1000;

    final static int SECOND_MATRIX_ROWS = FIRST_MATRIX_COLS;
    final static int SECOND_MATRIX_COLS = 1000;

    public static void main(String[] args) {

        final int[][] firstMatrix = new int[FIRST_MATRIX_ROWS][FIRST_MATRIX_COLS];
        final int[][] secondMatrix = new int[SECOND_MATRIX_ROWS][SECOND_MATRIX_COLS];

        randomMatrix(firstMatrix);
        randomMatrix(secondMatrix);

        final int[][] resultMatrixMT = multiplyMatrixMT(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors());
        final int[][] resultMatrix = multiplyMatrix(firstMatrix, secondMatrix);

    }


    private static void randomMatrix(final int[][] matrix){
        final Random random = new Random();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = random.nextInt(100);
            }
        }
    }

    private static int[][] multiplyMatrixMT(final int[][] firstMatrix, final int[][] secondMatrix, final int threadCount){
        assert threadCount > 0;

        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;

        final int[][] result = new int[rowCount][colCount];

        final int cellsForThread = rowCount * colCount / threadCount;

        int firstIndex = 0;

        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];

        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) { // backward
            int lastIndex = firstIndex + cellsForThread;
            if(threadIndex == 0){
                lastIndex = rowCount * colCount;
            }

            multiplierThreads[threadIndex] = new MultiplierThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        try {
            for (final MultiplierThread multiplierThread: multiplierThreads){
                multiplierThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private int[][] multiplyMatrix(final int[][] firstMatrix, final int[][] secondMatrix){
        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int sumLength = secondMatrix.length;
        final int [][] result = new int[rowCount][colCount];

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int sum = 0;
                for (int i = 0; i < sumLength; ++i){
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                }
                result[row][col] = sum;
            }
        }

    }
}
