// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcTransition extends SmcTransitionElement {

  @Nullable
  SmcActionsBlock getActionsBlock();

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcGuard getGuard();

  @Nullable
  SmcNextState getNextState();

  @Nullable
  SmcPopTransition getPopTransition();

  @Nullable
  SmcPushTransition getPushTransition();

  @Nullable
  SmcTransitionArgs getTransitionArgs();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
