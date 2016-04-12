package com.smcplugin.reference;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.smcplugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 25.03.2016.
 */
public class SmcUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return null;
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement element) {
        return element instanceof SmcMap ||
                element instanceof SmcState ||
                element instanceof SmcTransition ||
                element instanceof SmcAction ||
                element instanceof SmcParameterNameElement;
    }

    @Override
    public String getHelpId(@NotNull PsiElement element) {
        if (element instanceof SmcMap) {
            return SmcHelpID.FIND_MAP_USAGES;
        }
        if (element instanceof SmcState) {
            return SmcHelpID.FIND_STATE_USAGES;
        }
        if (element instanceof SmcTransition) {
            return SmcHelpID.FIND_TRANSITION_USAGES;
        }
        if (element instanceof SmcAction) {
            return SmcHelpID.FIND_ACTION_USAGES;
        }
        if (element instanceof SmcParameterNameElement) {
            return SmcHelpID.FIND_PARAMETER_NAMED_ELEMENT_USAGES;
        }
        return com.intellij.lang.HelpID.FIND_OTHER_USAGES;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof SmcMap) {
            return "Smc map";
        } else if (element instanceof SmcState) {
            return "Smc state";
        } else if (element instanceof SmcTransition) {
            return "Smc transition";
        } else if (element instanceof SmcAction) {
            return "Smc action";
        } else if (element instanceof SmcParameterNameElement) {
            return "Smc action";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof SmcNamedElement) {
            SmcNamedElement smcNamedElement = (SmcNamedElement) element;
            String name = smcNamedElement.getName();
            return name != null ? name : "";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return getDescriptiveName(element);
    }
}
