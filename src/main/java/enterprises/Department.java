package enterprises;

import account.*;

import java.util.List;

public class Department extends CompanyManagement {

    private String departmentName;
    private List<Account> employees;
    private Company company;

    public Department(String departmentName, List<Account> employees, Company company) {
        this.departmentName = departmentName;
        this.employees = employees;
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

    public void setEmployees(List<Account> employees) {
        this.employees = employees;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
