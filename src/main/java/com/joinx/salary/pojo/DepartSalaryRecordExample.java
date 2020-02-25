package com.joinx.salary.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepartSalaryRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartSalaryRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNull() {
            addCriterion("depart_ID is null");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNotNull() {
            addCriterion("depart_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartIdEqualTo(String value) {
            addCriterion("depart_ID =", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotEqualTo(String value) {
            addCriterion("depart_ID <>", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThan(String value) {
            addCriterion("depart_ID >", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThanOrEqualTo(String value) {
            addCriterion("depart_ID >=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThan(String value) {
            addCriterion("depart_ID <", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThanOrEqualTo(String value) {
            addCriterion("depart_ID <=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLike(String value) {
            addCriterion("depart_ID like", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotLike(String value) {
            addCriterion("depart_ID not like", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdIn(List<String> values) {
            addCriterion("depart_ID in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotIn(List<String> values) {
            addCriterion("depart_ID not in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdBetween(String value1, String value2) {
            addCriterion("depart_ID between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotBetween(String value1, String value2) {
            addCriterion("depart_ID not between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeIsNull() {
            addCriterion("salary_send_time is null");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeIsNotNull() {
            addCriterion("salary_send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeEqualTo(Date value) {
            addCriterion("salary_send_time =", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeNotEqualTo(Date value) {
            addCriterion("salary_send_time <>", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeGreaterThan(Date value) {
            addCriterion("salary_send_time >", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("salary_send_time >=", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeLessThan(Date value) {
            addCriterion("salary_send_time <", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeLessThanOrEqualTo(Date value) {
            addCriterion("salary_send_time <=", value, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeIn(List<Date> values) {
            addCriterion("salary_send_time in", values, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeNotIn(List<Date> values) {
            addCriterion("salary_send_time not in", values, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeBetween(Date value1, Date value2) {
            addCriterion("salary_send_time between", value1, value2, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalarySendTimeNotBetween(Date value1, Date value2) {
            addCriterion("salary_send_time not between", value1, value2, "salarySendTime");
            return (Criteria) this;
        }

        public Criteria andSalaryCountIsNull() {
            addCriterion("salary_count is null");
            return (Criteria) this;
        }

        public Criteria andSalaryCountIsNotNull() {
            addCriterion("salary_count is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryCountEqualTo(Long value) {
            addCriterion("salary_count =", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountNotEqualTo(Long value) {
            addCriterion("salary_count <>", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountGreaterThan(Long value) {
            addCriterion("salary_count >", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountGreaterThanOrEqualTo(Long value) {
            addCriterion("salary_count >=", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountLessThan(Long value) {
            addCriterion("salary_count <", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountLessThanOrEqualTo(Long value) {
            addCriterion("salary_count <=", value, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountIn(List<Long> values) {
            addCriterion("salary_count in", values, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountNotIn(List<Long> values) {
            addCriterion("salary_count not in", values, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountBetween(Long value1, Long value2) {
            addCriterion("salary_count between", value1, value2, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryCountNotBetween(Long value1, Long value2) {
            addCriterion("salary_count not between", value1, value2, "salaryCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountIsNull() {
            addCriterion("salary_basic_count is null");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountIsNotNull() {
            addCriterion("salary_basic_count is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountEqualTo(Long value) {
            addCriterion("salary_basic_count =", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountNotEqualTo(Long value) {
            addCriterion("salary_basic_count <>", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountGreaterThan(Long value) {
            addCriterion("salary_basic_count >", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountGreaterThanOrEqualTo(Long value) {
            addCriterion("salary_basic_count >=", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountLessThan(Long value) {
            addCriterion("salary_basic_count <", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountLessThanOrEqualTo(Long value) {
            addCriterion("salary_basic_count <=", value, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountIn(List<Long> values) {
            addCriterion("salary_basic_count in", values, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountNotIn(List<Long> values) {
            addCriterion("salary_basic_count not in", values, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountBetween(Long value1, Long value2) {
            addCriterion("salary_basic_count between", value1, value2, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andSalaryBasicCountNotBetween(Long value1, Long value2) {
            addCriterion("salary_basic_count not between", value1, value2, "salaryBasicCount");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceIsNull() {
            addCriterion("endowment_insurance is null");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceIsNotNull() {
            addCriterion("endowment_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceEqualTo(Long value) {
            addCriterion("endowment_insurance =", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceNotEqualTo(Long value) {
            addCriterion("endowment_insurance <>", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceGreaterThan(Long value) {
            addCriterion("endowment_insurance >", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceGreaterThanOrEqualTo(Long value) {
            addCriterion("endowment_insurance >=", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceLessThan(Long value) {
            addCriterion("endowment_insurance <", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceLessThanOrEqualTo(Long value) {
            addCriterion("endowment_insurance <=", value, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceIn(List<Long> values) {
            addCriterion("endowment_insurance in", values, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceNotIn(List<Long> values) {
            addCriterion("endowment_insurance not in", values, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceBetween(Long value1, Long value2) {
            addCriterion("endowment_insurance between", value1, value2, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andEndowmentInsuranceNotBetween(Long value1, Long value2) {
            addCriterion("endowment_insurance not between", value1, value2, "endowmentInsurance");
            return (Criteria) this;
        }

        public Criteria andMedicareIsNull() {
            addCriterion("medicare is null");
            return (Criteria) this;
        }

        public Criteria andMedicareIsNotNull() {
            addCriterion("medicare is not null");
            return (Criteria) this;
        }

        public Criteria andMedicareEqualTo(Long value) {
            addCriterion("medicare =", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareNotEqualTo(Long value) {
            addCriterion("medicare <>", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareGreaterThan(Long value) {
            addCriterion("medicare >", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareGreaterThanOrEqualTo(Long value) {
            addCriterion("medicare >=", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareLessThan(Long value) {
            addCriterion("medicare <", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareLessThanOrEqualTo(Long value) {
            addCriterion("medicare <=", value, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareIn(List<Long> values) {
            addCriterion("medicare in", values, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareNotIn(List<Long> values) {
            addCriterion("medicare not in", values, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareBetween(Long value1, Long value2) {
            addCriterion("medicare between", value1, value2, "medicare");
            return (Criteria) this;
        }

        public Criteria andMedicareNotBetween(Long value1, Long value2) {
            addCriterion("medicare not between", value1, value2, "medicare");
            return (Criteria) this;
        }

        public Criteria andUnemploymentIsNull() {
            addCriterion("unemployment is null");
            return (Criteria) this;
        }

        public Criteria andUnemploymentIsNotNull() {
            addCriterion("unemployment is not null");
            return (Criteria) this;
        }

        public Criteria andUnemploymentEqualTo(Long value) {
            addCriterion("unemployment =", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentNotEqualTo(Long value) {
            addCriterion("unemployment <>", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentGreaterThan(Long value) {
            addCriterion("unemployment >", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentGreaterThanOrEqualTo(Long value) {
            addCriterion("unemployment >=", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentLessThan(Long value) {
            addCriterion("unemployment <", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentLessThanOrEqualTo(Long value) {
            addCriterion("unemployment <=", value, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentIn(List<Long> values) {
            addCriterion("unemployment in", values, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentNotIn(List<Long> values) {
            addCriterion("unemployment not in", values, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentBetween(Long value1, Long value2) {
            addCriterion("unemployment between", value1, value2, "unemployment");
            return (Criteria) this;
        }

        public Criteria andUnemploymentNotBetween(Long value1, Long value2) {
            addCriterion("unemployment not between", value1, value2, "unemployment");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceIsNull() {
            addCriterion("injury_insurance is null");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceIsNotNull() {
            addCriterion("injury_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceEqualTo(Long value) {
            addCriterion("injury_insurance =", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceNotEqualTo(Long value) {
            addCriterion("injury_insurance <>", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceGreaterThan(Long value) {
            addCriterion("injury_insurance >", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceGreaterThanOrEqualTo(Long value) {
            addCriterion("injury_insurance >=", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceLessThan(Long value) {
            addCriterion("injury_insurance <", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceLessThanOrEqualTo(Long value) {
            addCriterion("injury_insurance <=", value, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceIn(List<Long> values) {
            addCriterion("injury_insurance in", values, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceNotIn(List<Long> values) {
            addCriterion("injury_insurance not in", values, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceBetween(Long value1, Long value2) {
            addCriterion("injury_insurance between", value1, value2, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andInjuryInsuranceNotBetween(Long value1, Long value2) {
            addCriterion("injury_insurance not between", value1, value2, "injuryInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceIsNull() {
            addCriterion("rear_insurance is null");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceIsNotNull() {
            addCriterion("rear_insurance is not null");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceEqualTo(Long value) {
            addCriterion("rear_insurance =", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceNotEqualTo(Long value) {
            addCriterion("rear_insurance <>", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceGreaterThan(Long value) {
            addCriterion("rear_insurance >", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceGreaterThanOrEqualTo(Long value) {
            addCriterion("rear_insurance >=", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceLessThan(Long value) {
            addCriterion("rear_insurance <", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceLessThanOrEqualTo(Long value) {
            addCriterion("rear_insurance <=", value, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceIn(List<Long> values) {
            addCriterion("rear_insurance in", values, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceNotIn(List<Long> values) {
            addCriterion("rear_insurance not in", values, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceBetween(Long value1, Long value2) {
            addCriterion("rear_insurance between", value1, value2, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andRearInsuranceNotBetween(Long value1, Long value2) {
            addCriterion("rear_insurance not between", value1, value2, "rearInsurance");
            return (Criteria) this;
        }

        public Criteria andReservedFundIsNull() {
            addCriterion("reserved_fund is null");
            return (Criteria) this;
        }

        public Criteria andReservedFundIsNotNull() {
            addCriterion("reserved_fund is not null");
            return (Criteria) this;
        }

        public Criteria andReservedFundEqualTo(Long value) {
            addCriterion("reserved_fund =", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundNotEqualTo(Long value) {
            addCriterion("reserved_fund <>", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundGreaterThan(Long value) {
            addCriterion("reserved_fund >", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundGreaterThanOrEqualTo(Long value) {
            addCriterion("reserved_fund >=", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundLessThan(Long value) {
            addCriterion("reserved_fund <", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundLessThanOrEqualTo(Long value) {
            addCriterion("reserved_fund <=", value, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundIn(List<Long> values) {
            addCriterion("reserved_fund in", values, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundNotIn(List<Long> values) {
            addCriterion("reserved_fund not in", values, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundBetween(Long value1, Long value2) {
            addCriterion("reserved_fund between", value1, value2, "reservedFund");
            return (Criteria) this;
        }

        public Criteria andReservedFundNotBetween(Long value1, Long value2) {
            addCriterion("reserved_fund not between", value1, value2, "reservedFund");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}