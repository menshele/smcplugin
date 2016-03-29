package com.smcplugin.linemarker;

import com.intellij.psi.PsiClass;
import com.intellij.util.containers.Predicate;
import com.smcplugin.psi.SmcFile;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public class ContextClassPredicate implements Predicate<SmcFile> {
    private PsiClass containingClass;

    public ContextClassPredicate(PsiClass containingClass) {
        this.containingClass = containingClass;
    }

    @Override
    public boolean apply(@Nullable SmcFile smcFile) {
        if (smcFile == null) {
            return false;
        }
        PsiClass contextClass = smcFile.getContextClass();
        return contextClass != null && (containingClass.isEquivalentTo(contextClass) || containingClass.isInheritor(contextClass, true));
    }
}
