package com.joinx.salary.repository;

import com.joinx.salary.pojo.SalaryOperate;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SalaryOperate)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-19 15:46:43
 */
public interface SalaryOperateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SalaryOperate queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SalaryOperate> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param salaryOperate 实例对象
     * @return 对象列表
     */
    List<SalaryOperate> queryAll(SalaryOperate salaryOperate);

    /**
     * 新增数据
     *
     * @param salaryOperate 实例对象
     * @return 影响行数
     */
    int insert(SalaryOperate salaryOperate);

    /**
     * 修改数据
     *
     * @param salaryOperate 实例对象
     * @return 影响行数
     */
    int update(SalaryOperate salaryOperate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}