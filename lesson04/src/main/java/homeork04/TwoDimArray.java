package homeork04;


import java.util.*;


public class TwoDimArray {
    private static final Random random = new Random();
    private static final int row = random.nextInt(4, 6);
    private static final int colum = row;
    private static final String[][] twoArray = new String[row][colum];
    private static final int[][] strTwoArray = new int[row][colum];
    static int size = 4;





    public static void creatingArray() throws MyArraySizeException {
        if (row != size)
            throw new MyArraySizeException("Размер массива выбран не верно", size);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                twoArray[i][j] = String.valueOf(random.nextInt(1, 9));
                if (Objects.equals(twoArray[i][j], "2")) {
                    twoArray[i][j] = "@";

                }
            }

        }
        System.out.println(Arrays.deepToString(twoArray));
    }

    public static boolean isInteger() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                try {
                    strTwoArray[i][j] = Integer.parseInt(twoArray[i][j]);
                } catch (NumberFormatException e) {
                    return false;
                }

            }
        }
        return true;
    }
    public static void sumElementsArray() throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {

                if (!isInteger()) {
                    throw new MyArrayDataException("В массиве лежит не число");
                } else {
                    sum += strTwoArray[i][j];
                }
            }
        }
        System.out.println("Сумма элементов " + sum);
    }

}
