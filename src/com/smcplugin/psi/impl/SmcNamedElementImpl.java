package com.smcplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.smcplugin.psi.SmcElementFactory;
import com.smcplugin.psi.SmcNamedElement;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public abstract class SmcNamedElementImpl extends ASTWrapperPsiElement implements SmcNamedElement{

    public SmcNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public PsiReference getReference() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this).length > 0 ?
                ReferenceProvidersRegistry.getReferencesFromProviders(this)[0] : null;
    }

    public String getName() {
        PsiElement nameIdentifier = getNameIdentifier();
        ASTNode keyNode = nameIdentifier != null ? nameIdentifier.getNode() : null;
        return SmcPsiImplUtil.normalizeNodeText(keyNode);
    }


    public PsiElement setName(@NotNull String newName) {
        PsiElement nameIdentifier = getNameIdentifier();
        ASTNode keyNode = nameIdentifier != null ? nameIdentifier.getNode() : null;
        if (keyNode != null) {
            SmcNamedElement property = SmcElementFactory.createElement(getProject(), getClass(), newName);
            ASTNode newKeyNode = property.getNode().findChildByType(keyNode.getElementType());
            if (newKeyNode != null) {
                getNode().replaceChild(keyNode, newKeyNode);
            }
        }
        return this;
    }

    public abstract PsiElement getNameIdentifier();
}