package com.joinx.salary.controller;

import com.joinx.salary.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PathController {
    @RequestMapping("/path/{operate}/{place}")
    public String userList(@PathVariable String operate, @PathVariable String place){
        System.out.println(operate+"/"+place);
        return operate+"/"+place;
    }
}
