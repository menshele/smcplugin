package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;

public class SmcReferenceContributor extends PsiReferenceContributor {

    public static final PsiReferenceProvider CLASS_REFERENCE_PROVIDER = new PsiReferenceProvider() {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            SmcQualifiedNamedElement smcQualifiedElement = (SmcQualifiedNamedElement) element;

            if (smcQualifiedElement.getQualifiedName() != null) {
                return new PsiReference[]{new SmcJavaClassReference(element, smcQualifiedElement.getQualifiedName(), new TextRange(0, element.getTextLength()))};
            }
            return new PsiReference[0];
        }
    };

    public static final PsiReferenceProvider CLASS_REFERENCE_PROVIDER_NAME = new PsiReferenceProvider() {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {

            SmcQualifiedNamedElement smcQualifiedElement = (SmcQualifiedNamedElement) element;
            if (smcQualifiedElement.getQualifiedName() != null) {
                return new PsiReference[]{new SmcJavaClassReference(element, smcQualifiedElement.getQualifiedName(), new TextRange(0, element.getTextLength()))};
            }
            return new PsiReference[0];
        }
    };

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
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcPushProxyStateNameElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcPushProxyStateNameElement stateNameElement = (SmcPushProxyStateNameElement) element;
                        if (stateNameElement.getName() != null) {
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
                            return new PsiReference[]{new SmcGlobalStateReference(startState, start.getMapName(), startState.getName(), new TextRange(0, element.getTextLength()))};
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
                            return new PsiReference[]{new SmcMapReference(element, new TextRange(0, startMap.getTextLength()))};
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcPushMapNameElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcPushMapNameElement pushMap = (SmcPushMapNameElement) element;
                        SmcPushState push = (SmcPushState) element.getParent();
                        if (pushMap.getName() != null && push.getMapName() != null) {
                            return new PsiReference[]{new SmcMapReference(element, new TextRange(0, pushMap.getTextLength()))};
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcPushStateNameElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcPushStateNameElement pushState = (SmcPushStateNameElement) element;
                        SmcPushState push = (SmcPushState) element.getParent();
                        if (pushState.getName() != null) {
                            if (push.getMapName() != null) {
                                return new PsiReference[]{new SmcGlobalStateReference(pushState, push.getMapName(), pushState.getName(), new TextRange(0, element.getTextLength()))};
                            } else {
                                return new PsiReference[]{new SmcStateReference(pushState)};
                            }
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcAction.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcAction smcAction = (SmcAction) element;

                        PsiElement namePsiElement = smcAction.getNameIdentifier();
                        if (namePsiElement != null && smcAction.getName() != null) {
                            TextRange textRange = new TextRange(0, namePsiElement.getTextLength());
                            return new PsiReference[]{new SmcActionToJavaMethodReference(namePsiElement, textRange,
                                    smcAction.getContextClassName(), smcAction.getArgumentCount())};

                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcQualifiedNamedElement.class), CLASS_REFERENCE_PROVIDER);
    /*    registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
                        if (value != null && value.startsWith("smc:")) {
                            return new PsiReference[]{new SmcMapReference(element, new TextRange(5, value.length() + 1))};
                        }
                        return new PsiReference[0];
                    }
                });*/
    }
}