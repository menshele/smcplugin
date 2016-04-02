// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SmcQualifiedIdentifier extends SmcCustomNamed {

  @NotNull
  List<SmcIdentifier> getIdentifierList();

  String getName();

  SmcQualifiedIdElement getLastIdentifier();

}
