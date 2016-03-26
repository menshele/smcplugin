package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.*;
import com.smcplugin.util.SmcStringUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcNamedElementExistsAnnotator implements Annotator {

    private static final String DECLARATION_NOT_FOUND = "There is no {0} with name {1} found";

    private final List<TypeDescriptor<SmcNamedElement>> validateClasses = new ArrayList<>();

    public SmcNamedElementExistsAnnotator() {
        validateClasses.add(new TypeDescriptor<>(SmcNextState.class, SmcTypes.STATE));
    }

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        for (TypeDescriptor<SmcNamedElement> descriptor : validateClasses) {
            Class<? extends SmcNamedElement> typeClass = descriptor.getTypeClass();
            if (typeClass.isInstance(element)) {
                SmcNamedElement smcCustomNamed = (SmcNamedElement) element;
                if (smcCustomNamed.getName() != null && !SmcPsiUtil.hasNamedElementsByTypeWithinParentType(element, SmcMap.class, SmcState.class, smcCustomNamed.getName())) {
                    holder.createErrorAnnotation(element, getNoElementDeclarationFoundMessage(descriptor.getTypeType(), smcCustomNamed.getName()));
                }
            }
        }
    }

    @NotNull
    protected String getNoElementDeclarationFoundMessage(IElementType element, String name) {
        return MessageFormat.format(DECLARATION_NOT_FOUND, getAnnotatedTypeDisplayName(element), name);
    }

    @NotNull
    protected String getAnnotatedTypeDisplayName(IElementType element) {
        return SmcStringUtils.toDisplayString(element.toString());
    }
}
