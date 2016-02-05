package com.smcplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.smcplugin.SmcLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcElementType extends IElementType {
    public SmcElementType(@NotNull @NonNls String debugName) {
        super(debugName, SmcLanguage.INSTANCE);
    }
}