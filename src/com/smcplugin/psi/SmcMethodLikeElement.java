package com.smcplugin.psi;

import com.intellij.navigation.ItemPresentation;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public interface SmcMethodLikeElement extends SmcNamedElement{

    int getArgumentCount();

    ItemPresentation getPresentation();

    String getFullName();

    String getQualifiedFullName();

    Icon getElementIcon(int flags);

}
