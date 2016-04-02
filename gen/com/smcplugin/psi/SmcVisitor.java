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
    visitMethodLikeElement(o);
  }

  public void visitActions(@NotNull SmcActions o) {
    visitPsiElement(o);
  }

  public void visitActionsBlock(@NotNull SmcActionsBlock o) {
    visitPsiElement(o);
  }

  public void visitArgument(@NotNull SmcArgument o) {
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

  public void visitContextClassDeclaration(@NotNull SmcContextClassDeclaration o) {
    visitCustomNamed(o);
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
    visitQualifiedNamedElement(o);
  }

  public void visitFsmClassDeclaration(@NotNull SmcFsmClassDeclaration o) {
    visitPsiElement(o);
  }

  public void visitFsmFile(@NotNull SmcFsmFile o) {
    visitPsiElement(o);
  }

  public void visitFsmPackage(@NotNull SmcFsmPackage o) {
    visitCustomNamed(o);
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

  public void visitIdentifier(@NotNull SmcIdentifier o) {
    visitQualifiedIdElement(o);
  }

  public void visitImportClass(@NotNull SmcImportClass o) {
    visitPsiElement(o);
  }

  public void visitImportClassDeclaration(@NotNull SmcImportClassDeclaration o) {
    visitPsiElement(o);
  }

  public void visitIncludeFile(@NotNull SmcIncludeFile o) {
    visitPsiElement(o);
  }

  public void visitMap(@NotNull SmcMap o) {
    visitMapElement(o);
  }

  public void visitMapDeclaration(@NotNull SmcMapDeclaration o) {
    visitPsiElement(o);
  }

  public void visitNextState(@NotNull SmcNextState o) {
    visitNamedElement(o);
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

  public void visitPushMapNameElement(@NotNull SmcPushMapNameElement o) {
    visitNamedElement(o);
  }

  public void visitPushProxyState(@NotNull SmcPushProxyState o) {
    visitPsiElement(o);
  }

  public void visitPushProxyStateNameElement(@NotNull SmcPushProxyStateNameElement o) {
    visitNamedElement(o);
  }

  public void visitPushState(@NotNull SmcPushState o) {
    visitStateFullName(o);
  }

  public void visitPushStateNameElement(@NotNull SmcPushStateNameElement o) {
    visitNamedElement(o);
  }

  public void visitPushTransition(@NotNull SmcPushTransition o) {
    visitPsiElement(o);
  }

  public void visitQualifiedIdentifier(@NotNull SmcQualifiedIdentifier o) {
    visitCustomNamed(o);
  }

  public void visitStartMapNameElement(@NotNull SmcStartMapNameElement o) {
    visitNamedElement(o);
  }

  public void visitStartState(@NotNull SmcStartState o) {
    visitStateFullName(o);
  }

  public void visitStartStateNameElement(@NotNull SmcStartStateNameElement o) {
    visitNamedElement(o);
  }

  public void visitState(@NotNull SmcState o) {
    visitStateElement(o);
  }

  public void visitStates(@NotNull SmcStates o) {
    visitPsiElement(o);
  }

  public void visitStaticImport(@NotNull SmcStaticImport o) {
    visitCustomNamed(o);
  }

  public void visitTransition(@NotNull SmcTransition o) {
    visitMethodLikeElement(o);
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

  public void visitMethodLikeElement(@NotNull SmcMethodLikeElement o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull SmcNamedElement o) {
    visitPsiElement(o);
  }

  public void visitOnStateNestedElement(@NotNull SmcOnStateNestedElement o) {
    visitPsiElement(o);
  }

  public void visitQualifiedIdElement(@NotNull SmcQualifiedIdElement o) {
    visitPsiElement(o);
  }

  public void visitQualifiedNamedElement(@NotNull SmcQualifiedNamedElement o) {
    visitPsiElement(o);
  }

  public void visitStateElement(@NotNull SmcStateElement o) {
    visitPsiElement(o);
  }

  public void visitStateFullName(@NotNull SmcStateFullName o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
