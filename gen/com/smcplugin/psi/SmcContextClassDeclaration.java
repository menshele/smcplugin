// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcContextClassDeclaration extends SmcCustomNamed {

  @NotNull
  List<SmcComment> getCommentList();

  @Nullable
  SmcQualifiedIdentifier getQualifiedIdentifier();

  String getName();

}
