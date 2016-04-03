// This is a generated file. Not intended for manual editing.
package com.smcplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.smcplugin.psi.SmcTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import static com.smcplugin.psi.impl.SmcPsiImplUtil.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SmcParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ACCESS) {
      r = access(b, 0);
    }
    else if (t == ACTION) {
      r = action(b, 0);
    }
    else if (t == ACTIONS) {
      r = actions(b, 0);
    }
    else if (t == ACTIONS_BLOCK) {
      r = actions_block(b, 0);
    }
    else if (t == ARGUMENT) {
      r = argument(b, 0);
    }
    else if (t == ARGUMENTS) {
      r = arguments(b, 0);
    }
    else if (t == BLOCK_COMMENT) {
      r = block_comment(b, 0);
    }
    else if (t == CALLBACK_TRANSITION) {
      r = callback_transition(b, 0);
    }
    else if (t == COMMENT) {
      r = comment(b, 0);
    }
    else if (t == CONTEXT_CLASS_DECLARATION) {
      r = context_class_declaration(b, 0);
    }
    else if (t == DECLARE) {
      r = declare(b, 0);
    }
    else if (t == ENTRY) {
      r = entry(b, 0);
    }
    else if (t == EXIT) {
      r = exit(b, 0);
    }
    else if (t == FSM_CLASS) {
      r = fsm_class(b, 0);
    }
    else if (t == FSM_CLASS_DECLARATION) {
      r = fsm_class_declaration(b, 0);
    }
    else if (t == FSM_FILE) {
      r = fsm_file(b, 0);
    }
    else if (t == FSM_PACKAGE) {
      r = fsm_package(b, 0);
    }
    else if (t == GUARD) {
      r = guard(b, 0);
    }
    else if (t == GUARD_RAW_CODE) {
      r = guard_raw_code(b, 0);
    }
    else if (t == HEADER_FILE) {
      r = header_file(b, 0);
    }
    else if (t == IDENTIFIER) {
      r = identifier(b, 0);
    }
    else if (t == IMPORT_CLASS) {
      r = import_class(b, 0);
    }
    else if (t == IMPORT_CLASS_DECLARATION) {
      r = import_class_declaration(b, 0);
    }
    else if (t == INCLUDE_FILE) {
      r = include_file(b, 0);
    }
    else if (t == MAP) {
      r = map(b, 0);
    }
    else if (t == MAP_DECLARATION) {
      r = map_declaration(b, 0);
    }
    else if (t == NEXT_STATE) {
      r = next_state(b, 0);
    }
    else if (t == ON_STATE) {
      r = on_state(b, 0);
    }
    else if (t == PARAMETER) {
      r = parameter(b, 0);
    }
    else if (t == PARAMETERS) {
      r = parameters(b, 0);
    }
    else if (t == POP_TRANSITION) {
      r = pop_transition(b, 0);
    }
    else if (t == PUSH_MAP_NAME_ELEMENT) {
      r = push_map_name_element(b, 0);
    }
    else if (t == PUSH_PROXY_STATE) {
      r = push_proxy_state(b, 0);
    }
    else if (t == PUSH_PROXY_STATE_NAME_ELEMENT) {
      r = push_proxy_state_name_element(b, 0);
    }
    else if (t == PUSH_STATE) {
      r = push_state(b, 0);
    }
    else if (t == PUSH_STATE_NAME_ELEMENT) {
      r = push_state_name_element(b, 0);
    }
    else if (t == PUSH_TRANSITION) {
      r = push_transition(b, 0);
    }
    else if (t == QUALIFIED_IDENTIFIER) {
      r = qualified_identifier(b, 0);
    }
    else if (t == START_MAP_NAME_ELEMENT) {
      r = start_map_name_element(b, 0);
    }
    else if (t == START_STATE) {
      r = start_state(b, 0);
    }
    else if (t == START_STATE_NAME_ELEMENT) {
      r = start_state_name_element(b, 0);
    }
    else if (t == STATE) {
      r = state(b, 0);
    }
    else if (t == STATES) {
      r = states(b, 0);
    }
    else if (t == STATIC_IMPORT) {
      r = static_import(b, 0);
    }
    else if (t == TRANSITION) {
      r = transition(b, 0);
    }
    else if (t == TRANSITION_ARGS) {
      r = transition_args(b, 0);
    }
    else if (t == TRANSITIONS) {
      r = transitions(b, 0);
    }
    else if (t == TRANSITIONS_BLOCK) {
      r = transitions_block(b, 0);
    }
    else if (t == VERBATIM_CODE_SECTION) {
      r = verbatim_code_section(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return fsmFile(b, l + 1);
  }

  /* ********************************************************** */
  // ACCESS_KEYWORD comment* ACCESS_LEVEL
  public static boolean access(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "access")) return false;
    if (!nextTokenIs(b, ACCESS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCESS_KEYWORD);
    r = r && access_1(b, l + 1);
    r = r && consumeToken(b, ACCESS_LEVEL);
    exit_section_(b, m, ACCESS, r);
    return r;
  }

  // comment*
  private static boolean access_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "access_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "access_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ACTION_NAME comment* PARENTHESES_OPEN comment* arguments? PARENTHESES_CLOSE comment* SEMICOLON
  public static boolean action(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action")) return false;
    if (!nextTokenIs(b, ACTION_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, ACTION_NAME);
    p = r; // pin = 1
    r = r && report_error_(b, action_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, PARENTHESES_OPEN)) && r;
    r = p && report_error_(b, action_3(b, l + 1)) && r;
    r = p && report_error_(b, action_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, PARENTHESES_CLOSE)) && r;
    r = p && report_error_(b, action_6(b, l + 1)) && r;
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, ACTION, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean action_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "action_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean action_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "action_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // arguments?
  private static boolean action_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_4")) return false;
    arguments(b, l + 1);
    return true;
  }

  // comment*
  private static boolean action_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "action_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (comment* action comment*)*
  public static boolean actions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<actions>");
    int c = current_position_(b);
    while (true) {
      if (!actions_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "actions", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, ACTIONS, true, false, null);
    return true;
  }

  // comment* action comment*
  private static boolean actions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = actions_0_0(b, l + 1);
    r = r && action(b, l + 1);
    r = r && actions_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean actions_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "actions_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean actions_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "actions_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // BRACE_OPEN actions BRACE_CLOSE
  public static boolean actions_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_block")) return false;
    if (!nextTokenIs(b, BRACE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACE_OPEN);
    r = r && actions(b, l + 1);
    r = r && consumeToken(b, BRACE_CLOSE);
    exit_section_(b, m, ACTIONS_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // ARGUMENT_STATEMENT|STRING_LITERAL comment*
  public static boolean argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument")) return false;
    if (!nextTokenIs(b, "<argument>", ARGUMENT_STATEMENT, STRING_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<argument>");
    r = consumeToken(b, ARGUMENT_STATEMENT);
    if (!r) r = argument_1(b, l + 1);
    exit_section_(b, l, m, ARGUMENT, r, false, null);
    return r;
  }

  // STRING_LITERAL comment*
  private static boolean argument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL);
    r = r && argument_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean argument_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argument_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // argument (COMMA comment* argument)*
  public static boolean arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments")) return false;
    if (!nextTokenIs(b, "<arguments>", ARGUMENT_STATEMENT, STRING_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<arguments>");
    r = argument(b, l + 1);
    r = r && arguments_1(b, l + 1);
    exit_section_(b, l, m, ARGUMENTS, r, false, null);
    return r;
  }

  // (COMMA comment* argument)*
  private static boolean arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!arguments_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arguments_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA comment* argument
  private static boolean arguments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && arguments_1_0_1(b, l + 1);
    r = r && argument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean arguments_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arguments_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // BLOCK_COMMENT_OPEN BLOCK_COMMENT_CONTENT? BLOCK_COMMENT_CLOSE
  public static boolean block_comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_comment")) return false;
    if (!nextTokenIs(b, BLOCK_COMMENT_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BLOCK_COMMENT_OPEN);
    r = r && block_comment_1(b, l + 1);
    r = r && consumeToken(b, BLOCK_COMMENT_CLOSE);
    exit_section_(b, m, BLOCK_COMMENT, r);
    return r;
  }

  // BLOCK_COMMENT_CONTENT?
  private static boolean block_comment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_comment_1")) return false;
    consumeToken(b, BLOCK_COMMENT_CONTENT);
    return true;
  }

  /* ********************************************************** */
  // GUARD_BRACKET_OPEN (GUARD_NOT_BRACKET|bracket_expression)* GUARD_BRACKET_CLOSE
  static boolean bracket_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression")) return false;
    if (!nextTokenIs(b, GUARD_BRACKET_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, GUARD_BRACKET_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, bracket_expression_1(b, l + 1));
    r = p && consumeToken(b, GUARD_BRACKET_CLOSE) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // (GUARD_NOT_BRACKET|bracket_expression)*
  private static boolean bracket_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!bracket_expression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "bracket_expression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // GUARD_NOT_BRACKET|bracket_expression
  private static boolean bracket_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracket_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GUARD_NOT_BRACKET);
    if (!r) r = bracket_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // comment*  PARENTHESES_OPEN comment* CALLBACK_TRANSITION_NAME comment* (COMMA comment* arguments)? PARENTHESES_CLOSE
  public static boolean callback_transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<callback transition>");
    r = callback_transition_0(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_OPEN);
    r = r && callback_transition_2(b, l + 1);
    r = r && consumeToken(b, CALLBACK_TRANSITION_NAME);
    r = r && callback_transition_4(b, l + 1);
    r = r && callback_transition_5(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_CLOSE);
    exit_section_(b, l, m, CALLBACK_TRANSITION, r, false, null);
    return r;
  }

  // comment*
  private static boolean callback_transition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "callback_transition_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean callback_transition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "callback_transition_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean callback_transition_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "callback_transition_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (COMMA comment* arguments)?
  private static boolean callback_transition_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_5")) return false;
    callback_transition_5_0(b, l + 1);
    return true;
  }

  // COMMA comment* arguments
  private static boolean callback_transition_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && callback_transition_5_0_1(b, l + 1);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean callback_transition_5_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callback_transition_5_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "callback_transition_5_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // LINE_COMMENT|block_comment
  public static boolean comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment")) return false;
    if (!nextTokenIs(b, "<comment>", BLOCK_COMMENT_OPEN, LINE_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<comment>");
    r = consumeToken(b, LINE_COMMENT);
    if (!r) r = block_comment(b, l + 1);
    exit_section_(b, l, m, COMMENT, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // comment* NIL_KEYWORD comment*
  static boolean comment_nil(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_nil")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment_nil_0(b, l + 1);
    r = r && consumeToken(b, NIL_KEYWORD);
    r = r && comment_nil_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean comment_nil_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_nil_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "comment_nil_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean comment_nil_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_nil_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "comment_nil_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // CLASS_KEYWORD comment* qualified_identifier
  public static boolean context_class_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "context_class_declaration")) return false;
    if (!nextTokenIs(b, CLASS_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, CLASS_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, context_class_declaration_1(b, l + 1));
    r = p && qualified_identifier(b, l + 1) && r;
    exit_section_(b, l, m, CONTEXT_CLASS_DECLARATION, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean context_class_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "context_class_declaration_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "context_class_declaration_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // DECLARE_KEYWORD comment* DECLARE_STATEMENT
  public static boolean declare(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declare")) return false;
    if (!nextTokenIs(b, DECLARE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DECLARE_KEYWORD);
    r = r && declare_1(b, l + 1);
    r = r && consumeToken(b, DECLARE_STATEMENT);
    exit_section_(b, m, DECLARE, r);
    return r;
  }

  // comment*
  private static boolean declare_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declare_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "declare_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* ENTRY_KEYWORD comment* actions_block
  public static boolean entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<entry>");
    r = entry_0(b, l + 1);
    r = r && consumeToken(b, ENTRY_KEYWORD);
    p = r; // pin = 2
    r = r && report_error_(b, entry_2(b, l + 1));
    r = p && actions_block(b, l + 1) && r;
    exit_section_(b, l, m, ENTRY, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean entry_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entry_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean entry_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entry_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* EXIT_KEYWORD comment* actions_block
  public static boolean exit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exit")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<exit>");
    r = exit_0(b, l + 1);
    r = r && consumeToken(b, EXIT_KEYWORD);
    p = r; // pin = 2
    r = r && report_error_(b, exit_2(b, l + 1));
    r = p && actions_block(b, l + 1) && r;
    exit_section_(b, l, m, EXIT, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean exit_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exit_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exit_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean exit_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exit_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exit_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (verbatim_code_section|fsm_package| context_class_declaration| start_state|fsm_class_declaration|fsm_file| header_file| include_file|
  //  import_class_declaration| declare| access| comment)* map_declaration+ comment*
  static boolean fsmFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fsmFile_0(b, l + 1);
    r = r && fsmFile_1(b, l + 1);
    r = r && fsmFile_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (verbatim_code_section|fsm_package| context_class_declaration| start_state|fsm_class_declaration|fsm_file| header_file| include_file|
  //  import_class_declaration| declare| access| comment)*
  private static boolean fsmFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fsmFile_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsmFile_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // verbatim_code_section|fsm_package| context_class_declaration| start_state|fsm_class_declaration|fsm_file| header_file| include_file|
  //  import_class_declaration| declare| access| comment
  private static boolean fsmFile_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = verbatim_code_section(b, l + 1);
    if (!r) r = fsm_package(b, l + 1);
    if (!r) r = context_class_declaration(b, l + 1);
    if (!r) r = start_state(b, l + 1);
    if (!r) r = fsm_class_declaration(b, l + 1);
    if (!r) r = fsm_file(b, l + 1);
    if (!r) r = header_file(b, l + 1);
    if (!r) r = include_file(b, l + 1);
    if (!r) r = import_class_declaration(b, l + 1);
    if (!r) r = declare(b, l + 1);
    if (!r) r = access(b, l + 1);
    if (!r) r = comment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // map_declaration+
  private static boolean fsmFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = map_declaration(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!map_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsmFile_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean fsmFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsmFile_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* FSM_CLASS_NAME
  public static boolean fsm_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_class")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<fsm class>");
    r = fsm_class_0(b, l + 1);
    r = r && consumeToken(b, FSM_CLASS_NAME);
    exit_section_(b, l, m, FSM_CLASS, r, false, null);
    return r;
  }

  // comment*
  private static boolean fsm_class_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_class_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsm_class_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // FSM_CLASS_KEYWORD comment* fsm_class
  public static boolean fsm_class_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_class_declaration")) return false;
    if (!nextTokenIs(b, FSM_CLASS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FSM_CLASS_KEYWORD);
    r = r && fsm_class_declaration_1(b, l + 1);
    r = r && fsm_class(b, l + 1);
    exit_section_(b, m, FSM_CLASS_DECLARATION, r);
    return r;
  }

  // comment*
  private static boolean fsm_class_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_class_declaration_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsm_class_declaration_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // FSM_FILE_KEYWORD comment* FSM_FILE_NAME
  public static boolean fsm_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_file")) return false;
    if (!nextTokenIs(b, FSM_FILE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FSM_FILE_KEYWORD);
    r = r && fsm_file_1(b, l + 1);
    r = r && consumeToken(b, FSM_FILE_NAME);
    exit_section_(b, m, FSM_FILE, r);
    return r;
  }

  // comment*
  private static boolean fsm_file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_file_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsm_file_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // PACKAGE_KEYWORD comment* qualified_identifier
  public static boolean fsm_package(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_package")) return false;
    if (!nextTokenIs(b, PACKAGE_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, PACKAGE_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, fsm_package_1(b, l + 1));
    r = p && qualified_identifier(b, l + 1) && r;
    exit_section_(b, l, m, FSM_PACKAGE, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean fsm_package_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_package_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsm_package_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // BRACKET_OPEN comment*  guard_raw_code? comment* BRACKET_CLOSE
  public static boolean guard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard")) return false;
    if (!nextTokenIs(b, BRACKET_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_OPEN);
    r = r && guard_1(b, l + 1);
    r = r && guard_2(b, l + 1);
    r = r && guard_3(b, l + 1);
    r = r && consumeToken(b, BRACKET_CLOSE);
    exit_section_(b, m, GUARD, r);
    return r;
  }

  // comment*
  private static boolean guard_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "guard_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // guard_raw_code?
  private static boolean guard_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_2")) return false;
    guard_raw_code(b, l + 1);
    return true;
  }

  // comment*
  private static boolean guard_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "guard_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (GUARD_NOT_BRACKET|bracket_expression)*
  public static boolean guard_raw_code(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_raw_code")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<guard raw code>");
    int c = current_position_(b);
    while (true) {
      if (!guard_raw_code_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "guard_raw_code", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, GUARD_RAW_CODE, true, false, null);
    return true;
  }

  // GUARD_NOT_BRACKET|bracket_expression
  private static boolean guard_raw_code_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_raw_code_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GUARD_NOT_BRACKET);
    if (!r) r = bracket_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HEADER_KEYWORD comment* HEADER_FILE_NAME
  public static boolean header_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header_file")) return false;
    if (!nextTokenIs(b, HEADER_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEADER_KEYWORD);
    r = r && header_file_1(b, l + 1);
    r = r && consumeToken(b, HEADER_FILE_NAME);
    exit_section_(b, m, HEADER_FILE, r);
    return r;
  }

  // comment*
  private static boolean header_file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header_file_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "header_file_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* IDENTIFIER_NAME comment*
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<identifier>");
    r = identifier_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER_NAME);
    p = r; // pin = 2
    r = r && identifier_2(b, l + 1);
    exit_section_(b, l, m, IDENTIFIER, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean identifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifier_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean identifier_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifier_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* qualified_identifier
  public static boolean import_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<import class>");
    r = import_class_0(b, l + 1);
    r = r && qualified_identifier(b, l + 1);
    exit_section_(b, l, m, IMPORT_CLASS, r, false, null);
    return r;
  }

  // comment*
  private static boolean import_class_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "import_class_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // IMPORT_KEYWORD comment* (static_import|import_class)
  public static boolean import_class_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class_declaration")) return false;
    if (!nextTokenIs(b, IMPORT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT_KEYWORD);
    r = r && import_class_declaration_1(b, l + 1);
    r = r && import_class_declaration_2(b, l + 1);
    exit_section_(b, m, IMPORT_CLASS_DECLARATION, r);
    return r;
  }

  // comment*
  private static boolean import_class_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class_declaration_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "import_class_declaration_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // static_import|import_class
  private static boolean import_class_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class_declaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = static_import(b, l + 1);
    if (!r) r = import_class(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INCLUDE_KEYWORD comment* INCLUDE_FILE_NAME
  public static boolean include_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_file")) return false;
    if (!nextTokenIs(b, INCLUDE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INCLUDE_KEYWORD);
    r = r && include_file_1(b, l + 1);
    r = r && consumeToken(b, INCLUDE_FILE_NAME);
    exit_section_(b, m, INCLUDE_FILE, r);
    return r;
  }

  // comment*
  private static boolean include_file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_file_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "include_file_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // MAP_NAME comment* MAP_SECTION_BOUND states MAP_SECTION_BOUND
  public static boolean map(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map")) return false;
    if (!nextTokenIs(b, MAP_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, MAP_NAME);
    r = r && map_1(b, l + 1);
    r = r && consumeToken(b, MAP_SECTION_BOUND);
    p = r; // pin = 3
    r = r && report_error_(b, states(b, l + 1));
    r = p && consumeToken(b, MAP_SECTION_BOUND) && r;
    exit_section_(b, l, m, MAP, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean map_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "map_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* MAP_KEYWORD comment* map
  public static boolean map_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_declaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<map declaration>");
    r = map_declaration_0(b, l + 1);
    r = r && consumeToken(b, MAP_KEYWORD);
    r = r && map_declaration_2(b, l + 1);
    r = r && map(b, l + 1);
    exit_section_(b, l, m, MAP_DECLARATION, r, false, null);
    return r;
  }

  // comment*
  private static boolean map_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_declaration_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "map_declaration_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean map_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_declaration_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "map_declaration_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* (NEXT_STATE_NAME|NIL_KEYWORD) comment*
  public static boolean next_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_state")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<next state>");
    r = next_state_0(b, l + 1);
    r = r && next_state_1(b, l + 1);
    p = r; // pin = 2
    r = r && next_state_2(b, l + 1);
    exit_section_(b, l, m, NEXT_STATE, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean next_state_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_state_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "next_state_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // NEXT_STATE_NAME|NIL_KEYWORD
  private static boolean next_state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_state_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEXT_STATE_NAME);
    if (!r) r = consumeToken(b, NIL_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean next_state_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_state_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "next_state_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (entry exit)|(exit entry)|((entry|exit)?)
  public static boolean on_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_state")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<on state>");
    r = on_state_0(b, l + 1);
    if (!r) r = on_state_1(b, l + 1);
    if (!r) r = on_state_2(b, l + 1);
    exit_section_(b, l, m, ON_STATE, r, false, null);
    return r;
  }

  // entry exit
  private static boolean on_state_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_state_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entry(b, l + 1);
    r = r && exit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // exit entry
  private static boolean on_state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_state_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = exit(b, l + 1);
    r = r && entry(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (entry|exit)?
  private static boolean on_state_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_state_2")) return false;
    on_state_2_0(b, l + 1);
    return true;
  }

  // entry|exit
  private static boolean on_state_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_state_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entry(b, l + 1);
    if (!r) r = exit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // comment* PARAMETER_NAME comment* COLON comment* PARAMETER_TYPE comment*
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<parameter>");
    r = parameter_0(b, l + 1);
    r = r && consumeToken(b, PARAMETER_NAME);
    p = r; // pin = 2
    r = r && report_error_(b, parameter_2(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, parameter_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, PARAMETER_TYPE)) && r;
    r = p && parameter_6(b, l + 1) && r;
    exit_section_(b, l, m, PARAMETER, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean parameter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean parameter_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean parameter_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean parameter_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // parameter (COMMA comment* parameter)*
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<parameters>");
    r = parameter(b, l + 1);
    r = r && parameters_1(b, l + 1);
    exit_section_(b, l, m, PARAMETERS, r, false, null);
    return r;
  }

  // (COMMA comment* parameter)*
  private static boolean parameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!parameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameters_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA comment* parameter
  private static boolean parameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameters_1_0_1(b, l + 1);
    r = r && parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean parameters_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameters_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // POP_KEYWORD callback_transition?
  public static boolean pop_transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition")) return false;
    if (!nextTokenIs(b, POP_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, POP_KEYWORD);
    p = r; // pin = 1
    r = r && pop_transition_1(b, l + 1);
    exit_section_(b, l, m, POP_TRANSITION, r, p, null);
    return r || p;
  }

  // callback_transition?
  private static boolean pop_transition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition_1")) return false;
    callback_transition(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PUSH_MAP_NAME
  public static boolean push_map_name_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_map_name_element")) return false;
    if (!nextTokenIs(b, PUSH_MAP_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUSH_MAP_NAME);
    exit_section_(b, m, PUSH_MAP_NAME_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // (push_proxy_state_name_element|NIL_KEYWORD) comment* PUSH_PROXY_STATE_KEYWORD_SEPARATOR
  public static boolean push_proxy_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_proxy_state")) return false;
    if (!nextTokenIs(b, "<push proxy state>", NIL_KEYWORD, PUSH_PROXY_STATE_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<push proxy state>");
    r = push_proxy_state_0(b, l + 1);
    r = r && push_proxy_state_1(b, l + 1);
    r = r && consumeToken(b, PUSH_PROXY_STATE_KEYWORD_SEPARATOR);
    exit_section_(b, l, m, PUSH_PROXY_STATE, r, false, null);
    return r;
  }

  // push_proxy_state_name_element|NIL_KEYWORD
  private static boolean push_proxy_state_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_proxy_state_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = push_proxy_state_name_element(b, l + 1);
    if (!r) r = consumeToken(b, NIL_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean push_proxy_state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_proxy_state_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_proxy_state_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // PUSH_PROXY_STATE_NAME
  public static boolean push_proxy_state_name_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_proxy_state_name_element")) return false;
    if (!nextTokenIs(b, PUSH_PROXY_STATE_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUSH_PROXY_STATE_NAME);
    exit_section_(b, m, PUSH_PROXY_STATE_NAME_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // comment* (push_map_name_element comment* MAP_NAME_STATE_NAME_SEPARATOR )? comment* push_state_name_element
  public static boolean push_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<push state>");
    r = push_state_0(b, l + 1);
    r = r && push_state_1(b, l + 1);
    r = r && push_state_2(b, l + 1);
    r = r && push_state_name_element(b, l + 1);
    exit_section_(b, l, m, PUSH_STATE, r, false, null);
    return r;
  }

  // comment*
  private static boolean push_state_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_state_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (push_map_name_element comment* MAP_NAME_STATE_NAME_SEPARATOR )?
  private static boolean push_state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_1")) return false;
    push_state_1_0(b, l + 1);
    return true;
  }

  // push_map_name_element comment* MAP_NAME_STATE_NAME_SEPARATOR
  private static boolean push_state_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = push_map_name_element(b, l + 1);
    r = r && push_state_1_0_1(b, l + 1);
    r = r && consumeToken(b, MAP_NAME_STATE_NAME_SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean push_state_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_state_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean push_state_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_state_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // PUSH_STATE_NAME comment*
  public static boolean push_state_name_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_name_element")) return false;
    if (!nextTokenIs(b, PUSH_STATE_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, PUSH_STATE_NAME);
    p = r; // pin = 1
    r = r && push_state_name_element_1(b, l + 1);
    exit_section_(b, l, m, PUSH_STATE_NAME_ELEMENT, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean push_state_name_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_state_name_element_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_state_name_element_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (push_proxy_state)? comment* PUSH_KEYWORD comment* PARENTHESES_OPEN (push_state|comment_nil) PARENTHESES_CLOSE
  public static boolean push_transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<push transition>");
    r = push_transition_0(b, l + 1);
    r = r && push_transition_1(b, l + 1);
    r = r && consumeToken(b, PUSH_KEYWORD);
    p = r; // pin = 3
    r = r && report_error_(b, push_transition_3(b, l + 1));
    r = p && report_error_(b, consumeToken(b, PARENTHESES_OPEN)) && r;
    r = p && report_error_(b, push_transition_5(b, l + 1)) && r;
    r = p && consumeToken(b, PARENTHESES_CLOSE) && r;
    exit_section_(b, l, m, PUSH_TRANSITION, r, p, null);
    return r || p;
  }

  // (push_proxy_state)?
  private static boolean push_transition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition_0")) return false;
    push_transition_0_0(b, l + 1);
    return true;
  }

  // (push_proxy_state)
  private static boolean push_transition_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = push_proxy_state(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean push_transition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_transition_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean push_transition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "push_transition_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // push_state|comment_nil
  private static boolean push_transition_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = push_state(b, l + 1);
    if (!r) r = comment_nil(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (identifier DOT)* identifier
  public static boolean qualified_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualified_identifier")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<qualified identifier>");
    r = qualified_identifier_0(b, l + 1);
    p = r; // pin = 1
    r = r && identifier(b, l + 1);
    exit_section_(b, l, m, QUALIFIED_IDENTIFIER, r, p, null);
    return r || p;
  }

  // (identifier DOT)*
  private static boolean qualified_identifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualified_identifier_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!qualified_identifier_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "qualified_identifier_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // identifier DOT
  private static boolean qualified_identifier_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualified_identifier_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // START_MAP_NAME comment*
  public static boolean start_map_name_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_map_name_element")) return false;
    if (!nextTokenIs(b, START_MAP_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, START_MAP_NAME);
    r = r && start_map_name_element_1(b, l + 1);
    exit_section_(b, m, START_MAP_NAME_ELEMENT, r);
    return r;
  }

  // comment*
  private static boolean start_map_name_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_map_name_element_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "start_map_name_element_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // START_KEYWORD comment* start_map_name_element MAP_NAME_STATE_NAME_SEPARATOR comment* start_state_name_element
  public static boolean start_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state")) return false;
    if (!nextTokenIs(b, START_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, START_KEYWORD);
    r = r && start_state_1(b, l + 1);
    r = r && start_map_name_element(b, l + 1);
    r = r && consumeToken(b, MAP_NAME_STATE_NAME_SEPARATOR);
    r = r && start_state_4(b, l + 1);
    r = r && start_state_name_element(b, l + 1);
    exit_section_(b, m, START_STATE, r);
    return r;
  }

  // comment*
  private static boolean start_state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "start_state_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean start_state_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "start_state_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // START_STATE_NAME comment*
  public static boolean start_state_name_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state_name_element")) return false;
    if (!nextTokenIs(b, START_STATE_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, START_STATE_NAME);
    p = r; // pin = 1
    r = r && start_state_name_element_1(b, l + 1);
    exit_section_(b, l, m, START_STATE_NAME_ELEMENT, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean start_state_name_element_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state_name_element_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "start_state_name_element_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment * STATE_NAME comment* on_state? comment* transitions_block comment*
  public static boolean state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<state>");
    r = state_0(b, l + 1);
    r = r && consumeToken(b, STATE_NAME);
    p = r; // pin = 2
    r = r && report_error_(b, state_2(b, l + 1));
    r = p && report_error_(b, state_3(b, l + 1)) && r;
    r = p && report_error_(b, state_4(b, l + 1)) && r;
    r = p && report_error_(b, transitions_block(b, l + 1)) && r;
    r = p && state_6(b, l + 1) && r;
    exit_section_(b, l, m, STATE, r, p, null);
    return r || p;
  }

  // comment *
  private static boolean state_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "state_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean state_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "state_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // on_state?
  private static boolean state_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_3")) return false;
    on_state(b, l + 1);
    return true;
  }

  // comment*
  private static boolean state_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "state_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean state_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "state_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* state* comment*
  public static boolean states(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<states>");
    r = states_0(b, l + 1);
    r = r && states_1(b, l + 1);
    r = r && states_2(b, l + 1);
    exit_section_(b, l, m, STATES, r, false, null);
    return r;
  }

  // comment*
  private static boolean states_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "states_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // state*
  private static boolean states_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!state(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "states_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean states_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "states_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // STATIC_JAVA_KEYWORD comment* qualified_identifier
  public static boolean static_import(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "static_import")) return false;
    if (!nextTokenIs(b, STATIC_JAVA_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STATIC_JAVA_KEYWORD);
    r = r && static_import_1(b, l + 1);
    r = r && qualified_identifier(b, l + 1);
    exit_section_(b, m, STATIC_IMPORT, r);
    return r;
  }

  // comment*
  private static boolean static_import_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "static_import_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "static_import_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // TRANSITION_NAME comment* transition_args? comment* guard? comment* (push_transition|pop_transition|next_state) comment* actions_block
  public static boolean transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition")) return false;
    if (!nextTokenIs(b, TRANSITION_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TRANSITION_NAME);
    p = r; // pin = 1
    r = r && report_error_(b, transition_1(b, l + 1));
    r = p && report_error_(b, transition_2(b, l + 1)) && r;
    r = p && report_error_(b, transition_3(b, l + 1)) && r;
    r = p && report_error_(b, transition_4(b, l + 1)) && r;
    r = p && report_error_(b, transition_5(b, l + 1)) && r;
    r = p && report_error_(b, transition_6(b, l + 1)) && r;
    r = p && report_error_(b, transition_7(b, l + 1)) && r;
    r = p && actions_block(b, l + 1) && r;
    exit_section_(b, l, m, TRANSITION, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean transition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // transition_args?
  private static boolean transition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_2")) return false;
    transition_args(b, l + 1);
    return true;
  }

  // comment*
  private static boolean transition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // guard?
  private static boolean transition_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_4")) return false;
    guard(b, l + 1);
    return true;
  }

  // comment*
  private static boolean transition_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // push_transition|pop_transition|next_state
  private static boolean transition_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = push_transition(b, l + 1);
    if (!r) r = pop_transition(b, l + 1);
    if (!r) r = next_state(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // comment*
  private static boolean transition_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_7")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_7", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // PARENTHESES_OPEN comment* parameters comment* PARENTHESES_CLOSE
  public static boolean transition_args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_args")) return false;
    if (!nextTokenIs(b, PARENTHESES_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, PARENTHESES_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, transition_args_1(b, l + 1));
    r = p && report_error_(b, parameters(b, l + 1)) && r;
    r = p && report_error_(b, transition_args_3(b, l + 1)) && r;
    r = p && consumeToken(b, PARENTHESES_CLOSE) && r;
    exit_section_(b, l, m, TRANSITION_ARGS, r, p, null);
    return r || p;
  }

  // comment*
  private static boolean transition_args_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_args_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_args_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean transition_args_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_args_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transition_args_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // comment* transition* comment*
  public static boolean transitions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<transitions>");
    r = transitions_0(b, l + 1);
    r = r && transitions_1(b, l + 1);
    r = r && transitions_2(b, l + 1);
    exit_section_(b, l, m, TRANSITIONS, r, false, null);
    return r;
  }

  // comment*
  private static boolean transitions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // transition*
  private static boolean transitions_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!transition(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean transitions_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // BRACE_OPEN comment* transitions comment* BRACE_CLOSE
  public static boolean transitions_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_block")) return false;
    if (!nextTokenIs(b, BRACE_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACE_OPEN);
    r = r && transitions_block_1(b, l + 1);
    r = r && transitions(b, l + 1);
    r = r && transitions_block_3(b, l + 1);
    r = r && consumeToken(b, BRACE_CLOSE);
    exit_section_(b, m, TRANSITIONS_BLOCK, r);
    return r;
  }

  // comment*
  private static boolean transitions_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_block_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions_block_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment*
  private static boolean transitions_block_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions_block_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!comment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions_block_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // VERBATIM_OPEN VERBATIM_CODE? VERBATIM_CLOSE
  public static boolean verbatim_code_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verbatim_code_section")) return false;
    if (!nextTokenIs(b, VERBATIM_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VERBATIM_OPEN);
    r = r && verbatim_code_section_1(b, l + 1);
    r = r && consumeToken(b, VERBATIM_CLOSE);
    exit_section_(b, m, VERBATIM_CODE_SECTION, r);
    return r;
  }

  // VERBATIM_CODE?
  private static boolean verbatim_code_section_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "verbatim_code_section_1")) return false;
    consumeToken(b, VERBATIM_CODE);
    return true;
  }

}
