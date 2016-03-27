package com.smcplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.SmcFileType;
import com.smcplugin.SmcLanguage;
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
        SmcContextClass childOfType = PsiTreeUtil.findChildOfType(this, SmcContextClass.class);
        if (childOfType == null) return null;
        return childOfType.getQualifiedName();
    }

    public PsiClass getContextClass() {
        String contextClassQName = getContextClassQName();
        return StringUtils.isBlank(contextClassQName) ? null : SmcPsiUtil.findClass(contextClassQName);
    }
}