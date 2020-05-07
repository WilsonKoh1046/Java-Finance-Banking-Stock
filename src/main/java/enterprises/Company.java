package enterprises;

import account.Account;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Company extends CompanyManagement {

    private String companyName;
    private String companyEmail;
    private CompanyType companyType;
    private int companyFund;
    private int totalEmployeePayCheck;
    private int companyProfit;

    public Company(String companyName, String companyEmail, CompanyType companyType, int companyFund) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyType = companyType;
        this.companyFund = companyFund;
        this.totalEmployeePayCheck = 0;
        this.companyProfit = 0;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public List<Department> getDepartmentList() {
        List<Department> acquired_department;
        try {
            acquired_department = getList_of_departments().stream()
                    .filter(d -> d.getCompany().getCompanyName().equals(this.companyName))
                    .collect(Collectors.toList());
        } catch(NullPointerException e) {
            acquired_department = new ArrayList<>();
        }
        return acquired_department;
    }

    public int getCompanyFund() {
        return companyFund;
    }

    public void setCompanyFund(int companyFund) {
        this.companyFund = companyFund;
    }

    public int getTotalEmployeePayCheck() {
        return totalEmployeePayCheck;
    }

    public void setTotalEmployeePayCheck(int totalEmployeePayCheck) {
        this.totalEmployeePayCheck = totalEmployeePayCheck;
    }

    public void hireEmployee(Department department, Account account, int salary) { ;
        if (Optional.ofNullable(account).isPresent()) {
            assignEmployee(department, account, salary, this);
            totalEmployeePayCheck += salary;
        } else {
            System.out.println("No such account!");
        }
    }

    public void fireEmployee(Department department, Account account) {
        if (Optional.ofNullable(account).isPresent()) {
            int employee_current_salary = account.getMonthlySalary();
            removeEmployee(department, account);
            totalEmployeePayCheck -= employee_current_salary;
        } else {
            System.out.println("No such employee!");
        }
    }

    public int getCompanyProfit() {
        return getTotalDepartmentsProfit(this);
    }
}
