package org.mdubois.freeveggie.framework.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
// </editor-fold>

public final class HQLSimpleParser {

    private static final Log LOGGER = LogFactory.getLog(HQLSimpleParser.class);
    private String alias;
    private String select;
    private String from;
    private String where;
    private String order;
    private static final String PATTERN_SELECT = "( *SELECT *)";
    private static final String PATTERN_SELECT_DISTINCT = "( *SELECT DISTINCT *)";
    private static final String PATTERN_ALIAS = "([^ ]*)";
    private static final String PATTERN_FROM = "( *FROM *.* )";
    private static final String PATTERN_WHERE = "( *WHERE *.*)";
    private static final String PATTERN_ORDER = "( *ORDER *BY *.*)";

    private HQLSimpleParser() {
    }

    public void addOrder(String sorder) {
        if (order == null) {
            order = "ORDER BY " + alias + "." + sorder;
        } else {
            order += ", " + alias + "." + sorder;
        }
    }

    public void addWhereClause(String swhere) {
        if (swhere == null) {
            return;
        }
        if (StringUtils.isBlank(where)){
            where = "WHERE " + alias + "." + swhere.trim();
        } else {
            where += " AND " + alias + "." + swhere.trim();
        }
    }

    public String getHQLQuery() {
        return (select == null ? "" : select) + (alias == null ? "" : alias) + from
                + (where == null ? "" : " " + where) + (order == null ? "" : " " + order);
    }

    public static HQLSimpleParser parseSimpleHql(String hql) {
        HQLSimpleParser hqlSimpleParser = new HQLSimpleParser();
        StringBuilder patternBuilder = new StringBuilder();
        if (hql.indexOf("FROM") == -1) {
            throw new IllegalArgumentException("The request :" + hql + " not supported : 'from' missing");
        }

        int value = 0;
        if (hql.contains("SELECT DISTINCT") && hql.indexOf("FROM") > hql.indexOf("SELECT")) {
            patternBuilder.append(PATTERN_SELECT_DISTINCT);
            patternBuilder.append(PATTERN_ALIAS);
            value |= 1;
        } else if (hql.contains("SELECT") && hql.indexOf("FROM") > hql.indexOf("SELECT")) {
            patternBuilder.append(PATTERN_SELECT);
            patternBuilder.append(PATTERN_ALIAS);
            value |= 1;
        }
        patternBuilder.append(PATTERN_FROM);
        if (hql.contains("WHERE")) {
            patternBuilder.append(PATTERN_WHERE);
            value |= 2;
        }
        if (hql.contains("ORDER")) {
            patternBuilder.append(PATTERN_ORDER);
            value |= 4;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PATTERN : " + patternBuilder.toString());
        }

        Pattern pattern = Pattern.compile(patternBuilder.toString());

        Matcher matcher = pattern.matcher(hql);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("The request :" + hql + " not supported");
        }

        int group = 1;
        if ((value & 1) == 1) {
            hqlSimpleParser.select = matcher.group(group);
            group++;
            hqlSimpleParser.alias = matcher.group(group);
            group++;
        }
        hqlSimpleParser.from = matcher.group(group);
        group++;
        if ((value & 2) == 2) {
            hqlSimpleParser.where = matcher.group(group);
            group++;
        }
        if ((value & 4) == 4) {
            hqlSimpleParser.order = matcher.group(group);
            group++;
        }

        return hqlSimpleParser;
    }
}
