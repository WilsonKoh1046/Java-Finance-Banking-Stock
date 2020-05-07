package enterprises;

import account.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Department extends CompanyManagement {

    private String departmentName;
    private List<Account> employees;
    private int departmentProfit;
    private Company company;

    public Department(String departmentName, Company company) {
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
        departmentProfit = 0;
        this.company = company;
        setList_of_departments(this);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Account> getEmployees() {
        return employees;
    }

    public void addEmployees(Account employee) {
        this.employees.add(employee);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getDepartmentProfit() {
        return departmentProfit;
    }

    public void setDepartmentProfit(int departmentProfit) {
        this.departmentProfit = departmentProfit;
    }
}
