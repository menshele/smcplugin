package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectCoreUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.impl.JavaFileManager;
import com.intellij.psi.impl.file.impl.JavaFileManagerImpl;
import com.intellij.psi.search.GlobalSearchScope;
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

    @SuppressWarnings("ConstantConditions")
    public static final JavaFileManager fileManager = new JavaFileManagerImpl(ProjectCoreUtil.theOnlyOpenProject());

    public SmcJavaClassReference(PsiElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    private String name;


    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final PsiClass[] properties = fileManager.findClasses(name, GlobalSearchScope.projectScope(project));
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
        Project project = myElement.getProject();
        final PsiClass[] properties = fileManager.findClasses(name, GlobalSearchScope.projectScope(project));
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiClass property : properties) {
            if (property.getQualifiedName().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(AllIcons.Hierarchy.Class).
                        withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
