package com.joinx.salary.repository;

import com.joinx.salary.pojo.SalaryStandard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SalaryStandard)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-16 14:06:18
 */
@Repository
public interface SalaryStandardRespository {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SalaryStandard queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SalaryStandard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param salaryStandard 实例对象
     * @return 对象列表
     */
    List<SalaryStandard> queryAll(SalaryStandard salaryStandard);

    /**
     * 新增数据
     *
     * @param salaryStandard 实例对象
     * @return 影响行数
     */
    int insert(SalaryStandard salaryStandard);

    /**
     * 修改数据
     *
     * @param salaryStandard 实例对象
     * @return 影响行数
     */
    int update(SalaryStandard salaryStandard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void removeByUserNos(@Param("userNos") List<String> userNos);
}