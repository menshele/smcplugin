package com.smcplugin.reference;

import com.intellij.codeInsight.completion.JavaLookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcQualifiedNamedElement;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Deprecation candidate see {@link AbstractNamedLocalReference}
 * <p>
 * Created by lemen on 13.03.2016.
 */
public class SmcJavaClassReference extends PsiReferenceBase<SmcQualifiedNamedElement> implements PsiPolyVariantReference {


    private static final String DOT = ".";
    private String packageName;
    private String name;


    public SmcJavaClassReference(SmcQualifiedNamedElement element, TextRange textRange) {
        super(element, textRange);
        this.name = element.getQualifiedName();
        this.packageName = element.getPackageName();
    }

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
        List<PsiClass> classesForPackage = SmcPsiUtil.findClassesForPackage(packageName);
        List<PsiPackage> subPackages = SmcPsiUtil.findSubPackagesForPackage(packageName);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiPackage subPackage : subPackages) {
            final String shortName = getShortName(subPackage.getQualifiedName());
            if (PsiNameHelper.getInstance(subPackage.getProject()).isIdentifier(shortName)) {
                variants.add(LookupElementBuilder.create(subPackage).withIcon(subPackage.getIcon(Iconable.ICON_FLAG_VISIBILITY)));
            }
        }

        for (final PsiClass psiClass : classesForPackage) {
            if (!StringUtils.isEmpty(psiClass.getQualifiedName())) {
                variants.add(JavaLookupElementBuilder.forClass(psiClass));
            }
        }
        return variants.toArray();
    }

    @NotNull
    private String getShortName(String qualifiedName) {
        int beginIndex = packageName.lastIndexOf(DOT);
        int beginIndexSub = beginIndex < 0 ? 0 : beginIndex + 1;
        final String nameAfterDot = qualifiedName.substring(beginIndexSub);
        int nextDotIndex = nameAfterDot.indexOf(DOT);
        return nameAfterDot.substring(0, nextDotIndex < 0 ? nameAfterDot.length() : nextDotIndex);
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        if (myElement != null) {
            myElement.setName(newElementName);
        }
        return myElement;
    }
}
