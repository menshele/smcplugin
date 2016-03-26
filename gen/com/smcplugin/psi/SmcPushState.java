// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcPushState extends SmcStateFullName {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcPushMapNameElement getPushMapNameElement();

  @NotNull
  SmcPushStateNameElement getPushStateNameElement();

  String getMapName();

  String getStateName();

}
