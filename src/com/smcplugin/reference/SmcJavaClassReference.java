package com.smcplugin.reference;

import com.intellij.codeInsight.completion.JavaLookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
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
 * <p>
 * Created by lemen on 13.03.2016.
 */
public class SmcJavaClassReference extends PsiReferenceBase<PsiNamedElement> implements PsiPolyVariantReference {

    private static final String DOT = ".";
    private String packageName;
    private String name;


    public SmcJavaClassReference(PsiNamedElement element, String packageName, TextRange textRange) {
        super(element, textRange);
        this.name = packageName + DOT + element.getName();
        this.packageName = packageName;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final PsiClass[] properties = SmcPsiUtil.findClasses(name, myElement.getProject());
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
        List<PsiClass> classesForPackage = SmcPsiUtil.findClassesForPackage(packageName, myElement.getProject());
        List<LookupElement> variants = new ArrayList<LookupElement>();

        for (final PsiClass psiClass : classesForPackage) {
            if (!StringUtils.isEmpty(psiClass.getQualifiedName())) {
                variants.add(JavaLookupElementBuilder.forClass(psiClass));
            }
        }
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (myElement != null) {
            myElement.setName(newElementName);
        }
        return myElement;
    }
}
