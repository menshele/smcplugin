package com.smcplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;
import com.smcplugin.SmcLanguage;
import com.smcplugin.psi.impl.SmcPsiImplUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcFile extends PsiFileBase {
    public SmcFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SmcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SmcFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Smc File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    public String getContextClassQName() {
        SmcContextClassDeclaration contextClass = PsiTreeUtil.getChildOfType(this, SmcContextClassDeclaration.class);
        SmcQualifiedIdentifier qualifiedIdentifier = null;
        if (contextClass != null) {
            qualifiedIdentifier = contextClass.getQualifiedIdentifier();
        }
        String declaredQname = qualifiedIdentifier != null ? qualifiedIdentifier.getName() : "";
        String name = declaredQname != null ? declaredQname : "";
        return name.contains(SmcPsiImplUtil.STRING_DOT) ? name : getPackageName() + SmcPsiImplUtil.STRING_DOT + name;
    }

    public PsiClass getContextClass() {
        String contextClassQName = getContextClassQName();
        return StringUtils.isBlank(contextClassQName) ? null : SmcPsiUtil.findClass(contextClassQName, getProject());
    }

    public String getFsmClassQName() {
        SmcFsmClass smcFsmClass = PsiTreeUtil.findChildOfType(this, SmcFsmClass.class);
        return smcFsmClass != null ?  getPackageName() + SmcPsiImplUtil.STRING_DOT + smcFsmClass.getName() : "";
    }

    public PsiClass getFsmClass() {
        String fsmQname = getFsmClassQName();
        return StringUtils.isBlank(fsmQname) ? null : SmcPsiUtil.findClass(fsmQname, getProject());
    }

    public String getPackageName() {
        SmcFsmPackage childOfType = PsiTreeUtil.findChildOfType(this, SmcFsmPackage.class);
        return childOfType != null ? childOfType.getName() : "";
    }
}