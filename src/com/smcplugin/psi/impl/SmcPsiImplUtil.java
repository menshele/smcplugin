package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcElementFactory;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcTypes;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static String getName(SmcMap element) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.MAP_NAME);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static PsiElement setName(SmcMap element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.MAP_NAME);
        if (keyNode != null) {
            SmcMap property = SmcElementFactory.createMap(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SmcMap element) {
        ASTNode keyNode = element.getNode().findChildByType(SmcTypes.MAP_NAME);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

}