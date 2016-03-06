package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static String getName(SmcMap element) {
        return getStringName(element, SmcTypes.MAP_NAME);
    }

    public static PsiElement setName(SmcMap element, String newName) {
        return setStringName(element, newName, SmcTypes.MAP_NAME);
    }

    public static PsiElement getNameIdentifier(SmcMap element) {
        return geNamedNameIdentifier(element, SmcTypes.MAP_NAME);
    }

    public static String getName(SmcState element) {
        return getStringName(element, SmcTypes.STATE_NAME);
    }

    public static PsiElement setName(SmcState element, String newName) {
        return setStringName(element, newName, SmcTypes.STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcState element) {
        return geNamedNameIdentifier(element, SmcTypes.STATE_NAME);
    }

    public static String getName(SmcTransition element) {
        return getStringName(element, SmcTypes.TRANSITION_NAME);
    }

    public static PsiElement setName(SmcTransition element, String newName) {
        return setStringName(element, newName, SmcTypes.TRANSITION_NAME);
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        return geNamedNameIdentifier(element, SmcTypes.TRANSITION_NAME);
    }

    @Nullable
    private static PsiElement geNamedNameIdentifier(SmcNamedElement element, IElementType nameToken) {
        ASTNode keyNode = element.getNode().findChildByType(nameToken);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @Nullable
    private static String getStringName(SmcNamedElement element, IElementType nameToken) {
        ASTNode keyNode = element.getNode().findChildByType(nameToken);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    @NotNull
    private static PsiElement setStringName(SmcNamedElement element, String newName, IElementType nameToken) {
        ASTNode keyNode = element.getNode().findChildByType(nameToken);
        if (keyNode != null) {
            SmcMap property = SmcElementFactory.createMap(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

}