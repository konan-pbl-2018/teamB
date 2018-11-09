package framework.game2D;

public abstract class Maze2D extends Map2D {
	// コンストラクタ
	public Maze2D(String blockImage, String tileImage) {
		super(new String[]{tileImage, blockImage}, 1);
	}

}
