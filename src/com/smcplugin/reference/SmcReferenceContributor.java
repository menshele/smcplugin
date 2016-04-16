package com.smcplugin.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.smcplugin.psi.*;
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
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcTypedArgumentElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcTypedArgumentElement argument = (SmcTypedArgumentElement) element;
                        if (argument.getName() != null) {
                            return new PsiReference[]{new SmcParameterReference(argument)};
                        }
                        return new PsiReference[0];
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcQualifiedIdElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcQualifiedIdElement qualifiedIdElement = (SmcQualifiedIdElement) element;
                        if (qualifiedIdElement.getName() != null) {
                            SmcContextClassDeclaration contextClass = PsiTreeUtil.getParentOfType(qualifiedIdElement, SmcContextClassDeclaration.class);
                            if (contextClass != null) {
                                SmcQualifiedIdentifier qualifiedIdentifier = contextClass.getQualifiedIdentifier();
                                if (qualifiedIdentifier != null && qualifiedIdentifier.getLastIdentifier().isEquivalentTo(element)) {
                                    String contextClassQName = ((SmcFile) contextClass.getContainingFile()).getContextClassQName();
                                    return new PsiReference[]{new SmcJavaReference(qualifiedIdElement, contextClassQName, new TextRange(0, element.getTextLength()))};
                                }
                            }
                            return new PsiReference[]{new SmcJavaReference(qualifiedIdElement, new TextRange(0, element.getTextLength()))};
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
                            SmcFile containingFile = (SmcFile) smcAction.getContainingFile();
                            return new PsiReference[]{new SmcToJavaMethodReference(namePsiElement, textRange,
                                    containingFile.getContextClassQName(), smcAction.getArgumentCount())};

                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcTransition.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcTransition smcTransition = (SmcTransition) element;

                        PsiElement namePsiElement = smcTransition.getNameIdentifier();
                        if (namePsiElement != null && smcTransition.getName() != null) {
                            TextRange textRange = new TextRange(0, namePsiElement.getTextLength());
                            if (SmcPsiUtil.classExists(smcTransition.getSmStateClassQName(), element.getProject())) {
                                return new PsiReference[]{new SmcToJavaMethodReference(namePsiElement, textRange,
                                        smcTransition.getSmStateClassQName(), smcTransition.getArgumentCount() + 1, false)};
                            } else {
                                SmcFile containingFile = (SmcFile) smcTransition.getContainingFile();
                                return new PsiReference[]{new SmcToJavaMethodReference(namePsiElement, textRange,
                                        containingFile.getFsmClassQName(), smcTransition.getArgumentCount())};
                            }
                        }
                        return new PsiReference[0];
                    }
                });
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SmcFsmClass.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        SmcFsmClass fsmClass = (SmcFsmClass) element;

                        PsiElement namePsiElement = fsmClass.getNameIdentifier();
                        if (namePsiElement != null && fsmClass.getName() != null) {
                            TextRange textRange = new TextRange(0, namePsiElement.getTextLength());
                            SmcFile containingFile = (SmcFile) fsmClass.getContainingFile();
                            return new PsiReference[]{new SmcJavaClassReference(fsmClass, containingFile.getPackageName(),
                                    textRange)};

                        }
                        return new PsiReference[0];
                    }
                });
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