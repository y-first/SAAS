package com.heima.controller;

import com.heima.pojo.*;
import com.heima.service.PermissionService;
import com.heima.utils.BeanMapUtils;
import com.heima.utils.IdWorker;
import com.heima.utils.PermissionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @PostMapping("/permission")
    public Result save(@RequestBody Map<String, Object> map) throws Exception {
        permissionService.save(map);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("/permission/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody Map<String, Object> map) throws Exception {
        map.put("id", id);
        permissionService.update(map);
        return  new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/permission")
    public Result findAll(@RequestParam Map map)
    {
        permissionService.findAll(map);
        return  new Result(ResultCode.SUCCESS);
    }

//    @GetMapping("/permission/{id}")
//    public Result findById(@PathVariable("id") String id){
//
//    }

}
