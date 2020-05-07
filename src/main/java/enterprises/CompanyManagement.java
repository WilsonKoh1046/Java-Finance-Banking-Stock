package enterprises;

import account.Account;
import bank.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CompanyManagement {

    private static List<Department> list_of_departments = new ArrayList<>();

    public static void setList_of_departments(Department department) {
        list_of_departments.add(department);
    }

    public static List<Department> getList_of_departments() {
        return list_of_departments;
    }

    public static void assignEmployee(Department department, Account account, int salary, Company company) {
        Optional<Department> targeted_department = getList_of_departments().stream()
                .filter(d -> d.getDepartmentName().equals(department.getDepartmentName()))
                .findFirst();
        Optional<Account> targeted_account = Bank.getDB().stream()
                .filter(a -> a.getId() == account.getId())
                .findFirst();
        if (targeted_account.isPresent() && targeted_department.isPresent()) {
            targeted_department.get().addEmployees(account);
            targeted_account.get().setCompany(company);
            targeted_account.get().setMonthlySalary(salary);
        } else {
            System.out.println("No such department!");
        }
    }

    public static void removeEmployee(Department department, Account account) {
        Optional<Department> targeted_department = getList_of_departments().stream()
                .filter(d -> d.getDepartmentName().equals(department.getDepartmentName()))
                .findFirst();
        Optional<Account> targeted_account = Bank.getDB().stream()
                .filter(a -> a.getId() == account.getId())
                .findFirst();
        if (targeted_account.isPresent() && targeted_department.isPresent()) {
            targeted_department.get().getEmployees().remove(account);
            targeted_account.get().setMonthlySalary(0);
            targeted_account.get().setCompany(null);
        } else {
            System.out.println("No such department!");
        }
    }

    public int getTotalDepartmentsProfit(Company company) {
        int totalProfit = 0;
        List<Department> acquired_department;
        try {
            acquired_department = getList_of_departments().stream()
                    .filter(d -> d.getCompany().getCompanyName().equals(company.getCompanyName()))
                    .collect(Collectors.toList());
        } catch(NullPointerException e) {
            acquired_department = null;
        }
        if (Optional.ofNullable(acquired_department).isPresent()) {
            for (Department department: acquired_department) {
                totalProfit += department.getDepartmentProfit();
            }
            return totalProfit;
        }
        return 0;
    }
}
