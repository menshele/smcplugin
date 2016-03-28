// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import javax.swing.Icon;

public interface SmcMap extends SmcMapElement {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcStates getStates();

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

  Icon getElementIcon(int flags);

}
