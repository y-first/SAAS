package com.heima.controller;

import com.heima.pojo.PageResult;
import com.heima.pojo.Result;
import com.heima.pojo.ResultCode;
import com.heima.pojo.User;
import com.heima.service.UserService;
import com.heima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public Result save(@RequestBody User user) {
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        userService.save(user);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("/user/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/user")
    public Result select(Map<String, Object> map, int page, int size) {
        PageResult<User> select = userService.select(map, page, size);

        return new Result(ResultCode.SUCCESS, select);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> m1) {
        String mobile = m1.get("mobile");
        String password = m1.get("password");
        User i = userService.selectByPhone(mobile, password);
        if (StringUtils.isEmpty(i)) {
            return new Result(ResultCode.FAIL);
        }
        Map<String, Object> m = new HashMap<>();
        m.put("companyId", i.getCompanyId());
        m.put("companyName", i.getCompanyName());
        String jwt = JwtUtils.createJwt(i.getId(), i.getUsername(), m);
        return new Result(ResultCode.SUCCESS, jwt);
    }

    @PostMapping("/profile")
    public Result profile(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return Result.ERROR();
        }
        String bearer_ = authorization.replace("Bearer ", "");
        Claims claims = JwtUtils.parseJwt(bearer_);
        String id = claims.getId();
        return null;
    }
}
