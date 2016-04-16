package com.smcplugin.linemarker;

import com.intellij.psi.PsiClass;
import com.intellij.util.containers.Predicate;
import com.smcplugin.psi.SmcFile;
import com.smcplugin.psi.SmcMethodLikeElement;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public class ContextClassMethodPredicate implements Predicate<SmcMethodLikeElement> {
    private PsiClass containingClass;

    public ContextClassMethodPredicate(PsiClass containingClass) {
        this.containingClass = containingClass;
    }

    @Override
    public boolean apply(@Nullable SmcMethodLikeElement methodLikeElement) {
        if (methodLikeElement == null) return false;
        PsiClass contextClass = ((SmcFile) methodLikeElement.getContainingFile()).getContextClass();
        return contextClass != null && (containingClass.isEquivalentTo(contextClass) || containingClass.isInheritor(contextClass, true));
    }
}
