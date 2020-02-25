package com.joinx.salary.service;

import com.joinx.salary.pojo.Leave;
import com.joinx.salary.util.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LeaveService {
   List<Leave> attendancesInfo(PageHelper<Leave> pageHelper);

    void applyLeave(Leave leave);

    List<Leave> attendancesInfoByUno(String userNo);

    void operate(Leave leave);
}
