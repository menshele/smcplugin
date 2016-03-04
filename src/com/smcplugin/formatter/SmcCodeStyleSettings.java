package com.smcplugin.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.smcplugin.SmcLanguage;

/**
 * scmplugin
 * Created by lemen on 29.02.2016.
 */
public class SmcCodeStyleSettings extends CustomCodeStyleSettings {


    public SmcCodeStyleSettings(CodeStyleSettings container) {
        super(SmcLanguage.INSTANCE.getID(), container);
    }



    public enum SmcOptions {
        SPACE_BEFORE_LEFT_BRACE,
        SPACE_BEFORE_KEYWORD,
        SPACE_AFTER_KEYWORD,
        SPACE_WITHIN_PARENTHESES,
        SPACE_BEFORE_PARENTHESES_OPEN,
        SPACE_AROUND_MAP_STATE_SEPARATOR,
        PUSH_PROXY_STATE_KEYWORD_SEPARATOR;
    }
    public boolean SPACE_BEFORE_LEFT_BRACE = true;

    public boolean SPACE_AFTER_KEYWORD = true;
    public boolean SPACE_WITHIN_PARENTHESES = false;

    public boolean SPACE_BEFORE_PARENTHESES_OPEN = false;
    public boolean SPACE_AROUND_MAP_STATE_SEPARATOR = false;
    public boolean PUSH_PROXY_STATE_KEYWORD_SEPARATOR = false ;

    public int TRANSITION_WRAPPING = CommonCodeStyleSettings.WRAP_ALWAYS;

}