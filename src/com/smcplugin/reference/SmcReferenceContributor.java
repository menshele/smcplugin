package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.smcplugin.psi.SmcNextState;
import com.smcplugin.psi.SmcStartState;
import org.jetbrains.annotations.NotNull;

public class SmcReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcNextState.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcNextState nextState = (SmcNextState) element;
                        if (nextState.getName() != null) {
                            return new PsiReference[]{new SmcStateReference(element)};
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcStartState.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcStartState startState = (SmcStartState) element;
                        if (startState.getMapName() != null && startState.getStateName() != null) {
                            PsiElement stateNamePsiElement = ((SmcStartState) element).getStateNamePsiElement();
                            PsiElement mapNamePsiElement = ((SmcStartState) element).getMapNamePsiElement();
                            TextRange textRange = new TextRange(mapNamePsiElement.getStartOffsetInParent(), stateNamePsiElement.getStartOffsetInParent() + stateNamePsiElement.getTextLength());
                            return new PsiReference[]{new SmcGlobalStateReference(element, startState.getMapName(), startState.getStateName(), textRange)};
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
                        if (value != null && value.startsWith("smc:")) {
                            return new PsiReference[]{new SmcReference(element, new TextRange(5, value.length() + 1))};
                        }
                        return new PsiReference[0];
                    }
                });
    }
}