package com.smcplugin.validation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcParameterType;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 27.03.2016.
 */
public class SmcTypeIsAvailableAnnotator implements Annotator {
    private static final String TYPE_NOT_RESOLVED_MESSAGE = "Cannot resolve \"{0}\" type.";

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SmcParameterType) {
            SmcParameterType type = (SmcParameterType) element;
            String name = type.getName();
            PsiElement nameIdentifier = type.getNameIdentifier();
            if (name == null || nameIdentifier == null) return;
            if (!SmcPsiUtil.isTypeAvailableFromSmc(type)) {
                Annotation errorAnnotation = holder.createErrorAnnotation(nameIdentifier, getMessage(name));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
            }
        }
    }

    @NotNull
    private String getMessage(String name) {
        return MessageFormat.format(TYPE_NOT_RESOLVED_MESSAGE, name);
    }
}
