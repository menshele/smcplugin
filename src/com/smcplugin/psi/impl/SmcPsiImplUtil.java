package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
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
        return gePsiByToken(element, SmcTypes.MAP_NAME);
    }

    public static String getName(SmcState element) {
        return getStringName(element, SmcTypes.STATE_NAME);
    }

    public static PsiElement setName(SmcState element, String newName) {
        return setStringName(element, newName, SmcTypes.STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcState element) {
        return gePsiByToken(element, SmcTypes.STATE_NAME);
    }

    public static String getName(SmcTransition element) {
        return getStringName(element, SmcTypes.TRANSITION_NAME);
    }

    public static PsiElement setName(SmcTransition element, String newName) {
        return setStringName(element, newName, SmcTypes.TRANSITION_NAME);
    }

    public static String getName(SmcNextState element) {
        return getStringName(element, SmcTypes.NEXT_STATE_NAME);
    }

    public static String getMapName(SmcStartState element) {
        return getStringName(element, SmcTypes.START_MAP_NAME);
    }

    public static String getStateName(SmcStartState element) {
        return getStringName(element, SmcTypes.START_STATE_NAME);
    }

    public static PsiElement getMapNamePsiElement(SmcStartState element) {
        return gePsiByToken(element, SmcTypes.START_MAP_NAME);
    }

    public static PsiElement getStateNamePsiElement(SmcStartState element) {
        return gePsiByToken(element, SmcTypes.START_STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        return gePsiByToken(element, SmcTypes.TRANSITION_NAME);
    }

    @NotNull
    public static PsiReference[] getReferences(SmcNextState element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    public static PsiReference getReference(SmcNextState element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element).length > 0?
                ReferenceProvidersRegistry.getReferencesFromProviders(element)[0]:null;
    }

    public static PsiReference getReference(SmcStartState element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element).length > 0?
                ReferenceProvidersRegistry.getReferencesFromProviders(element)[0]:null;
    }

    @Nullable
    private static PsiElement gePsiByToken(PsiElement element, IElementType nameToken) {
        ASTNode keyNode = element.getNode().findChildByType(nameToken);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @Nullable
    private static String getStringName(PsiElement element, IElementType nameToken) {
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