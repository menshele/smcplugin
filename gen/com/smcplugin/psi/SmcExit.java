// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface SmcExit extends SmcOnStateNestedElement {

  @Nullable
  SmcActionsBlock getActionsBlock();

  @NotNull
  List<SmcComment> getCommentList();

  String getType();

  ItemPresentation getPresentation();

}
