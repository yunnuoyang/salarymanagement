package com.joinx.salary.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * depart_salary_record
 * @author 
 */
@Data
@Setter
@Getter
public class DepartSalaryRecord implements Serializable {
    private Integer id;

    private String departId;

    /**
     * å·¥èµ„ä¸‹å�‘æ—¶é—´
     */
    private Date salarySendTime;

    /**
     * éƒ¨é—¨å…±æ”¯ä»˜å‘˜å·¥è–ªèµ„+äº”é™©ä¸€é‡‘
     */
    private Double salaryCount;

    /**
     * éƒ¨é—¨å‘˜å·¥çš„åŸºç¡€è–ªèµ„æ€»å’Œ
     */
    private Double salaryBasicCount;

    /**
     * å…»è€�ä¿�é™©é‡‘é¢�
     */
    private Double endowmentInsurance;

    /**
     * åŒ»ç–—ä¿�é™©æ€»è´¹ç”¨
     */
    private Double medicare;

    /**
     * å¤±ä¸šä¿�é™©
     */
    private Double unemployment;

    /**
     * å·¥ä¼¤ä¿�é™©
     */
    private Double injuryInsurance;

    /**
     * ç”Ÿè‚²ä¿�é™©
     */
    private Double rearInsurance;

    /**
     * å…¬ç§¯é‡‘
     */
    private Double reservedFund;


}