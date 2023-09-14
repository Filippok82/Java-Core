package task_Stanislav;

import java.util.Random;

public class Homework {

    /*
    1.  Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
            При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
    2.  Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
            Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
            должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
    3.  В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
            MyArrayDataException и вывести результат расчета.
     */
    private static Random random = new Random();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++)
        {
            System.out.printf("\n***** Итерация %d *****\n\n", i + 1);
            processArray();
        }

    }

    /**
     * Метод генерации двумерного массива строк
     * @return
     */
    public static String[][] generateArray(){
        int add = random.nextInt(2);
        String[][] arr = new String[4 + add][4 + add];

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                if (random.nextInt(11) < 2)
                    arr[i][j] = "abc";
                else
                    arr[i][j] =  Integer.toString(random.nextInt(100));
                System.out.printf("%s ", arr[i][j]);
            }
            System.out.println("\n");
        }
        return arr;
    }

    /**
     * Метод обработки массива
     * @param arr - двумерный массив строк
     * @return - сумма всех элементов массива
     * @throws MyArraySizeException - некорректный размер массива
     */
    public static int processArrayInternal(String[][] arr) throws MyArraySizeException {

        if (arr.length > 4 || arr[0].length > 4)
            throw new MyArraySizeException("Некорректный размер массива.", arr.length, arr[0].length);

        int counter = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                try
                {
                    counter += parseElement(arr[i][j], i, j);
                }
                catch (MyArrayDataException ex){
                    System.out.printf("%s данных по индексу [%d][%d]\n",ex.getMessage(), i, j);
                }
            }
        }
        return  counter;
    }

    /**
     * Вспомогательный метод, преобразование значения элемента к числу
     * @param e - значение элемента массива для анализа
     * @param x - индекс элемента в измерении x
     * @param y - индекс элемента в измерении y
     * @return - результат преобразования строки в число
     * @throws MyArrayDataException
     */
    private static int parseElement(String e, int x, int y) throws MyArrayDataException {
        try
        {
            return Integer.parseInt(e);
        }
        catch (Exception ex){
            throw new MyArrayDataException("Некорректный формат данных", x, y);
        }
    }

    /**
     * Базовый метод - обертка
     */
    public static void processArray() {
        try {
            System.out.println("Сумма всех элементов массива: " + processArrayInternal(generateArray()));
        }
        catch (MyArraySizeException ex){
            System.out.printf("%s Требовалось 4x4, получили %dx%d\n", ex.getMessage(), ex.getX(), ex.getY());
        }
        catch (Exception ex){
            System.out.println("Неизвестная ошибка.");
        }
    }

}

abstract class MyException extends  Exception{

    private final int x;
    private final int y;

    public MyException(String message, int x, int y) {
        super(message);
        this.x = x;
        this.y = y;
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

}

class MyArraySizeException extends  MyException{
    public MyArraySizeException(String message, int x, int y) {
        super(message, x, y);
    }

}
class MyArrayDataException  extends  MyException{

    public MyArrayDataException(String message, int x, int y) {
        super(message, x, y);
    }

}