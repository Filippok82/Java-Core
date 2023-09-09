import java.util.ArrayList;
import java.util.List;

public class Worker extends Employee{

    public Worker(String name, String surName, double salary) {
        super(name,surName,salary);
    }
    public static Employee getInstance() {
        return new Worker(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(names.length)],
                random.nextInt(30000, 300000));

    }

    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }


    @Override
    public double calculateSalary(){
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.)\n",
                surName, name, salary);
    }

    @Override
    public void addEmployee(List<Employee> employee) {

    }


}
