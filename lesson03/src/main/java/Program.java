import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        GroupEmployee employee = new GroupEmployee();
        employee.addEmployee(Frilancer.getEmployees(5));
        employee.addEmployee(Worker.getEmployees(5));
        System.out.println(employee);


//        employee.sort(new EmployeeNameComparator());
//        System.out.println("*************************");
//
//        for (Employee employee: employee) {
//            System.out.println(employee);
//        }
    }
}
