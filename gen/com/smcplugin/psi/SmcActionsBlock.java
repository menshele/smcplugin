// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcActionsBlock extends PsiElement {

  @NotNull
  SmcActions getActions();

  @NotNull
  List<SmcComment> getCommentList();

}
