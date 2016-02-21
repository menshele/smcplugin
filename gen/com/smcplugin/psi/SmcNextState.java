// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcNextState extends PsiElement {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcPopTransition getPopTransition();

  @Nullable
  SmcPushTransition getPushTransition();

}
