package com.heima.controller;

import com.heima.pojo.Company;
import com.heima.pojo.Result;
import com.heima.pojo.ResultCode;
import com.heima.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{companyId}")
    public Result c(@PathVariable("companyId")String id)
    {
        Company company = companyService.queryCompany(id);
        return new Result(ResultCode.SUCCESS,company);
    }

    @PostMapping
    public Result save(@RequestBody Company company){
        companyService.save(company);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("/{companyId}")
    public Result update(@PathVariable("companyId")String id,@RequestBody Company company){
        companyService.update(id,company);
        return new Result(ResultCode.SUCCESS);
    }
    @DeleteMapping("/{companyId}")
    public Result deleteById(@PathVariable("companyId")String id){
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping
    public Result selectAll()
    {
        List<Company> all = companyService.findAll();
        if(CollectionUtils.isEmpty(all)){
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS,all);
    }
}
