package com.smcplugin.psi;


import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public interface SmcNextStateElement extends SmcNamedElement{
    @NotNull
    PsiReference[] getReferences();
    PsiReference getReference();
}
