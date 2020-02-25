package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.SalaryOperate;
import com.joinx.salary.repository.SalaryOperateDao;
import com.joinx.salary.service.SalaryOperateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SalaryOperate)表服务实现类
 *
 * @author makejava
 * @since 2020-02-19 15:46:44
 */
@Service("salaryOperateService")
public class SalaryOperateServiceImpl implements SalaryOperateService {
    @Resource
    private SalaryOperateDao salaryOperateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SalaryOperate queryById(Integer id) {
        return this.salaryOperateDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SalaryOperate> queryAllByLimit(int offset, int limit) {
        return this.salaryOperateDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param salaryOperate 实例对象
     * @return 实例对象
     */
    @Override
    public SalaryOperate insert(SalaryOperate salaryOperate) {
        this.salaryOperateDao.insert(salaryOperate);
        return salaryOperate;
    }

    /**
     * 修改数据
     *
     * @param salaryOperate 实例对象
     * @return 实例对象
     */
    @Override
    public SalaryOperate update(SalaryOperate salaryOperate) {
        this.salaryOperateDao.update(salaryOperate);
        return this.queryById(salaryOperate.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.salaryOperateDao.deleteById(id) > 0;
    }

    @Override
    public List<SalaryOperate> queryAll() {
        return salaryOperateDao.queryAll(new SalaryOperate());
    }

    @Override
    public int queryBySno(SalaryOperate operate) {
        List<SalaryOperate> operates = salaryOperateDao.queryAll(operate);
        return operates.size();
    }
}