// This is a generated file. Not intended for manual editing.
package com.smcplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.smcplugin.psi.impl.*;

public interface SmcTypes {

  IElementType ACCESS = new SmcElementType("ACCESS");
  IElementType ACTION = new SmcElementType("ACTION");
  IElementType ACTIONS = new SmcElementType("ACTIONS");
  IElementType ARGUMENTS = new SmcElementType("ARGUMENTS");
  IElementType CLASS_IMPORT = new SmcElementType("CLASS_IMPORT");
  IElementType CLASS_NAME = new SmcElementType("CLASS_NAME");
  IElementType DECLARE = new SmcElementType("DECLARE");
  IElementType DOTNET_ASSIGNMENT = new SmcElementType("DOTNET_ASSIGNMENT");
  IElementType ENTRY = new SmcElementType("ENTRY");
  IElementType EXIT = new SmcElementType("EXIT");
  IElementType GUARD = new SmcElementType("GUARD");
  IElementType HEADER_FILE = new SmcElementType("HEADER_FILE");
  IElementType INCLUDE_FILE = new SmcElementType("INCLUDE_FILE");
  IElementType MAP = new SmcElementType("MAP");
  IElementType NEXT_STATE = new SmcElementType("NEXT_STATE");
  IElementType PACKAGE_NAME = new SmcElementType("PACKAGE_NAME");
  IElementType PARAMETER = new SmcElementType("PARAMETER");
  IElementType PARAMETERS = new SmcElementType("PARAMETERS");
  IElementType POP_ARGUMENTS = new SmcElementType("POP_ARGUMENTS");
  IElementType POP_TRANSITION = new SmcElementType("POP_TRANSITION");
  IElementType PUSH_TRANSITION = new SmcElementType("PUSH_TRANSITION");
  IElementType START_STATE = new SmcElementType("START_STATE");
  IElementType STATE = new SmcElementType("STATE");
  IElementType STATES = new SmcElementType("STATES");
  IElementType TRANSITION = new SmcElementType("TRANSITION");
  IElementType TRANSITIONS = new SmcElementType("TRANSITIONS");
  IElementType TRANSITION_ARGS = new SmcElementType("TRANSITION_ARGS");

  IElementType ACCESS_KEYWORD = new SmcTokenType("%access");
  IElementType ASSIGN_OP = new SmcTokenType("=");
  IElementType BLOCK_COMMENT = new SmcTokenType("BLOCK_COMMENT");
  IElementType BRACE_CLOSE = new SmcTokenType("}");
  IElementType BRACE_OPEN = new SmcTokenType("{");
  IElementType BRACKET_CLOSE = new SmcTokenType(")");
  IElementType BRACKET_OPEN = new SmcTokenType("(");
  IElementType CLASS_KEYWORD = new SmcTokenType("%class");
  IElementType COLON = new SmcTokenType(":");
  IElementType COMMA = new SmcTokenType(",");
  IElementType CRLF = new SmcTokenType("CRLF");
  IElementType DECLARE_KEYWORD = new SmcTokenType("%declare");
  IElementType ENTRY_KEYWORD = new SmcTokenType("Entry ");
  IElementType EXIT_KEYWORD = new SmcTokenType("Exit ");
  IElementType FSM_CLASS_KEYWORD = new SmcTokenType("%fsmclass");
  IElementType FSM_FILE_KEYWORD = new SmcTokenType("%fsmfile");
  IElementType GUARD_CLOSE = new SmcTokenType("]");
  IElementType GUARD_OPEN = new SmcTokenType("[");
  IElementType HEADER_KEYWORD = new SmcTokenType("%header");
  IElementType IMPORT_KEYWORD = new SmcTokenType("%import");
  IElementType INCLUDE_KEYWORD = new SmcTokenType("%include");
  IElementType LINE_COMMENT = new SmcTokenType("LINE_COMMENT");
  IElementType MAP_KEYWORD = new SmcTokenType("%map");
  IElementType MAP_SECTION_BOUND = new SmcTokenType("MAP_SECTION_BOUND");
  IElementType NIL_KEYWORD = new SmcTokenType("nil");
  IElementType PACKAGE_KEYWORD = new SmcTokenType("%package");
  IElementType POP_KEYWORD = new SmcTokenType("pop");
  IElementType PUSH_KEYWORD = new SmcTokenType("push");
  IElementType RAW_CODE = new SmcTokenType("RAW_CODE");
  IElementType RAW_CODE_LINE = new SmcTokenType("RAW_CODE_LINE");
  IElementType SEMICOLON = new SmcTokenType(";");
  IElementType SLASH_SIGN = new SmcTokenType("/");
  IElementType START_KEYWORD = new SmcTokenType("%start");
  IElementType VERBATIM_CLOSE = new SmcTokenType("%}");
  IElementType VERBATIM_OPEN = new SmcTokenType("%{");
  IElementType WORD = new SmcTokenType("WORD");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ACCESS) {
        return new SmcAccessImpl(node);
      }
      else if (type == ACTION) {
        return new SmcActionImpl(node);
      }
      else if (type == ACTIONS) {
        return new SmcActionsImpl(node);
      }
      else if (type == ARGUMENTS) {
        return new SmcArgumentsImpl(node);
      }
      else if (type == CLASS_IMPORT) {
        return new SmcClassImportImpl(node);
      }
      else if (type == CLASS_NAME) {
        return new SmcClassNameImpl(node);
      }
      else if (type == DECLARE) {
        return new SmcDeclareImpl(node);
      }
      else if (type == DOTNET_ASSIGNMENT) {
        return new SmcDotnetAssignmentImpl(node);
      }
      else if (type == ENTRY) {
        return new SmcEntryImpl(node);
      }
      else if (type == EXIT) {
        return new SmcExitImpl(node);
      }
      else if (type == GUARD) {
        return new SmcGuardImpl(node);
      }
      else if (type == HEADER_FILE) {
        return new SmcHeaderFileImpl(node);
      }
      else if (type == INCLUDE_FILE) {
        return new SmcIncludeFileImpl(node);
      }
      else if (type == MAP) {
        return new SmcMapImpl(node);
      }
      else if (type == NEXT_STATE) {
        return new SmcNextStateImpl(node);
      }
      else if (type == PACKAGE_NAME) {
        return new SmcPackageNameImpl(node);
      }
      else if (type == PARAMETER) {
        return new SmcParameterImpl(node);
      }
      else if (type == PARAMETERS) {
        return new SmcParametersImpl(node);
      }
      else if (type == POP_ARGUMENTS) {
        return new SmcPopArgumentsImpl(node);
      }
      else if (type == POP_TRANSITION) {
        return new SmcPopTransitionImpl(node);
      }
      else if (type == PUSH_TRANSITION) {
        return new SmcPushTransitionImpl(node);
      }
      else if (type == START_STATE) {
        return new SmcStartStateImpl(node);
      }
      else if (type == STATE) {
        return new SmcStateImpl(node);
      }
      else if (type == STATES) {
        return new SmcStatesImpl(node);
      }
      else if (type == TRANSITION) {
        return new SmcTransitionImpl(node);
      }
      else if (type == TRANSITIONS) {
        return new SmcTransitionsImpl(node);
      }
      else if (type == TRANSITION_ARGS) {
        return new SmcTransitionArgsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
