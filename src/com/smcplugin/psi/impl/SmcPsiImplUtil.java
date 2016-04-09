package com.smcplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.PresentationFactory;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.*;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
public class SmcPsiImplUtil {

    public static final String STRING_DOT = ".";
    public static final String ARG_PREFIX = "arg";
    public static final String MY_COMMA = ",";
    public static final String SPACE = " ";
    public static final String WILDCARD = "*";
    public static final String ARROW = "->";

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
        SmcQualifiedIdentifier childOfType = PsiTreeUtil.getChildOfType(element, SmcQualifiedIdentifier.class);
        return childOfType != null ? childOfType.getName() : "";
    }


    public static String getName(SmcContextClassDeclaration element) {
        SmcQualifiedIdentifier childOfType = PsiTreeUtil.getChildOfType(element, SmcQualifiedIdentifier.class);
        return childOfType != null ? childOfType.getName() : "";
    }

    public static PsiElement getNameIdentifier(SmcTransition element) {
        return gePsiByToken(element, SmcTypes.TRANSITION_NAME);
    }

    public static boolean matches(SmcMethodLikeElement methodLikeElement, PsiMethod method) {
        PsiClass containingClass = method.getContainingClass();
        SmcFile containingFile = (SmcFile) methodLikeElement.getContainingFile();
        PsiClass fsmClass = containingFile.getFsmClass();
        if (fsmClass == null || containingClass == null) {
            return false;
        }
        if (!containingClass.isEquivalentTo(fsmClass) && !containingClass.isInheritor(fsmClass, true)) {
            return false;
        }

        return method.getName().equals(methodLikeElement.getName()) &&
                method.getParameterList().getParametersCount() == methodLikeElement.getArgumentCount();
    }


    public static PsiElement getNameIdentifier(SmcStartMapNameElement element) {
        return gePsiByToken(element, SmcTypes.START_MAP_NAME);
    }

    public static PsiElement getNameIdentifier(SmcStartStateNameElement element) {
        return gePsiByToken(element, SmcTypes.START_STATE_NAME);
    }

    public static PsiElement getNameIdentifier(SmcQualifiedIdentifier element) {
        SmcIdentifier[] identifiers = PsiTreeUtil.getChildrenOfType(element, SmcIdentifier.class);

        return ArrayUtils.isEmpty(identifiers) ? null : gePsiByToken(identifiers[identifiers.length - 1], SmcTypes.IDENTIFIER_NAME);
    }

    public static PsiElement getNameIdentifier(SmcIdentifier element) {
        return gePsiByToken(element, SmcTypes.IDENTIFIER_NAME);
    }

    public static String getName(SmcStaticImport element) {
        return getStringName(element, SmcTypes.IDENTIFIER_NAME);
    }

    public static String getName(SmcQualifiedIdentifier element) {
        SmcQualifiedIdElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(element, SmcQualifiedIdElement.class);
        String name = "";
        if (!ArrayUtils.isEmpty(childrenOfType)) {
            name = childrenOfType[childrenOfType.length - 1].getQualifiedName();
        }
        return name;
    }

    public static SmcQualifiedIdElement getLastIdentifier(SmcQualifiedIdentifier element) {
        SmcQualifiedIdElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(element, SmcQualifiedIdElement.class);
        SmcQualifiedIdElement result = null;
        if (!ArrayUtils.isEmpty(childrenOfType)) {
            result = childrenOfType[childrenOfType.length - 1];
        }
        return result;
    }

    public static boolean isWildcard(SmcQualifiedIdentifier element) {
        SmcQualifiedIdElement lastIdentifier = element.getLastIdentifier();
        return lastIdentifier != null && WILDCARD.equals(lastIdentifier.getName());
    }

    public static PsiElement getNameIdentifier(SmcFsmClass element) {
        return gePsiByToken(element, SmcTypes.FSM_CLASS_NAME);
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

    public static ItemPresentation getPresentation(SmcEntry element) {
        return PresentationFactory.forEntry(element);
    }

    public static String getType(SmcExit element) {
        return SmcOnStateNestedElement.ON_EXIT_TYPE;
    }

    public static ItemPresentation getPresentation(SmcExit element) {
        return PresentationFactory.forExit(element);
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
        return (state != null ? state.getName() + STRING_DOT : "") + actionParentName + STRING_DOT + action.getFullName();
    }

    public static String getQualifiedFullName(SmcTransition smcTransition) {
        SmcState state = PsiTreeUtil.getParentOfType(smcTransition, SmcState.class);
        return (state != null ? state.getName() + STRING_DOT : "") + smcTransition.getFullName();
    }

    public static String getFullName(SmcAction action) {
        return SmcPsiUtil.getFullNameMethod(action.getName(), action.getArgumentCount(), true);
    }

    public static String getFullName(SmcTransition transition) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = transition.getName();
        stringBuilder.append(name);
        SmcTransitionArgs transitionArgs = transition.getTransitionArgs();
        if (transitionArgs != null) {
            stringBuilder.append(transitionArgs.getText());
        }
        SmcNextState next = transition.getNextState();
        if (next != null) {
            stringBuilder.append(ARROW);
            stringBuilder.append(next.getText());
        } else {
            SmcPushTransition push = transition.getPushTransition();
            if (push != null) {
                stringBuilder.append(ARROW);
                stringBuilder.append(push.getText());
            } else {
                SmcPopTransition pop = transition.getPopTransition();
                if (pop != null) {
                    stringBuilder.append(ARROW);
                    stringBuilder.append(pop.getText());
                }
            }
        }
        return stringBuilder.toString();
    }

    public static int getArgumentsCount(SmcArguments element) {
        return element.getArgumentList().size();
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