package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcState;

import javax.swing.*;

public class SmcStateReference extends AbstractNamedLocalReference<SmcMap, SmcState> {

    protected SmcStateReference(PsiElement element, TextRange rangeInElement) {
        super(SmcMap.class, SmcState.class, element, rangeInElement);
    }

    public SmcStateReference(PsiElement element) {
        super(SmcMap.class, SmcState.class, element);
    }

    @Override
    protected Icon getFile() {
        return SmcIcons.STATE;
    }
}