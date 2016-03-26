package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.*;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static final String DOT = ".";

    public static PsiElement getNameIdentifier(SmcMap element) {
        return gePsiByToken(element, SmcTypes.MAP_NAME);
    }

    public static PsiElement getNameIdentifier(SmcState element) {
        return gePsiByToken(element, SmcTypes.STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcAction element) {
        return gePsiByToken(element, SmcTypes.ACTION_NAME);
    }

    public static PsiElement getNameIdentifier(SmcNextState element) {
        return gePsiByToken(element, SmcTypes.NEXT_STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcPushMapNameElement element) {
        return gePsiByToken(element, SmcTypes.PUSH_MAP_NAME);
    }

    public static PsiElement getNameIdentifier(SmcPushProxyStateNameElement element) {
        return gePsiByToken(element, SmcTypes.PUSH_PROXY_STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcPushStateNameElement element) {
        return gePsiByToken(element, SmcTypes.PUSH_STATE_NAME);
    }

    public static String getMapName(SmcStartState element) {
        return getNameStringFromChildElement(element, SmcStartMapNameElement.class);
    }

    public static String getStateName(SmcStartState element) {
        return getNameStringFromChildElement(element, SmcStartStateNameElement.class);
    }

    public static String getMapName(SmcPushState element) {
        return getNameStringFromChildElement(element, SmcPushMapNameElement.class);
    }

    public static String getStateName(SmcPushState element) {
        return getNameStringFromChildElement(element, SmcPushStateNameElement.class);
    }


    public static String getName(SmcFsmPackage element) {
        return getStringName(element, SmcTypes.PACKAGE_STATEMENT);
    }

    public static String getName(SmcContextClass element) {
        return getStringName(element, SmcTypes.CONTEXT_CLASS_NAME);
    }

    public static String getName(SmcImportClass element) {
        return getStringName(element, SmcTypes.IMPORT_CLASS_STATEMENT);
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        return gePsiByToken(element, SmcTypes.TRANSITION_NAME);
    }

    public static PsiElement getNameIdentifier(SmcStartMapNameElement element) {
        return gePsiByToken(element, SmcTypes.START_MAP_NAME);
    }

    public static PsiElement getNameIdentifier(SmcStartStateNameElement element) {
        return gePsiByToken(element, SmcTypes.START_STATE_NAME);
    }


    public static String getQualifiedName(SmcContextClass element) {
        SmcFsmPackage smcFsmPackage = PsiTreeUtil.getChildOfType(element.getContainingFile(), SmcFsmPackage.class);
        String name = element.getName();
        StringBuilder qualifiedName = new StringBuilder();
        if (!StringUtils.contains(name, DOT) && smcFsmPackage != null) {
            String packageName = smcFsmPackage.getName();
            if (packageName != null) {
                qualifiedName.append(packageName).append(DOT);
            }
        }
        return name != null ? qualifiedName.append(name).toString() : null;
    }

    public static String getContextClassName(SmcAction element) {
        SmcContextClass contextClass = PsiTreeUtil.findChildOfType(element.getContainingFile(), SmcContextClass.class);
        return contextClass != null ? contextClass.getQualifiedName() : "";
    }

    public static int getArgumentCount(SmcAction element) {
        return element.getArguments() != null ? element.getArguments().getArgumentsCount() : 0;
    }

    public static int getArgumentsCount(SmcArguments element) {
        return element.getArgumentList().size();
    }


    public static String getQualifiedName(SmcImportClass element) {
        return element.isClassName() ? element.getName() : null;
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

    public static PsiElement getNamePsiElement(SmcContextClass element) {
        return gePsiByToken(element, SmcTypes.CONTEXT_CLASS_NAME);
    }


    public static boolean isWildcard(SmcImportClass element) {
        return element.getName() != null && element.getName().contains("*");
    }

    public static boolean isClassName(SmcImportClass element) {
        return element.getName() != null && !element.getName().contains("*");
    }


    public static PsiReference getReference(PsiElement element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element).length > 0 ?
                ReferenceProvidersRegistry.getReferencesFromProviders(element)[0] : null;
    }

    @Nullable
    private static <T extends SmcNamedElement> String getNameStringFromChildElement(PsiElement element, Class<T> nameElementClass) {
        T namedElement = PsiTreeUtil.findChildOfType(element, nameElementClass);
        return namedElement != null ? namedElement.getName() : null;
    }


    @Nullable
    private static String getStringName(PsiElement element, IElementType nameToken) {
        ASTNode keyNode = element.getNode().findChildByType(nameToken);
        return normalizeNodeText(keyNode);
    }

    public static String normalizeNodeText(ASTNode keyNode) {
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }
}