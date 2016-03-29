package com.smcplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcFileType extends LanguageFileType {
    public static final SmcFileType INSTANCE = new SmcFileType();

    private SmcFileType() {
        super(SmcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Smc file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Smc language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SmcIcons.SM;
    }
}