package com.smcplugin.validation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.smcplugin.psi.*;
import com.smcplugin.util.SmcStringUtils;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * scmplugin
 * Created by lemen on 10.03.2016.
 */
public class SmcSingleElementsAnnotator implements Annotator {

    private static final String DUPLICATE_PATTERN = "Duplicate {0} declaration";
    private static final String REQUIRED_PATTERN = "{0} declaration required";

    private final TypeDescriptor[] validateTooFewClasses = new TypeDescriptor[]{
            new TypeDescriptor<>(SmcFsmPackage.class, SmcTypes.FSM_PACKAGE),
            new TypeDescriptor<>(SmcFsmClass.class, SmcTypes.FSM_CLASS),
            new TypeDescriptor<>(SmcContextClassDeclaration.class, SmcTypes.QUALIFIED_IDENTIFIER),
            new TypeDescriptor<>(SmcStartState.class, SmcTypes.START_STATE),
    };

    private final TypeDescriptor[] validateTooManyClasses = new TypeDescriptor[]{
            new TypeDescriptor<>(SmcFsmFile.class, SmcTypes.FSM_FILE),
            new TypeDescriptor<>(SmcFsmPackage.class, SmcTypes.FSM_PACKAGE),
            new TypeDescriptor<>(SmcFsmClass.class, SmcTypes.FSM_CLASS),
            new TypeDescriptor<>(SmcContextClassDeclaration.class, SmcTypes.QUALIFIED_IDENTIFIER),
            new TypeDescriptor<>(SmcStartState.class, SmcTypes.START_STATE),
            new TypeDescriptor<>(SmcVerbatimCodeSection.class, SmcTypes.VERBATIM_CODE_SECTION)
    };

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        for (TypeDescriptor descriptor : validateTooManyClasses) {
            Class typeClass = descriptor.getTypeClass();
            if (typeClass.isInstance(element)) {
                PsiFile containingFile = element.getContainingFile();
                PsiElement firstChild = element.getFirstChild();
                Collection<?> childrenOfType = PsiTreeUtil.findChildrenOfType(containingFile, typeClass);
                if (isTooMany(childrenOfType)) {
                    if (firstChild != null) {
                        holder.createErrorAnnotation(element, getDuplicateElementDeclarationMessage(descriptor.getTypeType()));
                    }
                }
            }
        }

        if (SmcFile.class.isInstance(element)) {
            for (TypeDescriptor descriptor : validateTooFewClasses) {
                if (descriptor.isRequired()) {
                    Collection<?> childrenOfType = PsiTreeUtil.findChildrenOfType(element, descriptor.getTypeClass());
                    if (isTooFew(childrenOfType)) {
                        holder.createErrorAnnotation(TextRange.EMPTY_RANGE, getElementRequiredMessage(descriptor.getTypeType()));
                    }
                }
            }
        }
    }

    private boolean isTooFew(Collection<?> childrenOfType) {
        return childrenOfType.isEmpty();
    }

    private boolean isTooMany(Collection<?> childrenOfType) {
        return childrenOfType.size() > 1;
    }

    @NotNull
    protected String getDuplicateElementDeclarationMessage(IElementType element) {
        return MessageFormat.format(DUPLICATE_PATTERN, getAnnotatedTypeDisplayName(element));
    }

    @NotNull
    protected String getAnnotatedTypeDisplayName(IElementType element) {
        return SmcStringUtils.toDisplayString(element.toString());
    }

    protected String getElementRequiredMessage(IElementType element) {
        return MessageFormat.format(REQUIRED_PATTERN, getAnnotatedTypeDisplayName(element));
    }
}
