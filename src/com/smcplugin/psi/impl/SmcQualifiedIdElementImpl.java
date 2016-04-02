package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.SmcQualifiedIdElement;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public abstract class SmcQualifiedIdElementImpl extends SmcNamedElementImpl implements SmcQualifiedIdElement {

    public SmcQualifiedIdElementImpl(@NotNull ASTNode node) {
        super(node);
    }


    public String getQualifiedName() {
        SmcQualifiedIdElement element = PsiTreeUtil.getPrevSiblingOfType(this, SmcQualifiedIdElement.class);
        return (element != null ? element.getQualifiedName() +"." : "") + getName();
    }

    public SmcQualifiedIdElement getPreviousQualifiedIdElement() {
        return PsiTreeUtil.getPrevSiblingOfType(this, SmcQualifiedIdElement.class);
    }

    public String getPreviousQualifiedName() {
        SmcQualifiedIdElement previousQualifiedIdElement = getPreviousQualifiedIdElement();
        return previousQualifiedIdElement != null ? previousQualifiedIdElement.getQualifiedName() : "";
    }
}