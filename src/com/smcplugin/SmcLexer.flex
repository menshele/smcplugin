package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
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

CRLF=\n|\r|\r\n
WORD=[A-Za-z][A-Za-z0-9_.]* | _[A-Za-z][A-Za-z0-9_.]*
RAW_CODE_LINE=[A-Za-z][A-Za-z0-9_.\*]*\; | _[A-Za-z][A-Za-z0-9_.\*]*\;\n\r\f
RAW_CODE_IN_PARAMS=[A-Za-z][A-Za-z0-9_.]*\; | _[A-Za-z][A-Za-z0-9_.]*\n\r\f
RAW_CODE=%\{(.|\n)*%\}
//RAW_CODE=(.|\n)*
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"

%state RAW_CODE_WAIT
%%
<YYINITIAL> {
  {WHITE_SPACE}        { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

  "%{"                 { yybegin(YYINITIAL); return VERBATIM_OPEN; }
  "%}"                 { yybegin(YYINITIAL); return VERBATIM_CLOSE; }
  "%class"             { yybegin(YYINITIAL); return CLASS_KEYWORD; }
  "%package"           { yybegin(YYINITIAL); return PACKAGE_KEYWORD; }
  "%fsmclass"          { yybegin(YYINITIAL); return FSM_CLASS_KEYWORD; }
  "%fsmfile"           { yybegin(YYINITIAL); return FSM_FILE_KEYWORD; }
  "%access"            { yybegin(YYINITIAL); return ACCESS_KEYWORD; }
  "%header"            { yybegin(YYINITIAL); return HEADER_KEYWORD; }
  "%declare"           { yybegin(YYINITIAL); return DECLARE_KEYWORD; }
  "%import"            { yybegin(YYINITIAL); return IMPORT_KEYWORD; }
  "%include"           { yybegin(YYINITIAL); return INCLUDE_KEYWORD; }
  "%start"             { yybegin(YYINITIAL); return START_KEYWORD; }
  "%map"               { yybegin(YYINITIAL); return MAP_KEYWORD; }
  "{"                  { yybegin(YYINITIAL); return BRACE_OPEN; }
  "}"                  { yybegin(YYINITIAL); return BRACE_CLOSE; }
  "("                  { yybegin(YYINITIAL); return BRACKET_OPEN; }
  ")"                  { yybegin(YYINITIAL); return BRACKET_CLOSE; }
  "Entry "             { yybegin(YYINITIAL); return ENTRY_KEYWORD; }
  "Exit "              { yybegin(YYINITIAL); return EXIT_KEYWORD; }
  "["                  { yybegin(YYINITIAL); return GUARD_OPEN; }
  "]"                  { yybegin(YYINITIAL); return GUARD_CLOSE; }
  "%%"                 { yybegin(YYINITIAL); return MAP_SECTION_BOUND; }
  ":"                  { yybegin(YYINITIAL); return COLON; }
  ","                  { yybegin(YYINITIAL); return COMMA; }
  ";"                  { yybegin(YYINITIAL); return SEMICOLON; }
  "pop"                { yybegin(YYINITIAL); return POP_KEYWORD; }
  "push"               { yybegin(YYINITIAL); return PUSH_KEYWORD; }
  "="                  { yybegin(YYINITIAL); return ASSIGN_OP; }
  "nil"                { yybegin(YYINITIAL); return NIL_KEYWORD; }
  "/"                  { yybegin(YYINITIAL); return SLASH_SIGN; }

  {WORD}               { yybegin(YYINITIAL); return WORD; }
  {RAW_CODE}           { yybegin(YYINITIAL); return RAW_CODE; }
  {RAW_CODE_LINE}      { yybegin(YYINITIAL); return RAW_CODE_LINE; }
  {RAW_CODE_IN_PARAMS} { yybegin(YYINITIAL); return RAW_CODE_IN_PARAMS; }

  {LINE_COMMENT}       { yybegin(YYINITIAL); return LINE_COMMENT; }
  {BLOCK_COMMENT}      { yybegin(YYINITIAL); return BLOCK_COMMENT; }

  [^] { return TokenType.BAD_CHARACTER; }
}