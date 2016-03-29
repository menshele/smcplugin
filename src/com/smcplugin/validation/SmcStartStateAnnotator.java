package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.*;
import com.smcplugin.util.SmcStringUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcStartStateAnnotator implements Annotator {

    private static final String DECLARATION_NOT_FOUND = "There is no {0} with name {1} found";
    private static final String DECLARATION_NOT_FOUND_WITHIN = "There is no {0} with name {1} found inside {2} with name {3}";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        if (element instanceof SmcStartMapNameElement) {
            SmcStartMapNameElement mapNameElement = (SmcStartMapNameElement) element;
            if (mapNameElement.getName() != null && SmcPsiUtil.findMap(element.getContainingFile(), mapNameElement.getName()).isEmpty()) {
                holder.createErrorAnnotation(element, getNoElementDeclarationFoundMessage(SmcTypes.MAP, mapNameElement.getName()));
            }
        }

        if (element instanceof SmcStartStateNameElement) {
            SmcStartStateNameElement stateNameElement = (SmcStartStateNameElement) element;
            SmcStartState start = (SmcStartState) element.getParent();
            String name = stateNameElement.getName();
            String mapName = start.getMapName();
            if (name != null &&
                    SmcPsiUtil.getElementsByTypeAndNameWithinNamedType(element,
                            SmcMap.class,
                            mapName,
                            SmcState.class,
                            name).isEmpty()) {
                holder.createErrorAnnotation(element, getNoElementFoundInsideOtherElementMessage(SmcTypes.STATE,
                        name, SmcTypes.MAP, mapName));
            }
        }
    }

    @NotNull
    protected String getNoElementDeclarationFoundMessage(IElementType element, String name) {
        return MessageFormat.format(DECLARATION_NOT_FOUND, getAnnotatedTypeDisplayName(element), name);
    }

    @NotNull
    protected String getNoElementFoundInsideOtherElementMessage(IElementType element,
                                                                String name,
                                                                IElementType otherElement,
                                                                String otherName) {
        return MessageFormat.format(DECLARATION_NOT_FOUND_WITHIN,
                getAnnotatedTypeDisplayName(element), name,
                getAnnotatedTypeDisplayName(otherElement), otherName);
    }

    @NotNull
    protected String getAnnotatedTypeDisplayName(IElementType element) {
        return SmcStringUtils.toDisplayString(element.toString());
    }
}
