package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.*;
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

    public static PsiElement getNameIdentifier(SmcImportClassStatementElement element) {
        return gePsiByToken(element, SmcTypes.IMPORT_CLASS_STATEMENT);
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

    public static PsiElement getNameIdentifier(SmcContextClass element) {
        return gePsiByToken(element, SmcTypes.CONTEXT_CLASS_NAME);
    }

    public static PsiElement getNameIdentifier(SmcContextClassPackageElement element) {
        return gePsiByToken(element, SmcTypes.CONTEXT_CLASS_PACKAGE);
    }

    public static PsiElement getNameIdentifier(SmcImportClassPackageElement element) {
        return gePsiByToken(element, SmcTypes.IMPORT_CLASS_PACKAGE);
    }

    public static String getQualifiedName(SmcContextClass element) {
        return  getPackageText(element) + element.getName();
    }

    @Nullable
    public static String getPackageText(SmcContextClass element) {
        SmcContextClassDeclaration smcContextClass = PsiTreeUtil.getParentOfType(element, SmcContextClassDeclaration.class);
        SmcContextClassPackageElement packageNameElement = PsiTreeUtil.findChildOfType(smcContextClass, SmcContextClassPackageElement.class);
        String resultPackageName;
        if (packageNameElement == null) {
            SmcFsmPackage smcFsmPackage = PsiTreeUtil.getChildOfType(element.getContainingFile(), SmcFsmPackage.class);
            resultPackageName = smcFsmPackage != null ? smcFsmPackage.getName() : null;
        } else {
            resultPackageName = packageNameElement.getName();
        }
        return resultPackageName;
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


    public static String getQualifiedName(SmcImportClassStatementElement element) {
        return getPackageText(element) + element.getName();
    }

    public static String getPackageText(SmcImportClassStatementElement element) {
        SmcImportClass smcImportClass = PsiTreeUtil.getParentOfType(element, SmcImportClass.class);
        SmcImportClassPackageElement packageNameElement = PsiTreeUtil.getChildOfType(smcImportClass, SmcImportClassPackageElement.class);
        return packageNameElement != null ? packageNameElement.getName() : "";
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

    public static boolean isWildcard(SmcImportClassStatementElement element) {
        return element.getName() != null && element.getName().contains("*");
    }

    public static boolean isClassName(SmcImportClassStatementElement element) {
        return element.getName() != null && !element.getName().contains("*");
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