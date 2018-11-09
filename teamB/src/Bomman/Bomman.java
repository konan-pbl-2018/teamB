package Bomman;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Position2D;
import framework.gameMain.SimpleMazeGame;
import framework.model3D.Universe;
import template.maze2D.MazeSpritePlayer;

public class Bomman extends SimpleMazeGame {
	private MazeSpritePlayer mazeSpritePlayer;

	private MazeSpritePlayer mazeSpritePlayer2;

	private BomStage mazeGround;

	// ���x�ɂ���ĕ��̂������Ă��鎞�Ƀ{�^���������邩�ǂ����𔻒肷��t���O
	private boolean disableControl = false;

	//private long lastTime;

	@Override
	public void init(Universe universe) {
		mazeGround = new BomStage("data\\images\\block.gif", "data\\images\\sibafu.jpg");

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
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		// ���H�Q�[���X�e�[�W���\������I�u�W�F�N�g�̈ʒu�ƃv���C���[�̈ʒu�����Ƃɑ��x��0�ɂ��邩�ǂ����𒲂ׂ�B
		Position2D gridPoint = mazeGround.getNeighborGridPoint(mazeSpritePlayer);
		
		double speed = 2.0;
		
		// ���x��0�ɂ���t���O�������Ă���΁A���x��0�ɂ���
		if (gridPoint != null) {
			mazeSpritePlayer.setPosition(gridPoint);
			mazeSpritePlayer.setVelocity(0.0, 0.0);
			//disableControl = false;
		}

		// �L�������ړ����Ă��Ȃ���΁A�L�[����̏������s����B
		if(!disableControl){
			// �L�[����̏���
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
				mazeSpritePlayer.setVelocity(-speed, 0.0);
				
			}
			// �E
			else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
				mazeSpritePlayer.setVelocity(speed, 0.0);
				

			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
				mazeSpritePlayer.setVelocity(0.0, speed);
				
			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
				mazeSpritePlayer.setVelocity(0.0, -speed);
				
			}
		}
		mazeSpritePlayer.motion(interval, mazeGround);

		Position2D gridPoint1 = mazeGround.getNeighborGridPoint(mazeSpritePlayer2);

		// ���x��0�ɂ���t���O�������Ă���΁A���x��0�ɂ���
		if (gridPoint1 != null) {
			mazeSpritePlayer2.setPosition(gridPoint1);
			mazeSpritePlayer2.setVelocity(0.0, 0.0);
			disableControl = false;
		}

		// �L�������ړ����Ă��Ȃ���΁A�L�[����̏������s����B
		if(!disableControl){
			// �L�[����̏���
			// ��
			if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_B)) {
				mazeSpritePlayer2.setVelocity(-speed, 0.0);
				
			}
			// �E
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_A)) {
				mazeSpritePlayer2.setVelocity(speed, 0.0);
				

			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_E)) {
				mazeSpritePlayer2.setVelocity(0.0, speed);
				
			}
			// ��
			else if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_C)) {
				mazeSpritePlayer2.setVelocity(0.0, -speed);
				
			}
		}
		mazeSpritePlayer2.motion(interval, mazeGround);
		
		//�����蔻��
		if (mazeSpritePlayer.checkCollision(mazeSpritePlayer2)) {
			System.out.println("�A�Z�p�`���`�I�I�I�@�@�o�C�o�C�L�`�Z�I�H");
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
	 * �Q�[���̃��C��
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Bomman game = new Bomman();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
