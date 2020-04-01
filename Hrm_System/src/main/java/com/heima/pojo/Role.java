package com.heima.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@TableName(value = "pe_role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;
    @TableId
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;


    private Set<User> users = new HashSet<User>(0);//角色与用户   多对多


    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多
}