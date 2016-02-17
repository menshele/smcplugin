package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;
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
WORD=[A-Za-z][A-Za-z0-9_.]*| [A-Za-z][A-Za-z0-9_.]*{EOL}
CONTEXT_CLASS_NAME={WORD}
FSM_CLASS={WORD}
FSM_FILE={WORD}
PACKAGE_STATEMENT={WORD}
HEADER_FILE_NAME={WORD}
DECLARE_STATEMENT={WORD}
INCLUDE_FILE_NAME={WORD}
MAP_NAME={WORD}
STATE_NAME={WORD}
START_STATE_WORD=[A-Za-z][A-Za-z0-9]+::[A-Za-z][A-Za-z0-9]+
START_STATE_NAME={START_STATE_WORD}|{START_STATE_WORD}{EOL}
ACCESS_LEVEL="public"|"protected"|"private"
IMPORT_STATEMENT=[A-Za-z][A-Za-z0-9_.\*]*| [A-Za-z][A-Za-z0-9_.\*]*{EOL}
JAVA_CODE_STATEMENT=[A-Za-z][A-Za-z0-9_.()]*| [A-Za-z][A-Za-z0-9_.()]*{EOL}

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
%state WAITING_FOR_MAP
%state WAITING_FOR_STATE
%state WAITING_FOR_ARGUMENTS
%state WAITING_FOR_STATE_ENTRY
%state WAITING_FOR_STATE_EXIT
%state WAITING_FOR_TRANSITIONS
%state WAITING_FOR_PARAMETERS
%state WAITING_FOR_PARAMETER_TYPE
%state WAITING_FOR_PARAMETER_NAME
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
  "("                         { return BRACKET_OPEN; }
  ")"                         { return BRACKET_CLOSE; }
  "["                         { return GUARD_OPEN; }
  "]"                         { return GUARD_CLOSE; }
  ":"                         { return COLON; }
  ","                         { return COMMA; }
  ";"                         { return SEMICOLON; }
  "pop"                       { return POP_KEYWORD; }
  "push"                      { return PUSH_KEYWORD; }
  "="                         { return ASSIGN_OP; }
  "nil"                       { return NIL_KEYWORD; }
  "/"                         { return SLASH_SIGN; }
  "LINE_COMMENT"              { return LINE_COMMENT; }
  "BLOCK_COMMENT"             { return BLOCK_COMMENT; }
  "CRLF"                      { return CRLF; }
  "START_STATE_NAME"          { return START_STATE_NAME; }
  "CONTEXT_CLASS_NAME"        { return CONTEXT_CLASS_NAME; }
  "HEADER_FILE_NAME"          { return HEADER_FILE_NAME; }
  "INCLUDE_FILE_NAME"         { return INCLUDE_FILE_NAME; }
  "PACKAGE_STATEMENT"         { return PACKAGE_STATEMENT; }
  "IMPORT_CLASS_STATEMENT"    { return IMPORT_CLASS_STATEMENT; }
  "DECLARE_STATEMENT"         { return DECLARE_STATEMENT; }
  "ACCESS_LEVEL"              { return ACCESS_LEVEL; }
  "MAP_NAME"                  { return MAP_NAME; }
  "MAP_SECTION_BOUND"         { return MAP_SECTION_BOUND; }
  "STATE_NAME"                { return STATE_NAME; }
  "TRANSITION_NAME"           { return TRANSITION_NAME; }
  "PARAMETER_TYPE"            { return PARAMETER_TYPE; }
  "PARAMETER_NAME"            { return PARAMETER_NAME; }
  "GUARD_RAW_CODE"            { return GUARD_RAW_CODE; }
  "NEXT_STATE_NAME"           { return NEXT_STATE_NAME; }
  "WORD"                      { return WORD; }
  "POP_ARGUMENT_RAW_CODE"     { return POP_ARGUMENT_RAW_CODE; }
  "ARGUMENT_STATEMENT"        { return ARGUMENT_STATEMENT; }


  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
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
  "Entry"                     { yybegin(WAITING_FOR_STATE_ENTRY); return ENTRY_KEYWORD; }
  "Exit"                      { yybegin(WAITING_FOR_STATE_EXIT); return EXIT_KEYWORD; }
  "{"                         { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_OPEN; }
  "%%"                        { yybegin(WAITING_FOR_MAP); return MAP_SECTION_BOUND; }
  {STATE_NAME}                { yybegin(WAITING_FOR_STATE); return STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_STATE_ENTRY>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE_ENTRY); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "{"                         { yybegin(WAITING_FOR_STATE_ENTRY); return BRACE_OPEN; }
  "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_STATE_EXIT>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE_EXIT); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "{"                         { yybegin(WAITING_FOR_STATE_EXIT); return BRACE_OPEN; }
  "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
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
 "("                         { yybegin(WAITING_FOR_PARAMETER_NAME);  return BRACKET_OPEN; }
 //"["                         { yybegin(WAITING_FOR_GUARD_RAW_CODE); return GUARD_OPEN; }
 "}"                         { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
 "{"                         { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN; }
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
  ")"                         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_PARAMETER_TYPE); return PARAMETER_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_NEXT_STATE_NAME>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_NEXT_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
 // "["                         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return GUARD_OPEN; }
  "{"                         { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN; }
  {WORD}                      { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NEXT_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ACTIONS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  "("                         { yybegin(WAITING_FOR_ARGUMENTS);  return BRACKET_OPEN; }
  ";"                         { yybegin(WAITING_FOR_ACTIONS); return SEMICOLON; }
  "}"                         { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ACTIONS); return ACTION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ARGUMENTS>{
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ARGUMENTS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  ","                         { yybegin(WAITING_FOR_ARGUMENTS); return COMMA; }
  ")"                         { yybegin(WAITING_FOR_ACTIONS); return BRACKET_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ARGUMENTS); return ARGUMENT_STATEMENT;}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
