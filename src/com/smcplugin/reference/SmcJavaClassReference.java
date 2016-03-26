package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcPsiUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Deprecation candidate see {@link AbstractNamedLocalReference}
 *
 * Created by lemen on 13.03.2016.
 */
public class SmcJavaClassReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {


    public SmcJavaClassReference(PsiElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    public SmcJavaClassReference(PsiElement element, String name, TextRange textRange) {
        super(element, textRange);
        this.name =name;
    }

    private String name;


    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final PsiClass[] properties = SmcPsiUtil.findClasses(name);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (PsiClass property : properties) {
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
        final PsiClass[] properties = SmcPsiUtil.findClasses(name);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiClass property : properties) {
            if (!StringUtils.isEmpty(property.getQualifiedName())) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(AllIcons.Hierarchy.Class).
                        withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (myElement instanceof PsiNamedElement) {
            ((PsiNamedElement)myElement).setName(newElementName);
        }
        return myElement;
    }
}
