import java.util.ArrayList;
import java.util.List;



public class Frilancer extends Employee{


    public Frilancer(String name, String surName,double noFixSalary) {
        super(name, surName);
        this.noFixSalary=noFixSalary;
    }

    public static Employee getInstance() {

        return new Frilancer(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(names.length)],
                RATE*random.nextInt(1,20)*random.nextInt(1,8));


    }

    public static List<Employee> getEmployees(int count) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }


    @Override
    public  double calculateSalary() {
        return noFixSalary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер; Среднемесячная заработная плата (почасовая  оплата): %.2f (руб.)\n",
                name, surName, noFixSalary);
    }

    @Override
    public void addEmployee(List<Employee> employee) {

    }


}