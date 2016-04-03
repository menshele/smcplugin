// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiMethod;
import javax.swing.Icon;

public interface SmcTransition extends SmcMethodLikeElement {

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

  boolean matches(PsiMethod method);

  PsiElement getNameIdentifier();

  int getArgumentCount();

  ItemPresentation getPresentation();

  String getFullName();

  String getQualifiedFullName();

  Icon getElementIcon(int flags);

}
