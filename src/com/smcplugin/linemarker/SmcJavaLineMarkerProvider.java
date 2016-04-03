package com.smcplugin.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.*;
import com.intellij.util.containers.Predicate;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

/**
 * scmplugin
 * Created by lemen on 13.03.2016.
 */


public class SmcJavaLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof PsiClass) {
            PsiClass psiClass = (PsiClass) element;
            registerMarker(element, result, new ContextClassPredicate(psiClass), SmcIcons.CONTEXT, "Navigate to a sm files with Context class \"{0}\"");
            registerMarker(element, result, new FsmClassPredicate(psiClass), SmcIcons.SM, "Navigate to FSM file for \"{0}\"");
        }

        if (element instanceof PsiMethod) {
            PsiMethod psiMethod = (PsiMethod) element;
            PsiIdentifier nameIdentifier = psiMethod.getNameIdentifier();
            PsiClass containingClass = psiMethod.getContainingClass();
            if (nameIdentifier != null && containingClass != null) {
                registerMethodLike(psiMethod, null, result, SmcAction.class,
                        new ContextClassMethodPredicate(containingClass),
                        SmcIcons.CTX_ACTION_REF, "Navigate to a FSM actions for \"{0}\"");
                registerMethodLike(psiMethod, null, result, SmcTransition.class,
                        new FsmClassMethodPredicate(containingClass),
                        SmcIcons.TRANSITION, "Navigate to a FSM transitions for \"{0}\"");
            }
        }
        if (element instanceof PsiMethodCallExpression) {
            PsiMethodCallExpression methodCallExpression = (PsiMethodCallExpression) element;
            PsiMethod psiMethod = methodCallExpression.resolveMethod();
            if (psiMethod != null) {
                PsiClass containingClass = psiMethod.getContainingClass();
                if (containingClass != null) {
                    registerMethodLike(psiMethod, methodCallExpression, result, SmcTransition.class,
                            new FsmClassMethodPredicate(containingClass),
                            SmcIcons.TRANSITION_REF, "Navigate to a FSM transition for \"{0}\"");
                }
            }
        }
    }

    private void registerMethodLike(PsiMethod psiMethod,
                                    PsiElement identifier,
                                    Collection<? super RelatedItemLineMarkerInfo> result,
                                    Class<? extends SmcMethodLikeElement> aClass,
                                    Predicate<SmcMethodLikeElement> predicate,
                                    Icon icon,
                                    String toolTipPattern) {
        PsiElement nameIdentifier = identifier != null ? identifier : psiMethod.getNameIdentifier();
        if (nameIdentifier != null) {
            final List<? extends SmcMethodLikeElement> methodLikeElements = SmcPsiUtil.findMethodLikeForMethod(psiMethod, aClass, predicate);
            if (methodLikeElements != null && !methodLikeElements.isEmpty()) {
                ItemPresentation presentation = psiMethod.getPresentation();
                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(icon).
                                setTargets(methodLikeElements).
                                setTooltipText(MessageFormat.format(toolTipPattern,
                                        presentation != null ? presentation.getPresentableText() : psiMethod.getName()));
                result.add(builder.createLineMarkerInfo(nameIdentifier));
            }
        }
    }

    public void registerMarker(PsiElement element,
                               Collection<? super RelatedItemLineMarkerInfo> result,
                               Predicate<SmcFile> predicate,
                               Icon icon,
                               String messagePattern) {
        PsiClass psiClass = (PsiClass) element;
        PsiIdentifier psiName = psiClass.getNameIdentifier();
        String name = psiClass.getQualifiedName();
        if (name != null && psiName != null) {
            final List<SmcFile> smcFiles = SmcPsiUtil.findSmcFile(psiClass.getProject(), predicate);
            if (smcFiles != null && !smcFiles.isEmpty()) {
                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(icon).
                                setTargets(smcFiles).
                                setTooltipText(MessageFormat.format(messagePattern, name));
                result.add(builder.createLineMarkerInfo(psiName));
            }
        }
    }
}
