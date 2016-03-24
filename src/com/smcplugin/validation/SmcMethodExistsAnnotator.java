package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.SmcAction;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcMethodExistsAnnotator implements Annotator {
    private static final String METHOD_NOT_FOUND_IN_CLASS = "Can\'\'t find method \"{0}({1})\" in Java class \"{2}\"";
    public static final String ARG_PREFIX = "arg";
    public static final String COMMA = ",";
    public static final String SPACE = " ";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SmcAction) {
            SmcAction action = (SmcAction) element;
            String contextClassName = action.getContextClassName();
            if (!SmcPsiUtil.isMethodInClass(contextClassName, action.getName(), action.getArgumentCount())) {
                holder.createErrorAnnotation(action, getMessage(action.getName(), action.getArgumentCount(), contextClassName));
            }
        }
    }

    @NotNull
    private String getMessage(String methodName, int argCount, String className) {
        return MessageFormat.format(METHOD_NOT_FOUND_IN_CLASS, methodName, getArgsString(argCount), className);
    }

    public static String getArgsString(int argCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < argCount; i++) {
            stringBuilder.append(SPACE);
            stringBuilder.append(ARG_PREFIX).append(i);
            if (i < argCount - 1) {
                stringBuilder.append(COMMA);
            }
        }
        stringBuilder.append(SPACE);
        return stringBuilder.toString();
    }
}
