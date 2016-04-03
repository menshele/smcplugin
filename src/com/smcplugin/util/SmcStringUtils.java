package com.smcplugin.util;

import com.intellij.openapi.util.text.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * scmplugin
 * Created by lemen on 01.03.2016.
 */
public class SmcStringUtils {

    private static final String ANGLE_BRACKETS = "<{0}>";

    public static final Pattern JAVA_LITERAL_PATTERN
            = Pattern.compile("\"(?:\\\\[\\\\'\"tnbfru01234567]|[^\\\\\"])*?\"");
    public static final Pattern WHITESPACE_CHAR_PATTERN = Pattern.compile("\\s");
    public static final int NOT_FOUND_CHAR = -1;

    public static int indexOf(CharSequence seq, Pattern compile) {
        Matcher matcher = compile.matcher(seq);
        if (matcher.find()) {
            return matcher.start();
        }
        return NOT_FOUND_CHAR;
    }

    public static String getSimpleName(String qName, String base) {
        return StringUtils.removeStart(qName, base).substring(1);
    }

    public static String toDisplayString(String text) {
        String lowerCase = StringUtil.toLowerCase(text);
        return MessageFormat.format(ANGLE_BRACKETS, StringUtil.replaceChar(lowerCase, '_', ' '));
    }

    public static int indexOfWhiteSpaceChar(final CharSequence seq) {
        return indexOf(seq, WHITESPACE_CHAR_PATTERN);
    }
}
