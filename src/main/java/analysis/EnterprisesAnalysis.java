package analysis;

import enterprises.Company;
import enterprises.CompanyManagement;

import java.util.ArrayList;
import java.util.List;

public class EnterprisesAnalysis {

    private static List<Company> list_of_company = new ArrayList<>();

    public static List<Company> getList_of_company() {
        return list_of_company;
    }

    public static void addNewCompany(Company company) {
        list_of_company.add(company);
    }
}
