package Bomman;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.audio.BGM3D;
import framework.audio.Sound3D;
import framework.game2D.Position2D;
import framework.gameMain.SimpleMazeGame;
import framework.model3D.Universe;
import template.maze2D.MazeSpritePlayer;

public class Bomman extends SimpleMazeGame {
	private MazeSpritePlayer mazeSpritePlayer;

	private MazeSpritePlayer mazeSpritePlayer2;

	private BomStage mazeGround;

	private  BomStage gameOver;

	// 速度によって物体が動いている時にボタンを押せるかどうかを判定するフラグ
	private boolean disableControl = false;


	private Sound3D fightBGM = BGM3D.registerBGM("data\\images\\last-war.wav");


	@Override
	public void init(Universe universe) {





		mazeGround = new BomStage("data\\images\\brokenblock.png", "data\\images\\sibafu.jpg");

	universe.place(mazeGround);
		camera.addTarget(mazeGround);

		//player1
		mazeSpritePlayer = new MazeSpritePlayer("data\\images\\maincharactor.png");
		mazeSpritePlayer.setPosition(18.0, 18.0);
		mazeSpritePlayer.setCollisionRadius(0.5);
		universe.place(mazeSpritePlayer);

		//player2
		mazeSpritePlayer2 = new MazeSpritePlayer("data\\images\\enemycharctor.png");
		mazeSpritePlayer2.setPosition(2.0, 2.0);
		mazeSpritePlayer2.setCollisionRadius(0.5);
		universe.place(mazeSpritePlayer2);
		BGM3D.playBGM(fightBGM);
	}


	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		// 迷路ゲームステージを構成するオブジェクトの位置とプレイヤーの位置をもとに速度を0にするかどうかを調べる。
		Position2D gridPoint = mazeGround.getNeighborGridPoint(mazeSpritePlayer);

		double speed1 = 2.15;
		double speed2 = 2.0;


		// 速度が0にするフラグが立っていれば、速度を0にする
		if (gridPoint != null) {
			mazeSpritePlayer.setPosition(gridPoint);
			mazeSpritePlayer.setVelocity(0.0, 0.0);
			//disableControl = false;
		}

		// キャラが移動していなければ、キー操作の処理を行える。
		if(!disableControl){
			// キー操作の処理
			// 左
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				mazeSpritePlayer.setVelocity(-speed1, 0.0);

			}
			// 右
			else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				mazeSpritePlayer.setVelocity(speed1, 0.0);


			}
			// 上
			else if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				mazeSpritePlayer.setVelocity(0.0, speed1);

			}
			// 下
			else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				mazeSpritePlayer.setVelocity(0.0, -speed1);

			}
		}
		mazeSpritePlayer.motion(interval, mazeGround);

		Position2D gridPoint1 = mazeGround.getNeighborGridPoint(mazeSpritePlayer2);

		// 速度が0にするフラグが立っていれば、速度を0にする
		if (gridPoint1 != null) {
			mazeSpritePlayer2.setPosition(gridPoint1);
			mazeSpritePlayer2.setVelocity(0.0, 0.0);
			disableControl = false;
		}

		// キャラが移動していなければ、キー操作の処理を行える。
		if(!disableControl){
			// キー操作の処理
			// 左
			if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_B)) {
				mazeSpritePlayer2.setVelocity(-speed2, 0.0);

			}
			// 右
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_A)) {
				mazeSpritePlayer2.setVelocity(speed2, 0.0);


			}
			// 上
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_E)) {
				mazeSpritePlayer2.setVelocity(0.0, speed2);

			}
			// 下
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_C)) {
				mazeSpritePlayer2.setVelocity(0.0, -speed2);

			}
		}
		mazeSpritePlayer2.motion(interval, mazeGround);

		//あたり判定
		if (mazeSpritePlayer.checkCollision(mazeSpritePlayer2)) {
			System.out.println("ア〇パ～ンチ！！！　　バイバイキ～〇！？");


			stop();

			gameOver = new BomStage(null,null);

			universe.place(gameOver);





			//mazeGround = new BomStage("data\\images\\opening.png");
			//universe.place(mazeGround);
			//camera.addTarget(mazeGround);
		}
	}



	@Override
	public RWTFrame3D createFrame3D() {
		RWTFrame3D f = new RWTFrame3D();
		f.setSize(800, 800);
		f.setTitle("bom man");
		return f;
	}

	/**
	 * ゲームのメイン
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Bomman game = new Bomman();
		game.setFramePolicy(5, 33, false);
		game.start();

	}

}

