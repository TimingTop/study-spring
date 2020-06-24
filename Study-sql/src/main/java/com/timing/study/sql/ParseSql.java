package com.timing.study.sql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
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




    }
}
