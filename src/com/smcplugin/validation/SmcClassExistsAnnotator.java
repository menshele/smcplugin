package com.smcplugin.validation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.*;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcClassExistsAnnotator implements Annotator {
    private static final String CLASS_NOT_FOUND = "Can\'\'t find Java class \"{0}\"";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SmcContextClassDeclaration) {
            SmcContextClassDeclaration classNameElement = (SmcContextClassDeclaration) element;
            SmcFile file = (SmcFile) classNameElement.getContainingFile();
            String contextClassQName = file.getContextClassQName();
            SmcQualifiedIdentifier qualifiedIdentifier = classNameElement.getQualifiedIdentifier();
            if (contextClassQName != null && !SmcPsiUtil.classExists(contextClassQName) && qualifiedIdentifier != null) {
                Annotation errorAnnotation = holder.createErrorAnnotation(qualifiedIdentifier.getLastIdentifier(),
                        getMessage(contextClassQName));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
            }
        } else if (element instanceof SmcImportClass) {
            SmcImportClass classNameElement = (SmcImportClass) element;
            SmcQualifiedIdentifier qualifiedIdentifier = classNameElement.getQualifiedIdentifier();
            String qualifiedName = classNameElement.getQualifiedIdentifier().getName();
            if (StringUtils.isNotBlank(qualifiedName) && !SmcPsiUtil.classExists(qualifiedName)) {
                Annotation errorAnnotation = holder.createErrorAnnotation(qualifiedIdentifier.getLastIdentifier(),
                        getMessage(qualifiedName));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
            }
        }
    }

    @NotNull
    private String getMessage(String className) {
        return MessageFormat.format(CLASS_NOT_FOUND, className);
    }
}
