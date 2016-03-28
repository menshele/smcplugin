package com.smcplugin.linemarker;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
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
            registerMarker(element, result, SmcContextClass.class, SmcIcons.CONTEXT, "Navigate to a sm files with Context class \"{0}\"");
            registerMarker(element, result, SmcFsmClass.class, SmcIcons.SM, "Navigate to FSM file for \"{0}\"");
        }

        if (element instanceof PsiMethod) {
            PsiMethod psiMethod = (PsiMethod) element;
            PsiIdentifier nameIdentifier = psiMethod.getNameIdentifier();
            if (nameIdentifier != null) {
                registerActions(result, psiMethod, nameIdentifier);
                registerTransitions(result, psiMethod, nameIdentifier);
            }
        }
    }

    private void registerActions(Collection<? super RelatedItemLineMarkerInfo> result, PsiMethod psiMethod, PsiIdentifier nameIdentifier) {
        final List<SmcAction> smcActions = SmcPsiUtil.findActionsForMethod(psiMethod);
        if (smcActions != null && !smcActions.isEmpty()) {
            ItemPresentation presentation = psiMethod.getPresentation();
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(SmcIcons.CTX_ACTION_REF).
                            setTargets(smcActions).
                            setTooltipText(MessageFormat.format("Navigate to a FSM actions for \"{0}\"",
                                    presentation != null ? presentation.getPresentableText() : psiMethod.getName()));
            result.add(builder.createLineMarkerInfo(nameIdentifier));
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
