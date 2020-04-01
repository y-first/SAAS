package com.heima;

import com.heima.pojo.Company;
import com.heima.pojo.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultList {
    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> depts;

    public ResultList(Company company, List depts) {
        this.companyId = "1";
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();
        this.depts = depts;
    }
}
