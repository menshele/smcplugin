package com.smcplugin.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiMethod;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public interface SmcMethodLikeElement extends SmcNamedElement {

    int getArgumentCount();

    ItemPresentation getPresentation();

    String getFullName();

    String getQualifiedFullName();

    Icon getElementIcon(int flags);

    boolean matches(PsiMethod method);
}
