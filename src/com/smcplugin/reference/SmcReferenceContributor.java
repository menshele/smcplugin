package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.smcplugin.psi.SmcNextState;
import com.smcplugin.psi.SmcStartMapNameElement;
import com.smcplugin.psi.SmcStartState;
import com.smcplugin.psi.SmcStartStateNameElement;
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
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcStartStateNameElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcStartStateNameElement startState = (SmcStartStateNameElement) element;
                        SmcStartState start = (SmcStartState) element.getParent();
                        if (startState.getName() != null && start.getMapName() != null) {
                            return new PsiReference[]{new SmcGlobalStateReference(startState,  start.getMapName(), startState.getName(), new TextRange(0,element.getTextLength()))};
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcStartMapNameElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcStartMapNameElement startMap = (SmcStartMapNameElement) element;
                        SmcStartState start = (SmcStartState) element.getParent();
                        if (startMap.getName() != null && start.getMapName() != null) {
                            return new PsiReference[]{new SmcReference(element, new TextRange(0, startMap.getTextLength()))};
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