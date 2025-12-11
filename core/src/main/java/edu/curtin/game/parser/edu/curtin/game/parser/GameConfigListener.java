// Generated from edu/curtin/game/parser/GameConfig.g4 by ANTLR 4.13.1
package edu.curtin.game.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GameConfigParser}.
 */
public interface GameConfigListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(GameConfigParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(GameConfigParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(GameConfigParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(GameConfigParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#sizeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSizeDeclaration(GameConfigParser.SizeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#sizeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSizeDeclaration(GameConfigParser.SizeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#startDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStartDeclaration(GameConfigParser.StartDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#startDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStartDeclaration(GameConfigParser.StartDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#goalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGoalDeclaration(GameConfigParser.GoalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#goalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGoalDeclaration(GameConfigParser.GoalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#itemDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterItemDeclaration(GameConfigParser.ItemDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#itemDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitItemDeclaration(GameConfigParser.ItemDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#obstacleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterObstacleDeclaration(GameConfigParser.ObstacleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#obstacleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitObstacleDeclaration(GameConfigParser.ObstacleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#pluginDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPluginDeclaration(GameConfigParser.PluginDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#pluginDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPluginDeclaration(GameConfigParser.PluginDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#scriptDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterScriptDeclaration(GameConfigParser.ScriptDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#scriptDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitScriptDeclaration(GameConfigParser.ScriptDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#atDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAtDeclaration(GameConfigParser.AtDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#atDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAtDeclaration(GameConfigParser.AtDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#messageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMessageDeclaration(GameConfigParser.MessageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#messageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMessageDeclaration(GameConfigParser.MessageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#requiresDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRequiresDeclaration(GameConfigParser.RequiresDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#requiresDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRequiresDeclaration(GameConfigParser.RequiresDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#locationList}.
	 * @param ctx the parse tree
	 */
	void enterLocationList(GameConfigParser.LocationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#locationList}.
	 * @param ctx the parse tree
	 */
	void exitLocationList(GameConfigParser.LocationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(GameConfigParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(GameConfigParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameConfigParser#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(GameConfigParser.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameConfigParser#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(GameConfigParser.StringListContext ctx);
}