package com.smcplugin;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcLanguage extends Language{
    public static final SmcLanguage INSTANCE = new SmcLanguage();

    protected SmcLanguage() {
        super("smc");
    }
}
