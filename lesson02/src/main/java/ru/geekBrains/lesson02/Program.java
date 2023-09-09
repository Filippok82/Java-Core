package ru.geekBrains.lesson02;


import java.util.Random;
import java.util.Scanner;



public class Program {
    private static final int WIN_COUNT = 4;//комбинация для выигрыша
    private static final char DOT_HUMAN = 'x';//человек
    private static final char DOT_AI = '0';//компьютер
    private static final char DOT_EMPTY = '*';//пустое поле
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field; // игоровое поле
    private static int fieldSizeX; //размерность игрового поля
    private static int fieldSizeY; //размерность игрового поля


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameChecks(DOT_HUMAN, WIN_COUNT, "Human win")) break;
                aiTurn();
                printField();
                if (gameChecks(DOT_AI, WIN_COUNT, "Computer win")) break;
            }
            System.out.println("Play again?");
            if (!scanner.next().equals("Y"))
                break;
        }
    }

    /**
     * Инициализация объектов
     */
    private static void initialize() {
        System.out.print("Введите размерность поля по вертикали:");
        scanner.hasNextInt();
        fieldSizeX = scanner.nextInt();
        System.out.print("Введите размерность поля по горизонтали:");
        scanner.hasNextInt();
        fieldSizeY = scanner.nextInt();
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }

    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++) {
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * Обработка хода человека
     */
    private static void humanTurn() {
        int x, y;

        do {

            while (true) {
                System.out.print("Введите координату хода X (от 1 до " + fieldSizeX + "):");
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ошибка, повторите попытку ввода");
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.print("Введите координату хода Y (от 1 до " + fieldSizeY + "):");
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Ошибка, повторите попытку ввода");
                    scanner.nextLine();
                }
            }
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода компьютера
     */
    private static void aiTurn() {

        // Побеждает ли компьютер в текущем ходе (при выигрышной комбинации WIN_COUNT)?
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) {
                    field[y][x] = DOT_AI;
                    if (checkWin(DOT_AI, WIN_COUNT))
                        return;
                    else
                        field[y][x] = DOT_EMPTY;
                }
            }
        }
        // Побеждает ли игрок на текущий момент при выигрышной комбинации WIN_COUNT - 1?
        boolean f = checkWin(DOT_HUMAN, WIN_COUNT - 1);
        // Теперь, снова пройдем по всем свободным ячейкам игрового поля, если игрок уже побеждает при
        // выигрышной комбинации WIN_COUNT - 1, компьютер попытается закрыть последнюю выигрышную ячейку.
        // Если игрок НЕ побеждает при выигрышной комбинации WIN_COUNT - 1, компьютер будет действовать
        // на опережение, попытается заранее "подпортить" человеку выигрышную комбинацию.
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY){
                    field[y][x] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN, WIN_COUNT - (f ? 0 : 1))) {
                        field[y][x] = DOT_AI;
                        return;
                    }
                    else
                        field[y][x] = DOT_EMPTY;
                }
            }
        }

        // Ни человек, ни компьютер не выигрывают, значит, компьютер ставит фишку случайным образом
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    /**
     * Проверка состояния игры
     *
     * @param c фишка игрока
     * @param s победный слоган
     * @return
     */
        /**
         * Метод проверки состояния игры
         * @param dot фишка игрока (человек/компьютер)
         * @param win выигрышная комбинация
         * @param s победное сообщение
         * @return результат проверки
         */
        private static boolean gameChecks ( char dot, int win, String s){
            if (checkWin(dot, win)) {
                System.out.println(s);
                return true;
            }
            if (checkDraw()) {
                System.out.println("draw!");
                return true;
            }
            return false;
        }

        /**
         * Проверка победы игрока
         * @param dot фишка игрока (человек или компьютер)
         * @param winCount кол-во фишек для победы
         * @return
         */
        static boolean checkWin ( char dot, int winCount){
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (field[y][x] == dot)
                        if (checkXY(y, x, 1, winCount) ||
                                checkXY(y, x, -1, winCount) ||
                                checkDiagonal(y, x, -1, winCount) ||
                                checkDiagonal(y, x, 1, winCount))
                            return true;
                }
            }
            return false;
        }


        /**
         * Проверка на ничью
         *
         * @return
         */
        private static boolean checkDraw () {
            for (int x = 0; x < fieldSizeX; x++) {
                for (int y = 0; y < fieldSizeY; y++) {
                    if (isCellEmpty(x, y)) return false;
                }
            }
            return true;
        }
        //region Универсальная проверка победы игрока (задача 3*)

        /**
         * Проверка выигрыша игрока (человек или компьютер) горизонтали + вправо/вертикали + вниз
         * @param x начальная координата фишки
         * @param y начальная координата фишки
         * @param dir направление проверки (-1 => горизонтали + вправо/ 1 => вертикали + вниз)
         * @param win выигрышная комбинация
         * @return результат проверки
         */
        static boolean checkXY ( int x, int y, int dir, int win){
            char c = field[x][y]; // получим текущую фишку (игрок или компьютер)
            // Пройдем по всем ячейкам от начальной координаты (например 2,3) по горизонтали вправо и по вертикали вниз
            // (в зависимости от значения параметра dir)
        /*  +-1-2-3-4-5-
            1|.|.|.|.|.|
            2|.|.|.|.|.|
            3|.|X|X|X|X|
            4|.|X|.|.|.|
            5|.|X|.|.|.|
            ------------
        */
            for (int i = 1; i < win; i++)
                if (dir > 0 && (!isCellValid(x + i, y) || c != field[x + i][y])) return false;
                else if (dir < 0 && (!isCellValid(x, y + i) || c != field[x][y + i])) return false;
            return true;
        }
        /**
         * Проверка выигрыша игрока (человек или компьютер) по диагонали вверх + вправо/вниз + вправо
         * @param x начальная координата фишки
         * @param y начальная координата фишки
         * @param dir направление проверки (-1 => вверх + вправо/ 1 => вниз + вправо)
         * @param win кол-во фишек для победы
         * @return результат проверки
         */
        static boolean checkDiagonal ( int x, int y, int dir, int win){
            char c = field[x][y]; // получим текущую фишку (игрок или компьютер)
            // Пройдем по всем ячейкам от начальной координаты (например 3,3) по диагонали вверх и по диагонали вниз
            // (в зависимости от значения параметра dir)
        /*  +-1-2-3-4-5-
            1|.|.|.|.|X|
            2|.|.|.|X|.|
            3|.|.|X|.|.|
            4|.|.|.|X|.|
            5|.|.|.|.|X|
            ------------
        */
            for (int i = 1; i < win; i++)
                if (!isCellValid(x + i, y + i * dir) || c != field[x + i][y + i * dir]) return false;
            return true;
        }

    }











