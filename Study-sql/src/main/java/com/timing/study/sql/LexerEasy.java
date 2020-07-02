package com.timing.study.sql;





import java.util.ArrayList;
import java.util.List;

/**
 *
 *  keyword : 1
 *  identify: 2
 *
 *
 *
 *
 */
public class LexerEasy {

    class SqlToken {
        private int keyId;
        private String value;
        public SqlToken(int keyId, String val) {
            this.keyId = keyId;
            this.value = val;
        }
    }

    public static void main(String[] args) {


        String[] key_word = new String[] {"begin", "end", "if", "then", "while", "do"};


        int index = 0;

    }

    public void extactCharacters(char[] sql, String[] keywords) {

        List<SqlToken> result = new ArrayList<>();

        // 当前需要解析的 字符串位置
        int currentIndex = 0;
        char temp = sql[currentIndex];
        // 存放 待解析的 token
        StringBuffer sb = new StringBuffer();


        // 找到 非空 字符，就进入 截取 整个字符串，
        while (temp != ' ') {

            if (temp >= 'a' && temp <= 'z' || temp >= 'A' && temp <= 'Z') {

                while (temp >= 'a' && temp <= 'z' || temp >= 'A' && temp <= 'Z' || temp >= '0' && temp <= '9') {
                    sb.append(temp);
                    temp = sql[++currentIndex];
                }

                String token = sb.toString();
                // 判断是否为 key
                for (int i = 0; i < keywords.length; i++) {
                    if (token.equals(keywords[i])) {
                        // todo

                        result.add(new SqlToken(1, token));
                    }
                }

                // 如果不是key 就是 标识符
                // todo

                result.add(new SqlToken(2, token));

            } else if (temp >= '0' && temp <= '9') {
                // 是否为数字
                sb = new StringBuffer();

                while (temp >= '0' && temp <= '9') {
                    sb.append(temp);
                    temp = sql[++currentIndex];
                }
                // 如果是数字的
                // todo
                result.add(new SqlToken(3, sb.toString()));

            }

            String token = "";
            sb = new StringBuffer();
            switch (temp) {
                case '<':
                    token += temp;
                    if (sql[++currentIndex] == '=') {

                    }
            }

        }

    }
}
