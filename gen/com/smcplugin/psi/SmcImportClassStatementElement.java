// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcImportClassStatementElement extends SmcQualifiedNamedElement {

  @NotNull
  List<SmcComment> getCommentList();

  String getPackageText();

  boolean isWildcard();

  boolean isClassName();

  String getQualifiedName();

  PsiElement getNameIdentifier();

}
