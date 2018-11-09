package exercise;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Texture;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Sprite;
import framework.gameMain.SimpleShootingGame;
import framework.model3D.BackgroundBox;
import framework.model3D.Universe;

public class ExerciseGame extends SimpleShootingGame {
	@Override
	public void init(Universe universe) {
		// 平行光源を配置する
        DirectionalLight dirlight = new DirectionalLight(
        		true,                           //光のON/OFF
                new Color3f(1.0f, 1.0f, 1.0f),  //光の色
                new Vector3f(0.0f, -1.0f, -0.5f) //光の方向ベクトル
        );
        dirlight.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));
        universe.placeLight(dirlight);

		// 環境光を配置する
		AmbientLight amblight = new AmbientLight(new Color3f(0.5f, 0.5f, 0.5f));
		amblight.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));
		universe.placeLight(amblight);

		// 背景を作成する
		buildSkyBox(universe);

		Sprite myShip = new Sprite("data\\images\\MyShip.gif");
		universe.place(myShip);

		//ステージの基礎の作成
		int Y = 11; int X = 13;

		int[][] base = new int[Y][X];

		for(int y = 0 ; y < Y ; y ++) {
			for(int x = 0 ; x < X ; x ++) {
				if(x%2 == 0 || y%2 == 0) {
					base[y][x] = 1;
					universe.place(myShip);
					setViewRange(50,50);
				}else {
					base[y][x] = 0;
				}
			}
		}
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {

	}

	@Override
	public RWTFrame3D createFrame3D() {
		RWTFrame3D f = new RWTFrame3D();
		f.setSize(1200, 1000);
		f.setTitle("2018年プロジェクト演習");
		return f;
	}

	/**
	 * 背景を作成する
	 * @param universe
	 */
	private void buildSkyBox(Universe universe) {
		TextureLoader loaderTop = new TextureLoader("data\\texture\\top.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureTop = loaderTop.getTexture();
		TextureLoader loaderBottom = new TextureLoader("data\\texture\\bottom.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureBottom = loaderBottom.getTexture();
		TextureLoader loaderNorth = new TextureLoader("data\\texture\\north.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureNorth = loaderNorth.getTexture();
		TextureLoader loaderSouth = new TextureLoader("data\\texture\\south.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureSouth = loaderSouth.getTexture();
		TextureLoader loaderWest = new TextureLoader("data\\texture\\west.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureWest = loaderWest.getTexture();
		TextureLoader loaderEast = new TextureLoader("data\\texture\\east.jpg",
				TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
				null);
		Texture textureEast = loaderEast.getTexture();

		BackgroundBox background = new BackgroundBox(textureNorth, textureWest,
				textureSouth, textureEast, textureTop, textureBottom);
		BoundingSphere bs = new BoundingSphere();
		bs.setRadius(1000);
		background.setApplicationBounds(bs);
		universe.place(background);
	}
}
