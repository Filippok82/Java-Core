package task01;

public abstract class Animal {
    protected final String name;
    public String getName() {
        return name;
    }

    /**
     * Создание нового животного
     * @param name имя животного
     * @throws AnimalNameException некорректное имя
     */
    public Animal(String name)throws AnimalNameException {
        if (name==null||name.length()<3 )
            throw new AnimalNameException("Имя животного указано нe корректно", name);

        this.name = name;
    }


    abstract void swim(int distance)throws AnimalSwimException;
    abstract void run(int distance)throws AnimalRunException;
}
