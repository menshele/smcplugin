// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import javax.swing.Icon;

public interface SmcAction extends SmcNamedElement {

  @Nullable
  SmcArguments getArguments();

  @NotNull
  List<SmcComment> getCommentList();

  PsiElement getNameIdentifier();

  int getArgumentCount();

  ItemPresentation getPresentation();

  String getFullName();

  String getQualifiedFullName();

  Icon getElementIcon(int flags);

}
