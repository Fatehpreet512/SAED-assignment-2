// Generated from edu/curtin/game/parser/GameConfig.g4 by ANTLR 4.13.1
package edu.curtin.game.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GameConfigParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		INTEGER=18, STRING=19, IDENTIFIER=20, SCRIPT_CONTENT=21, WS=22, COMMENT=23;
	public static final int
		RULE_config = 0, RULE_declarations = 1, RULE_sizeDeclaration = 2, RULE_startDeclaration = 3, 
		RULE_goalDeclaration = 4, RULE_itemDeclaration = 5, RULE_obstacleDeclaration = 6, 
		RULE_pluginDeclaration = 7, RULE_scriptDeclaration = 8, RULE_atDeclaration = 9, 
		RULE_messageDeclaration = 10, RULE_requiresDeclaration = 11, RULE_locationList = 12, 
		RULE_location = 13, RULE_stringList = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"config", "declarations", "sizeDeclaration", "startDeclaration", "goalDeclaration", 
			"itemDeclaration", "obstacleDeclaration", "pluginDeclaration", "scriptDeclaration", 
			"atDeclaration", "messageDeclaration", "requiresDeclaration", "locationList", 
			"location", "stringList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'size'", "'('", "','", "')'", "'start'", "'goal'", "'item'", "'{'", 
			"'}'", "'obstacle'", "'plugin'", "'.'", "'script'", "'!{'", "'at'", "'message'", 
			"'requires'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "INTEGER", "STRING", "IDENTIFIER", 
			"SCRIPT_CONTENT", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GameConfig.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GameConfigParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConfigContext extends ParserRuleContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GameConfigParser.EOF, 0); }
		public ConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_config; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitConfig(this);
		}
	}

	public final ConfigContext config() throws RecognitionException {
		ConfigContext _localctx = new ConfigContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_config);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			declarations();
			setState(31);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationsContext extends ParserRuleContext {
		public SizeDeclarationContext sizeDeclaration() {
			return getRuleContext(SizeDeclarationContext.class,0);
		}
		public StartDeclarationContext startDeclaration() {
			return getRuleContext(StartDeclarationContext.class,0);
		}
		public GoalDeclarationContext goalDeclaration() {
			return getRuleContext(GoalDeclarationContext.class,0);
		}
		public List<ItemDeclarationContext> itemDeclaration() {
			return getRuleContexts(ItemDeclarationContext.class);
		}
		public ItemDeclarationContext itemDeclaration(int i) {
			return getRuleContext(ItemDeclarationContext.class,i);
		}
		public List<ObstacleDeclarationContext> obstacleDeclaration() {
			return getRuleContexts(ObstacleDeclarationContext.class);
		}
		public ObstacleDeclarationContext obstacleDeclaration(int i) {
			return getRuleContext(ObstacleDeclarationContext.class,i);
		}
		public List<PluginDeclarationContext> pluginDeclaration() {
			return getRuleContexts(PluginDeclarationContext.class);
		}
		public PluginDeclarationContext pluginDeclaration(int i) {
			return getRuleContext(PluginDeclarationContext.class,i);
		}
		public List<ScriptDeclarationContext> scriptDeclaration() {
			return getRuleContexts(ScriptDeclarationContext.class);
		}
		public ScriptDeclarationContext scriptDeclaration(int i) {
			return getRuleContext(ScriptDeclarationContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitDeclarations(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			sizeDeclaration();
			setState(34);
			startDeclaration();
			setState(35);
			goalDeclaration();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 11392L) != 0)) {
				{
				setState(40);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__6:
					{
					setState(36);
					itemDeclaration();
					}
					break;
				case T__9:
					{
					setState(37);
					obstacleDeclaration();
					}
					break;
				case T__10:
					{
					setState(38);
					pluginDeclaration();
					}
					break;
				case T__12:
					{
					setState(39);
					scriptDeclaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SizeDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER() { return getTokens(GameConfigParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(GameConfigParser.INTEGER, i);
		}
		public SizeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sizeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterSizeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitSizeDeclaration(this);
		}
	}

	public final SizeDeclarationContext sizeDeclaration() throws RecognitionException {
		SizeDeclarationContext _localctx = new SizeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sizeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__0);
			setState(46);
			match(T__1);
			setState(47);
			match(INTEGER);
			setState(48);
			match(T__2);
			setState(49);
			match(INTEGER);
			setState(50);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER() { return getTokens(GameConfigParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(GameConfigParser.INTEGER, i);
		}
		public StartDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterStartDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitStartDeclaration(this);
		}
	}

	public final StartDeclarationContext startDeclaration() throws RecognitionException {
		StartDeclarationContext _localctx = new StartDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_startDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__4);
			setState(53);
			match(T__1);
			setState(54);
			match(INTEGER);
			setState(55);
			match(T__2);
			setState(56);
			match(INTEGER);
			setState(57);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GoalDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER() { return getTokens(GameConfigParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(GameConfigParser.INTEGER, i);
		}
		public GoalDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterGoalDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitGoalDeclaration(this);
		}
	}

	public final GoalDeclarationContext goalDeclaration() throws RecognitionException {
		GoalDeclarationContext _localctx = new GoalDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_goalDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__5);
			setState(60);
			match(T__1);
			setState(61);
			match(INTEGER);
			setState(62);
			match(T__2);
			setState(63);
			match(INTEGER);
			setState(64);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemDeclarationContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GameConfigParser.STRING, 0); }
		public AtDeclarationContext atDeclaration() {
			return getRuleContext(AtDeclarationContext.class,0);
		}
		public MessageDeclarationContext messageDeclaration() {
			return getRuleContext(MessageDeclarationContext.class,0);
		}
		public ItemDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterItemDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitItemDeclaration(this);
		}
	}

	public final ItemDeclarationContext itemDeclaration() throws RecognitionException {
		ItemDeclarationContext _localctx = new ItemDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_itemDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(T__6);
			setState(67);
			match(STRING);
			setState(68);
			match(T__7);
			setState(69);
			atDeclaration();
			setState(70);
			messageDeclaration();
			setState(71);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObstacleDeclarationContext extends ParserRuleContext {
		public AtDeclarationContext atDeclaration() {
			return getRuleContext(AtDeclarationContext.class,0);
		}
		public RequiresDeclarationContext requiresDeclaration() {
			return getRuleContext(RequiresDeclarationContext.class,0);
		}
		public ObstacleDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obstacleDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterObstacleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitObstacleDeclaration(this);
		}
	}

	public final ObstacleDeclarationContext obstacleDeclaration() throws RecognitionException {
		ObstacleDeclarationContext _localctx = new ObstacleDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_obstacleDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__9);
			setState(74);
			match(T__7);
			setState(75);
			atDeclaration();
			setState(76);
			requiresDeclaration();
			setState(77);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PluginDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(GameConfigParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(GameConfigParser.IDENTIFIER, i);
		}
		public PluginDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pluginDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterPluginDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitPluginDeclaration(this);
		}
	}

	public final PluginDeclarationContext pluginDeclaration() throws RecognitionException {
		PluginDeclarationContext _localctx = new PluginDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_pluginDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__10);
			setState(80);
			match(IDENTIFIER);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(81);
				match(T__11);
				setState(82);
				match(IDENTIFIER);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptDeclarationContext extends ParserRuleContext {
		public TerminalNode SCRIPT_CONTENT() { return getToken(GameConfigParser.SCRIPT_CONTENT, 0); }
		public ScriptDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scriptDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterScriptDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitScriptDeclaration(this);
		}
	}

	public final ScriptDeclarationContext scriptDeclaration() throws RecognitionException {
		ScriptDeclarationContext _localctx = new ScriptDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_scriptDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__12);
			setState(89);
			match(T__13);
			setState(90);
			match(SCRIPT_CONTENT);
			setState(91);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtDeclarationContext extends ParserRuleContext {
		public LocationListContext locationList() {
			return getRuleContext(LocationListContext.class,0);
		}
		public AtDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterAtDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitAtDeclaration(this);
		}
	}

	public final AtDeclarationContext atDeclaration() throws RecognitionException {
		AtDeclarationContext _localctx = new AtDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_atDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__14);
			setState(94);
			locationList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MessageDeclarationContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(GameConfigParser.STRING, 0); }
		public MessageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterMessageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitMessageDeclaration(this);
		}
	}

	public final MessageDeclarationContext messageDeclaration() throws RecognitionException {
		MessageDeclarationContext _localctx = new MessageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_messageDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__15);
			setState(97);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RequiresDeclarationContext extends ParserRuleContext {
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public RequiresDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiresDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterRequiresDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitRequiresDeclaration(this);
		}
	}

	public final RequiresDeclarationContext requiresDeclaration() throws RecognitionException {
		RequiresDeclarationContext _localctx = new RequiresDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_requiresDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__16);
			setState(100);
			stringList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationListContext extends ParserRuleContext {
		public List<LocationContext> location() {
			return getRuleContexts(LocationContext.class);
		}
		public LocationContext location(int i) {
			return getRuleContext(LocationContext.class,i);
		}
		public LocationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterLocationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitLocationList(this);
		}
	}

	public final LocationListContext locationList() throws RecognitionException {
		LocationListContext _localctx = new LocationListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_locationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			location();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(103);
				match(T__2);
				setState(104);
				location();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER() { return getTokens(GameConfigParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(GameConfigParser.INTEGER, i);
		}
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__1);
			setState(111);
			match(INTEGER);
			setState(112);
			match(T__2);
			setState(113);
			match(INTEGER);
			setState(114);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringListContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(GameConfigParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GameConfigParser.STRING, i);
		}
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameConfigListener ) ((GameConfigListener)listener).exitStringList(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(STRING);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(117);
				match(T__2);
				setState(118);
				match(STRING);
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017}\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001)\b\u0001\n\u0001\f\u0001,\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007T\b\u0007\n\u0007\f\u0007W\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0005\fj\b\f\n\f\f\fm\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000ex\b\u000e\n"+
		"\u000e\f\u000e{\t\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000\u0000"+
		"t\u0000\u001e\u0001\u0000\u0000\u0000\u0002!\u0001\u0000\u0000\u0000\u0004"+
		"-\u0001\u0000\u0000\u0000\u00064\u0001\u0000\u0000\u0000\b;\u0001\u0000"+
		"\u0000\u0000\nB\u0001\u0000\u0000\u0000\fI\u0001\u0000\u0000\u0000\u000e"+
		"O\u0001\u0000\u0000\u0000\u0010X\u0001\u0000\u0000\u0000\u0012]\u0001"+
		"\u0000\u0000\u0000\u0014`\u0001\u0000\u0000\u0000\u0016c\u0001\u0000\u0000"+
		"\u0000\u0018f\u0001\u0000\u0000\u0000\u001an\u0001\u0000\u0000\u0000\u001c"+
		"t\u0001\u0000\u0000\u0000\u001e\u001f\u0003\u0002\u0001\u0000\u001f \u0005"+
		"\u0000\u0000\u0001 \u0001\u0001\u0000\u0000\u0000!\"\u0003\u0004\u0002"+
		"\u0000\"#\u0003\u0006\u0003\u0000#*\u0003\b\u0004\u0000$)\u0003\n\u0005"+
		"\u0000%)\u0003\f\u0006\u0000&)\u0003\u000e\u0007\u0000\')\u0003\u0010"+
		"\b\u0000($\u0001\u0000\u0000\u0000(%\u0001\u0000\u0000\u0000(&\u0001\u0000"+
		"\u0000\u0000(\'\u0001\u0000\u0000\u0000),\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+\u0003\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000-.\u0005\u0001\u0000\u0000./\u0005\u0002"+
		"\u0000\u0000/0\u0005\u0012\u0000\u000001\u0005\u0003\u0000\u000012\u0005"+
		"\u0012\u0000\u000023\u0005\u0004\u0000\u00003\u0005\u0001\u0000\u0000"+
		"\u000045\u0005\u0005\u0000\u000056\u0005\u0002\u0000\u000067\u0005\u0012"+
		"\u0000\u000078\u0005\u0003\u0000\u000089\u0005\u0012\u0000\u00009:\u0005"+
		"\u0004\u0000\u0000:\u0007\u0001\u0000\u0000\u0000;<\u0005\u0006\u0000"+
		"\u0000<=\u0005\u0002\u0000\u0000=>\u0005\u0012\u0000\u0000>?\u0005\u0003"+
		"\u0000\u0000?@\u0005\u0012\u0000\u0000@A\u0005\u0004\u0000\u0000A\t\u0001"+
		"\u0000\u0000\u0000BC\u0005\u0007\u0000\u0000CD\u0005\u0013\u0000\u0000"+
		"DE\u0005\b\u0000\u0000EF\u0003\u0012\t\u0000FG\u0003\u0014\n\u0000GH\u0005"+
		"\t\u0000\u0000H\u000b\u0001\u0000\u0000\u0000IJ\u0005\n\u0000\u0000JK"+
		"\u0005\b\u0000\u0000KL\u0003\u0012\t\u0000LM\u0003\u0016\u000b\u0000M"+
		"N\u0005\t\u0000\u0000N\r\u0001\u0000\u0000\u0000OP\u0005\u000b\u0000\u0000"+
		"PU\u0005\u0014\u0000\u0000QR\u0005\f\u0000\u0000RT\u0005\u0014\u0000\u0000"+
		"SQ\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000V\u000f\u0001\u0000\u0000\u0000WU\u0001"+
		"\u0000\u0000\u0000XY\u0005\r\u0000\u0000YZ\u0005\u000e\u0000\u0000Z[\u0005"+
		"\u0015\u0000\u0000[\\\u0005\t\u0000\u0000\\\u0011\u0001\u0000\u0000\u0000"+
		"]^\u0005\u000f\u0000\u0000^_\u0003\u0018\f\u0000_\u0013\u0001\u0000\u0000"+
		"\u0000`a\u0005\u0010\u0000\u0000ab\u0005\u0013\u0000\u0000b\u0015\u0001"+
		"\u0000\u0000\u0000cd\u0005\u0011\u0000\u0000de\u0003\u001c\u000e\u0000"+
		"e\u0017\u0001\u0000\u0000\u0000fk\u0003\u001a\r\u0000gh\u0005\u0003\u0000"+
		"\u0000hj\u0003\u001a\r\u0000ig\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000"+
		"\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000l\u0019\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005\u0002\u0000\u0000"+
		"op\u0005\u0012\u0000\u0000pq\u0005\u0003\u0000\u0000qr\u0005\u0012\u0000"+
		"\u0000rs\u0005\u0004\u0000\u0000s\u001b\u0001\u0000\u0000\u0000ty\u0005"+
		"\u0013\u0000\u0000uv\u0005\u0003\u0000\u0000vx\u0005\u0013\u0000\u0000"+
		"wu\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000"+
		"\u0000yz\u0001\u0000\u0000\u0000z\u001d\u0001\u0000\u0000\u0000{y\u0001"+
		"\u0000\u0000\u0000\u0005(*Uky";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}