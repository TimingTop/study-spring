package com.timing.study.sql;

public class Lexer {

    class SQLToken {
        private final int value;
        private final int offset;
        private final int length;
        private final String name;

        public SQLToken(int value, int offStart, int offEnd, String name) {
            this.value = value;
            this.offset = offStart;
            this.length = offEnd - offStart;
            this.name = name;
        }
    }

    public void parseSQL(char[] sql) {

        for (int i = 0; i < sql.length; i++) {
            char c = sql[i];

            switch (c) {
                case '\"':
                case '\'':
                    // comment
                case '*':

            }
        }
    }

}
