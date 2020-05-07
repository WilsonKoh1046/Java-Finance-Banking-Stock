package enterprises;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Company extends CompanyManagement {

    private String companyName;
    private String companyEmail;
    private CompanyType companyType;

    public Company(String companyName, String companyEmail, CompanyType companyType) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyType = companyType;
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

}
