package com.smcplugin.validation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcContextClass;
import com.smcplugin.psi.SmcImportClassStatementElement;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcQualifiedNamedElement;
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
        if (element instanceof SmcContextClass) {
            SmcQualifiedNamedElement classNameElement = (SmcQualifiedNamedElement) element;
            String qualifiedName = classNameElement.getQualifiedName();
            if (qualifiedName != null && !SmcPsiUtil.classExists(qualifiedName)) {
                Annotation errorAnnotation = holder.createErrorAnnotation(classNameElement.getParent(), getMessage(classNameElement.getQualifiedName()));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
            }
        } else if (element instanceof SmcImportClassStatementElement) {
            SmcImportClassStatementElement classNameElement = (SmcImportClassStatementElement) element;
            String qualifiedName = classNameElement.getQualifiedName();
            if (classNameElement.isClassName() && !SmcPsiUtil.classExists(qualifiedName)) {
                Annotation errorAnnotation = holder.createErrorAnnotation(classNameElement.getParent(), getMessage(classNameElement.getQualifiedName()));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
            }
        }
    }

    @NotNull
    private String getMessage(String className) {
        return MessageFormat.format(CLASS_NOT_FOUND, className);
    }
}
