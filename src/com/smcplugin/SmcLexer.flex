package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static com.smcplugin.psi.SmcTypes.*;

%%

%{
  public SmcLexer() {
    this((java.io.Reader)null);
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
IMPORT_STATEMENT=[A-Za-z][A-Za-z0-9_.\*]*| [A-Za-z][A-Za-z0-9_.\*]*{EOL}

%state WAITING_FOR_VERBATIM_CODE
%state WAITING_FOR_PACKAGE_STATEMENT
%state WAITING_FOR_CONTEXT_CLASS
%state WAITING_FOR_IMPORT_CLASS
%state WAITING_FOR_FSM_CLASS
%state WAITING_FOR_FSM_FILE

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
  "%access"                   { return ACCESS_KEYWORD; }
  "%header"                   { return HEADER_KEYWORD; }
  "%declare"                  { return DECLARE_KEYWORD; }
  "%include"                  { return INCLUDE_KEYWORD; }
  "%start"                    { return START_KEYWORD; }
  "%map"                      { return MAP_KEYWORD; }
  "{"                         { return BRACE_OPEN; }
  "}"                         { return BRACE_CLOSE; }
  "("                         { return BRACKET_OPEN; }
  ")"                         { return BRACKET_CLOSE; }
  "Entry "                    { return ENTRY_KEYWORD; }
  "Exit "                     { return EXIT_KEYWORD; }
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
  {FSM_FILE}                 { yybegin(YYINITIAL); return FSM_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
