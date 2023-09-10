package task01;

public class Cat extends Animal{
    public Cat(String name) throws AnimalNameException {
        super(name);
    }

    @Override
    void swim(int distance) throws AnimalSwimException{
        throw new AnimalSwimException("Кот плохо плавает", name,distance);
    }

    @Override
    void run(int distance) throws AnimalRunException{
        if (distance<50) {
            //TODO: Выполнени каких-то действий
        }
        else{
            throw new AnimalRunException("Кот не очень далеко бегает",name,distance);
        }

    }
}
