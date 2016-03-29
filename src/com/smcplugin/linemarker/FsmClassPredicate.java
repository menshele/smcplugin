package com.smcplugin.linemarker;

import com.intellij.psi.PsiClass;
import com.intellij.util.containers.Predicate;
import com.smcplugin.psi.SmcFile;
import org.jetbrains.annotations.Nullable;

/**
 * scmplugin
 * Created by lemen on 29.03.2016.
 */
public class FsmClassPredicate implements Predicate<SmcFile> {
    private PsiClass containingClass;

    public FsmClassPredicate(PsiClass containingClass) {
        this.containingClass = containingClass;
    }

    @Override
    public boolean apply(@Nullable SmcFile smcFile) {
        if (smcFile == null) {
            return false;
        }
        PsiClass fsmClass = smcFile.getFsmClass();
        return fsmClass != null && (containingClass.isEquivalentTo(fsmClass) || containingClass.isInheritor(fsmClass, true));
    }
}
