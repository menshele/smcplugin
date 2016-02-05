package com.smcplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.smcplugin.psi.SmcNamedElement;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public abstract class SmcNamedElementImpl extends ASTWrapperPsiElement implements SmcNamedElement {
    public SmcNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}