package com.ciaosgarage.stickie.parameters.attachStatement.stateWhere;

import com.ciaosgarage.stickie.parameters.attachStatement.ATStatus;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;

import java.util.*;

public class StateWhere implements AttachStatement {

    private SWComparator comparator = SWComparator.EQUAL;
    private SWValuesOperator operator = SWValuesOperator.AND;
    private ATStatus atStatus = ATStatus.VARIABLE;
    private List<FindingUnit> unitList;
    private String columnName;

    public StateWhere(String columnName, Object key) {
        this.columnName = columnName;
        this.setKey(key);

    }

    public StateWhere(FindingUnit... units) {
        this.setUnitList(units);
    }

    public StateWhere(String columnName) {
        this.columnName = columnName;
    }

    public void setKey(Object key) {
        unitList = Arrays.asList(new FindingUnit(columnName, key));
    }

    private void setUnitList(FindingUnit... units) {
        unitList = new ArrayList<>();
        for (FindingUnit unit : units) {
            unitList.add(unit);
        }
    }

    public void setComparator(SWComparator comparator) {
        this.comparator = comparator;
    }

    public void setOperator(SWValuesOperator operator) {
        this.operator = operator;
    }

    public void setAtStatus(ATStatus atStatus) {
        this.atStatus = atStatus;
    }

    @Override
    public String getStatement() {
        StringBuffer sql = new StringBuffer("WHERE ");
        int count = 0;
        for (FindingUnit unit : unitList) {
            sql.append(unit.getColumnName() + getComparator());
            sql.append(":stateWhere" + unit.getColumnName() + String.valueOf(count));
            sql.append(getOperator());
            count += 1;
        }
        return sql.substring(0, sql.length() - 5);
    }

    @Override
    public Map<String, Object> getMapper() {
        Map<String, Object> map = new HashMap<>();
        int count = 0;
        for (FindingUnit unit : unitList) {
            map.put("stateWhere" + unit.getColumnName() + String.valueOf(count), unit.getKey());
            count += 1;
        }
        return map;
    }

    @Override
    public ATStatus getType() {
        return atStatus;
    }

    private String getComparator() {
        switch (comparator) {
            case EQUAL:
                return "=";
            case LIKE:
                return "LIKE";
            default:
                return null;
        }
    }

    private String getOperator() {
        switch (operator) {
            case OR:
                return " OR  ";
            case AND:
                return " AND ";
            default:
                return null;
        }
    }
}
