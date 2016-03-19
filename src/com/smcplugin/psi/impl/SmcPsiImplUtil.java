package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
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

    public static String getName(SmcAction element) {
        return getStringName(element, SmcTypes.ACTION_NAME);
    }

    public static PsiElement getNamePsiElement(SmcAction element) {
        return gePsiByToken(element, SmcTypes.ACTION_NAME);
    }

    public static PsiElement setName(SmcTransition element, String newName) {
        return setStringName(element, newName, SmcTypes.TRANSITION_NAME);
    }

    public static String getName(SmcNextState element) {
        return getStringName(element, SmcTypes.NEXT_STATE_NAME);
    }

    public static String getName(SmcPushMapNameElement element) {
        return getStringName(element, SmcTypes.PUSH_MAP_NAME);
    }

    public static String getName(SmcPushStateNameElement element) {
        return getStringName(element, SmcTypes.PUSH_STATE_NAME);
    }

    public static String getMapName(SmcStartState element) {
        SmcStartMapNameElement map = PsiTreeUtil.findChildOfType(element, SmcStartMapNameElement.class);
        return map != null ? getStringName(map, SmcTypes.START_MAP_NAME) : null;
    }

    public static String getStateName(SmcStartState element) {
        SmcStartStateNameElement state = PsiTreeUtil.findChildOfType(element, SmcStartStateNameElement.class);
        return state != null ? getStringName(state, SmcTypes.START_STATE_NAME) : null;
    }


    public static String getMapName(SmcPushState element) {
        SmcPushMapNameElement map = PsiTreeUtil.findChildOfType(element, SmcPushMapNameElement.class);
        return map != null ? getStringName(map, SmcTypes.PUSH_MAP_NAME) : null;
    }

    public static String getStateName(SmcPushState element) {
        SmcPushStateNameElement state = PsiTreeUtil.findChildOfType(element, SmcPushStateNameElement.class);
        return state != null ? getStringName(state, SmcTypes.PUSH_STATE_NAME) : null;
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        return gePsiByToken(element, SmcTypes.TRANSITION_NAME);
    }

    public static String getName(SmcStartMapNameElement element) {
        return getStringName(element, SmcTypes.START_MAP_NAME);
    }

    public static String getName(SmcStartStateNameElement element) {
        return getStringName(element, SmcTypes.START_STATE_NAME);
    }

    public static PsiReference getReference(SmcStartStateNameElement element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcStartMapNameElement element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcAction element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcPushState element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcPushMapNameElement element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcPushStateNameElement element) {
        return getPsiReference(element);
    }

    @NotNull
    public static PsiReference[] getReferences(SmcNextState element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    public static PsiReference getReference(SmcNextState element) {
        return getPsiReference(element);
    }

    public static PsiReference getReference(SmcStartState element) {
        return getPsiReference(element);
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
            ASTNode newKeyNode = property.getNode().findChildByType(nameToken);
            if (newKeyNode != null) {
                element.getNode().replaceChild(keyNode, newKeyNode);
            }
        }
        return element;
    }


    public static String getName(SmcContextClass element) {
        return getStringName(element, SmcTypes.CONTEXT_CLASS_NAME);
    }

    public static String getPackageName(SmcContextClass element) {
        return getStringName(element, SmcTypes.CONTEXT_CLASS_NAME);
    }

    public static String getClassName(SmcContextClass element) {
        return getStringName(element, SmcTypes.CONTEXT_CLASS_NAME);
    }

    public static PsiReference getReference(SmcContextClass element) {
        return getPsiReference(element);
    }

    public static PsiElement getNamePsiElement(SmcContextClass element) {
        return gePsiByToken(element, SmcTypes.CONTEXT_CLASS_NAME);
    }


    @Nullable
    private static PsiReference getPsiReference(PsiElement element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element).length > 0 ?
                ReferenceProvidersRegistry.getReferencesFromProviders(element)[0] : null;
    }
}