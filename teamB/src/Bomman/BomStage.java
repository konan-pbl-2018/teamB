package Bomman;

import java.math.BigDecimal;
import java.util.Random;

import framework.game2D.Maze2D;
import template.maze2D.MazeSpritePlayer;

/**
 * 迷路ゲームのステージのクラス
 * @author T.Kuno
 *
 */
public class BomStage extends Maze2D {
	// コンストラクタ
	public BomStage(String blockImage, String tileImage) {
		super(blockImage, tileImage);
	}

	// 抽象メソッドの実装
	//　0:タイル　1:ブロック　
	//@Override

	Random rand = new Random();

	public int[][] createMap() {

		int[][] map = {

				{1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,0,1,0,1,0,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,0,1,0,1,0,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,0,1,0,1,0,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,1},
				{1,0,1,0,1,0,1,0,1,0,1},
				{1,0,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1}


		};

		return map;
	}

	public boolean checkGridPoint(MazeSpritePlayer mazeSpritePlayer) {
		// 丸め誤差処理用変数の生成
		double mazeSpritePositionX = new BigDecimal(mazeSpritePlayer
				.getPosition().getX()).setScale(1, BigDecimal.ROUND_DOWN)
				.doubleValue();
		double mazeSpritePositionY = new BigDecimal(mazeSpritePlayer
				.getPosition().getY()).setScale(1, BigDecimal.ROUND_DOWN)
				.doubleValue();

		// ステージの構成オブジェクトの位置とプレイヤーの位置が同じかどうか判定する
		for (int i = 0; i < this.getStageObjectList().size(); i++) {
			if (
					mazeSpritePositionX == this.getStageObjectList().get(i).getPosition().getX()
					&& mazeSpritePositionY == this.getStageObjectList().get(i).getPosition().getY()
				){
				return true;
			}

		}
		return false;
	}



		public boolean checkGridPoint1(MazeSpritePlayer mazeSpritePlayer2) {
		// 丸め誤差処理用変数の生成
		double mazeSpritePositionX = new BigDecimal(mazeSpritePlayer2
				.getPosition().getX()).setScale(1, BigDecimal.ROUND_DOWN)
				.doubleValue();
		double mazeSpritePositionY = new BigDecimal(mazeSpritePlayer2
				.getPosition().getY()).setScale(1, BigDecimal.ROUND_DOWN)
				.doubleValue();

		// ステージの構成オブジェクトの位置とプレイヤーの位置が同じかどうかっ判定する
		for (int i = 0; i < this.getStageObjectList().size(); i++) {
			if (
					mazeSpritePositionX == this.getStageObjectList().get(i).getPosition().getX()
					&& mazeSpritePositionY == this.getStageObjectList().get(i).getPosition().getY()
				){
				return true;
			}

		}
		return false;
	}
}





