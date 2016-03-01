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
GUARD_RAW_CODE=([^\]] | ]+ [^\n\r\]])*
WORD=[A-Za-z][A-Za-z0-9_.]*
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
CALLBACK_TRANSITION_NAME={WORD}
START_STATE_NAME={WORD}|{WORD}{EOL}
ACCESS_LEVEL="public"|"protected"|"private"
IMPORT_STATEMENT=[A-Za-z][A-Za-z0-9_.\*]*| [A-Za-z][A-Za-z0-9_.\*]*{EOL}

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
PUSH_PROXY_STATE_START={WORD}{SLASH_SIGN}
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
%state IN_BLOCK_COMMENT
%state WAITING_FOR_POP
%state WAITING_FOR_PUSH
%state WAITING_FOR_PUSH_STATE_NAME

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
  {PACKAGE_STATEMENT}         { yybegin(YYINITIAL); return PACKAGE_STATEMENT; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_CONTEXT_CLASS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_CONTEXT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {CONTEXT_CLASS_NAME}        { yybegin(YYINITIAL); return CONTEXT_CLASS_NAME; }
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}
<WAITING_FOR_IMPORT_CLASS>{
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {WHITE_SPACE}               { yybegin(WAITING_FOR_IMPORT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BLOCK_COMMENT_OPEN}        { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {STATIC_KEYWORD}            { yybegin(WAITING_FOR_IMPORT_CLASS); return STATIC_JAVA_KEYWORD; }
  {IMPORT_STATEMENT}          { yybegin(YYINITIAL); return IMPORT_CLASS_STATEMENT; }
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
  {MAP_SECTION_BOUND}         { yybegin(WAITING_FOR_MAP); return MAP_SECTION_BOUND; }
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
  {WORD}                      { yybegin(WAITING_FOR_PARAMETER_TYPE); return PARAMETER_TYPE; }
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
  {PUSH_PROXY_STATE_START}    { yybegin(WAITING_FOR_PUSH); yypushback(1); return PUSH_PROXY_STATE_NAME; }
  {NIL_KEYWORD}               { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NIL_KEYWORD; }
  {POP_KEYWORD}               { yybegin(WAITING_FOR_POP); return POP_KEYWORD; }
  {PUSH_KEYWORD}               { yybegin(WAITING_FOR_PUSH); return PUSH_KEYWORD; }
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

<WAITING_FOR_PUSH_STATE_NAME>{
  {LINE_COMMENT}                  { return LINE_COMMENT; }
  {BLOCK_COMMENT_OPEN}            { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;}
  {WHITE_SPACE}                   { yybegin(WAITING_FOR_PUSH_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {PARENTHESES_CLOSE}             { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE; }
  {NIL_KEYWORD}                   { yybegin(WAITING_FOR_PUSH_STATE_NAME); return NIL_KEYWORD; }
  {MAP_STATE_SEPARATOR}           { yybegin(WAITING_FOR_PUSH_STATE_NAME); return MAP_NAME_STATE_NAME_SEPARATOR; }
  {MAP_NAME}{MAP_STATE_SEPARATOR} { yybegin(WAITING_FOR_PUSH_STATE_NAME); yypushback(2); return PUSH_MAP_NAME;}
  {STATE_NAME}                    { yybegin(WAITING_FOR_PUSH_STATE_NAME); return PUSH_STATE_NAME;}
  [^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

//TODO: Need to implement proper Java Code Parsing one day. http://cui.unige.ch/isi/bnf/JAVA/expression.html
<WAITING_FOR_GUARD_RAW_CODE>{
  {LINE_COMMENT}                       { return LINE_COMMENT; }
  {WHITE_SPACE}                        { yybegin(WAITING_FOR_GUARD_RAW_CODE); return com.intellij.psi.TokenType.WHITE_SPACE; }
  {BRACKET_CLOSE}                      { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE;}
  {GUARD_RAW_CODE}                     { yybegin(WAITING_FOR_GUARD_RAW_CODE); return GUARD_RAW_CODE; }
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
