package task01;

public abstract class AnimalActionException extends AnimalNameException {

    private final int distance;

    public int getDistance() {
        return distance;
    }

    public AnimalActionException(String message, String name, int distance) {
        super(message, name);
        this.distance= distance;
    }
}
