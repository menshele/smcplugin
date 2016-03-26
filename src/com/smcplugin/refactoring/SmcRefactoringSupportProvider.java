package com.smcplugin.refactoring;

/**
 * scmplugin
 * Created by lemen on 26.03.2016.
 */

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcState;
import org.jetbrains.annotations.NotNull;

public class SmcRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof SmcMap
                || element instanceof SmcState;
    }
}
