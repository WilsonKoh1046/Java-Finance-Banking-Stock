package enterprises;

import account.Account;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import account.*;
import bank.Bank;

public class Main {

    public static void main(String[] args) {
        List<Account> employees = List.of(
                new Account("john", UUID.randomUUID(), 3000, null),
                new Account("mary", UUID.randomUUID(), 3000, null)
        );
        List<Account> employees2 = List.of(
                new Account("simon", UUID.randomUUID(), 4000, null),
                new Account("jane", UUID.randomUUID(), 4000, null)
        );
        Company company1 = new Company("Apple", "apple@email.com", CompanyType.PROFIT, 10000);
        Company company2 = new Company("Banana", "banana@email.com", CompanyType.NONPROFIT, 15000);
        System.out.println(company1.getDepartmentList());
        System.out.println(company2.getDepartmentList());
        Department department1 = new Department("finance", company1);
        Department department2 = new Department("technical", company1);
        Department department3 = new Department("security", company1);
        Department department4 = new Department("fashion", company2);
        Department department5 = new Department("music", company2);
        Department department6 = new Department("culinary", company2);
        System.out.println();
        Account acc3 = new Account("peter", UUID.randomUUID(), 0, null);
        Account acc4 = new Account("chris", UUID.randomUUID(), 0, null);
        System.out.println(acc3.getMonthlySalary());
        System.out.println(acc4.getMonthlySalary());
        company1.hireEmployee(department1, acc3, 5000);
        System.out.println("Department 1 of Company 1 current employee: ");
        for (Account employee: department1.getEmployees()) {
            System.out.println(employee.getName());
        }
        System.out.println();
        System.out.println("Acc 3 current company: " + acc3.getCompany().getCompanyName());
        System.out.println("Acc3 current salary: " + acc3.getMonthlySalary());
        System.out.println("Company 1 total employee pay check: " + company1.getTotalEmployeePayCheck());
        company1.hireEmployee(department2, acc4, 5000);
        System.out.println("Department 2 of Company 1 current employee: ");
        for (Account employee: department2.getEmployees()) {
            System.out.println(employee.getName());
        }
        System.out.println();
        System.out.println("Acc 4 current company: " + acc4.getCompany().getCompanyName());
        System.out.println("Acc4 current salary: " + acc4.getMonthlySalary());
        System.out.println("Company 1 total employee pay check: " + company1.getTotalEmployeePayCheck());
        System.out.println();
        company1.fireEmployee(department1, acc3);
        System.out.println("Department 1 of Company 1 current employee: ");
        for (Account employee: department1.getEmployees()) {
            System.out.println(employee.getName());
        }
        System.out.println();
        // System.out.println("Acc 3 current company: " + acc3.getCompany().getCompanyName());
        System.out.println("Acc3 current salary: " + acc3.getMonthlySalary());
        System.out.println("Company 1 total employee pay check: " + company1.getTotalEmployeePayCheck());
    }
}
