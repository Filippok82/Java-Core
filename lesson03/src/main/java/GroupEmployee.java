import java.util.ArrayList;
import java.util.List;

public  class GroupEmployee{


    private List<Employee> groupList;

    @Override
    public String toString() {
        return "Группа сотрудников: \n" +  groupList;
    }

    public GroupEmployee() {
        groupList = new ArrayList<>();
    }





    public void addEmployee(List<Employee> employee) {
        groupList.addAll(employee);

    }



}