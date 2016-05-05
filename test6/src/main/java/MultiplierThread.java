package main.java;


class MultiplierThread extends Thread{

    private final int[][] firstMatrix;
    private final int[][] secondMatrix;
    private final int[][] resultMatrix;

    private final int firstIndex;
    private final int lastIndex;
    private final int sumLength; /** Число членов суммы при вычислении значения ячейки. */

    public MultiplierThread(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix, int firstIndex, int lastIndex) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;

        sumLength = secondMatrix.length;
    }

    @Override
    public void run() {
        System.out.println("Thread " + this.getName() + " started. Calculating cells from " + firstIndex + " to " + lastIndex + "...");

        final int colCount = secondMatrix[0].length;
        for (int index = firstIndex; index < lastIndex; index++) {
            calcValue(index/colCount, index%colCount);
        }
        System.out.println("Thread " + getName() + " finished.");
    }

    private void calcValue(final int row, final int col){
        int sum = 0;
        for (int i = 0; i < sumLength; i++) {
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        }
        resultMatrix[row][col] = sum;
    }
}
