package com.smcplugin.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * scmplugin
 * Created by lemen on 06.03.2016.
 */
public interface SmcOnStateNestedElement extends PsiElement {
    @Nullable
    SmcActionsBlock getActionsBlock();

    @NotNull
    List<SmcComment> getCommentList();
}
