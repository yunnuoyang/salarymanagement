package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.SalaryStandard;
import com.joinx.salary.repository.SalaryStandardRespository;
import com.joinx.salary.service.SalaryStandardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SalaryStandard)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 14:06:21
 */
@Service("salaryStandardService")
public class SalaryStandardServiceImpl implements SalaryStandardService {
    @Resource
    private SalaryStandardRespository salaryStandardRespository;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SalaryStandard queryById(Integer id) {
        return this.salaryStandardRespository.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SalaryStandard> queryAllByLimit(int offset, int limit) {
        return this.salaryStandardRespository.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param salaryStandard 实例对象
     * @return 实例对象
     */
    @Override
    public SalaryStandard insert(SalaryStandard salaryStandard) {
        this.salaryStandardRespository.insert(salaryStandard);
        return salaryStandard;
    }

    /**
     * 修改数据
     *
     * @param salaryStandard 实例对象
     * @return 实例对象
     */
    @Override
    public SalaryStandard update(SalaryStandard salaryStandard) {
        this.salaryStandardRespository.update(salaryStandard);
        return this.queryById(salaryStandard.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.salaryStandardRespository.deleteById(id) > 0;
    }
}