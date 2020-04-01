package com.heima.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.mapper.UserMapper;
import com.heima.pojo.PageResult;
import com.heima.pojo.Permission;
import com.heima.pojo.User;
import com.heima.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    public void save(User user) {
        String id = idWorker.nextId() + "";
        user.setPassword("123456");
        user.setId(id);
        user.setEnableState(1);
        System.out.println(user);
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.updateById(user);
    }


    public PageResult<User> select(Map<String, Object> map, int page, int size) {
        Page<User> p = new Page<>(page, size);
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("company_id", map.get("companyId")).or().eq("department_id", map.get("departmentId")).or();
        Page<User> userPage = userMapper.selectPage(p, new QueryWrapper<>());
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setTotal(userPage.getTotal());
        pageResult.setRows(userPage.getRecords());
        return pageResult;
    }

    public User selectByPhone(String mobile, String password) {
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("mobile", mobile).eq("password", password);
        User user = userMapper.selectOne(q);
        if (StringUtils.isEmpty(user)) {
            return null;
        }
        return user;
    }

}
