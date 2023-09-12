package homeork04;


public class Program {
    public static void main(String[] args) {
        try {
            TwoDimArray.creatingArray();

            try {
                TwoDimArray.sumElementsArray();
            } catch (MyArrayDataException e) {
                System.out.printf("Если в массиве лежит не число  " + "  выводиться ошибка - " + e.getMessage());
            }
        } catch (MyArraySizeException e) {
            System.out.printf("При попытке генерации массива не равного размеру " + e.getSize() + " выводиться ошибка - " + e.getMessage());


        }
    }


}


