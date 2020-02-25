package com.joinx.salary.repository;

import com.joinx.salary.pojo.DepartSalaryRecord;
import com.joinx.salary.pojo.DepartSalaryRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartSalaryRecordMapper {
    long countByExample(DepartSalaryRecordExample example);

    int deleteByExample(DepartSalaryRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepartSalaryRecord record);

    int insertSelective(DepartSalaryRecord record);

    List<DepartSalaryRecord> selectByExample(DepartSalaryRecordExample example);

    DepartSalaryRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepartSalaryRecord record, @Param("example") DepartSalaryRecordExample example);

    int updateByExample(@Param("record") DepartSalaryRecord record, @Param("example") DepartSalaryRecordExample example);

    int updateByPrimaryKeySelective(DepartSalaryRecord record);

    int updateByPrimaryKey(DepartSalaryRecord record);
}