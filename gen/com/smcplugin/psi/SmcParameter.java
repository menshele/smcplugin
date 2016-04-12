// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcParameter extends PsiElement {

  @NotNull
  List<SmcComment> getCommentList();

  @NotNull
  SmcParameterNameElement getParameterNameElement();

  @NotNull
  SmcParameterTypeElement getParameterTypeElement();

  String getName();

  String getType();

}
