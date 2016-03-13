package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Reference
 * <p>
 * scmplugin
 * Created by lemen on 13.03.2016.
 */
public abstract class AbstractNamedLocalReference<ENCLOSING_TYPE extends PsiElement, ENCLOSED_TYPE extends PsiNamedElement>
        extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    protected String name;
    private final Class<ENCLOSING_TYPE> enclosingTypeClass;
    private final Class<ENCLOSED_TYPE> enclosedTypeClass;

    protected AbstractNamedLocalReference(Class<ENCLOSING_TYPE> enclosingTypeClass,
                                          Class<ENCLOSED_TYPE> enclosedTypeClass,
                                          PsiElement element,
                                          TextRange rangeInElement) {
        super(element, rangeInElement);
        this.enclosingTypeClass = enclosingTypeClass;
        this.enclosedTypeClass = enclosedTypeClass;
        name = element.getText().substring(getRangeInElement().getStartOffset(), getRangeInElement().getEndOffset());
    }

    protected AbstractNamedLocalReference(Class<ENCLOSING_TYPE> enclosingTypeClass,
                                          Class<ENCLOSED_TYPE> enclosedTypeClass,
                                          PsiElement element) {
        this(enclosingTypeClass, enclosedTypeClass, element, new TextRange(0, element.getText().length()));
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
        List<LookupElement> variants = new ArrayList<>();

        Collection<ENCLOSED_TYPE> enclosedTypes = SmcPsiUtil.getElementsByTypeWithinSameType(myElement, enclosingTypeClass, enclosedTypeClass);
        for (final ENCLOSED_TYPE enclosed : enclosedTypes) {
            if (!StringUtil.isEmpty(enclosed.getName())) {
                variants.add(LookupElementBuilder.create(enclosed).
                        withIcon(SmcIcons.FILE).
                        withTypeText(enclosed.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        Collection<ENCLOSED_TYPE> findNamedElementsByName = SmcPsiUtil.getNamedElementsByTypeWithinSameType(myElement, enclosingTypeClass, enclosedTypeClass, name);
        for (ENCLOSED_TYPE enclosed : findNamedElementsByName) {
            results.add(new PsiElementResolveResult(enclosed));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }
}
