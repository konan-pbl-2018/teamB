package framework.game2D;

public abstract class Maze2D extends Map2D {
	// �R���X�g���N�^
	public Maze2D(String blockImage, String tileImage) {
		super(new String[]{tileImage, blockImage}, 1);
	}

}
