// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcTransition extends PsiElement {

  @Nullable
  SmcActions getActions();

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

}
