/* The following code was generated by JFlex 1.4.3 on 21.02.16 20:01 */

package com.smcplugin;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.smcplugin.psi.SmcTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 21.02.16 20:01 from the specification file
 * <tt>C:/learn/git/scmplugin/src/com/smcplugin/SmcLexer.flex</tt>
 */
public class SmcLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int WAITING_FOR_NEXT_STATE_NAME = 48;
  public static final int WAITING_FOR_START_STATE_NAME = 24;
  public static final int WAITING_FOR_HEADER = 16;
  public static final int WAITING_FOR_FSM_FILE = 12;
  public static final int WAITING_FOR_ACCESS = 14;
  public static final int WAITING_FOR_ACTIONS = 50;
  public static final int WAITING_FOR_INCLUDE = 20;
  public static final int IN_BLOCK_COMMENT = 52;
  public static final int WAITING_FOR_ARGUMENTS = 30;
  public static final int WAITING_FOR_GUARD_RAW_CODE = 46;
  public static final int WAITING_FOR_ENTRY_EXIT_ACTIONS = 36;
  public static final int WAITING_FOR_MAP = 26;
  public static final int WAITING_FOR_CONTEXT_CLASS = 6;
  public static final int WAITING_FOR_PACKAGE_STATEMENT = 4;
  public static final int WAITING_FOR_IMPORT_CLASS = 8;
  public static final int WAITING_FOR_START = 22;
  public static final int WAITING_FOR_STATE = 28;
  public static final int WAITING_FOR_DECLARE = 18;
  public static final int WAITING_FOR_STATE_ENTRY_EXIT = 32;
  public static final int WAITING_FOR_TRANSITIONS = 38;
  public static final int WAITING_FOR_PARAMETER_NAME = 44;
  public static final int WAITING_FOR_STATE_EXIT = 34;
  public static final int YYINITIAL = 0;
  public static final int WAITING_FOR_PARAMETERS = 40;
  public static final int WAITING_FOR_FSM_CLASS = 10;
  public static final int WAITING_FOR_VERBATIM_CODE = 2;
  public static final int WAITING_FOR_PARAMETER_TYPE = 42;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7, 
     8,  8,  9,  9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 
    16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 
    24, 24, 25, 25, 26, 26
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\0\1\6"+
    "\2\0\1\27\2\0\1\37\1\40\1\24\1\0\1\41\1\0\1\5"+
    "\1\26\12\5\1\33\1\42\5\0\4\4\1\34\25\4\1\43\1\0"+
    "\1\44\1\0\1\5\1\0\1\23\1\11\1\14\1\21\1\20\1\47"+
    "\1\46\1\51\1\13\1\4\1\45\1\12\1\50\1\25\1\16\1\7"+
    "\1\4\1\15\1\32\1\17\1\10\1\22\1\4\1\36\1\35\1\4"+
    "\1\30\1\0\1\31\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\30\0\1\2\1\3\1\4\2\3\1\1"+
    "\1\3\1\5\1\6\1\7\1\10\1\11\2\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\3\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\3"+
    "\1\32\1\33\1\3\1\34\1\35\1\3\1\36\1\35"+
    "\1\37\1\40\1\3\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\47\1\50\1\51\1\52\1\53\1\54\2\55"+
    "\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65"+
    "\1\66\1\67\1\3\1\70\1\3\1\71\1\72\2\73"+
    "\1\74\1\75\1\76\1\77\1\100\1\2\1\3\1\2"+
    "\1\101\1\102\5\0\1\103\5\0\1\104\1\105\2\6"+
    "\2\10\3\12\2\14\2\16\2\0\2\21\2\23\2\25"+
    "\2\27\2\31\1\106\2\33\1\107\2\35\1\110\2\35"+
    "\2\40\1\0\1\111\2\47\2\53\3\55\2\62\2\66"+
    "\1\0\1\102\3\73\2\76\1\0\1\112\1\113\12\0"+
    "\1\12\3\0\2\31\2\35\2\114\10\0\1\115\1\0"+
    "\1\12\3\0\1\35\1\116\12\0\1\12\3\0\1\117"+
    "\3\0\1\120\2\0\1\121\3\0\1\122\1\123\4\0"+
    "\1\124\1\0\1\125\2\0\1\126\1\0\1\127\1\130"+
    "\1\131\1\0\1\132\1\0\1\133";

  private static int [] zzUnpackAction() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\u0126"+
    "\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\u0222\0\u024c\0\u0276"+
    "\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348\0\u0372\0\u039c\0\u03c6"+
    "\0\u03f0\0\u041a\0\u0444\0\u02ca\0\u046e\0\u0498\0\u04c2\0\u04ec"+
    "\0\u0516\0\u0540\0\u056a\0\u0594\0\u05be\0\u05e8\0\u0612\0\u063c"+
    "\0\u0666\0\u0690\0\u06ba\0\u06e4\0\u070e\0\u0738\0\u0762\0\u078c"+
    "\0\u07b6\0\u07e0\0\u080a\0\u0834\0\u085e\0\u0888\0\u08b2\0\u08dc"+
    "\0\u0906\0\u0930\0\u095a\0\u0984\0\u09ae\0\u09d8\0\u0a02\0\u02ca"+
    "\0\u0a2c\0\u0a56\0\u0a80\0\u0aaa\0\u02ca\0\u02ca\0\u0ad4\0\u02ca"+
    "\0\u02ca\0\u0afe\0\u0b28\0\u02ca\0\u02ca\0\u0b52\0\u0b7c\0\u0ba6"+
    "\0\u0bd0\0\u0bfa\0\u02ca\0\u02ca\0\u02ca\0\u0c24\0\u0c4e\0\u02ca"+
    "\0\u02ca\0\u0c78\0\u0ca2\0\u02ca\0\u0ccc\0\u0cf6\0\u0d20\0\u0ccc"+
    "\0\u0d4a\0\u0d74\0\u0d9e\0\u02ca\0\u0dc8\0\u0df2\0\u02ca\0\u02ca"+
    "\0\u0e1c\0\u0e46\0\u0e70\0\u02ca\0\u0e9a\0\u0ec4\0\u0eee\0\u0f18"+
    "\0\u0f42\0\u0f6c\0\u02ca\0\u0f96\0\u0fc0\0\u0fea\0\u1014\0\u103e"+
    "\0\u04ec\0\u02ca\0\u1068\0\u02ca\0\u1092\0\u02ca\0\u10bc\0\u02ca"+
    "\0\u10e6\0\u1110\0\u02ca\0\u113a\0\u02ca\0\u1164\0\u118e\0\u11b8"+
    "\0\u02ca\0\u11e2\0\u02ca\0\u120c\0\u02ca\0\u1236\0\u02ca\0\u1260"+
    "\0\u128a\0\u02ca\0\u12b4\0\u02ca\0\u02ca\0\u12de\0\u02ca\0\u02ca"+
    "\0\u1308\0\u1332\0\u135c\0\u02ca\0\u0aaa\0\u0aaa\0\u1386\0\u02ca"+
    "\0\u13b0\0\u02ca\0\u13da\0\u02ca\0\u1404\0\u142e\0\u02ca\0\u1458"+
    "\0\u02ca\0\u0ccc\0\u1482\0\u14ac\0\u02ca\0\u14d6\0\u1500\0\u02ca"+
    "\0\u152a\0\u02ca\0\u152a\0\u1554\0\u157e\0\u15a8\0\u15d2\0\u15fc"+
    "\0\u1626\0\u1650\0\u167a\0\u16a4\0\u16ce\0\u16f8\0\u1722\0\u174c"+
    "\0\u1776\0\u17a0\0\u02ca\0\u17ca\0\u17f4\0\u0bd0\0\u0d74\0\u181e"+
    "\0\u1848\0\u1872\0\u189c\0\u18c6\0\u18f0\0\u191a\0\u1944\0\u02ca"+
    "\0\u196e\0\u1998\0\u19c2\0\u19ec\0\u1a16\0\u1a40\0\u09d8\0\u1a6a"+
    "\0\u1a94\0\u1abe\0\u1ae8\0\u1b12\0\u1b3c\0\u1b66\0\u1b90\0\u1bba"+
    "\0\u1be4\0\u1c0e\0\u1c38\0\u1c62\0\u1c8c\0\u09d8\0\u1cb6\0\u1ce0"+
    "\0\u1d0a\0\u02ca\0\u1d34\0\u1d5e\0\u02ca\0\u1d88\0\u1db2\0\u1ddc"+
    "\0\u0612\0\u02ca\0\u1e06\0\u1e30\0\u1e5a\0\u1e84\0\u02ca\0\u1eae"+
    "\0\u02ca\0\u1ed8\0\u1f02\0\u02ca\0\u1f2c\0\u02ca\0\u02ca\0\u02ca"+
    "\0\u1f56\0\u02ca\0\u1f80\0\u02ca";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\34\3\35\22\34\1\36\1\37\22\34\27\40\1\41"+
    "\22\40\1\34\3\42\1\43\2\34\15\43\1\34\1\43"+
    "\1\36\3\34\1\43\1\34\3\43\6\34\5\43\1\34"+
    "\3\44\1\45\2\34\15\45\1\34\1\45\1\36\3\34"+
    "\1\45\1\34\3\45\6\34\5\45\1\34\3\46\1\47"+
    "\2\34\15\47\1\34\1\47\1\36\3\34\1\50\1\34"+
    "\3\47\6\34\5\47\1\34\3\51\1\52\2\34\15\52"+
    "\1\34\1\52\1\36\3\34\1\52\1\34\3\52\6\34"+
    "\5\52\1\34\3\53\1\54\2\34\15\54\1\34\1\54"+
    "\1\36\3\34\1\54\1\34\3\54\6\34\5\54\1\34"+
    "\3\55\3\34\1\56\16\34\1\36\24\34\3\57\1\60"+
    "\2\34\15\60\1\34\1\60\1\36\3\34\1\60\1\34"+
    "\3\60\6\34\5\60\1\34\3\61\1\62\2\34\15\62"+
    "\1\34\1\62\1\36\3\34\1\62\1\34\3\62\6\34"+
    "\5\62\1\34\3\63\1\64\2\34\15\64\1\34\1\64"+
    "\1\36\3\34\1\64\1\34\3\64\6\34\5\64\1\34"+
    "\3\65\1\66\2\34\15\66\1\34\1\66\1\36\3\34"+
    "\1\66\1\34\3\66\6\34\5\66\1\34\3\67\1\70"+
    "\2\34\15\70\1\34\1\70\1\36\3\34\1\70\1\71"+
    "\3\70\6\34\5\70\1\34\3\72\1\73\2\34\15\73"+
    "\1\34\1\73\1\36\1\74\2\34\1\73\1\34\3\73"+
    "\6\34\5\73\1\34\3\75\1\76\2\34\15\76\1\34"+
    "\1\76\1\36\1\77\1\100\1\34\1\76\1\34\1\101"+
    "\2\76\6\34\5\76\1\34\3\102\1\103\1\34\1\104"+
    "\15\103\1\34\1\103\1\36\3\34\1\103\1\34\3\103"+
    "\1\34\1\105\1\106\3\34\5\103\1\34\3\107\22\34"+
    "\1\36\1\34\1\110\1\111\20\34\52\0\1\34\3\112"+
    "\1\113\2\34\15\113\1\34\1\113\1\36\2\34\1\111"+
    "\1\113\1\34\3\113\1\114\2\34\1\115\2\34\5\113"+
    "\1\34\3\116\1\117\2\34\15\117\1\34\1\117\1\36"+
    "\2\34\1\111\1\117\1\34\3\117\6\34\5\117\1\34"+
    "\3\120\1\121\2\34\15\121\1\34\1\122\1\36\1\34"+
    "\1\123\1\111\1\121\1\34\3\121\1\124\3\34\1\125"+
    "\1\34\5\121\1\34\3\126\1\127\2\34\15\127\1\34"+
    "\1\127\1\36\3\34\1\127\1\34\3\127\1\34\1\130"+
    "\1\131\3\34\5\127\1\34\3\132\1\133\2\34\15\133"+
    "\1\34\1\133\1\36\3\34\1\133\1\134\3\133\6\34"+
    "\5\133\1\135\3\136\22\135\1\137\15\135\1\140\5\135"+
    "\1\34\3\141\1\142\2\34\15\142\1\34\1\143\1\36"+
    "\1\34\1\123\1\34\1\142\1\34\3\142\4\34\1\125"+
    "\1\144\5\142\1\34\3\145\1\146\2\34\15\146\1\34"+
    "\1\146\1\36\2\34\1\147\1\146\1\34\3\146\1\114"+
    "\2\34\1\150\2\34\5\146\24\151\1\152\1\151\1\153"+
    "\23\151\1\0\3\35\72\0\1\154\1\0\1\155\32\0"+
    "\1\156\3\0\1\157\1\160\4\0\1\161\1\0\1\162"+
    "\4\0\1\163\1\0\1\164\14\0\1\165\1\166\1\167"+
    "\27\40\1\170\51\40\1\170\1\171\1\172\20\40\1\0"+
    "\3\42\47\0\1\173\1\174\1\0\2\43\1\0\15\43"+
    "\1\0\1\43\4\0\1\43\1\0\3\43\6\0\5\43"+
    "\1\0\3\44\47\0\1\175\1\176\1\0\2\45\1\0"+
    "\15\45\1\0\1\45\4\0\1\45\1\0\3\45\6\0"+
    "\5\45\1\0\3\46\47\0\1\177\1\200\1\0\2\47"+
    "\1\0\17\47\4\0\1\47\1\0\3\47\6\0\5\47"+
    "\1\0\1\177\1\200\1\0\2\47\1\0\10\47\1\201"+
    "\6\47\4\0\1\47\1\0\3\47\6\0\5\47\1\0"+
    "\3\51\47\0\1\202\1\203\1\0\2\52\1\0\15\52"+
    "\1\0\1\52\4\0\1\52\1\0\3\52\6\0\5\52"+
    "\1\0\3\53\47\0\1\204\1\205\1\0\2\54\1\0"+
    "\15\54\1\0\1\54\4\0\1\54\1\0\3\54\6\0"+
    "\5\54\1\0\3\55\56\0\1\206\4\0\1\207\35\0"+
    "\3\57\47\0\1\210\1\211\1\0\2\60\1\0\15\60"+
    "\1\0\1\60\4\0\1\60\1\0\3\60\6\0\5\60"+
    "\1\0\3\61\47\0\1\212\1\213\1\0\2\62\1\0"+
    "\15\62\1\0\1\62\4\0\1\62\1\0\3\62\6\0"+
    "\5\62\1\0\3\63\47\0\1\214\1\215\1\0\2\64"+
    "\1\0\15\64\1\0\1\64\4\0\1\64\1\0\3\64"+
    "\6\0\5\64\1\0\3\65\47\0\1\216\1\217\1\0"+
    "\2\66\1\0\15\66\1\0\1\66\4\0\1\66\1\0"+
    "\3\66\6\0\5\66\1\0\3\67\47\0\1\220\1\221"+
    "\1\0\2\70\1\0\15\70\1\0\1\70\4\0\1\70"+
    "\1\0\3\70\6\0\5\70\33\0\1\222\17\0\3\72"+
    "\47\0\1\223\1\224\1\0\2\73\1\0\15\73\1\0"+
    "\1\73\4\0\1\73\1\0\3\73\6\0\5\73\27\0"+
    "\1\225\23\0\3\75\47\0\1\226\1\227\1\0\2\76"+
    "\1\0\15\76\1\0\1\76\4\0\1\76\1\0\3\76"+
    "\6\0\5\76\27\0\1\230\23\0\1\226\1\227\1\0"+
    "\2\76\1\0\15\76\1\0\1\231\4\0\1\76\1\0"+
    "\2\76\1\232\6\0\5\76\1\0\3\102\47\0\1\233"+
    "\1\234\1\0\2\103\1\0\15\103\1\0\1\103\4\0"+
    "\1\103\1\0\3\103\6\0\5\103\2\235\1\0\3\235"+
    "\1\236\43\235\1\0\3\107\47\0\3\112\47\0\1\237"+
    "\1\240\1\0\2\113\1\0\15\113\1\0\1\113\4\0"+
    "\1\113\1\0\3\113\6\0\5\113\1\0\3\116\47\0"+
    "\1\241\1\242\1\0\2\117\1\0\15\117\1\0\1\117"+
    "\4\0\1\117\1\0\3\117\6\0\5\117\1\0\3\120"+
    "\47\0\1\243\1\244\1\0\2\121\1\0\15\121\1\0"+
    "\1\121\4\0\1\121\1\0\3\121\6\0\5\121\1\0"+
    "\1\243\1\244\1\0\2\121\1\0\4\121\1\245\10\121"+
    "\1\0\1\121\4\0\1\121\1\0\3\121\6\0\5\121"+
    "\1\0\3\126\47\0\1\246\1\247\1\0\2\127\1\0"+
    "\15\127\1\0\1\127\4\0\1\127\1\0\3\127\6\0"+
    "\5\127\1\0\3\132\47\0\1\250\1\251\1\0\2\133"+
    "\1\0\15\133\1\0\1\133\4\0\1\133\1\0\3\133"+
    "\6\0\5\133\44\252\1\140\6\252\3\136\40\252\1\140"+
    "\33\252\1\253\15\252\1\140\5\252\1\0\3\141\47\0"+
    "\1\254\1\255\1\0\2\142\1\0\15\142\1\0\1\142"+
    "\4\0\1\142\1\0\3\142\6\0\5\142\1\0\1\254"+
    "\1\255\1\0\2\142\1\0\4\142\1\256\10\142\1\0"+
    "\1\142\4\0\1\142\1\0\3\142\6\0\5\142\1\0"+
    "\3\145\47\0\1\257\1\260\1\0\2\146\1\0\15\146"+
    "\1\0\1\146\4\0\1\146\1\0\3\146\6\0\5\146"+
    "\24\151\1\261\51\151\1\261\1\151\1\262\47\151\1\263"+
    "\25\151\2\155\1\0\47\155\23\0\1\264\53\0\1\265"+
    "\22\0\1\266\13\0\1\267\57\0\1\270\45\0\1\271"+
    "\54\0\1\272\64\0\1\273\42\0\1\274\46\0\1\275"+
    "\31\0\27\40\1\170\1\40\1\0\20\40\2\0\1\174"+
    "\51\0\1\176\51\0\1\200\50\0\1\177\1\200\1\0"+
    "\2\47\1\0\14\47\1\276\2\47\4\0\1\47\1\0"+
    "\3\47\6\0\5\47\2\0\1\203\51\0\1\205\60\0"+
    "\1\277\53\0\1\300\2\0\1\301\35\0\1\211\51\0"+
    "\1\213\51\0\1\215\51\0\1\217\50\0\1\302\1\221"+
    "\50\0\1\302\1\303\51\0\1\224\51\0\1\227\50\0"+
    "\1\226\1\227\1\0\2\76\1\0\10\76\1\304\4\76"+
    "\1\0\1\76\4\0\1\76\1\0\3\76\6\0\5\76"+
    "\1\0\1\226\1\227\1\0\2\76\1\0\4\76\1\305"+
    "\10\76\1\0\1\76\4\0\1\76\1\0\3\76\6\0"+
    "\5\76\2\0\1\234\51\0\1\240\51\0\1\242\51\0"+
    "\1\244\50\0\1\243\1\244\1\0\2\121\1\0\3\121"+
    "\1\306\11\121\1\0\1\121\4\0\1\121\1\0\3\121"+
    "\6\0\5\121\2\0\1\247\51\0\1\251\47\0\2\253"+
    "\1\252\47\253\2\0\1\255\50\0\1\254\1\255\1\0"+
    "\2\142\1\0\3\142\1\307\11\142\1\0\1\142\4\0"+
    "\1\142\1\0\3\142\6\0\5\142\2\0\1\260\47\0"+
    "\24\151\1\261\1\151\1\0\23\151\14\0\1\310\51\0"+
    "\1\311\44\0\1\312\65\0\1\313\42\0\1\314\51\0"+
    "\1\315\60\0\1\316\76\0\1\317\10\0\1\320\65\0"+
    "\1\321\27\0\1\177\1\200\1\0\2\47\1\0\10\47"+
    "\1\322\6\47\4\0\1\47\1\0\3\47\6\0\5\47"+
    "\12\0\1\323\61\0\1\324\46\0\1\325\34\0\1\303"+
    "\50\0\1\226\1\227\1\0\2\76\1\0\6\76\1\326"+
    "\6\76\1\0\1\76\4\0\1\76\1\0\3\76\6\0"+
    "\5\76\1\0\1\226\1\227\1\0\2\76\1\0\10\76"+
    "\1\327\4\76\1\0\1\76\4\0\1\76\1\0\3\76"+
    "\6\0\5\76\45\0\1\330\16\0\1\331\55\0\1\332"+
    "\65\0\1\333\31\0\1\334\57\0\1\335\46\0\1\336"+
    "\50\0\1\337\32\0\1\340\23\0\1\341\31\0\1\177"+
    "\1\200\1\0\2\47\1\0\4\47\1\342\12\47\4\0"+
    "\1\47\1\0\3\47\6\0\5\47\13\0\1\343\61\0"+
    "\1\344\46\0\1\345\32\0\1\226\1\227\1\0\2\76"+
    "\1\0\15\76\1\0\1\76\4\0\1\76\1\0\1\76"+
    "\1\346\1\76\6\0\5\76\23\0\1\347\36\0\1\350"+
    "\56\0\1\351\66\0\1\352\42\0\1\353\60\0\1\354"+
    "\36\0\1\355\44\0\1\356\52\0\1\357\56\0\1\360"+
    "\32\0\1\177\1\200\1\0\2\47\1\0\5\47\1\361"+
    "\11\47\4\0\1\47\1\0\3\47\6\0\5\47\14\0"+
    "\1\362\54\0\1\363\46\0\1\364\103\0\1\365\24\0"+
    "\1\366\47\0\1\367\47\0\1\370\66\0\1\371\42\0"+
    "\1\372\40\0\1\373\54\0\1\374\54\0\1\362\50\0"+
    "\1\375\52\0\1\376\51\0\1\377\51\0\1\u0100\63\0"+
    "\1\u0101\37\0\1\u0102\51\0\1\u0103\63\0\1\u0104\40\0"+
    "\1\362\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8106];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\1\17\0\1\10\10\0\1\1\1\11\43\1"+
    "\1\11\4\1\2\11\1\1\2\11\2\1\2\11\5\1"+
    "\3\11\2\1\2\11\2\1\1\11\7\1\1\11\2\1"+
    "\2\11\3\1\1\11\1\1\5\0\1\11\5\0\1\1"+
    "\1\11\1\1\1\11\1\1\1\11\1\1\1\11\2\1"+
    "\1\11\1\1\1\11\2\0\1\1\1\11\1\1\1\11"+
    "\1\1\1\11\1\1\1\11\2\1\1\11\1\1\2\11"+
    "\1\1\2\11\3\1\1\11\1\0\2\1\1\11\1\1"+
    "\1\11\1\1\1\11\2\1\1\11\1\1\1\11\1\0"+
    "\2\1\1\11\2\1\1\11\1\0\1\11\1\1\12\0"+
    "\1\1\3\0\1\1\1\11\4\1\10\0\1\11\1\0"+
    "\1\1\3\0\2\1\12\0\1\1\3\0\1\1\3\0"+
    "\1\11\2\0\1\11\3\0\1\1\1\11\4\0\1\11"+
    "\1\0\1\11\2\0\1\11\1\0\3\11\1\0\1\11"+
    "\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public SmcLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 128) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 90: 
          { yybegin(WAITING_FOR_FSM_FILE); return FSM_FILE_KEYWORD;
          }
        case 92: break;
        case 46: 
          { yybegin(WAITING_FOR_ACTIONS); return BRACE_OPEN;
          }
        case 93: break;
        case 82: 
          { yybegin(WAITING_FOR_IMPORT_CLASS); return STATIC_JAVA_KEYWORD;
          }
        case 94: break;
        case 86: 
          { yybegin(WAITING_FOR_HEADER); return HEADER_KEYWORD;
          }
        case 95: break;
        case 39: 
          { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return ACTION_NAME;
          }
        case 96: break;
        case 81: 
          { yybegin(WAITING_FOR_START); return START_KEYWORD;
          }
        case 97: break;
        case 65: 
          { yypushState(IN_BLOCK_COMMENT); return BLOCK_COMMENT_OPEN;
          }
        case 98: break;
        case 16: 
          { yybegin(WAITING_FOR_HEADER); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 99: break;
        case 84: 
          { yybegin(WAITING_FOR_IMPORT_CLASS); return IMPORT_KEYWORD;
          }
        case 100: break;
        case 67: 
          { yybegin(WAITING_FOR_VERBATIM_CODE);  return VERBATIM_OPEN;
          }
        case 101: break;
        case 61: 
          { yybegin(WAITING_FOR_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 102: break;
        case 48: 
          { yybegin(WAITING_FOR_GUARD_RAW_CODE); return BRACKET_OPEN;
          }
        case 103: break;
        case 53: 
          { yybegin(WAITING_FOR_PARAMETER_NAME); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 104: break;
        case 14: 
          { yybegin(YYINITIAL); return FSM_FILE_NAME;
          }
        case 105: break;
        case 77: 
          { yybegin(WAITING_FOR_MAP); return MAP_KEYWORD;
          }
        case 106: break;
        case 71: 
          { yybegin(WAITING_FOR_STATE); return MAP_SECTION_BOUND;
          }
        case 107: break;
        case 75: 
          { yypopState(); return BLOCK_COMMENT_OPEN;
          }
        case 108: break;
        case 44: 
          { yybegin(WAITING_FOR_PARAMETERS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 109: break;
        case 87: 
          { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return PACKAGE_KEYWORD;
          }
        case 110: break;
        case 28: 
          { yybegin(WAITING_FOR_STATE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 111: break;
        case 29: 
          { yybegin(WAITING_FOR_STATE); return STATE_NAME;
          }
        case 112: break;
        case 26: 
          { yybegin(WAITING_FOR_MAP); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 113: break;
        case 19: 
          { yybegin(YYINITIAL); return DECLARE_STATEMENT;
          }
        case 114: break;
        case 31: 
          { yybegin(WAITING_FOR_ARGUMENTS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 115: break;
        case 60: 
          { yybegin(WAITING_FOR_NEXT_STATE_NAME); return BRACKET_CLOSE;
          }
        case 116: break;
        case 4: 
          { return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 117: break;
        case 9: 
          { yybegin(WAITING_FOR_IMPORT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 118: break;
        case 35: 
          { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 119: break;
        case 21: 
          { yybegin(YYINITIAL); return INCLUDE_FILE_NAME;
          }
        case 120: break;
        case 56: 
          { yybegin(WAITING_FOR_GUARD_RAW_CODE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 121: break;
        case 32: 
          { yybegin(WAITING_FOR_ARGUMENTS); return ARGUMENT_STATEMENT;
          }
        case 122: break;
        case 42: 
          { yybegin(WAITING_FOR_TRANSITIONS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 123: break;
        case 68: 
          { yybegin(YYINITIAL);  return VERBATIM_OPEN;
          }
        case 124: break;
        case 37: 
          { yybegin(WAITING_FOR_STATE); return BRACE_CLOSE;
          }
        case 125: break;
        case 34: 
          { yybegin(WAITING_FOR_ARGUMENTS); return COMMA;
          }
        case 126: break;
        case 85: 
          { yybegin(WAITING_FOR_ACCESS); return ACCESS_KEYWORD;
          }
        case 127: break;
        case 69: 
          { yybegin(YYINITIAL);  return VERBATIM_CLOSE;
          }
        case 128: break;
        case 23: 
          { yybegin(WAITING_FOR_START_STATE_NAME); return START_MAP_NAME;
          }
        case 129: break;
        case 1: 
          { yybegin(WAITING_FOR_VERBATIM_CODE); return VERBATIM_CODE;
          }
        case 130: break;
        case 83: 
          { yybegin(YYINITIAL); return ACCESS_LEVEL;
          }
        case 131: break;
        case 2: 
          { yybegin(IN_BLOCK_COMMENT); return BLOCK_COMMENT_CONTENT;
          }
        case 132: break;
        case 11: 
          { yybegin(WAITING_FOR_FSM_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 133: break;
        case 89: 
          { yybegin(WAITING_FOR_DECLARE); return DECLARE_KEYWORD;
          }
        case 134: break;
        case 57: 
          { yybegin(WAITING_FOR_NEXT_STATE_NAME);  yypushback(1); return GUARD_RAW_CODE;
          }
        case 135: break;
        case 49: 
          { yybegin(WAITING_FOR_PARAMETER_TYPE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 136: break;
        case 17: 
          { yybegin(YYINITIAL); return HEADER_FILE_NAME;
          }
        case 137: break;
        case 47: 
          { yybegin(WAITING_FOR_PARAMETER_NAME);  return PARENTHESES_OPEN;
          }
        case 138: break;
        case 64: 
          { yybegin(WAITING_FOR_ACTIONS); return SEMICOLON;
          }
        case 139: break;
        case 54: 
          { yybegin(WAITING_FOR_PARAMETER_NAME); return PARAMETER_NAME;
          }
        case 140: break;
        case 41: 
          { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return SEMICOLON;
          }
        case 141: break;
        case 36: 
          { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return BRACE_OPEN;
          }
        case 142: break;
        case 8: 
          { yybegin(YYINITIAL); return CONTEXT_CLASS_NAME;
          }
        case 143: break;
        case 5: 
          { yybegin(WAITING_FOR_PACKAGE_STATEMENT); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 144: break;
        case 7: 
          { yybegin(WAITING_FOR_CONTEXT_CLASS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 145: break;
        case 30: 
          { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_OPEN;
          }
        case 146: break;
        case 52: 
          { yybegin(WAITING_FOR_PARAMETER_NAME); return COMMA;
          }
        case 147: break;
        case 55: 
          { yybegin(WAITING_FOR_PARAMETER_TYPE); return COLON;
          }
        case 148: break;
        case 45: 
          { yybegin(WAITING_FOR_PARAMETERS); return NEXT_STATE_NAME;
          }
        case 149: break;
        case 58: 
          { yybegin(WAITING_FOR_NEXT_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 150: break;
        case 59: 
          { yybegin(WAITING_FOR_NEXT_STATE_NAME); return NEXT_STATE_NAME;
          }
        case 151: break;
        case 38: 
          { yybegin(WAITING_FOR_ENTRY_EXIT_ACTIONS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 152: break;
        case 78: 
          { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return EXIT_KEYWORD;
          }
        case 153: break;
        case 27: 
          { yybegin(WAITING_FOR_MAP); return MAP_NAME;
          }
        case 154: break;
        case 12: 
          { yybegin(YYINITIAL); return FSM_CLASS_NAME;
          }
        case 155: break;
        case 66: 
          { return LINE_COMMENT;
          }
        case 156: break;
        case 20: 
          { yybegin(WAITING_FOR_INCLUDE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 157: break;
        case 51: 
          { yybegin(WAITING_FOR_NEXT_STATE_NAME); return PARENTHESES_CLOSE;
          }
        case 158: break;
        case 22: 
          { yybegin(WAITING_FOR_START); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 159: break;
        case 6: 
          { yybegin(YYINITIAL); return PACKAGE_STATEMENT;
          }
        case 160: break;
        case 62: 
          { yybegin(WAITING_FOR_ACTIONS); return ACTION_NAME;
          }
        case 161: break;
        case 43: 
          { yybegin(WAITING_FOR_PARAMETERS); return TRANSITION_NAME;
          }
        case 162: break;
        case 10: 
          { yybegin(YYINITIAL); return IMPORT_CLASS_STATEMENT;
          }
        case 163: break;
        case 25: 
          { yybegin(YYINITIAL); return START_STATE_NAME;
          }
        case 164: break;
        case 79: 
          { yybegin(WAITING_FOR_STATE_ENTRY_EXIT); return ENTRY_KEYWORD;
          }
        case 165: break;
        case 33: 
          { yypopState(); return PARENTHESES_CLOSE;
          }
        case 166: break;
        case 63: 
          { yybegin(WAITING_FOR_TRANSITIONS); return BRACE_CLOSE;
          }
        case 167: break;
        case 3: 
          { return com.intellij.psi.TokenType.BAD_CHARACTER;
          }
        case 168: break;
        case 40: 
          { yypushState(WAITING_FOR_ARGUMENTS);  return PARENTHESES_OPEN;
          }
        case 169: break;
        case 13: 
          { yybegin(WAITING_FOR_FSM_FILE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 170: break;
        case 80: 
          { yybegin(WAITING_FOR_CONTEXT_CLASS); return CLASS_KEYWORD;
          }
        case 171: break;
        case 15: 
          { yybegin(WAITING_FOR_ACCESS); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 172: break;
        case 88: 
          { yybegin(WAITING_FOR_INCLUDE); return INCLUDE_KEYWORD;
          }
        case 173: break;
        case 91: 
          { yybegin(WAITING_FOR_FSM_CLASS); return FSM_CLASS_KEYWORD;
          }
        case 174: break;
        case 72: 
          { yybegin(WAITING_FOR_MAP); return MAP_SECTION_BOUND;
          }
        case 175: break;
        case 76: 
          { yybegin(WAITING_FOR_PARAMETERS); return NIL_KEYWORD;
          }
        case 176: break;
        case 18: 
          { yybegin(WAITING_FOR_DECLARE); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 177: break;
        case 70: 
          { yybegin(WAITING_FOR_START_STATE_NAME); return START_MAP_NAME_STATE_NAME_SEPARATOR;
          }
        case 178: break;
        case 74: 
          { yypopState(); return BLOCK_COMMENT_CLOSE;
          }
        case 179: break;
        case 24: 
          { yybegin(WAITING_FOR_START_STATE_NAME); return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 180: break;
        case 50: 
          { yybegin(WAITING_FOR_PARAMETER_TYPE); return PARAMETER_TYPE;
          }
        case 181: break;
        case 73: 
          { yybegin(WAITING_FOR_ARGUMENTS); return validateJavaString(yytext());
          }
        case 182: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
