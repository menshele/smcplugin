package com.smcplugin;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcCodeStyleSettings extends CustomCodeStyleSettings {
    public SmcCodeStyleSettings(CodeStyleSettings settings) {
        super("SmcCodeStyleSettings", settings);
    }
}