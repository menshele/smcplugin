package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcElementFactory;
import com.smcplugin.psi.SmcTransition;
import com.smcplugin.psi.SmcTypes;

import java.lang.String;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static String getName(SmcTransition element) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.TRANSITION);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to smc spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static PsiElement setName(SmcTransition element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.TRANSITION);
        if (keyNode != null) {
            SmcTransition property = SmcElementFactory.createTransition(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.TRANSITION);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }
}