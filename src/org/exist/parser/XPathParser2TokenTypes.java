// $ANTLR 2.7.2: "XPathParser2.g" -> "XPathParser2.java"$

	package org.exist.parser;

	import antlr.debug.misc.*;
	import java.io.StringReader;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Iterator;
	import java.util.Stack;
	import org.exist.storage.BrokerPool;
	import org.exist.storage.DBBroker;
	import org.exist.storage.analysis.Tokenizer;
	import org.exist.EXistException;
	import org.exist.dom.DocumentSet;
	import org.exist.dom.DocumentImpl;
	import org.exist.dom.QName;
	import org.exist.security.PermissionDeniedException;
	import org.exist.security.User;
	import org.exist.xpath.*;
	import org.exist.xpath.value.*;
	import org.exist.xpath.functions.*;

public interface XPathParser2TokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int QNAME = 4;
	int PREDICATE = 5;
	int FLWOR = 6;
	int PARENTHESIZED = 7;
	int ABSOLUTE_SLASH = 8;
	int ABSOLUTE_DSLASH = 9;
	int WILDCARD = 10;
	int PREFIX_WILDCARD = 11;
	int FUNCTION = 12;
	int UNARY_MINUS = 13;
	int UNARY_PLUS = 14;
	int XPOINTER = 15;
	int XPOINTER_ID = 16;
	int VARIABLE_REF = 17;
	int VARIABLE_BINDING = 18;
	int ELEMENT = 19;
	int ATTRIBUTE = 20;
	int TEXT = 21;
	int VERSION_DECL = 22;
	int NAMESPACE_DECL = 23;
	int DEF_NAMESPACE_DECL = 24;
	int DEF_FUNCTION_NS_DECL = 25;
	int GLOBAL_VAR = 26;
	int FUNCTION_DECL = 27;
	int PROLOG = 28;
	int ATOMIC_TYPE = 29;
	int MODULE = 30;
	int ORDER_BY = 31;
	int POSITIONAL_VAR = 32;
	int LITERAL_xpointer = 33;
	int LPAREN = 34;
	int RPAREN = 35;
	int NCNAME = 36;
	int XQUERY = 37;
	int VERSION = 38;
	int SEMICOLON = 39;
	int LITERAL_declare = 40;
	int LITERAL_namespace = 41;
	int LITERAL_default = 42;
	int LITERAL_function = 43;
	int LITERAL_variable = 44;
	int STRING_LITERAL = 45;
	int EQ = 46;
	int LITERAL_element = 47;
	int DOLLAR = 48;
	int LCURLY = 49;
	int RCURLY = 50;
	int LITERAL_as = 51;
	int COMMA = 52;
	int LITERAL_empty = 53;
	int QUESTION = 54;
	int STAR = 55;
	int PLUS = 56;
	int LITERAL_item = 57;
	int LITERAL_for = 58;
	int LITERAL_let = 59;
	int LITERAL_if = 60;
	int LITERAL_where = 61;
	int LITERAL_return = 62;
	int LITERAL_in = 63;
	int LITERAL_at = 64;
	int COLON = 65;
	int LITERAL_order = 66;
	int LITERAL_by = 67;
	int LITERAL_ascending = 68;
	int LITERAL_descending = 69;
	int LITERAL_greatest = 70;
	int LITERAL_least = 71;
	int LITERAL_then = 72;
	int LITERAL_else = 73;
	int LITERAL_or = 74;
	int LITERAL_and = 75;
	int NEQ = 76;
	int GT = 77;
	int GTEQ = 78;
	int LT = 79;
	int LTEQ = 80;
	int ANDEQ = 81;
	int OREQ = 82;
	int LITERAL_to = 83;
	int MINUS = 84;
	int LITERAL_div = 85;
	int LITERAL_mod = 86;
	int UNION = 87;
	int SLASH = 88;
	int DSLASH = 89;
	int LITERAL_text = 90;
	int LITERAL_node = 91;
	int SELF = 92;
	int XML_COMMENT = 93;
	int LPPAREN = 94;
	int RPPAREN = 95;
	int AT = 96;
	int PARENT = 97;
	int LITERAL_child = 98;
	int LITERAL_self = 99;
	int LITERAL_attribute = 100;
	int LITERAL_descendant = 101;
	// "descendant-or-self" = 102
	// "following-sibling" = 103
	int LITERAL_parent = 104;
	int LITERAL_ancestor = 105;
	// "ancestor-or-self" = 106
	// "preceding-sibling" = 107
	int DOUBLE_LITERAL = 108;
	int DECIMAL_LITERAL = 109;
	int INTEGER_LITERAL = 110;
	int LITERAL_comment = 111;
	// "processing-instruction" = 112
	// "document-node" = 113
	int WS = 114;
	int END_TAG_START = 115;
	int QUOT = 116;
	int ATTRIBUTE_CONTENT = 117;
	int ELEMENT_CONTENT = 118;
	int XML_COMMENT_END = 119;
	int XML_PI = 120;
	int XML_PI_END = 121;
	int LITERAL_document = 122;
	int LITERAL_collection = 123;
	int XML_PI_START = 124;
	int LETTER = 125;
	int DIGITS = 126;
	int HEX_DIGITS = 127;
	int NMSTART = 128;
	int NMCHAR = 129;
	int EXPR_COMMENT = 130;
	int PREDEFINED_ENTITY_REF = 131;
	int CHAR_REF = 132;
	int NEXT_TOKEN = 133;
	int CHAR = 134;
	int BASECHAR = 135;
	int IDEOGRAPHIC = 136;
	int COMBINING_CHAR = 137;
	int DIGIT = 138;
	int EXTENDER = 139;
}
