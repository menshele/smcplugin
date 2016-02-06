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
RAW_CODE_LINE=.*\n\r\f
RAW_CODE=%\{(.|\n)*%\}
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"

FIRST_RAW_CHARACTER=[^ \n\r\f\\] | "\\"{CRLF} | "\\".
RAW_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".


%%
<YYINITIAL> {
  {WHITE_SPACE}        { return TokenType.WHITE_SPACE; }

  "%{"                 { return VERBATIM_OPEN; }
  "%}"                 { return VERBATIM_CLOSE; }
  "%class"             { return CLASS_KEYWORD; }
  "%package"           { return PACKAGE_KEYWORD; }
  "%fsmclass"          { return FSM_CLASS_KEYWORD; }
  "%fsmfile"           { return FSM_FILE_KEYWORD; }
  "%access"            { return ACCESS_KEYWORD; }
  "%header"            { return HEADER_KEYWORD; }
  "%declare"           { return DECLARE_KEYWORD; }
  "%import"            { return IMPORT_KEYWORD; }
  "%include"           { return INCLUDE_KEYWORD; }
  "%start"             { return START_KEYWORD; }
  "%map"               { return MAP_KEYWORD; }
  "{"                  { return BRACE_OPEN; }
  "}"                  { return BRACE_CLOSE; }
  "("                  { return BRACKET_OPEN; }
  ")"                  { return BRACKET_CLOSE; }
  "Entry "             { return ENTRY_KEYWORD; }
  "Exit "              { return EXIT_KEYWORD; }
  "["                  { return GUARD_OPEN; }
  "]"                  { return GUARD_CLOSE; }
  "%%"                 { return MAP_SECTION_BOUND; }
  ":"                  { return COLON; }
  ","                  { return COMMA; }
  ";"                  { return SEMICOLON; }
  "pop"                { return POP_KEYWORD; }
  "push"               { return PUSH_KEYWORD; }
  "="                  { return ASSIGN_OP; }
  "nil"                { return NIL_KEYWORD; }
  "/"                  { return SLASH_SIGN; }

  {CRLF}               { return CRLF; }
  {WORD}               { return WORD; }
  {RAW_CODE}           { return RAW_CODE; }
  {RAW_CODE_LINE}      { return RAW_CODE_LINE; }

  {LINE_COMMENT}       { return LINE_COMMENT; }
  {BLOCK_COMMENT}      { return BLOCK_COMMENT; }

  [^] { return TokenType.BAD_CHARACTER; }
}