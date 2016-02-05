package com.smcplugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */

public class SmcReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public SmcReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<SmcTransition> properties = SmcUtil.findTransitions(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (SmcTransition transition : properties) {
            results.add(new PsiElementResolveResult(transition));
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
        Project project = myElement.getProject();
        List<SmcTransition> properties = SmcUtil.findTransitions(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final SmcTransition property : properties) {
            if (property.getName() != null && property.getName().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(SmcIcons.FILE).
                        withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}