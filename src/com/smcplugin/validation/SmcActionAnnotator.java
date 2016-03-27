package com.smcplugin.validation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.psi.PsiElement;
import com.smcplugin.intentions.CreateMethodInContextClassFix;
import com.smcplugin.psi.SmcAction;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcActionAnnotator implements Annotator {
    private static final String METHOD_NOT_FOUND_IN_CLASS = "Can\'\'t find method \"{0}({1})\" in Java class \"{2}\"";
    public static final String ARG_PREFIX = "arg";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    private static final String AMBIGUOS_METHOD_FOUND_IN_CLASS = "Ambiguos method call \"{0}({1})\" in Java class \"{2}\"";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (SmcTypes.ACTION_NAME.equals(element.getNode().getElementType())) {
            PsiElement parent = element.getParent();
            if (parent == null || !(parent instanceof SmcAction)) {
                return;
            }
            SmcAction action = (SmcAction) parent;
            String contextClassName = action.getContextClassName();
            if (!SmcPsiUtil.isMethodInClass(contextClassName, action.getName(), action.getArgumentCount())) {
                Annotation errorAnnotation = holder.createErrorAnnotation(element, getNotResolvedMessage(action.getName(), action.getArgumentCount(), contextClassName));
                errorAnnotation.setTextAttributes(CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
                errorAnnotation.registerFix(new CreateMethodInContextClassFix(action.getName(),action.getArgumentCount()));
            } else if (SmcPsiUtil.isMethodInClassNotUnique(contextClassName, action.getName(), action.getArgumentCount())) {
                holder.createWarningAnnotation(element, getAmbiguityMessage(action.getName(), action.getArgumentCount(), contextClassName));
            }
        }
    }

    private String getAmbiguityMessage(String name, int argumentCount, String contextClassName) {
        return MessageFormat.format(AMBIGUOS_METHOD_FOUND_IN_CLASS, contextClassName, getArgsString(argumentCount), name);
    }

    @NotNull
    private String getNotResolvedMessage(String methodName, int argCount, String className) {
        return MessageFormat.format(METHOD_NOT_FOUND_IN_CLASS, methodName, getArgsString(argCount), className);
    }


    public static String getArgsString(int argCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < argCount; i++) {
            stringBuilder.append(ARG_PREFIX).append(i);
            if (i < argCount - 1) {
                stringBuilder.append(COMMA).append(SPACE);
            }
        }
        return stringBuilder.toString();
    }
}
