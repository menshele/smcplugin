// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcAction extends SmcNamedElement {

  @Nullable
  SmcArguments getArguments();

  @NotNull
  List<SmcComment> getCommentList();

  PsiElement getNameIdentifier();

  String getContextClassName();

  int getArgumentCount();

}
