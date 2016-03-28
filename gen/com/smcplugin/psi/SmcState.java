// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import javax.swing.Icon;

public interface SmcState extends SmcStateElement {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcOnState getOnState();

  @Nullable
  SmcTransitionsBlock getTransitionsBlock();

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

  Icon getElementIcon(int flags);

}
