package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTransition;
import com.smcplugin.psi.SmcTransitions;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 27.03.2016.
 */
public class SmcTransitionAnnotator implements Annotator {
    private static final String DUPLICATE_NAMED_MESSAGE = "Duplicate transition declaration with name \"{0}\"";

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SmcTransition) {
            SmcTransitions transitions = (SmcTransitions) element.getParent();
            SmcTransition transition = (SmcTransition) element;
            String name = transition.getName();
            PsiElement nameIdentifier = transition.getNameIdentifier();
            if (name == null || nameIdentifier == null) return;
            if (SmcPsiUtil.isNotSingleTransition(transitions, name)) {
                holder.createErrorAnnotation(transition, getMessage(name));
            }
        }
    }

    @NotNull
    private String getMessage(String name) {
        return MessageFormat.format(DUPLICATE_NAMED_MESSAGE, name);
    }
}
