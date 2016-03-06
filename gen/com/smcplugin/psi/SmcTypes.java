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
  IElementType BLOCK_COMMENT = new SmcElementType("BLOCK_COMMENT");
  IElementType CALLBACK_TRANSITION = new SmcElementType("CALLBACK_TRANSITION");
  IElementType CLASS_NAME = new SmcElementType("CLASS_NAME");
  IElementType COMMENT = new SmcElementType("COMMENT");
  IElementType DECLARE = new SmcElementType("DECLARE");
  IElementType ENTRY = new SmcElementType("ENTRY");
  IElementType EXIT = new SmcElementType("EXIT");
  IElementType FSM_CLASS = new SmcElementType("FSM_CLASS");
  IElementType FSM_FILE = new SmcElementType("FSM_FILE");
  IElementType GUARD = new SmcElementType("GUARD");
  IElementType GUARD_RAW_CODE = new SmcElementType("GUARD_RAW_CODE");
  IElementType HEADER_FILE = new SmcElementType("HEADER_FILE");
  IElementType IMPORT_CLASS = new SmcElementType("IMPORT_CLASS");
  IElementType INCLUDE_FILE = new SmcElementType("INCLUDE_FILE");
  IElementType MAP = new SmcElementType("MAP");
  IElementType NEXT_STATE = new SmcElementType("NEXT_STATE");
  IElementType ON_STATE = new SmcElementType("ON_STATE");
  IElementType PACKAGE_NAME = new SmcElementType("PACKAGE_NAME");
  IElementType PARAMETER = new SmcElementType("PARAMETER");
  IElementType PARAMETERS = new SmcElementType("PARAMETERS");
  IElementType POP_TRANSITION = new SmcElementType("POP_TRANSITION");
  IElementType PUSH_PROXY_STATE = new SmcElementType("PUSH_PROXY_STATE");
  IElementType PUSH_STATE = new SmcElementType("PUSH_STATE");
  IElementType PUSH_TRANSITION = new SmcElementType("PUSH_TRANSITION");
  IElementType START_STATE = new SmcElementType("START_STATE");
  IElementType STATE = new SmcElementType("STATE");
  IElementType STATES = new SmcElementType("STATES");
  IElementType TRANSITION = new SmcElementType("TRANSITION");
  IElementType TRANSITIONS = new SmcElementType("TRANSITIONS");
  IElementType TRANSITION_ARGS = new SmcElementType("TRANSITION_ARGS");
  IElementType VERBATIM_CODE_SECTION = new SmcElementType("VERBATIM_CODE_SECTION");

  IElementType ACCESS_KEYWORD = new SmcTokenType("%access");
  IElementType ACCESS_LEVEL = new SmcTokenType("ACCESS_LEVEL");
  IElementType ACTION_NAME = new SmcTokenType("ACTION_NAME");
  IElementType ARGUMENT_STATEMENT = new SmcTokenType("ARGUMENT_STATEMENT");
  IElementType ASSIGN_OP = new SmcTokenType("=");
  IElementType BLOCK_COMMENT_CLOSE = new SmcTokenType("BLOCK_COMMENT_CLOSE");
  IElementType BLOCK_COMMENT_CONTENT = new SmcTokenType("BLOCK_COMMENT_CONTENT");
  IElementType BLOCK_COMMENT_OPEN = new SmcTokenType("BLOCK_COMMENT_OPEN");
  IElementType BRACE_CLOSE = new SmcTokenType("}");
  IElementType BRACE_OPEN = new SmcTokenType("{");
  IElementType BRACKET_CLOSE = new SmcTokenType("]");
  IElementType BRACKET_OPEN = new SmcTokenType("[");
  IElementType CALLBACK_TRANSITION_NAME = new SmcTokenType("CALLBACK_TRANSITION_NAME");
  IElementType CLASS_KEYWORD = new SmcTokenType("%class");
  IElementType COLON = new SmcTokenType(":");
  IElementType COMMA = new SmcTokenType(",");
  IElementType CONTEXT_CLASS_NAME = new SmcTokenType("CONTEXT_CLASS_NAME");
  IElementType DECLARE_KEYWORD = new SmcTokenType("%declare");
  IElementType DECLARE_STATEMENT = new SmcTokenType("DECLARE_STATEMENT");
  IElementType ENTRY_KEYWORD = new SmcTokenType("Entry");
  IElementType EXIT_KEYWORD = new SmcTokenType("Exit");
  IElementType FSM_CLASS_KEYWORD = new SmcTokenType("%fsmclass");
  IElementType FSM_CLASS_NAME = new SmcTokenType("FSM_CLASS_NAME");
  IElementType FSM_FILE_KEYWORD = new SmcTokenType("%fsmfile");
  IElementType FSM_FILE_NAME = new SmcTokenType("FSM_FILE_NAME");
  IElementType GUARD_BRACKET_CLOSE = new SmcTokenType("GUARD_BRACKET_CLOSE");
  IElementType GUARD_BRACKET_OPEN = new SmcTokenType("GUARD_BRACKET_OPEN");
  IElementType GUARD_NOT_BRACKET = new SmcTokenType("GUARD_NOT_BRACKET");
  IElementType HEADER_FILE_NAME = new SmcTokenType("HEADER_FILE_NAME");
  IElementType HEADER_KEYWORD = new SmcTokenType("%header");
  IElementType IMPORT_CLASS_STATEMENT = new SmcTokenType("IMPORT_CLASS_STATEMENT");
  IElementType IMPORT_KEYWORD = new SmcTokenType("%import");
  IElementType INCLUDE_FILE_NAME = new SmcTokenType("INCLUDE_FILE_NAME");
  IElementType INCLUDE_KEYWORD = new SmcTokenType("%include");
  IElementType LINE_COMMENT = new SmcTokenType("LINE_COMMENT");
  IElementType MAP_KEYWORD = new SmcTokenType("%map");
  IElementType MAP_NAME = new SmcTokenType("MAP_NAME");
  IElementType MAP_NAME_STATE_NAME_SEPARATOR = new SmcTokenType("MAP_NAME_STATE_NAME_SEPARATOR");
  IElementType MAP_SECTION_BOUND = new SmcTokenType("MAP_SECTION_BOUND");
  IElementType NEXT_STATE_NAME = new SmcTokenType("NEXT_STATE_NAME");
  IElementType NIL_KEYWORD = new SmcTokenType("nil");
  IElementType PACKAGE_KEYWORD = new SmcTokenType("%package");
  IElementType PACKAGE_STATEMENT = new SmcTokenType("PACKAGE_STATEMENT");
  IElementType PARAMETER_NAME = new SmcTokenType("PARAMETER_NAME");
  IElementType PARAMETER_TYPE = new SmcTokenType("PARAMETER_TYPE");
  IElementType PARENTHESES_CLOSE = new SmcTokenType(")");
  IElementType PARENTHESES_OPEN = new SmcTokenType("(");
  IElementType POP_KEYWORD = new SmcTokenType("pop");
  IElementType PUSH_KEYWORD = new SmcTokenType("push");
  IElementType PUSH_MAP_NAME = new SmcTokenType("PUSH_MAP_NAME");
  IElementType PUSH_PROXY_STATE_KEYWORD_SEPARATOR = new SmcTokenType("PUSH_PROXY_STATE_KEYWORD_SEPARATOR");
  IElementType PUSH_PROXY_STATE_NAME = new SmcTokenType("PUSH_PROXY_STATE_NAME");
  IElementType PUSH_STATE_NAME = new SmcTokenType("PUSH_STATE_NAME");
  IElementType SEMICOLON = new SmcTokenType(";");
  IElementType SLASH_SIGN = new SmcTokenType("/");
  IElementType START_KEYWORD = new SmcTokenType("%start");
  IElementType START_MAP_NAME = new SmcTokenType("START_MAP_NAME");
  IElementType START_STATE_NAME = new SmcTokenType("START_STATE_NAME");
  IElementType STATE_NAME = new SmcTokenType("STATE_NAME");
  IElementType STATIC_JAVA_KEYWORD = new SmcTokenType("STATIC_JAVA_KEYWORD");
  IElementType STRING_LITERAL = new SmcTokenType("STRING_LITERAL");
  IElementType TRANSITION_NAME = new SmcTokenType("TRANSITION_NAME");
  IElementType VERBATIM_CLOSE = new SmcTokenType("%}");
  IElementType VERBATIM_CODE = new SmcTokenType("VERBATIM_CODE");
  IElementType VERBATIM_OPEN = new SmcTokenType("%{");

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
      else if (type == BLOCK_COMMENT) {
        return new SmcBlockCommentImpl(node);
      }
      else if (type == CALLBACK_TRANSITION) {
        return new SmcCallbackTransitionImpl(node);
      }
      else if (type == CLASS_NAME) {
        return new SmcClassNameImpl(node);
      }
      else if (type == COMMENT) {
        return new SmcCommentImpl(node);
      }
      else if (type == DECLARE) {
        return new SmcDeclareImpl(node);
      }
      else if (type == ENTRY) {
        return new SmcEntryImpl(node);
      }
      else if (type == EXIT) {
        return new SmcExitImpl(node);
      }
      else if (type == FSM_CLASS) {
        return new SmcFsmClassImpl(node);
      }
      else if (type == FSM_FILE) {
        return new SmcFsmFileImpl(node);
      }
      else if (type == GUARD) {
        return new SmcGuardImpl(node);
      }
      else if (type == GUARD_RAW_CODE) {
        return new SmcGuardRawCodeImpl(node);
      }
      else if (type == HEADER_FILE) {
        return new SmcHeaderFileImpl(node);
      }
      else if (type == IMPORT_CLASS) {
        return new SmcImportClassImpl(node);
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
      else if (type == ON_STATE) {
        return new SmcOnStateImpl(node);
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
      else if (type == POP_TRANSITION) {
        return new SmcPopTransitionImpl(node);
      }
      else if (type == PUSH_PROXY_STATE) {
        return new SmcPushProxyStateImpl(node);
      }
      else if (type == PUSH_STATE) {
        return new SmcPushStateImpl(node);
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
      else if (type == VERBATIM_CODE_SECTION) {
        return new SmcVerbatimCodeSectionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
