// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface SmcImportClass extends SmcQualifiedNamed {

  @NotNull
  List<SmcComment> getCommentList();

  String getName();

  boolean isWildcard();

  boolean isClassName();

  String getQualifiedName();

  PsiReference getReference();

}
