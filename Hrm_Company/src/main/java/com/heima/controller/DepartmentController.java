package com.heima.controller;

import com.heima.ResultList;
import com.heima.pojo.Company;
import com.heima.pojo.Department;
import com.heima.pojo.Result;
import com.heima.pojo.ResultCode;
import com.heima.service.CompanyService;
import com.heima.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class DepartmentController extends BaseController{
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;
    @PostMapping("/department")
    public Result save(@RequestBody Department department) {
        String companyId = "1";
        department.setCompanyId(companyId);
        int save = departmentService.save(department);
        System.out.println(save);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/department")
    public Result select() {
        System.out.println(companyId);
        List<Department> departments = departmentService.selectAll(companyId);
        if (CollectionUtils.isEmpty(departments)) {
            return new Result(ResultCode.FAIL);
        }
        Company company = companyService.queryCompany(companyId);
        ResultList resultList = new ResultList(company, departments);
        return new Result(ResultCode.SUCCESS,resultList);
    }

    @GetMapping("/department/{id}")
    public Result selectById(@PathVariable("id") String id) {
        Department department = departmentService.selectById(id);
        if (StringUtils.isEmpty(department)) {
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS, department);
    }

    @PutMapping("/department/{id}")
    public Result update(@PathVariable("id") String id,Department department){
        department.setId(id);
        departmentService.update(department);
        return  new Result(ResultCode.SUCCESS);
    }
    @DeleteMapping("department/{id}")
    public Result delete(@PathVariable("id") String id){
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
