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
  IElementType ACTIONS_BLOCK = new SmcElementType("ACTIONS_BLOCK");
  IElementType ARGUMENT = new SmcElementType("ARGUMENT");
  IElementType ARGUMENTS = new SmcElementType("ARGUMENTS");
  IElementType BLOCK_COMMENT = new SmcElementType("BLOCK_COMMENT");
  IElementType CALLBACK_TRANSITION = new SmcElementType("CALLBACK_TRANSITION");
  IElementType COMMENT = new SmcElementType("COMMENT");
  IElementType CONTEXT_CLASS = new SmcElementType("CONTEXT_CLASS");
  IElementType CONTEXT_CLASS_DECLARATION = new SmcElementType("CONTEXT_CLASS_DECLARATION");
  IElementType CONTEXT_CLASS_NAME_FULL = new SmcElementType("CONTEXT_CLASS_NAME_FULL");
  IElementType CONTEXT_CLASS_PACKAGE_ELEMENT = new SmcElementType("CONTEXT_CLASS_PACKAGE_ELEMENT");
  IElementType DECLARE = new SmcElementType("DECLARE");
  IElementType ENTRY = new SmcElementType("ENTRY");
  IElementType EXIT = new SmcElementType("EXIT");
  IElementType FSM_CLASS = new SmcElementType("FSM_CLASS");
  IElementType FSM_CLASS_DECLARATION = new SmcElementType("FSM_CLASS_DECLARATION");
  IElementType FSM_FILE = new SmcElementType("FSM_FILE");
  IElementType FSM_PACKAGE = new SmcElementType("FSM_PACKAGE");
  IElementType GUARD = new SmcElementType("GUARD");
  IElementType GUARD_RAW_CODE = new SmcElementType("GUARD_RAW_CODE");
  IElementType HEADER_FILE = new SmcElementType("HEADER_FILE");
  IElementType IMPORT_CLASS = new SmcElementType("IMPORT_CLASS");
  IElementType IMPORT_CLASS_DECLARATION = new SmcElementType("IMPORT_CLASS_DECLARATION");
  IElementType IMPORT_CLASS_PACKAGE_ELEMENT = new SmcElementType("IMPORT_CLASS_PACKAGE_ELEMENT");
  IElementType IMPORT_CLASS_STATEMENT_ELEMENT = new SmcElementType("IMPORT_CLASS_STATEMENT_ELEMENT");
  IElementType INCLUDE_FILE = new SmcElementType("INCLUDE_FILE");
  IElementType MAP = new SmcElementType("MAP");
  IElementType MAP_DECLARATION = new SmcElementType("MAP_DECLARATION");
  IElementType NEXT_STATE = new SmcElementType("NEXT_STATE");
  IElementType ON_STATE = new SmcElementType("ON_STATE");
  IElementType PARAMETER = new SmcElementType("PARAMETER");
  IElementType PARAMETERS = new SmcElementType("PARAMETERS");
  IElementType POP_TRANSITION = new SmcElementType("POP_TRANSITION");
  IElementType PUSH_MAP_NAME_ELEMENT = new SmcElementType("PUSH_MAP_NAME_ELEMENT");
  IElementType PUSH_PROXY_STATE = new SmcElementType("PUSH_PROXY_STATE");
  IElementType PUSH_PROXY_STATE_NAME_ELEMENT = new SmcElementType("PUSH_PROXY_STATE_NAME_ELEMENT");
  IElementType PUSH_STATE = new SmcElementType("PUSH_STATE");
  IElementType PUSH_STATE_NAME_ELEMENT = new SmcElementType("PUSH_STATE_NAME_ELEMENT");
  IElementType PUSH_TRANSITION = new SmcElementType("PUSH_TRANSITION");
  IElementType START_MAP_NAME_ELEMENT = new SmcElementType("START_MAP_NAME_ELEMENT");
  IElementType START_STATE = new SmcElementType("START_STATE");
  IElementType START_STATE_NAME_ELEMENT = new SmcElementType("START_STATE_NAME_ELEMENT");
  IElementType STATE = new SmcElementType("STATE");
  IElementType STATES = new SmcElementType("STATES");
  IElementType TRANSITION = new SmcElementType("TRANSITION");
  IElementType TRANSITIONS = new SmcElementType("TRANSITIONS");
  IElementType TRANSITIONS_BLOCK = new SmcElementType("TRANSITIONS_BLOCK");
  IElementType TRANSITION_ARGS = new SmcElementType("TRANSITION_ARGS");
  IElementType VERBATIM_CODE_SECTION = new SmcElementType("VERBATIM_CODE_SECTION");

  IElementType ACCESS_KEYWORD = new SmcTokenType("%access");
  IElementType ACCESS_LEVEL = new SmcTokenType("<public|protected|private>");
  IElementType ACTION_NAME = new SmcTokenType("<method name>");
  IElementType ARGUMENT_STATEMENT = new SmcTokenType("<argument>");
  IElementType BLOCK_COMMENT_CLOSE = new SmcTokenType("*/");
  IElementType BLOCK_COMMENT_CONTENT = new SmcTokenType("<comment body>");
  IElementType BLOCK_COMMENT_OPEN = new SmcTokenType("/*");
  IElementType BRACE_CLOSE = new SmcTokenType("}");
  IElementType BRACE_OPEN = new SmcTokenType("{");
  IElementType BRACKET_CLOSE = new SmcTokenType("]");
  IElementType BRACKET_OPEN = new SmcTokenType("[");
  IElementType CALLBACK_TRANSITION_NAME = new SmcTokenType("<pop state transition name>");
  IElementType CLASS_KEYWORD = new SmcTokenType("%class");
  IElementType COLON = new SmcTokenType(":");
  IElementType COMMA = new SmcTokenType(",");
  IElementType CONTEXT_CLASS_NAME = new SmcTokenType("<context class name>");
  IElementType CONTEXT_CLASS_PACKAGE = new SmcTokenType("<context package name>");
  IElementType DECLARE_KEYWORD = new SmcTokenType("%declare");
  IElementType DECLARE_STATEMENT = new SmcTokenType("<declare statement>");
  IElementType ENTRY_KEYWORD = new SmcTokenType("Entry");
  IElementType EXIT_KEYWORD = new SmcTokenType("Exit");
  IElementType FSM_CLASS_KEYWORD = new SmcTokenType("%fsmclass");
  IElementType FSM_CLASS_NAME = new SmcTokenType("<fsm class name>");
  IElementType FSM_FILE_KEYWORD = new SmcTokenType("%fsmfile");
  IElementType FSM_FILE_NAME = new SmcTokenType("<file name>");
  IElementType GUARD_BRACKET_CLOSE = new SmcTokenType("inner ]");
  IElementType GUARD_BRACKET_OPEN = new SmcTokenType("inner [");
  IElementType GUARD_NOT_BRACKET = new SmcTokenType("<any char except '[' or ']' >");
  IElementType HEADER_FILE_NAME = new SmcTokenType("<header file name>");
  IElementType HEADER_KEYWORD = new SmcTokenType("%header");
  IElementType IMPORT_CLASS_PACKAGE = new SmcTokenType("IMPORT_CLASS_PACKAGE");
  IElementType IMPORT_CLASS_STATEMENT = new SmcTokenType("<java import statement>");
  IElementType IMPORT_KEYWORD = new SmcTokenType("%import");
  IElementType INCLUDE_FILE_NAME = new SmcTokenType("<include file name>");
  IElementType INCLUDE_KEYWORD = new SmcTokenType("%include");
  IElementType LINE_COMMENT = new SmcTokenType("//");
  IElementType MAP_KEYWORD = new SmcTokenType("%map");
  IElementType MAP_NAME = new SmcTokenType("<map name>");
  IElementType MAP_NAME_STATE_NAME_SEPARATOR = new SmcTokenType("::");
  IElementType MAP_SECTION_BOUND = new SmcTokenType("%%");
  IElementType NEXT_STATE_NAME = new SmcTokenType("<next state name>");
  IElementType NIL_KEYWORD = new SmcTokenType("nil");
  IElementType PACKAGE_KEYWORD = new SmcTokenType("%package");
  IElementType PACKAGE_STATEMENT = new SmcTokenType("<package: like com.foo.bar>");
  IElementType PARAMETER_NAME = new SmcTokenType("<parameter name>");
  IElementType PARAMETER_TYPE = new SmcTokenType("<parameter type>");
  IElementType PARENTHESES_CLOSE = new SmcTokenType(")");
  IElementType PARENTHESES_OPEN = new SmcTokenType("(");
  IElementType POP_KEYWORD = new SmcTokenType("pop");
  IElementType PUSH_KEYWORD = new SmcTokenType("push");
  IElementType PUSH_MAP_NAME = new SmcTokenType("<push map name>");
  IElementType PUSH_PROXY_STATE_KEYWORD_SEPARATOR = new SmcTokenType("/");
  IElementType PUSH_PROXY_STATE_NAME = new SmcTokenType("<proxy state name>");
  IElementType PUSH_STATE_NAME = new SmcTokenType("<push state name>");
  IElementType SEMICOLON = new SmcTokenType(";");
  IElementType START_KEYWORD = new SmcTokenType("%start");
  IElementType START_MAP_NAME = new SmcTokenType("<start map name>");
  IElementType START_STATE_NAME = new SmcTokenType("<start state name>");
  IElementType STATE_NAME = new SmcTokenType("<state name>");
  IElementType STATIC_JAVA_KEYWORD = new SmcTokenType("<keyword \"static\">");
  IElementType STRING_LITERAL = new SmcTokenType("<string literal>");
  IElementType TRANSITION_NAME = new SmcTokenType("<transition name>");
  IElementType VERBATIM_CLOSE = new SmcTokenType("%}");
  IElementType VERBATIM_CODE = new SmcTokenType("<row code block>");
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
      else if (type == ACTIONS_BLOCK) {
        return new SmcActionsBlockImpl(node);
      }
      else if (type == ARGUMENT) {
        return new SmcArgumentImpl(node);
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
      else if (type == COMMENT) {
        return new SmcCommentImpl(node);
      }
      else if (type == CONTEXT_CLASS) {
        return new SmcContextClassImpl(node);
      }
      else if (type == CONTEXT_CLASS_DECLARATION) {
        return new SmcContextClassDeclarationImpl(node);
      }
      else if (type == CONTEXT_CLASS_NAME_FULL) {
        return new SmcContextClassNameFullImpl(node);
      }
      else if (type == CONTEXT_CLASS_PACKAGE_ELEMENT) {
        return new SmcContextClassPackageElementImpl(node);
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
      else if (type == FSM_CLASS_DECLARATION) {
        return new SmcFsmClassDeclarationImpl(node);
      }
      else if (type == FSM_FILE) {
        return new SmcFsmFileImpl(node);
      }
      else if (type == FSM_PACKAGE) {
        return new SmcFsmPackageImpl(node);
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
      else if (type == IMPORT_CLASS_DECLARATION) {
        return new SmcImportClassDeclarationImpl(node);
      }
      else if (type == IMPORT_CLASS_PACKAGE_ELEMENT) {
        return new SmcImportClassPackageElementImpl(node);
      }
      else if (type == IMPORT_CLASS_STATEMENT_ELEMENT) {
        return new SmcImportClassStatementElementImpl(node);
      }
      else if (type == INCLUDE_FILE) {
        return new SmcIncludeFileImpl(node);
      }
      else if (type == MAP) {
        return new SmcMapImpl(node);
      }
      else if (type == MAP_DECLARATION) {
        return new SmcMapDeclarationImpl(node);
      }
      else if (type == NEXT_STATE) {
        return new SmcNextStateImpl(node);
      }
      else if (type == ON_STATE) {
        return new SmcOnStateImpl(node);
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
      else if (type == PUSH_MAP_NAME_ELEMENT) {
        return new SmcPushMapNameElementImpl(node);
      }
      else if (type == PUSH_PROXY_STATE) {
        return new SmcPushProxyStateImpl(node);
      }
      else if (type == PUSH_PROXY_STATE_NAME_ELEMENT) {
        return new SmcPushProxyStateNameElementImpl(node);
      }
      else if (type == PUSH_STATE) {
        return new SmcPushStateImpl(node);
      }
      else if (type == PUSH_STATE_NAME_ELEMENT) {
        return new SmcPushStateNameElementImpl(node);
      }
      else if (type == PUSH_TRANSITION) {
        return new SmcPushTransitionImpl(node);
      }
      else if (type == START_MAP_NAME_ELEMENT) {
        return new SmcStartMapNameElementImpl(node);
      }
      else if (type == START_STATE) {
        return new SmcStartStateImpl(node);
      }
      else if (type == START_STATE_NAME_ELEMENT) {
        return new SmcStartStateNameElementImpl(node);
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
      else if (type == TRANSITIONS_BLOCK) {
        return new SmcTransitionsBlockImpl(node);
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
