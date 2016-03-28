package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.PresentationFactory;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static final String DOT = ".";
    public static final String ARG_PREFIX = "arg";
    public static final String MY_COMMA = ",";
    public static final String SPACE = " ";

    public static PsiElement getNameIdentifier(SmcMap element) {
        return gePsiByToken(element, SmcTypes.MAP_NAME);
    }

    public static ItemPresentation getPresentation(SmcMap element) {
        return PresentationFactory.forMap(element);
    }


    public static Icon getElementIcon(SmcMap element, @Iconable.IconFlags int flags) {
        return SmcIcons.SM_MAP;
    }

    public static PsiElement getNameIdentifier(SmcState element) {
        return gePsiByToken(element, SmcTypes.STATE_NAME);
    }

    public static ItemPresentation getPresentation(SmcState element) {
        return PresentationFactory.forState(element);
    }

    public static Icon getElementIcon(SmcState element, @Iconable.IconFlags int flags) {
        return SmcIcons.STATE;
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

    public static PsiElement getNameIdentifier(SmcImportClassPackageElement element) {
        return gePsiByToken(element, SmcTypes.IMPORT_CLASS_PACKAGE);
    }

    public static PsiElement getNameIdentifier(SmcContextClassPackageElement element) {
        return gePsiByToken(element, SmcTypes.CONTEXT_CLASS_PACKAGE);
    }

    public static String getQualifiedName(SmcContextClass element) {
        return element.getPackageName() + element.getName();
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

    public static PsiElement getNameIdentifier(SmcFsmClass element) {
        return gePsiByToken(element, SmcTypes.FSM_CLASS_NAME);
    }


    @Nullable
    public static String getPackageText(SmcFsmClass element) {
        SmcFsmPackage smcFsmPackage = PsiTreeUtil.getChildOfType(element.getContainingFile(), SmcFsmPackage.class);
        return smcFsmPackage != null ? smcFsmPackage.getName() : ".";
    }

    public static int getArgumentCount(SmcAction element) {
        return element.getArguments() != null ? element.getArguments().getArgumentsCount() : 0;
    }

    public static int getArgumentCount(SmcTransition element) {
        SmcTransitionArgs transitionArgs = element.getTransitionArgs();
        SmcParameters parameters = transitionArgs != null ? transitionArgs.getParameters() : null;
        return parameters != null ? parameters.getParameterList().size() : 0;
    }

    public static Icon getElementIcon(SmcAction element, @Iconable.IconFlags int flags) {
        return SmcIcons.CTX_ACTION;
    }

    public static Icon getElementIcon(SmcTransition element, @Iconable.IconFlags int flags) {
        return SmcIcons.TRANSITION;
    }

    public static ItemPresentation getPresentation(SmcAction element) {
        return PresentationFactory.forAction(element);
    }

    public static ItemPresentation getPresentation(SmcTransition element) {
        return PresentationFactory.forTransition(element);
    }


    public static String getType(SmcEntry element) {
        return SmcOnStateNestedElement.ON_ENTRY_TYPE;
    }

    public static String getType(SmcExit element) {
        return SmcOnStateNestedElement.ON_EXIT_TYPE;
    }

    public static String getQualifiedFullName(SmcAction action) {
        PsiNamedElement transition = PsiTreeUtil.getParentOfType(action, SmcTransition.class);
        String actionParentName;
        if (transition != null) {
            actionParentName = transition.getName();
        } else {
            SmcOnStateNestedElement onState = PsiTreeUtil.getParentOfType(action, SmcOnStateNestedElement.class);
            actionParentName = onState != null ? onState.getType() : "";
        }

        SmcState state = PsiTreeUtil.getParentOfType(action, SmcState.class);
        return (state != null ? state.getName() + DOT : "") + actionParentName + DOT + action.getFullName();
    }

    public static String getQualifiedFullName(SmcTransition smcTransition) {
        SmcState state = PsiTreeUtil.getParentOfType(smcTransition, SmcState.class);
        return (state != null ? state.getName() + DOT : "") +  smcTransition.getFullName();
    }

    public static String getFullName(SmcAction action) {
        return SmcPsiUtil.getFullNameMethod(action.getName(), action.getArgumentCount(), true);
    }

    public static String getFullName(SmcTransition transition) {
        return SmcPsiUtil.getFullNameMethod(transition.getName(), transition.getArgumentCount(), false);
    }

    public static int getArgumentsCount(SmcArguments element) {
        return element.getArgumentList().size();
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