package com.smcplugin.validation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.psi.PsiElement;
import com.smcplugin.intentions.CreateMethodInContextClassFix;
import com.smcplugin.psi.SmcAction;
import com.smcplugin.psi.SmcFile;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcActionAnnotator implements Annotator {
    private static final String METHOD_NOT_FOUND_IN_CLASS = "Can\'\'t find method \"{0}\" in Java class \"{1}\"";
    private static final String AMBIGUOS_METHOD_FOUND_IN_CLASS = "Ambiguos method call \"{0}\" in Java class \"{1}\"";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (SmcTypes.ACTION_NAME.equals(element.getNode().getElementType())) {
            PsiElement parent = element.getParent();
            if (parent == null || !(parent instanceof SmcAction)) {
                return;
            }
            SmcAction action = (SmcAction) parent;
            SmcFile containingFile = (SmcFile)action.getContainingFile();
            String contextClassName = containingFile.getContextClassQName();
            if (!SmcPsiUtil.isMethodInClass(contextClassName, action.getName(), action.getArgumentCount())) {
                Annotation errorAnnotation = holder.createErrorAnnotation(element, getNotResolvedMessage(action.getFullName(), contextClassName));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
                errorAnnotation.registerFix(new CreateMethodInContextClassFix(action.getName(), action.getArgumentCount()));
            } else if (SmcPsiUtil.isMethodInClassNotUnique(contextClassName, action.getName(), action.getArgumentCount())) {
                holder.createWarningAnnotation(element, getAmbiguityMessage(action.getFullName(),contextClassName));
            }
        }
    }

    private String getAmbiguityMessage(String name, String contextClassName) {
        return MessageFormat.format(AMBIGUOS_METHOD_FOUND_IN_CLASS, name,contextClassName);
    }

    @NotNull
    private String getNotResolvedMessage(String methodName, String className) {
        return MessageFormat.format(METHOD_NOT_FOUND_IN_CLASS, methodName, className);
    }
}
