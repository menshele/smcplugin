package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcContextClass;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcContextClassAnnotator implements Annotator {
    private static final String CLASS_NOT_FOUND = "Can\'\'t find Java class \"{0}\"";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SmcContextClass) {
            SmcContextClass classNameElement = (SmcContextClass) element;
            String qualifiedName = classNameElement.getQualifiedName();
            if (qualifiedName != null && SmcPsiUtil.classExists(qualifiedName)) {
                holder.createErrorAnnotation(classNameElement, getMessage(classNameElement.getQualifiedName()));
            }
        }
    }

    @NotNull
    private String getMessage(String className) {
        return MessageFormat.format(CLASS_NOT_FOUND, className);
    }
}
