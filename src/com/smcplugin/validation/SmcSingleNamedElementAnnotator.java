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
public class SmcSingleNamedElementAnnotator implements Annotator {
    private static final String DUPLICATE_NAMED_MESSAGE = "Duplicate {0} named \"{1}\"";
    private final TypeDescriptor[] validateClasses = new TypeDescriptor[]{
            new TypeDescriptor<SmcNamedElement>(SmcMap.class, SmcTypes.MAP),
            new TypeDescriptor<SmcNamedElement>(SmcState.class, SmcTypes.STATE)
    };

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        for (TypeDescriptor descriptor : validateClasses) {
            Class typeClass = descriptor.getTypeClass();
            if (typeClass.isInstance(element)) {
                PsiElement parent = element.getParent();
                SmcNamedElement namedElement = (SmcNamedElement) element;
                String mapName = namedElement.getName();
                PsiElement nameIdentifier = namedElement.getNameIdentifier();
                if (mapName == null || nameIdentifier == null) return;
                if (SmcPsiUtil.isNotSingleNamedElement(parent, typeClass, mapName)) {
                    holder.createErrorAnnotation(namedElement.getNameIdentifier(), getMessage(mapName, descriptor.getTypeType()));
                }
            }
        }
    }

    @NotNull
    private String getMessage(String mapName, IElementType type) {
        return MessageFormat.format(DUPLICATE_NAMED_MESSAGE, SmcStringUtils.toDisplayString(type.toString()), mapName);
    }


}
