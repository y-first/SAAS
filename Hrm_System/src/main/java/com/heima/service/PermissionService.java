package com.heima.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heima.mapper.PermissionApiMapper;
import com.heima.mapper.PermissionMapper;
import com.heima.mapper.PermissionMenuMapper;
import com.heima.mapper.PermissionPointMapper;
import com.heima.pojo.Permission;
import com.heima.pojo.PermissionApi;
import com.heima.pojo.PermissionMenu;
import com.heima.pojo.PermissionPoint;
import com.heima.utils.BeanMapUtils;
import com.heima.utils.IdWorker;
import com.heima.utils.PermissionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    private PermissionApiMapper permissionApiMapper;
    @Autowired
    private PermissionPointMapper permissionPointMapper;
    @Autowired
    private PermissionMenuMapper permissionMenuMapper;
    @Autowired
    private IdWorker idWorker;

    public void save(Map<String, Object> map) throws Exception {
        String id = idWorker.nextId() + "";
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        perm.setId(id);
        Integer type = perm.getType();
        switch (type) {
            case PermissionConstants
                    .PY_MENU:
                PermissionMenu permissionMenu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                permissionMenu.setId(id);
                permissionMenuMapper.insert(permissionMenu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint permissionPoint = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                permissionPoint.setId(id);
                permissionPointMapper.insert(permissionPoint);
                break;
            case PermissionConstants.PY_API:
                PermissionApi permissionApi = BeanMapUtils.mapToBean(map, PermissionApi.class);
                permissionApi.setId(id);
                permissionApiMapper.insert(permissionApi);
                break;
        }
        permissionMapper.insert(perm);
    }

    public void update(Map<String, Object> map) throws Exception {

        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        Integer type = perm.getType();
        switch (type) {
            case PermissionConstants
                    .PY_MENU:
                PermissionMenu permissionMenu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                permissionMenu.setId(perm.getId());
                permissionMenuMapper.updateById(permissionMenu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint permissionPoint = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                permissionPoint.setId(perm.getId());
                permissionPointMapper.updateById(permissionPoint);
                break;
            case PermissionConstants.PY_API:
                PermissionApi permissionApi = BeanMapUtils.mapToBean(map, PermissionApi.class);
                permissionApi.setId(perm.getId());
                permissionApiMapper.updateById(permissionApi);
                break;
        }
        permissionMapper.updateById(perm);
    }

    public List<Permission> findAll(Map<String, Object> map) {
        QueryWrapper<Permission> q = new QueryWrapper<>();
        if (!StringUtils.isEmpty(map.get("pid"))) {
            q.eq("pid", map.get("pid"));
        }
        if (!StringUtils.isEmpty(map.get("enVisible"))) {
            q.eq("enVisible", map.get("enVisible"));
        }
        if (!StringUtils.isEmpty(map.get("type"))) {
            q.eq("type", map.get("type"));
        }
        List<Permission> permissions = permissionMapper.selectList(q);
        return permissions;
    }
}
