package com.joinx.salary.service;

import com.joinx.salary.pojo.SalaryOperate;
import java.util.List;

/**
 * (SalaryOperate)表服务接口
 *
 * @author makejava
 * @since 2020-02-19 15:46:43
 */
public interface SalaryOperateService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SalaryOperate queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SalaryOperate> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param salaryOperate 实例对象
     * @return 实例对象
     */
    SalaryOperate insert(SalaryOperate salaryOperate);

    /**
     * 修改数据
     *
     * @param salaryOperate 实例对象
     * @return 实例对象
     */
    SalaryOperate update(SalaryOperate salaryOperate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<SalaryOperate> queryAll();

    int queryBySno(SalaryOperate operate);
}