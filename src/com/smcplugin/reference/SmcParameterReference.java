package com.smcplugin.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import com.smcplugin.psi.SmcParameter;
import com.smcplugin.psi.SmcPsiUtil;
import com.smcplugin.psi.SmcTypedArgumentElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Created by lemen on 13.03.2016.
 */
public class SmcParameterReference extends PsiReferenceBase<SmcTypedArgumentElement> implements PsiPolyVariantReference {

    public SmcParameterReference(SmcTypedArgumentElement element) {
        super(element, new TextRange(0, element.getText().length()));
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
        List<SmcParameter> parameterList = SmcPsiUtil.findParameterListByTypedArgument(myElement);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final SmcParameter parameter : parameterList) {
            if (!StringUtil.isEmpty(parameter.getName())) {
                variants.add(LookupElementBuilder.create(parameter.getParameterNameElement()).
                        withIcon(PlatformIcons.PARAMETER_ICON).
                        withTypeText(parameter.getName())
                );
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

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        SmcParameter declaration = myElement.getDeclaration();
        return declaration != null ?
                new ResolveResult[]{new PsiElementResolveResult(declaration.getParameterNameElement())} :
                new ResolveResult[0];
    }
}
