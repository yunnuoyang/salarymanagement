package com.joinx.salary.service;

import com.joinx.salary.pojo.SalaryStandard;
import java.util.List;

/**
 * (SalaryStandard)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 14:06:21
 */
public interface SalaryStandardService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SalaryStandard queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SalaryStandard> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param salaryStandard 实例对象
     * @return 实例对象
     */
    SalaryStandard insert(SalaryStandard salaryStandard);

    /**
     * 修改数据
     *
     * @param salaryStandard 实例对象
     * @return 实例对象
     */
    SalaryStandard update(SalaryStandard salaryStandard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}