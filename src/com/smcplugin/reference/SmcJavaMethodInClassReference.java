package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Deprecation candidate see {@link AbstractNamedLocalReference}
 * <p>
 * Created by lemen on 13.03.2016.
 */
public class SmcJavaMethodInClassReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final String className;
    private final int paramCount;

    public SmcJavaMethodInClassReference(PsiElement element, TextRange textRange, String className, int paramCount) {
        super(element, textRange);
        methodName = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        this.className = className;
        this.paramCount = paramCount;
    }

    public SmcJavaMethodInClassReference(PsiElement element, TextRange textRange, String className) {
        super(element, textRange);
        methodName = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        this.className = className;
        this.paramCount = -1;
    }

    private String methodName;


    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<PsiMethod> properties = SmcPsiUtil.findMethodInClass(className, methodName, paramCount);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (PsiMethod property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        List<PsiMethod> properties = SmcPsiUtil.findMethodInClass(className, methodName, paramCount);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiMethod property : properties) {
            if (property.getName().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(AllIcons.Hierarchy.MethodDefined).
                        withTypeText(property.getContainingFile().getName())
                );
            }
        }

        return variants.toArray();
    }
}
