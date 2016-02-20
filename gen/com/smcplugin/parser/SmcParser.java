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
    else if (t == ARGUMENTS) {
      r = arguments(b, 0);
    }
    else if (t == CLASS_NAME) {
      r = class_name(b, 0);
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
    else if (t == FSM_FILE) {
      r = fsm_file(b, 0);
    }
    else if (t == GUARD) {
      r = guard(b, 0);
    }
    else if (t == HEADER_FILE) {
      r = header_file(b, 0);
    }
    else if (t == IMPORT_CLASS) {
      r = import_class(b, 0);
    }
    else if (t == INCLUDE_FILE) {
      r = include_file(b, 0);
    }
    else if (t == MAP) {
      r = map(b, 0);
    }
    else if (t == NEXT_STATE) {
      r = next_state(b, 0);
    }
    else if (t == PACKAGE_NAME) {
      r = package_name(b, 0);
    }
    else if (t == PARAMETER) {
      r = parameter(b, 0);
    }
    else if (t == PARAMETERS) {
      r = parameters(b, 0);
    }
    else if (t == POP_ARGUMENTS) {
      r = pop_arguments(b, 0);
    }
    else if (t == POP_TRANSITION) {
      r = pop_transition(b, 0);
    }
    else if (t == PUSH_TRANSITION) {
      r = push_transition(b, 0);
    }
    else if (t == START_STATE) {
      r = start_state(b, 0);
    }
    else if (t == STATE) {
      r = state(b, 0);
    }
    else if (t == STATES) {
      r = states(b, 0);
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
  // ACCESS_KEYWORD ACCESS_LEVEL
  public static boolean access(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "access")) return false;
    if (!nextTokenIs(b, ACCESS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ACCESS_KEYWORD, ACCESS_LEVEL);
    exit_section_(b, m, ACCESS, r);
    return r;
  }

  /* ********************************************************** */
  // ACTION_NAME PARENTHESES_OPEN arguments? PARENTHESES_CLOSE SEMICOLON
  public static boolean action(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action")) return false;
    if (!nextTokenIs(b, ACTION_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 1, ACTION_NAME, PARENTHESES_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, action_2(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, PARENTHESES_CLOSE, SEMICOLON)) && r;
    exit_section_(b, l, m, ACTION, r, p, null);
    return r || p;
  }

  // arguments?
  private static boolean action_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_2")) return false;
    arguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // action actions|action?
  public static boolean actions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<actions>");
    r = actions_0(b, l + 1);
    if (!r) r = actions_1(b, l + 1);
    exit_section_(b, l, m, ACTIONS, r, false, null);
    return r;
  }

  // action actions
  private static boolean actions_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = action(b, l + 1);
    r = r && actions(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // action?
  private static boolean actions_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "actions_1")) return false;
    action(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (ARGUMENT_STATEMENT|STRING_LITERAL) COMMA arguments| ARGUMENT_STATEMENT|STRING_LITERAL
  public static boolean arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments")) return false;
    if (!nextTokenIs(b, "<arguments>", ARGUMENT_STATEMENT, STRING_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<arguments>");
    r = arguments_0(b, l + 1);
    if (!r) r = consumeToken(b, ARGUMENT_STATEMENT);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    exit_section_(b, l, m, ARGUMENTS, r, false, null);
    return r;
  }

  // (ARGUMENT_STATEMENT|STRING_LITERAL) COMMA arguments
  private static boolean arguments_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arguments_0_0(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ARGUMENT_STATEMENT|STRING_LITERAL
  private static boolean arguments_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arguments_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARGUMENT_STATEMENT);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CLASS_KEYWORD CONTEXT_CLASS_NAME
  public static boolean class_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_name")) return false;
    if (!nextTokenIs(b, CLASS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CLASS_KEYWORD, CONTEXT_CLASS_NAME);
    exit_section_(b, m, CLASS_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // DECLARE_KEYWORD DECLARE_STATEMENT
  public static boolean declare(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declare")) return false;
    if (!nextTokenIs(b, DECLARE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DECLARE_KEYWORD, DECLARE_STATEMENT);
    exit_section_(b, m, DECLARE, r);
    return r;
  }

  /* ********************************************************** */
  // ENTRY_KEYWORD BRACE_OPEN actions BRACE_CLOSE
  public static boolean entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry")) return false;
    if (!nextTokenIs(b, ENTRY_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 1, ENTRY_KEYWORD, BRACE_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, actions(b, l + 1));
    r = p && consumeToken(b, BRACE_CLOSE) && r;
    exit_section_(b, l, m, ENTRY, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // EXIT_KEYWORD BRACE_OPEN actions BRACE_CLOSE
  public static boolean exit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exit")) return false;
    if (!nextTokenIs(b, EXIT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 1, EXIT_KEYWORD, BRACE_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, actions(b, l + 1));
    r = p && consumeToken(b, BRACE_CLOSE) && r;
    exit_section_(b, l, m, EXIT, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (verbatim_code_section|class_name| start_state|fsm_class|fsm_file| header_file| include_file|
  // package_name| import_class| declare| access| map+|LINE_COMMENT|BLOCK_COMMENT|CRLF)*
  static boolean fsmFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!fsmFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsmFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // verbatim_code_section|class_name| start_state|fsm_class|fsm_file| header_file| include_file|
  // package_name| import_class| declare| access| map+|LINE_COMMENT|BLOCK_COMMENT|CRLF
  private static boolean fsmFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = verbatim_code_section(b, l + 1);
    if (!r) r = class_name(b, l + 1);
    if (!r) r = start_state(b, l + 1);
    if (!r) r = fsm_class(b, l + 1);
    if (!r) r = fsm_file(b, l + 1);
    if (!r) r = header_file(b, l + 1);
    if (!r) r = include_file(b, l + 1);
    if (!r) r = package_name(b, l + 1);
    if (!r) r = import_class(b, l + 1);
    if (!r) r = declare(b, l + 1);
    if (!r) r = access(b, l + 1);
    if (!r) r = fsmFile_0_11(b, l + 1);
    if (!r) r = consumeToken(b, LINE_COMMENT);
    if (!r) r = consumeToken(b, BLOCK_COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    exit_section_(b, m, null, r);
    return r;
  }

  // map+
  private static boolean fsmFile_0_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsmFile_0_11")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = map(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!map(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fsmFile_0_11", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FSM_CLASS_KEYWORD FSM_CLASS_NAME
  public static boolean fsm_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_class")) return false;
    if (!nextTokenIs(b, FSM_CLASS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FSM_CLASS_KEYWORD, FSM_CLASS_NAME);
    exit_section_(b, m, FSM_CLASS, r);
    return r;
  }

  /* ********************************************************** */
  // FSM_FILE_KEYWORD FSM_FILE_NAME
  public static boolean fsm_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fsm_file")) return false;
    if (!nextTokenIs(b, FSM_FILE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FSM_FILE_KEYWORD, FSM_FILE_NAME);
    exit_section_(b, m, FSM_FILE, r);
    return r;
  }

  /* ********************************************************** */
  // BRACKET_OPEN GUARD_RAW_CODE BRACKET_CLOSE
  public static boolean guard(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard")) return false;
    if (!nextTokenIs(b, BRACKET_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BRACKET_OPEN, GUARD_RAW_CODE, BRACKET_CLOSE);
    exit_section_(b, m, GUARD, r);
    return r;
  }

  /* ********************************************************** */
  // HEADER_KEYWORD HEADER_FILE_NAME
  public static boolean header_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header_file")) return false;
    if (!nextTokenIs(b, HEADER_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HEADER_KEYWORD, HEADER_FILE_NAME);
    exit_section_(b, m, HEADER_FILE, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT_KEYWORD STATIC_JAVA_KEYWORD? IMPORT_CLASS_STATEMENT
  public static boolean import_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class")) return false;
    if (!nextTokenIs(b, IMPORT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT_KEYWORD);
    r = r && import_class_1(b, l + 1);
    r = r && consumeToken(b, IMPORT_CLASS_STATEMENT);
    exit_section_(b, m, IMPORT_CLASS, r);
    return r;
  }

  // STATIC_JAVA_KEYWORD?
  private static boolean import_class_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_class_1")) return false;
    consumeToken(b, STATIC_JAVA_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // INCLUDE_KEYWORD INCLUDE_FILE_NAME
  public static boolean include_file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_file")) return false;
    if (!nextTokenIs(b, INCLUDE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INCLUDE_KEYWORD, INCLUDE_FILE_NAME);
    exit_section_(b, m, INCLUDE_FILE, r);
    return r;
  }

  /* ********************************************************** */
  // MAP_KEYWORD MAP_NAME MAP_SECTION_BOUND states MAP_SECTION_BOUND
  public static boolean map(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map")) return false;
    if (!nextTokenIs(b, MAP_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 1, MAP_KEYWORD, MAP_NAME, MAP_SECTION_BOUND);
    p = r; // pin = 1
    r = r && report_error_(b, states(b, l + 1));
    r = p && consumeToken(b, MAP_SECTION_BOUND) && r;
    exit_section_(b, l, m, MAP, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NEXT_STATE_NAME |
  //               NIL_KEYWORD|
  //               push_transition |
  //               pop_transition
  public static boolean next_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_state")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<next state>");
    r = consumeToken(b, NEXT_STATE_NAME);
    if (!r) r = consumeToken(b, NIL_KEYWORD);
    if (!r) r = push_transition(b, l + 1);
    if (!r) r = pop_transition(b, l + 1);
    exit_section_(b, l, m, NEXT_STATE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PACKAGE_KEYWORD PACKAGE_STATEMENT
  public static boolean package_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "package_name")) return false;
    if (!nextTokenIs(b, PACKAGE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE_KEYWORD, PACKAGE_STATEMENT);
    exit_section_(b, m, PACKAGE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // PARAMETER_NAME COLON PARAMETER_TYPE
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    if (!nextTokenIs(b, PARAMETER_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PARAMETER_NAME, COLON, PARAMETER_TYPE);
    exit_section_(b, m, PARAMETER, r);
    return r;
  }

  /* ********************************************************** */
  // parameter COMMA parameters | parameter
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    if (!nextTokenIs(b, PARAMETER_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameters_0(b, l + 1);
    if (!r) r = parameter(b, l + 1);
    exit_section_(b, m, PARAMETERS, r);
    return r;
  }

  // parameter COMMA parameters
  private static boolean parameters_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameter(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && parameters(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // POP_ARGUMENT_RAW_CODE |
  //                  POP_ARGUMENT_RAW_CODE COMMA pop_arguments
  public static boolean pop_arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_arguments")) return false;
    if (!nextTokenIs(b, POP_ARGUMENT_RAW_CODE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POP_ARGUMENT_RAW_CODE);
    if (!r) r = pop_arguments_1(b, l + 1);
    exit_section_(b, m, POP_ARGUMENTS, r);
    return r;
  }

  // POP_ARGUMENT_RAW_CODE COMMA pop_arguments
  private static boolean pop_arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_arguments_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POP_ARGUMENT_RAW_CODE, COMMA);
    r = r && pop_arguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // POP_KEYWORD |
  //                   POP_KEYWORD PARENTHESES_OPEN WORD? PARENTHESES_CLOSE |
  //                   POP_KEYWORD PARENTHESES_OPEN WORD COMMA pop_arguments* PARENTHESES_CLOSE
  public static boolean pop_transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition")) return false;
    if (!nextTokenIs(b, POP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POP_KEYWORD);
    if (!r) r = pop_transition_1(b, l + 1);
    if (!r) r = pop_transition_2(b, l + 1);
    exit_section_(b, m, POP_TRANSITION, r);
    return r;
  }

  // POP_KEYWORD PARENTHESES_OPEN WORD? PARENTHESES_CLOSE
  private static boolean pop_transition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POP_KEYWORD, PARENTHESES_OPEN);
    r = r && pop_transition_1_2(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // WORD?
  private static boolean pop_transition_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition_1_2")) return false;
    consumeToken(b, WORD);
    return true;
  }

  // POP_KEYWORD PARENTHESES_OPEN WORD COMMA pop_arguments* PARENTHESES_CLOSE
  private static boolean pop_transition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POP_KEYWORD, PARENTHESES_OPEN, WORD, COMMA);
    r = r && pop_transition_2_4(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // pop_arguments*
  private static boolean pop_transition_2_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pop_transition_2_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!pop_arguments(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "pop_transition_2_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // WORD SLASH_SIGN PUSH_KEYWORD PARENTHESES_OPEN WORD PARENTHESES_CLOSE |
  //                    NIL_KEYWORD SLASH_SIGN PUSH_KEYWORD PARENTHESES_OPEN WORD PARENTHESES_CLOSE |
  //                    PUSH_KEYWORD PARENTHESES_OPEN WORD PARENTHESES_CLOSE
  public static boolean push_transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "push_transition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<push transition>");
    r = parseTokens(b, 0, WORD, SLASH_SIGN, PUSH_KEYWORD, PARENTHESES_OPEN, WORD, PARENTHESES_CLOSE);
    if (!r) r = parseTokens(b, 0, NIL_KEYWORD, SLASH_SIGN, PUSH_KEYWORD, PARENTHESES_OPEN, WORD, PARENTHESES_CLOSE);
    if (!r) r = parseTokens(b, 0, PUSH_KEYWORD, PARENTHESES_OPEN, WORD, PARENTHESES_CLOSE);
    exit_section_(b, l, m, PUSH_TRANSITION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // START_KEYWORD START_STATE_NAME
  public static boolean start_state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "start_state")) return false;
    if (!nextTokenIs(b, START_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, START_KEYWORD, START_STATE_NAME);
    exit_section_(b, m, START_STATE, r);
    return r;
  }

  /* ********************************************************** */
  // STATE_NAME ((entry exit)|(exit entry)|((entry|exit)?))? BRACE_OPEN transitions BRACE_CLOSE
  public static boolean state(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state")) return false;
    if (!nextTokenIs(b, STATE_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, STATE_NAME);
    p = r; // pin = 1
    r = r && report_error_(b, state_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, BRACE_OPEN)) && r;
    r = p && report_error_(b, transitions(b, l + 1)) && r;
    r = p && consumeToken(b, BRACE_CLOSE) && r;
    exit_section_(b, l, m, STATE, r, p, null);
    return r || p;
  }

  // ((entry exit)|(exit entry)|((entry|exit)?))?
  private static boolean state_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1")) return false;
    state_1_0(b, l + 1);
    return true;
  }

  // (entry exit)|(exit entry)|((entry|exit)?)
  private static boolean state_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = state_1_0_0(b, l + 1);
    if (!r) r = state_1_0_1(b, l + 1);
    if (!r) r = state_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // entry exit
  private static boolean state_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entry(b, l + 1);
    r = r && exit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // exit entry
  private static boolean state_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = exit(b, l + 1);
    r = r && entry(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (entry|exit)?
  private static boolean state_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1_0_2")) return false;
    state_1_0_2_0(b, l + 1);
    return true;
  }

  // entry|exit
  private static boolean state_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "state_1_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entry(b, l + 1);
    if (!r) r = exit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // state*
  public static boolean states(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "states")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<states>");
    int c = current_position_(b);
    while (true) {
      if (!state(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "states", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, STATES, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // TRANSITION_NAME transition_args? guard? next_state BRACE_OPEN actions BRACE_CLOSE
  public static boolean transition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition")) return false;
    if (!nextTokenIs(b, TRANSITION_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, TRANSITION_NAME);
    p = r; // pin = 1
    r = r && report_error_(b, transition_1(b, l + 1));
    r = p && report_error_(b, transition_2(b, l + 1)) && r;
    r = p && report_error_(b, next_state(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, BRACE_OPEN)) && r;
    r = p && report_error_(b, actions(b, l + 1)) && r;
    r = p && consumeToken(b, BRACE_CLOSE) && r;
    exit_section_(b, l, m, TRANSITION, r, p, null);
    return r || p;
  }

  // transition_args?
  private static boolean transition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_1")) return false;
    transition_args(b, l + 1);
    return true;
  }

  // guard?
  private static boolean transition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_2")) return false;
    guard(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PARENTHESES_OPEN parameters PARENTHESES_CLOSE
  public static boolean transition_args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transition_args")) return false;
    if (!nextTokenIs(b, PARENTHESES_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, PARENTHESES_OPEN);
    p = r; // pin = 1
    r = r && report_error_(b, parameters(b, l + 1));
    r = p && consumeToken(b, PARENTHESES_CLOSE) && r;
    exit_section_(b, l, m, TRANSITION_ARGS, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // transition*
  public static boolean transitions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transitions")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<transitions>");
    int c = current_position_(b);
    while (true) {
      if (!transition(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transitions", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, TRANSITIONS, true, false, null);
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
