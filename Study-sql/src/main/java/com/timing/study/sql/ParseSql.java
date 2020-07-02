package com.timing.study.sql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.druid.util.StringUtils;

import java.util.List;

public class ParseSql {

    // AST  --> 语法  --> 词法

    // https://blog.csdn.net/qq_25104587/article/details/90577646
    public void parseInsert() {
        String sql = "select * from user where user_id > 2";
        MySqlStatementParser parser = new MySqlStatementParser(sql);


        // AST
        SQLStatement statement = parser.parseStatement();

        MySqlInsertStatement insert = (MySqlInsertStatement)statement;


        String tableName = StringUtils.removeNameQuotes(insert.getTableName().getSimpleName());

        List<SQLExpr> columns = insert.getColumns();

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);


    }


    public static void lexurTest() {
        Lexer lexer = new Lexer("select * from dual");
        for (;;) {
            lexer.nextToken();

            Token tok = lexer.token();

            if (tok == Token.EOF) {
                break;
            }

            System.out.println(tok.name() +"-------------" + tok.name);


        }
    }

    public static void main(String[] args) {
//        lexurTest();
        testAA();

    }

    public static void testAA() {
        String sql = "SELECT a.id AS \"appId\", a.name AS \"appName\", b.number AS \"instanceNumber\", b.hostname AS \"hostname\", c.name AS \"monitorItemName\" , d.id AS \"alarmRuleStatusId\", d.name AS \"alarmRuleName\", d.current_status AS \"alarmRuleStatus\", d.last_change_time AS \"lastChangeTimeOfAlarmRule\" FROM mi_alarm_rule_status d LEFT JOIN monitor_item_status c ON d.mi_status_id = c.id LEFT JOIN instance b ON b.number = c.inst_num AND b.app_num = c.app_num AND b.hostname = c.hostname LEFT JOIN app a ON a.number = b.app_num WHERE 1 = 0 OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? \n" +
                "OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? OR a.id = ? ORDER BY lastChangeTimeOfAlarmRule DESC LIMIT ?, ?";
        String sql2 = "select id from table1 where id > 2";
        MySqlStatementParser parser = new MySqlStatementParser(sql2);
        List<SQLStatement> statementList = parser.parseStatementList();

        System.out.println("aaaa");
    }
}
