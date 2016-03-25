package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcMap;
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
//TODO: Implement AbstractGlobalReference once required
public class SmcMapReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public SmcMapReference(PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    private String key;


    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<SmcMap> properties = SmcPsiUtil.findMap(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (SmcMap property : properties) {
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
        List<SmcMap> properties = SmcPsiUtil.findMap(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final SmcMap property : properties) {
            if (!StringUtil.isEmpty(property.getName())) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(AllIcons.Hierarchy.MethodDefined).
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
