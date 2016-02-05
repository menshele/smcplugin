package com.smcplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.smcplugin.psi.SmcTypes;
import com.intellij.psi.TokenType;

%%

%class SmcLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
WORD = [A-Za-z][A-Za-z0-9_.]* | _[A-Za-z][A-Za-z0-9_.]*
RAW_CODE=.*
RAW_CODE_LINE=.* '\n\r\f'

%state WAITING_VALUE

%%

<YYINITIAL> {WORD}                                          { yybegin(YYINITIAL); return SmcTypes.WORD; }

<WAITING_VALUE> {WORD}                                      { yybegin(YYINITIAL); return SmcTypes.WORD; }

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return SmcTypes.LINE_COMMENT; }

<YYINITIAL> {RAW_CODE_LINE}                                 { yybegin(YYINITIAL); return SmcTypes.RAW_CODE_LINE; }

<YYINITIAL> {RAW_CODE}                                      { yybegin(YYINITIAL); return SmcTypes.RAW_CODE; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

{WHITE_SPACE}+                                              { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

.                                                           { return TokenType.BAD_CHARACTER; }