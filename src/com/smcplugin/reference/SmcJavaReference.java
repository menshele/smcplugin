package com.smcplugin.reference;

import com.intellij.codeInsight.completion.JavaLookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.smcplugin.psi.SmcFile;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcQualifiedIdElement;
import com.smcplugin.util.SmcStringUtils;
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
public class SmcJavaReference extends PsiReferenceBase<SmcQualifiedIdElement> implements PsiPolyVariantReference {


    private static final String DOT = ".";
    private String name;


    public SmcJavaReference(SmcQualifiedIdElement element, TextRange textRange) {
        super(element, textRange);
        this.name = element.getQualifiedName();
    }

    public SmcJavaReference(SmcQualifiedIdElement element, String name, TextRange textRange) {
        super(element, textRange);
        this.name = name;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final PsiClass[] properties = SmcPsiUtil.findClasses(name);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (PsiClass psiClass : properties) {
            results.add(new PsiElementResolveResult(psiClass));
        }
        SmcQualifiedIdElement previousQualifiedIdElement = myElement.getPreviousQualifiedIdElement();
        if (previousQualifiedIdElement != null) {
            String previousQualifiedName = previousQualifiedIdElement.getQualifiedName();
            PsiClass previousClass = SmcPsiUtil.findClass(previousQualifiedName);
            if (previousClass != null) {
                PsiField fieldByName = previousClass.findFieldByName(myElement.getName(), false);
                if(fieldByName != null){
                    results.add(new PsiElementResolveResult(fieldByName));
                }
                PsiMethod[] methodsByName = previousClass.findMethodsByName(myElement.getName(), false);
                for (PsiMethod method :methodsByName) {
                    results.add(new PsiElementResolveResult(method));
                }
            }
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
        String previousQualifiedName = myElement.getPreviousQualifiedName();
        List<PsiClass> classesForPackage = SmcPsiUtil.findClassesForPackage(previousQualifiedName);
        List<PsiPackage> subPackages = SmcPsiUtil.findSubPackagesForPackage(previousQualifiedName);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final PsiPackage subPackage : subPackages) {
            final String shortName = SmcStringUtils.getSimpleName(subPackage.getQualifiedName(), previousQualifiedName);
            if (PsiNameHelper.getInstance(subPackage.getProject()).isIdentifier(shortName)) {
                variants.add(LookupElementBuilder.create(subPackage).withIcon(subPackage.getIcon(Iconable.ICON_FLAG_VISIBILITY)));
            }
        }

        List<PsiClass> findInnerClasses = SmcPsiUtil.findInnerClasses(previousQualifiedName);
        for (final PsiClass psiClass : findInnerClasses) {
            if (!StringUtils.isEmpty(psiClass.getQualifiedName())) {
                variants.add(JavaLookupElementBuilder.forClass(psiClass));
            }
        }

        variants.addAll(SmcPsiUtil.fillVariantsForStaticMembers(previousQualifiedName,
                ((SmcFile) myElement.getContainingFile()).getFsmClass()));

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
