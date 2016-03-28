package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.smcplugin.psi.SmcQualifiedNamedElement;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public abstract class SmcQualifiedNamedElementImpl extends SmcNamedElementImpl implements SmcQualifiedNamedElement {

    public SmcQualifiedNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getPackageName() {
        String packageText = getPackageText();
        return packageText.endsWith(SmcPsiImplUtil.DOT) ?
                StringUtils.substring(packageText, 0, packageText.length() - 1): packageText;
    }

    public String getQualifiedName() {
        return getPackageName() + SmcPsiImplUtil.DOT + getName();
    }
}