package com.heima.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heima.mapper.DepartmentMapper;
import com.heima.pojo.Department;
import com.heima.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private IdWorker idWorker;

    public int save(Department department) {
        String id = idWorker.nextId() + "";
        department.setId(id);
        int insert = departmentMapper.insert(department);
        return insert;
    }

    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    public Department selectById(String id) {
        return  departmentMapper.selectById(id);
    }

    public List<Department> selectAll(String id) {
        QueryWrapper q = new QueryWrapper();
        q.eq("company_id",id);
        return departmentMapper.selectList(q) ;
    }

    public void deleteById(String id) {
        departmentMapper.deleteById(id);
    }
}
