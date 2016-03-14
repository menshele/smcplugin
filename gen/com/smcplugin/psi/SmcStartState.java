// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface SmcStartState extends SmcStateFullName {

  @NotNull
  List<SmcComment> getCommentList();

  @NotNull
  SmcStartMapNameElement getStartMapNameElement();

  @NotNull
  SmcStartStateNameElement getStartStateNameElement();

  String getMapName();

  String getStateName();

  PsiReference getReference();

}
