package com.heima.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heima.mapper.CompanyMapper;
import com.heima.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    public Company queryCompany(String id) {
        Company company = companyMapper.selectById(id);
        return company;
    }

    public void deleteById(String id) {
        companyMapper.deleteById(id);
    }

    public List<Company> findAll() {
        List<Company> companies = companyMapper.selectList(new QueryWrapper<>());
        return companies;
    }


    public void save(Company company) {
        companyMapper.insert(company);
    }

    public void update(String id, Company company) {
        companyMapper.updateById(company);
    }
}
