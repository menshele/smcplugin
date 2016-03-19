package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.smcplugin.psi.SmcPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Deprecation candidate see {@link AbstractNamedLocalReference}
 *
 * Created by lemen on 13.03.2016.
 */
//TODO: Implement AbstractGlobalReference once required
public class SmcJavaMethodReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public SmcJavaMethodReference(PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    private String key;


    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<PsiMethod> properties = SmcPsiUtil.findJavaMethod(project, key);
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
        Project project = myElement.getProject();
        List<PsiMethod> properties = SmcPsiUtil.findJavaMethod(project);
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
