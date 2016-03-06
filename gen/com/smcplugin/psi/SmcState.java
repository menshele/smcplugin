// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcState extends SmcStateElement {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcOnState getOnState();

  @Nullable
  SmcTransitions getTransitions();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
