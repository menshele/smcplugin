package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.smcplugin.util.SmcStringUtils;
import static com.smcplugin.psi.SmcTypes.*;
import static com.smcplugin.util.SmcStringUtils.*;

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

TWO_SIGN_EOL="\r\n"
ONE_SIGN_EOL="\r"|"\n"
EOL={ONE_SIGN_EOL}|{TWO_SIGN_EOL}
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+
VERBATIM_CODE=(.*|{EOL})*
NOT_BRACKET=([^\[\]])*
WORD=[A-Za-z][A-Za-z0-9_.]*
WORD_IN_ARGUMENTS=\"(\\['\"tnbfru01234567]|[^\"\n\r])*?\"
CONTEXT_CLASS_NAME={WORD_NOT_DOT}
FSM_CLASS={WORD}
FSM_FILE={WORD}
HEADER_FILE_NAME={WORD}
DECLARE_STATEMENT={WORD}
INCLUDE_FILE_NAME={WORD}
PARAMETER_TYPE=[A-Za-z][A-Za-z0-9_ ]*
MAP_NAME={WORD}
MAP_NAME_AND_SEPARATOR={MAP_NAME}{WHITE_SPACE}*{MAP_STATE_SEPARATOR}
STATE_NAME={WORD}
CALLBACK_TRANSITION_NAME={WORD}
START_STATE_NAME={WORD}
ACCESS_LEVEL="public"|"protected"|"private"
WORD_NOT_DOT=[A-Za-z][A-Za-z0-9_]*
IMPORT_CLASS_NAME={WORD_NOT_DOT}|\*
IDENTIFIER={WORD_NOT_DOT}|\*
DOT="."

LINE_COMMENT="//".*

BLOCK_COMMENT_OPEN="/*"
BLOCK_COMMENT_CLOSE="*/"
BLOCK_COMMENT_CONTENT=( [^*] | \*+ [^/*] )*
VERBATIM_OPEN="%{"
VERBATIM_CLOSE="%}"
VERBATIM_CODE=( [^%] | %+ [^}%] )*
STATIC_KEYWORD="static"
MAP_STATE_SEPARATOR="::"
MAP_SECTION_BOUND="%%"
ENTRY_KEYWORD="Entry"
EXIT_KEYWORD="Exit"
BRACE_OPEN="{"
BRACE_CLOSE="}"
PARENTHESES_OPEN="("
PARENTHESES_CLOSE=")"
COMMA=","
SEMICOLON=";"
COLON=":"
SLASH_SIGN="/"
BRACKET_OPEN="["
BRACKET_CLOSE="]"
PUSH_PROXY_STATE_START={WORD}({WHITE_SPACE}*){SLASH_SIGN}({WHITE_SPACE}*){PUSH_KEYWORD}
PUSH_PROXY_STATE={WORD}
PUSH_KEYWORD="push"
POP_KEYWORD="pop"

NIL_KEYWORD="nil"
CLASS_KEYWORD="%class"
PACKAGE_KEYWORD="%package"
FSM_CLASS_KEYWORD="%fsmclass"
FSM_FILE_KEYWORD="%fsmfile"
ACCESS_KEYWORD="%access"
HEADER_KEYWORD="%header"
DECLARE_KEYWORD="%declare"
IMPORT_KEYWORD="%import"
INCLUDE_KEYWORD="%include"
START_KEYWORD="%start"
MAP_KEYWORD="%map"
EXTENDS_KEYWORD="extends"
SUPER_KEYWORD="super"

ANGLE_OPEN="<"
ANGLE_CLOSE=">"
QUESTION_MARK="?"
TYPE_PARAMETER={WORD_NOT_DOT}


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
%state INSIDE_GUARD_BRACKET
%state WAITING_FOR_NEXT_STATE_NAME
%state WAITING_FOR_ACTIONS
%state IN_BLOCK_COMMENT
%state WAITING_FOR_POP
%state WAITING_FOR_PUSH
%state WAITING_FOR_PUSH_STATE_NAME
%state WAITING_FOR_PUSH_MAP_NAME
%state WAITING_FOR_PROXY_STATE
%state WAITING_FOR_CONTEXT_CLASS_NAME
%state WAITING_FOR_IMPORT_CLASS_NAME
%state WAITING_FOR_QUALIFIED_NAME
%state WAITING_FOR_QUALIFIED_IDENTIFIER
%state WAITING_FOR_PARAMETER_GENERIC
%state WAITING_FOR_PARAMETER_GENERIC_TYPE
%state WAITING_FOR_PARAMETER_GENERIC_KEYWORD

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return com.intellij.psi.TokenType.WHITE_SPACE; }
  {VERBATIM_OPEN}             { yybegin(WAITING_FOR_VERBATIM_CODE);  return VERBATIM_OPEN; }
  {PACKAGE_KEYWORD}           { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return PACKAGE_KEYWORD; }
  {IMPORT_KEYWORD}            { yybegin(WAITING_FOR_IMPORT_CLASS); return IMPORT_KEYWORD; }
  {CLASS_KEYWORD}             { yybegin(WAITING_FOR_CONTEXT_CLASS); return CLASS_KEYWORD; }
  {FSM_CLASS_KEYWORD}         { yybegin(WAITING_FOR_FSM_CLASS); return FSM_CLASS_KEYWORD; }
  {FSM_FILE_KEYWORD}          { yybegin(WAITING_FOR_FSM_FILE); return FSM_FILE_KEYWORD; }
  {ACCESS_KEYWORD}            { yybegin(WAITING_FOR_ACCESS); return ACCESS_KEYWORD; }
  {HEADER_KEYWORD}            { yybegin(WAITING_FOR_HEADER); return HEADER_KEYWORD; }
  {DECLARE_KEYWORD}           { yybegin(WAITING_FOR_DECLARE); return DECLARE_KEYWORD; }
  {INCLUDE_KEYWORD}           { yybegin(WAITING_FOR_INCLUDE); return INCLUDE_KEYWORD; }
  {START_KEYWORD}             { yybegin(WAITING_FOR_START); return START_KEYWORD; }
  {MAP_KEYWORD}               { yybegin(WAITING_FOR_MAP); return MAP_KEYWORD; }

  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}

  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}


<IN_BLOCK_COMMENT>{
     {BLOCK_COMMENT_OPEN}     { yypopState(); return BLOCK_COMMENT_OPEN; }
     {BLOCK_COMMENT_CLOSE}    { yypopState(); return BLOCK_COMMENT_CLOSE; }
     {BLOCK_COMMENT_CONTENT}  { yybegin(IN_BLOCK_COMMENT); return BLOCK_COMMENT_CONTENT; }
     [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

//TODO: Need to implement proper Java Code Parsing instead of VERBATIM_CODE one day. http://cui.unige.ch/isi/bnf/JAVA/statement_block.html
<WAITING_FOR_VERBATIM_CODE>{
  {VERBATIM_OPEN}             { yybegin(YYINITIAL);  return VERBATIM_OPEN; }
  {VERBATIM_CLOSE}            { yybegin(YYINITIAL);  return VERBATIM_CLOSE; }
  {VERBATIM_CODE}             { yybegin(WAITING_FOR_VERBATIM_CODE); return VERBATIM_CODE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PACKAGE_STATEMENT>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {IDENTIFIER}                { yybegin(WAITING_FOR_QUALIFIED_NAME); yypushback(yylength()); }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_CONTEXT_CLASS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_CONTEXT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {IDENTIFIER}                { yybegin(WAITING_FOR_QUALIFIED_NAME); yypushback(yylength()); }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_CONTEXT_CLASS_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_CONTEXT_CLASS_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {CONTEXT_CLASS_NAME}        { yybegin(YYINITIAL); return CONTEXT_CLASS_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_IMPORT_CLASS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_IMPORT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {STATIC_KEYWORD}            { yybegin(WAITING_FOR_QUALIFIED_NAME); return STATIC_JAVA_KEYWORD; }
  {IDENTIFIER}                { yybegin(WAITING_FOR_QUALIFIED_NAME); yypushback(yylength()); }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_QUALIFIED_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {LINE_WS}                   { yybegin(WAITING_FOR_QUALIFIED_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {IDENTIFIER}                { yybegin(WAITING_FOR_QUALIFIED_NAME); return IDENTIFIER_NAME; }
  {DOT}                       { yybegin(WAITING_FOR_QUALIFIED_IDENTIFIER); return DOT; }
  {EOL}                       { yybegin(YYINITIAL); yypushback(yylength()); }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_QUALIFIED_IDENTIFIER>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {LINE_WS}                   { yybegin(WAITING_FOR_QUALIFIED_IDENTIFIER); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {IDENTIFIER}                { yybegin(WAITING_FOR_QUALIFIED_NAME); return IDENTIFIER_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}



<WAITING_FOR_IMPORT_CLASS_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_IMPORT_CLASS_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {IMPORT_CLASS_NAME}         { yybegin(YYINITIAL); return IMPORT_CLASS_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_FSM_CLASS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_FSM_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {FSM_CLASS}                 { yybegin(YYINITIAL); return FSM_CLASS_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_FSM_FILE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_FSM_FILE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {FSM_FILE}                  { yybegin(YYINITIAL); return FSM_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_ACCESS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ACCESS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {ACCESS_LEVEL}              { yybegin(YYINITIAL); return ACCESS_LEVEL; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_HEADER>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_HEADER); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {HEADER_FILE_NAME}          { yybegin(YYINITIAL); return HEADER_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_DECLARE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_DECLARE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {DECLARE_STATEMENT}         { yybegin(YYINITIAL); return DECLARE_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_INCLUDE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_INCLUDE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {INCLUDE_FILE_NAME}         { yybegin(YYINITIAL); return INCLUDE_FILE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_START>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_START); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WORD}                      { yybegin(WAITING_FOR_START_STATE_NAME); return START_MAP_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_START_STATE_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_START_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {MAP_STATE_SEPARATOR}       { yybegin(WAITING_FOR_START_STATE_NAME); return MAP_NAME_STATE_NAME_SEPARATOR; }
  {START_STATE_NAME}          { yybegin(YYINITIAL); return START_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_MAP>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_MAP); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {MAP_SECTION_BOUND}         { yybegin(WAITING_FOR_STATE); return MAP_SECTION_BOUND; }
  {MAP_NAME}                  { yybegin(WAITING_FOR_MAP); return MAP_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_STATE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {ENTRY_KEYWORD}             { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return ENTRY_KEYWORD; }
  {EXIT_KEYWORD}              { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return EXIT_KEYWORD; }
  {BRACE_OPEN}                { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_OPEN; }
  {MAP_SECTION_BOUND}         { yybegin(YYINITIAL); return MAP_SECTION_BOUND; }
  {STATE_NAME}                { yybegin(WAITING_FOR_STATE); return STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_STATE_ENTRY_EXIT>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {BRACE_OPEN}                { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return BRACE_OPEN; }
  {BRACE_CLOSE}               { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ENTRY_EXIT_ACTIONS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {PARENTHESES_OPEN}          { yypushState(WAITING_FOR_ARGUMENTS);  return PARENTHESES_OPEN; }
  {SEMICOLON}                 { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return SEMICOLON; }
  {BRACE_CLOSE}               { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return ACTION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_TRANSITIONS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_TRANSITIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {BRACE_CLOSE}               { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_NEXT_STATE_NAME); return TRANSITION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PARAMETER_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {COLON}                     { yybegin(WAITING_FOR_PARAMETER_TYPE); return COLON; }
  {WORD}                      { yybegin(WAITING_FOR_PARAMETER_NAME); return PARAMETER_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PARAMETER_TYPE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_TYPE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {PARENTHESES_CLOSE}         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE; }
  {COMMA}                     { yybegin(WAITING_FOR_PARAMETER_NAME); return COMMA; }
  {ANGLE_CLOSE}               { yybegin(WAITING_FOR_PARAMETER_TYPE); return ANGLE_CLOSE; }
  {PARAMETER_TYPE}            { yybegin(WAITING_FOR_PARAMETER_GENERIC); return PARAMETER_TYPE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_PARAMETER_GENERIC>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_GENERIC); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {ANGLE_OPEN}                { yybegin(WAITING_FOR_PARAMETER_GENERIC_TYPE); return ANGLE_OPEN; }
  {ANGLE_CLOSE}               { yybegin(WAITING_FOR_PARAMETER_GENERIC); return ANGLE_CLOSE; }
  {COMMA}                     { yybegin(WAITING_FOR_PARAMETER_NAME); return COMMA; }
  {PARENTHESES_CLOSE}         { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PARAMETER_GENERIC_TYPE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_GENERIC_TYPE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {QUESTION_MARK}             { yybegin(WAITING_FOR_PARAMETER_GENERIC_KEYWORD); return QUESTION_MARK; }
  {TYPE_PARAMETER}            { yybegin(WAITING_FOR_PARAMETER_TYPE); return PARAMETER_TYPE_NAME;}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PARAMETER_GENERIC_KEYWORD>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PARAMETER_GENERIC_KEYWORD); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {EXTENDS_KEYWORD}           { yybegin(WAITING_FOR_PARAMETER_TYPE); return EXTENDS_KEYWORD; }
  {SUPER_KEYWORD}             { yybegin(WAITING_FOR_PARAMETER_TYPE); return SUPER_KEYWORD; }
  {ANGLE_CLOSE}               { yybegin(WAITING_FOR_PARAMETER_TYPE); return ANGLE_CLOSE; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_NEXT_STATE_NAME>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_NEXT_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {PARENTHESES_OPEN}          { yybegin(WAITING_FOR_PARAMETER_NAME);  return PARENTHESES_OPEN; }
  {BRACKET_OPEN}              { yybegin(WAITING_FOR_GUARD_RAW_CODE); return BRACKET_OPEN; }
  {BRACKET_CLOSE}             { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE; }
  {BRACE_OPEN}                { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN; }
  {BRACE_CLOSE}               { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE; }
  {PUSH_PROXY_STATE_START}    { yybegin(WAITING_FOR_PROXY_STATE); yypushback(yylength());  }
  {NIL_KEYWORD}               { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NIL_KEYWORD; }
  {POP_KEYWORD}               { yybegin(WAITING_FOR_POP); return POP_KEYWORD; }
  {PUSH_KEYWORD}              { yybegin(WAITING_FOR_PUSH); yypushback(yylength()); }
  {STATE_NAME}                { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NEXT_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_POP>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}               { yybegin(WAITING_FOR_POP); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PARENTHESES_OPEN}          { yybegin(WAITING_FOR_POP); return PARENTHESES_OPEN; }
  {PARENTHESES_CLOSE}         { yybegin(WAITING_FOR_POP); return PARENTHESES_CLOSE; }
  {COMMA}                     { yypushState(WAITING_FOR_ARGUMENTS); return COMMA; }
  {BRACE_OPEN}                { yybegin(WAITING_FOR_NEXT_STATE_NAME); yypushback(1); }
  {CALLBACK_TRANSITION_NAME}  { yybegin(WAITING_FOR_POP); return CALLBACK_TRANSITION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PUSH>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PUSH); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PUSH_KEYWORD}              { yybegin(WAITING_FOR_PUSH); return PUSH_KEYWORD; }
  {SLASH_SIGN}                { yybegin(WAITING_FOR_PUSH); return PUSH_PROXY_STATE_KEYWORD_SEPARATOR; }
  {NIL_KEYWORD}               { yybegin(WAITING_FOR_PUSH); return NIL_KEYWORD; }
  {PARENTHESES_OPEN}          { yybegin(WAITING_FOR_PUSH_STATE_NAME); return PARENTHESES_OPEN; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PROXY_STATE>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}               { yybegin(WAITING_FOR_PROXY_STATE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PUSH_PROXY_STATE}          { yybegin(WAITING_FOR_PUSH); return PUSH_PROXY_STATE_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PUSH_STATE_NAME>{
  {LINE_COMMENT}                  { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}            { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}                   { yybegin(WAITING_FOR_PUSH_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PARENTHESES_CLOSE}             { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE; }
  {NIL_KEYWORD}                   { yybegin(WAITING_FOR_PUSH_STATE_NAME); return NIL_KEYWORD; }
  {MAP_NAME_AND_SEPARATOR}        { yybegin(WAITING_FOR_PUSH_MAP_NAME); yypushback(yylength()); }
  {STATE_NAME}                    { yybegin(WAITING_FOR_PUSH_STATE_NAME); return PUSH_STATE_NAME;}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_PUSH_MAP_NAME>{
  {LINE_COMMENT}                  { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}            { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}                   { yybegin(WAITING_FOR_PUSH_MAP_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {MAP_STATE_SEPARATOR}           { yybegin(WAITING_FOR_PUSH_STATE_NAME); return MAP_NAME_STATE_NAME_SEPARATOR; }
  {MAP_NAME}                      { yybegin(WAITING_FOR_PUSH_MAP_NAME); return PUSH_MAP_NAME;}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

//TODO: Need to implement proper Java Code Parsing one day. http://cui.unige.ch/isi/bnf/JAVA/expression.html
<WAITING_FOR_GUARD_RAW_CODE>{
  {WHITE_SPACE}                   { yybegin(WAITING_FOR_GUARD_RAW_CODE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BRACKET_OPEN}                  { yypushState(INSIDE_GUARD_BRACKET); return GUARD_BRACKET_OPEN;}
  {BRACKET_CLOSE}                 { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE;}
  {NOT_BRACKET}                   { yybegin(WAITING_FOR_GUARD_RAW_CODE); return GUARD_NOT_BRACKET; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<INSIDE_GUARD_BRACKET>{
  {WHITE_SPACE}                   { yybegin(INSIDE_GUARD_BRACKET); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BRACKET_OPEN}                  { yypushState(INSIDE_GUARD_BRACKET); return GUARD_BRACKET_OPEN;}
  {BRACKET_CLOSE}                 { yypopState(); return GUARD_BRACKET_CLOSE;}
  {NOT_BRACKET}                   { yybegin(INSIDE_GUARD_BRACKET); return GUARD_NOT_BRACKET; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ACTIONS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {PARENTHESES_OPEN}          { yypushState(WAITING_FOR_ARGUMENTS);  return PARENTHESES_OPEN; }
  {SEMICOLON}                 { yybegin(WAITING_FOR_ACTIONS); return SEMICOLON; }
  {BRACE_CLOSE}               { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ACTIONS); return ACTION_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<WAITING_FOR_ARGUMENTS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_ARGUMENTS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {COMMA}                     { yybegin(WAITING_FOR_ARGUMENTS); return COMMA; }
  {PARENTHESES_CLOSE}         { yypopState(); return PARENTHESES_CLOSE; }
  {WORD}                      { yybegin(WAITING_FOR_ARGUMENTS); return ARGUMENT_STATEMENT;}
  {WORD_IN_ARGUMENTS}         { yybegin(WAITING_FOR_ARGUMENTS); return validateJavaString(yytext());}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
