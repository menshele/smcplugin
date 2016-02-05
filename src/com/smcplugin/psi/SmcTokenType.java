package com.smcplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcTokenType extends IElementType {
    public SmcTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SmcLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SmcTokenType." + super.toString();
    }
}