package enterprises;

import account.Account;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import account.*;

public class Main {

    public static void main(String[] args) {
        List<Account> employees = List.of(
                new Account("john", UUID.randomUUID()),
                new Account("mary", UUID.randomUUID())
        );
        List<Account> employees2 = List.of(
                new Account("simon", UUID.randomUUID()),
                new Account("jane", UUID.randomUUID())
        );
        Company company1 = new Company("Apple", "apple@email.com", CompanyType.PROFIT);
        Company company2 = new Company("Banana", "banana@email.com", CompanyType.NONPROFIT);
        System.out.println(company1.getDepartmentList());
        System.out.println(company2.getDepartmentList());
        Department department1 = new Department("finance", employees, company1);
        Department department2 = new Department("technical", employees2, company1);
        Department department3 = new Department("security", employees2, company1);
        Department department4 = new Department("fashion", employees, company2);
        Department department5 = new Department("music", employees2, company2);
        Department department6 = new Department("culinary", employees2, company2);
        System.out.println("Company 1 current list of departments: ");
        for (Department departmentname: company1.getDepartmentList()) {
            System.out.println(departmentname.getDepartmentName());
            System.out.println(departmentname.getEmployees());
        }
        System.out.println("Company 2 current list of departments: ");
        for (Department departmentname: company2.getDepartmentList()) {
            System.out.println(departmentname.getDepartmentName());
            System.out.println(departmentname.getEmployees());
        }
    }
}
