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
        SPACE_AFTER_KEYWORD,
        SPACE_WITHIN_PARENTHESES,
        SPACE_BEFORE_PARENTHESES_OPEN,
        SPACE_AROUND_MAP_STATE_SEPARATOR,
        SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR,
        BLANK_LINES_AROUND_STATE,
        BLANK_LINES_AROUND_TRANSITION,
        BLANK_LINES_AROUND_MAP,
        BLANK_LINES_AROUND_ENTRY,
        BLANK_LINES_AROUND_EXIT,
        WRAP_ACTIONS,
        WRAP_TRANSITION;

    }

    public boolean SPACE_BEFORE_LEFT_BRACE = true;

    public boolean SPACE_AFTER_KEYWORD = true;

    public boolean SPACE_WITHIN_PARENTHESES = false;

    public boolean SPACE_BEFORE_PARENTHESES_OPEN = false;
    public boolean SPACE_AROUND_MAP_STATE_SEPARATOR = false;
    public boolean SPACE_AROUND_PUSH_PROXY_STATE_KEYWORD_SEPARATOR = false ;
    public int BLANK_LINES_AROUND_STATE = 1;
    public int BLANK_LINES_AROUND_TRANSITION = 1;
    public int BLANK_LINES_AROUND_MAP = 1;
    public int BLANK_LINES_AROUND_ENTRY = 1;
    public int BLANK_LINES_AROUND_EXIT = 1;
    public int WRAP_STATE = CommonCodeStyleSettings.WRAP_ALWAYS;

    public int WRAP_NEXT_STATE = CommonCodeStyleSettings.WRAP_ALWAYS;
    public int WRAP_ACTIONS = CommonCodeStyleSettings.WRAP_ALWAYS;
    public int WRAP_ENTRY = CommonCodeStyleSettings.WRAP_ALWAYS;
    public int WRAP_EXIT = CommonCodeStyleSettings.WRAP_ALWAYS;
    public int WRAP_TRANSITION = CommonCodeStyleSettings.WRAP_ALWAYS;
    public int WRAP_PUSH_TRANSITION = CommonCodeStyleSettings.DO_NOT_WRAP;
    public int WRAP_POP_TRANSITION = CommonCodeStyleSettings.DO_NOT_WRAP;
    public int WRAP_TRANSITION_ARGS = CommonCodeStyleSettings.DO_NOT_WRAP;
    public int WRAP_GUARD = CommonCodeStyleSettings.WRAP_ALWAYS;
}