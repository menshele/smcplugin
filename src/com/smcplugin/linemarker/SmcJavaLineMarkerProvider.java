package com.smcplugin.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import com.intellij.util.containers.Predicate;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
            registerMarker(element, result, SmcContextClass.class, SmcIcons.CONTEXT, "Navigate to a sm files with Context class \"{0}\"");
            registerMarker(element, result, SmcFsmClass.class, SmcIcons.SM, "Navigate to FSM file for \"{0}\"");
        }

        if (element instanceof PsiMethod) {
            PsiMethod psiMethod = (PsiMethod) element;
            PsiIdentifier nameIdentifier = psiMethod.getNameIdentifier();
            PsiClass containingClass = psiMethod.getContainingClass();
            if (nameIdentifier != null && containingClass != null) {
                registerMethodLike(psiMethod, result, SmcAction.class, new Predicate<SmcMethodLikeElement>() {
                    @Override
                    public boolean apply(@Nullable SmcMethodLikeElement methodLikeElement) {
                        return methodLikeElement != null && ((SmcFile) methodLikeElement.getContainingFile()).getContextClassQName().equals(containingClass.getQualifiedName());
                    }
                }, SmcIcons.CTX_ACTION_REF, "Navigate to a FSM actions for \"{0}\"");
                registerMethodLike(psiMethod, result, SmcTransition.class, new Predicate<SmcMethodLikeElement>() {
                    @Override
                    public boolean apply(@Nullable SmcMethodLikeElement methodLikeElement) {
                        return methodLikeElement != null && ((SmcFile) methodLikeElement.getContainingFile()).getFsmClassQName().equals(containingClass.getQualifiedName());
                    }
                },SmcIcons.TRANSITION, "Navigate to a FSM transitions for \"{0}\"");
            }
        }
    }

    private void registerMethodLike(PsiMethod psiMethod,
                                    Collection<? super RelatedItemLineMarkerInfo> result,
                                    Class<? extends SmcMethodLikeElement> aClass,
                                    Predicate<SmcMethodLikeElement> predicate,
                                    Icon icon,
                                    String toolTipPattern) {
        PsiIdentifier nameIdentifier = psiMethod.getNameIdentifier();
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

    private void registerTransitions(Collection<? super RelatedItemLineMarkerInfo> result, PsiMethod psiMethod, PsiIdentifier nameIdentifier) {
        final List<SmcTransition> smcTransitions = SmcPsiUtil.findTransitionsForMethod(psiMethod);
        if (smcTransitions != null && !smcTransitions.isEmpty()) {
            ItemPresentation presentation = psiMethod.getPresentation();
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(SmcIcons.TRANSITION).
                            setTargets(smcTransitions).
                            setTooltipText(MessageFormat.format("Navigate to a FSM transitions for \"{0}\"",
                                    presentation != null ? presentation.getPresentableText() : psiMethod.getName()));
            result.add(builder.createLineMarkerInfo(nameIdentifier));
        }
    }

    public void registerMarker(PsiElement element,
                               Collection<? super RelatedItemLineMarkerInfo> result,
                               Class<? extends SmcQualifiedNamedElement> cClass,
                               Icon icon,
                               String messagePattern) {
        PsiClass psiClass = (PsiClass) element;
        PsiIdentifier psiName = psiClass.getNameIdentifier();
        String name = psiClass.getQualifiedName();
        if (name != null && psiName != null) {
            final List<SmcFile> smcFiles = SmcPsiUtil.findSmcWithQualifiedNamedElement(psiClass.getProject(),
                    cClass, name);
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
