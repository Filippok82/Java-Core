import java.util.List;
import java.util.Random;

public abstract class Employee implements Comparable<Employee> {
    static double RATE =5000;
    private static int counter = 10;
    protected double noFixSalary;
    private int id;
    protected String name;
    protected String surName;
    protected  double salary;


    protected static String[] names = new String[]{"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман"};
    protected static String[] surNames = new String[]{"Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов"};
    protected static Random random = new Random();

    {
        id = ++counter;
    }

    protected Employee(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    protected Employee(String name, String surName, double salary) {
        if (salary < RATE) {
            throw new RuntimeException("Ставка должна быть не менее" + RATE + "рублей");
        }
        this.name = name;
        this.surName = surName;
        this.salary = salary;
    }



    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }


    /**
     * расчет среднемесячной зарплаты
     *
     * @return
     */
    public abstract double calculateSalary();


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public abstract void addEmployee(List<Employee> employee);

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", salary=" + salary +
                '}';
    }


    @Override
    public int compareTo(Employee o) {
        return Double.compare(calculateSalary(), o.calculateSalary());

    }



}



