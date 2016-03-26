package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.SmcIcons;
import com.smcplugin.psi.SmcMap;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SmcGlobalStateReference  extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    protected String mapName;
    protected String stateName;

    protected SmcGlobalStateReference(PsiElement element, String mapName, String stateName, TextRange rangeInElement) {
            super(element, rangeInElement);
            this.mapName = mapName;
            this.stateName = stateName;
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

        Collection<SmcState> enclosedTypes = SmcPsiUtil.getAllElementsByTypeWithinNamedType(myElement, SmcMap.class, mapName, SmcState.class);
        for (final SmcState enclosed : enclosedTypes) {
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
        Collection<SmcState> findNamedElementsByName = SmcPsiUtil.getElementsByTypeAndNameWithinNamedType(myElement,  SmcMap.class, mapName, SmcState.class, stateName);
        for (SmcState enclosed : findNamedElementsByName) {
            results.add(new PsiElementResolveResult(enclosed));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (myElement instanceof PsiNamedElement) {
            ((PsiNamedElement)myElement).setName(newElementName);
        }
        return myElement;
    }
}