package enterprises;

import java.util.ArrayList;
import java.util.List;

public abstract class CompanyManagement {

    private static List<Department> list_of_departments = new ArrayList<>();

    public static void setList_of_departments(Department department) {
        list_of_departments.add(department);
    }

    public static List<Department> getList_of_departments() {
        return list_of_departments;
    }
}
