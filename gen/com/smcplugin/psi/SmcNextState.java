// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface SmcNextState extends SmcCustomNamed {

  @NotNull
  List<SmcComment> getCommentList();

  String getName();

  PsiReference getReference();

  @NotNull
  PsiReference[] getReferences();

}
