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
    public static final String ON_ENTRY_TYPE = "@Entry";
    public static final String ON_EXIT_TYPE = "@Exit";

    @Nullable
    SmcActionsBlock getActionsBlock();

    @NotNull
    List<SmcComment> getCommentList();

    String getType();
}
