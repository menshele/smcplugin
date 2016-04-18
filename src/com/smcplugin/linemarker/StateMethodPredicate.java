package com.smcplugin.linemarker;

import com.intellij.psi.PsiClass;
import com.intellij.util.containers.Predicate;
import com.smcplugin.psi.SmcTransition;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public class StateMethodPredicate implements Predicate<SmcTransition> {
    private PsiClass containingClass;

    public StateMethodPredicate(PsiClass containingClass) {
        this.containingClass = containingClass;
    }

    @Override
    public boolean apply(@Nullable SmcTransition transition) {
        if (transition == null) return false;
        PsiClass smStateClass = transition.getSmStateClass();
        return smStateClass != null && (containingClass.isEquivalentTo(smStateClass) || containingClass.isInheritor(smStateClass, true));
    }
}
