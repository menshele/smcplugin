package com.smcplugin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * scmplugin
 * Created by lemen on 01.03.2016.
 */
public class SmcStringUtils {

    public static final Pattern JAVA_LITERAL_PATTERN
            = Pattern.compile("\"(?:\\\\[\\\\'\"tnbfru01234567]|[^\\\\\"])*?\"");
    public static final Pattern WHITESPACE_CHAR_PATTERN = Pattern.compile("\\s");
    public static final int NOT_FOUND_CHAR = -1;

    public static int indexOf(final CharSequence seq, String pattern) {
        Pattern compiled = Pattern.compile(pattern);
        return indexOf(seq, compiled);
    }

    public static int indexOf(CharSequence seq, Pattern compile) {
        Matcher matcher = compile.matcher(seq);
        if(matcher.find()){
            return matcher.start();
        }
        return NOT_FOUND_CHAR;
    }

    public static int indexOfWhiteSpaceChar(final CharSequence seq) {
        return indexOf(seq, WHITESPACE_CHAR_PATTERN);
    }

    public static void main(String[] args) {
        System.out.println(SmcStringUtils.indexOf("test ","\\s"));
    }
}
