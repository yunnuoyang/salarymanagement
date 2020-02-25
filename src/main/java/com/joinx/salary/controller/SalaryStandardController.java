package com.joinx.salary.controller;

import com.joinx.salary.pojo.SalaryStandard;
import com.joinx.salary.service.SalaryStandardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SalaryStandard)表控制层
 *
 * @author makejava
 * @since 2020-02-16 14:06:21
 */
@RestController
@RequestMapping("salaryStandard")
public class SalaryStandardController {
    /**
     * 服务对象
     */
    @Resource
    private SalaryStandardService salaryStandardService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SalaryStandard selectOne(Integer id) {
        return this.salaryStandardService.queryById(id);
    }

}