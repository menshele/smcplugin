package com.smcplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public class SmcNamedElementImpl extends ASTWrapperPsiElement{
    public SmcNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
