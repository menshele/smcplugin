// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcMap extends SmcMapElement {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcStates getStates();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
