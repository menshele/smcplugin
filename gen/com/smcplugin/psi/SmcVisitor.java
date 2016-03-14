// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SmcVisitor extends PsiElementVisitor {

  public void visitAccess(@NotNull SmcAccess o) {
    visitPsiElement(o);
  }

  public void visitAction(@NotNull SmcAction o) {
    visitPsiElement(o);
  }

  public void visitActions(@NotNull SmcActions o) {
    visitPsiElement(o);
  }

  public void visitActionsBlock(@NotNull SmcActionsBlock o) {
    visitPsiElement(o);
  }

  public void visitArguments(@NotNull SmcArguments o) {
    visitPsiElement(o);
  }

  public void visitBlockComment(@NotNull SmcBlockComment o) {
    visitPsiElement(o);
  }

  public void visitCallbackTransition(@NotNull SmcCallbackTransition o) {
    visitPsiElement(o);
  }

  public void visitComment(@NotNull SmcComment o) {
    visitPsiElement(o);
  }

  public void visitContextClass(@NotNull SmcContextClass o) {
    visitPsiElement(o);
  }

  public void visitDeclare(@NotNull SmcDeclare o) {
    visitPsiElement(o);
  }

  public void visitEntry(@NotNull SmcEntry o) {
    visitOnStateNestedElement(o);
  }

  public void visitExit(@NotNull SmcExit o) {
    visitOnStateNestedElement(o);
  }

  public void visitFsmClass(@NotNull SmcFsmClass o) {
    visitPsiElement(o);
  }

  public void visitFsmFile(@NotNull SmcFsmFile o) {
    visitPsiElement(o);
  }

  public void visitFsmPackage(@NotNull SmcFsmPackage o) {
    visitPsiElement(o);
  }

  public void visitGuard(@NotNull SmcGuard o) {
    visitPsiElement(o);
  }

  public void visitGuardRawCode(@NotNull SmcGuardRawCode o) {
    visitPsiElement(o);
  }

  public void visitHeaderFile(@NotNull SmcHeaderFile o) {
    visitPsiElement(o);
  }

  public void visitImportClass(@NotNull SmcImportClass o) {
    visitPsiElement(o);
  }

  public void visitIncludeFile(@NotNull SmcIncludeFile o) {
    visitPsiElement(o);
  }

  public void visitMap(@NotNull SmcMap o) {
    visitMapElement(o);
  }

  public void visitNextState(@NotNull SmcNextState o) {
    visitCustomNamed(o);
  }

  public void visitOnState(@NotNull SmcOnState o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull SmcParameter o) {
    visitPsiElement(o);
  }

  public void visitParameters(@NotNull SmcParameters o) {
    visitPsiElement(o);
  }

  public void visitPopTransition(@NotNull SmcPopTransition o) {
    visitPsiElement(o);
  }

  public void visitPushProxyState(@NotNull SmcPushProxyState o) {
    visitPsiElement(o);
  }

  public void visitPushState(@NotNull SmcPushState o) {
    visitPsiElement(o);
  }

  public void visitPushTransition(@NotNull SmcPushTransition o) {
    visitPsiElement(o);
  }

  public void visitStartState(@NotNull SmcStartState o) {
    visitStateFullName(o);
  }

  public void visitState(@NotNull SmcState o) {
    visitStateElement(o);
  }

  public void visitStates(@NotNull SmcStates o) {
    visitPsiElement(o);
  }

  public void visitTransition(@NotNull SmcTransition o) {
    visitTransitionElement(o);
  }

  public void visitTransitionArgs(@NotNull SmcTransitionArgs o) {
    visitPsiElement(o);
  }

  public void visitTransitions(@NotNull SmcTransitions o) {
    visitPsiElement(o);
  }

  public void visitTransitionsBlock(@NotNull SmcTransitionsBlock o) {
    visitPsiElement(o);
  }

  public void visitVerbatimCodeSection(@NotNull SmcVerbatimCodeSection o) {
    visitPsiElement(o);
  }

  public void visitCustomNamed(@NotNull SmcCustomNamed o) {
    visitPsiElement(o);
  }

  public void visitMapElement(@NotNull SmcMapElement o) {
    visitPsiElement(o);
  }

  public void visitOnStateNestedElement(@NotNull SmcOnStateNestedElement o) {
    visitPsiElement(o);
  }

  public void visitStateElement(@NotNull SmcStateElement o) {
    visitPsiElement(o);
  }

  public void visitStateFullName(@NotNull SmcStateFullName o) {
    visitPsiElement(o);
  }

  public void visitTransitionElement(@NotNull SmcTransitionElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
