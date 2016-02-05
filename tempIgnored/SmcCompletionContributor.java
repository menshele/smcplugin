package com.smcplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.smcplugin.psi.SmcTypes;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 30.01.2016.
 */
//TODO: implement this for completion of methods from java
public class SmcCompletionContributor extends CompletionContributor {
    public SmcCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SmcTypes.TRANSITION).withLanguage(SmcLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}
