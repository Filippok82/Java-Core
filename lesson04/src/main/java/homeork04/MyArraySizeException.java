package homeork04;

public class MyArraySizeException extends  Exception {

    public int getSize() {
        return size;
    }

    private final int size;



    public MyArraySizeException(String message, int size) {
        super(message);
        this.size=size;

    }
}
