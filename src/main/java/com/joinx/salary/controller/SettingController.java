package com.joinx.salary.controller;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.SalaryStandard;
import com.joinx.salary.pojo.Standard;
import com.joinx.salary.repository.StandardRepository;
import com.joinx.salary.util.DecorateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SettingController {

    @Autowired
    private StandardRepository standardRepository;
    @RequestMapping("/standard")
    @ResponseBody
    public Map standard(){
        Standard standard = standardRepository.getStandard();
        ArrayList<Object> data = new ArrayList<>();
        Map map = new DecorateMap()
                .put("onLineDays", standard.getOnlineDays())
                .put("onLineHours", standard.getOnlineHours())
                .put("workStartHour", standard.getWorkStartHour())
                .put("id", standard.getId());
        data.add(map);
        return  new DecorateMap()
                .put("status", 200)
                .put("message", "")
                .put("total",data.size())
                .put("data",data);

    }
    @RequestMapping("/settings/edit")
    @ResponseBody
    public void edit(Standard standard){
        standardRepository.update(standard);
    }
}
