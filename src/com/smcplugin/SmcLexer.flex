package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.smcplugin.psi.SmcTypes.*;

%%

%{
  public SmcLexer() {
    this((java.io.Reader)null);
  }
  private Stack<Integer> stack = new Stack<Integer>();
  public void yypushState(int newState) {
    stack.push(yystate());
    yybegin(newState);
  }

  public void yypopState() {
    yybegin(stack.pop());
  }
  private static final Pattern JAVA_LITERAL_PATTERN = Pattern.compile("\"(?:\\\\[\\\\'\"tnbfru01234567]|[^\\\\\"])*?\"");
  public IElementType validateJavaString(CharSequence match) {
    Matcher matcher = JAVA_LITERAL_PATTERN.matcher(match);
    return matcher.matches()? STRING_LITERAL: com.intellij.psi.TokenType.BAD_CHARACTER;
  }
%}

%public
%class SmcLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+
VERBATIM_CODE=(.*|{EOL})*
GUARD_RAW_CODE=(.*|{EOL})*
WORD=[A-Za-z][A-Za-z0-9_.]*| [A-Za-z][A-Za-z0-9_.]*{EOL}
WORD_IN_ARGUMENTS=\"(.*)\"
CONTEXT_CLASS_NAME={WORD}
FSM_CLASS={WORD}
FSM_FILE={WORD}
PACKAGE_STATEMENT={WORD}
HEADER_FILE_NAME={WORD}
DECLARE_STATEMENT={WORD}
INCLUDE_FILE_NAME={WORD}
MAP_NAME={WORD}
STATE_NAME={WORD}
START_STATE_NAME={WORD}|{WORD}{EOL}
ACCESS_LEVEL="public"|"protected"|"private"
IMPORT_STATEMENT=[A-Za-z][A-Za-z0-9_.\*]*| [A-Za-z][A-Za-z0-9_.\*]*{EOL}
KEYWORD_NIL="nil"

LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"

%state WAITING_FOR_VERBATIM_CODE
%state WAITING_FOR_PACKAGE_STATEMENT
%state WAITING_FOR_CONTEXT_CLASS
%state WAITING_FOR_IMPORT_CLASS
%state WAITING_FOR_FSM_CLASS
%state WAITING_FOR_FSM_FILE
%state WAITING_FOR_ACCESS
%state WAITING_FOR_HEADER
%state WAITING_FOR_DECLARE
%state WAITING_FOR_INCLUDE
%state WAITING_FOR_START
%state WAITING_FOR_START_STATE_NAME
%state WAITING_FOR_MAP
%state WAITING_FOR_STATE
%state WAITING_FOR_ARGUMENTS
%state WAITING_FOR_STATE_ENTRY_EXIT
%state WAITING_FOR_STATE_EXIT
%state WAITING_FOR_ENTRY_EXIT_ACTIONS
%state WAITING_FOR_TRANSITIONS
%state WAITING_FOR_PARAMETERS
%state WAITING_FOR_PARAMETER_TYPE
%state WAITING_FOR_PARAMETER_NAME
%state WAITING_FOR_GUARD_RAW_CODE
%state WAITING_FOR_NEXT_STATE_NAME
%state WAITING_FOR_ACTIONS
%state WAITING_FOR_ACTION_NAME

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "%{"                        { yybegin(WAITING_FOR_VERBATIM_CODE);  return VERBATIM_OPEN; }
  "%}"                        { yybegin(YYINITIAL); return VERBATIM_CLOSE; }
  "%package"                  { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return PACKAGE_KEYWORD; }
  "%import"                   { yybegin(WAITING_FOR_IMPORT_CLASS); return IMPORT_KEYWORD; }
  "%class"                    { yybegin(WAITING_FOR_CONTEXT_CLASS); return CLASS_KEYWORD; }
  "%fsmclass"                 { yybegin(WAITING_FOR_FSM_CLASS); return FSM_CLASS_KEYWORD; }
  "%fsmfile"                  { yybegin(WAITING_FOR_FSM_FILE); return FSM_FILE_KEYWORD; }
  "%access"                   { yybegin(WAITING_FOR_ACCESS); return ACCESS_KEYWORD; }
  "%header"                   { yybegin(WAITING_FOR_HEADER); return HEADER_KEYWORD; }
  "%declare"                  { yybegin(WAITING_FOR_DECLARE); return DECLARE_KEYWORD; }
  "%include"                  { yybegin(WAITING_FOR_INCLUDE); return INCLUDE_KEYWORD; }
  "%start"                    { yybegin(WAITING_FOR_START); return START_KEYWORD; }
  "%map"                      { yybegin(WAITING_FOR_MAP); return MAP_KEYWORD; }
  "{"                         { return BRACE_OPEN; }
  "}"                         { return BRACE_CLOSE; }
  "("                         { return PARENTHESES_OPEN; }
  ")"                         { return PARENTHESES_CLOSE; }
  "["                         { return BRACKET_OPEN; }
  "]"                         { return BRACKET_CLOSE; }
  ":"                         { return COLON; }
  ","                         { return COMMA; }
  ";"                         { return SEMICOLON; }
  "pop"                       { return POP_KEYWORD; }
  "push"                      { return PUSH_KEYWORD; }
  "="                         { return ASSIGN_OP; }
  "nil"                       { return NIL_KEYWORD; }
  "/"                         { return SLASH_SIGN; }

  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT}             { return BLOCK_COMMENT; }

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
//TODO: Need to implement proper Java Code Parsing instead of VERBATIM_CODE one day. http://cui.unige.ch/isi/bnf/JAVA/statement_block.html
<WAITING_FOR_VERBATIM_CODE>{
  "%{"                        { yybegin(WAITING_FOR_VERBATIM_CODE);  return VERBATIM_OPEN; }
  {VERBATIM_CODE}"%}"         { yybegin(YYINITIAL);  yypushback(2); return VERBATIM_CODE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PACKAGE_STATEMENT>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PACKAGE_STATEMENT}         { yybegin(YYINITIAL); return PACKAGE_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_CONTEXT_CLASS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_CONTEXT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {CONTEXT_CLASS_NAME}        { yybegin(YYINITIAL); return CONTEXT_CLASS_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_IMPORT_CLASS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_IMPORT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "static"                    { yybegin(WAITING_FOR_IMPORT_CLASS); return STATIC_JAVA_KEYWORD; }
  {IMPORT_STATEMENT}          { yybegin(YYINITIAL); return IMPORT_CLASS_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_FSM_CLASS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_FSM_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {FSM_CLASS}                 { yybegin(YYINITIAL); return FSM_CLASS_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_FSM_FILE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_FSM_FILE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {FSM_FILE}                  { yybegin(YYINITIAL); return FSM_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_ACCESS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ACCESS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {ACCESS_LEVEL}              { yybegin(YYINITIAL); return ACCESS_LEVEL; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_HEADER>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_HEADER); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {HEADER_FILE_NAME}          { yybegin(YYINITIAL); return HEADER_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_DECLARE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_DECLARE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {DECLARE_STATEMENT}         { yybegin(YYINITIAL); return DECLARE_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_INCLUDE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_INCLUDE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {INCLUDE_FILE_NAME}         { yybegin(YYINITIAL); return INCLUDE_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_START>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_START); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {WORD}                      { yybegin(WAITING_FOR_START_STATE_NAME); return START_MAP_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_START_STATE_NAME>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_START_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "::"                        { yybegin(WAITING_FOR_START_STATE_NAME); return START_MAP_NAME_STATE_NAME_SEPARATOR; }
  {START_STATE_NAME}          { yybegin(YYINITIAL); return START_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_MAP>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_MAP); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "%%"                        { yybegin(WAITING_FOR_STATE); return MAP_SECTION_BOUND; }
  {MAP_NAME}                  { yybegin(WAITING_FOR_MAP); return MAP_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_STATE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "Entry"                     { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return ENTRY_KEYWORD; }
  "Exit"                      { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return EXIT_KEYWORD; }
  "{"                         { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_OPEN; }
  "%%"                        { yybegin(WAITING_FOR_MAP); return MAP_SECTION_BOUND; }
  {STATE_NAME}                { yybegin(WAITING_FOR_STATE); return STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_STATE_ENTRY_EXIT>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "{"                         { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return BRACE_OPEN; }
  "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ENTRY_EXIT_ACTIONS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "("                         { yypushState(WAITING_FOR_ARGUMENTS);  return PARENTHESES_OPEN; }
  ";"                         { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return SEMICOLON; }
  "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return ACTION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_TRANSITIONS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_TRANSITIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_PARAMETERS); return TRANSITION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PARAMETERS>{
 {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETERS); return com.intellij.psi.TokenType.WHITE_SPACE; }
 "("                         { yybegin(WAITING_FOR_PARAMETER_NAME);  return PARENTHESES_OPEN; }
 "["                         { yybegin(WAITING_FOR_GUARD_RAW_CODE); return BRACKET_OPEN; }
 "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
 "{"                         { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN; }
 {KEYWORD_NIL}               { yybegin(WAITING_FOR_PARAMETERS); return NIL_KEYWORD; }
 {WORD}                      { yybegin(WAITING_FOR_PARAMETERS); return NEXT_STATE_NAME; }
 [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}


<WAITING_FOR_PARAMETER_NAME>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  ":"                         { yybegin(WAITING_FOR_PARAMETER_TYPE); return COLON; }
  {WORD}                      { yybegin(WAITING_FOR_PARAMETER_NAME); return PARAMETER_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PARAMETER_TYPE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_TYPE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  ")"                         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE; }
  ","                         { yybegin(WAITING_FOR_PARAMETER_NAME); return COMMA; }
  {WORD}                      { yybegin(WAITING_FOR_PARAMETER_TYPE); return PARAMETER_TYPE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_NEXT_STATE_NAME>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_NEXT_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "["                         { yybegin(WAITING_FOR_GUARD_RAW_CODE); return BRACKET_OPEN; }
  "]"                         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE; }
  "{"                         { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN; }
  {KEYWORD_NIL}               { yybegin(WAITING_FOR_PARAMETERS); return NIL_KEYWORD; }
  {WORD}                      { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NEXT_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
//TODO: Need to implement proper Java Code Parsing one day. http://cui.unige.ch/isi/bnf/JAVA/expression.html
<WAITING_FOR_GUARD_RAW_CODE>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_GUARD_RAW_CODE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {GUARD_RAW_CODE}"]"         { yybegin(WAITING_FOR_NEXT_STATE_NAME);  yypushback(1); return GUARD_RAW_CODE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ACTIONS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "("                         { yypushState(WAITING_FOR_ARGUMENTS);  return PARENTHESES_OPEN; }
  ";"                         { yybegin(WAITING_FOR_ACTIONS); return SEMICOLON; }
  "}"                         { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ACTIONS); return ACTION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ARGUMENTS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ARGUMENTS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  ","                         { yybegin(WAITING_FOR_ARGUMENTS); return COMMA; }
  ")"                         { yypopState(); return PARENTHESES_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ARGUMENTS); return ARGUMENT_STATEMENT;}
  {WORD_IN_ARGUMENTS}         { yybegin(WAITING_FOR_ARGUMENTS); return validateJavaString(yytext());}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
