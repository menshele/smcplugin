package com.smcplugin;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcTransition;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */

public class SmcRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof SmcTransition;
    }
}