package com.smcplugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.smcplugin.SmcConstants.SMC_PREFIX;
import static com.smcplugin.SmcConstants.SMC_PREFIX_LENGTH;
/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */

public class SmcReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
                        if (value != null && value.startsWith(SMC_PREFIX)) {
                            return new PsiReference[]{new SmcReference(element, new TextRange(SMC_PREFIX_LENGTH + 1 , value.length() + 1))};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
