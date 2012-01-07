package x.x.x;

import java.util.ArrayList;
import java.util.List;

import x.x.x.R;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

// DO NOT change class name or else it won't be detected by BIG! caller ID
public class AnimationView extends View {
	private String packageName = "x.x.x"; // change this to your package name
	private Bitmap snowFlakeOne, snowFlakeTwo, snowFlakeThree; // snowflakes bitmaps
	private List<SnowFlakes> snowflakes; // list containing SnowFlakes
	private int leftCount = 0; // varible assciated with left half snow flakes count
	private int rightCount = 0; // varible assciated with right half snow flakes count
	private int width; // screen width
	private int height; // screen height
	private int verticalSpeed; // vertical speed of Snowflakes
	private int horizontalSpeed; // horizontal speed of Snowflakes
	private int lastTimeAddedSnowFlake = 0;  // as variable name
	private int maxNumber; // max number of snowflakes

	// BIG! caller ID is importing AnimationView using this constructor with context paramet. Other constructors are unaviable
	public AnimationView(Context context) {
		super(context);
		maxNumber = 12;
		snowflakes = new ArrayList<SnowFlakes>();
		verticalSpeed = 2;
		horizontalSpeed = 0;
		Resources res;
		try {
			res = getContext().getPackageManager().getResourcesForApplication(
					packageName); // Note we are using package name , this is caused code is exectued inside BIG! caller ID, which has different package name, where Theme resources are not available
			snowFlakeOne = BitmapFactory.decodeResource(res,
					R.drawable.snowflak_1);
			snowFlakeTwo = BitmapFactory.decodeResource(res,
					R.drawable.snowflak_2);
			snowFlakeThree = BitmapFactory.decodeResource(res,
					R.drawable.snowflak_3);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

	}

	// Do not change or if you know what are you doing
	// This function is setting screen width and height
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		width = chooseDimension(widthMode, widthSize);
		height = chooseDimension(heightMode, heightSize);

		setMeasuredDimension(width, height);
	}
	// Do not change or if you know what are you doing
	private int chooseDimension(int mode, int size) {
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
			return size;
		} else { // (mode == MeasureSpec.UNSPECIFIED)
			return 300;
		}
	}
	
	// SnowFlake algorithm is implemented in onDraw function, which is executed 40 times per second in BIG! caller ID
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (canvas != null) {
			for (int i = 0; i < snowflakes.size(); i++) {
				snowflakes.get(i).savedMatrix.postTranslate(0,
						(int) (verticalSpeed));
				snowflakes.get(i).y += (int) (2 * Math.random() + verticalSpeed);
				if (snowflakes.get(i).x < -10
						|| snowflakes.get(i).x > width + 10) {
					snowflakes.remove(i);
					continue;
				} else {
					if (snowflakes.get(i).moveVertically) {
						int x = (int) (horizontalSpeed * Math.random());
						snowflakes.get(i).savedMatrix.postTranslate(-x, 0);
						snowflakes.get(i).x -= x;
						snowflakes.get(i).savedMatrix
								.postRotate(
										-2,
										snowflakes.get(i).x
												+ x
												+ snowflakes.get(i).snowFlakeWidth
												/ 2,
										snowflakes.get(i).y
												+ snowflakes.get(i).snowFlakeHeigth
												/ 2);
					} else {
						int x = (int) (horizontalSpeed * Math.random());
						snowflakes.get(i).savedMatrix.postTranslate(x, 0);
						snowflakes.get(i).x += x;
						snowflakes.get(i).savedMatrix
								.postRotate(
										2,
										snowflakes.get(i).x
												- x
												+ snowflakes.get(i).snowFlakeWidth
												/ 2,
										snowflakes.get(i).y
												+ snowflakes.get(i).snowFlakeHeigth
												/ 2);

					}

				}

				if (snowflakes.get(i).y > height + 5) {
					snowflakes.remove(i);
					continue;
				}
			}

			if (snowflakes.size() <= maxNumber) {
				if (lastTimeAddedSnowFlake < 0) {
					lastTimeAddedSnowFlake = height / verticalSpeed
							/ maxNumber;
					addSnowflake();

				} else {
					lastTimeAddedSnowFlake -= 1;
				}

			}
			drawSnowFlakes(canvas, snowflakes);
		}
	}
	
	// Helper function which is drawing snowflakes
	private void drawSnowFlakes(Canvas canvas, List<SnowFlakes> circles) {
		for (SnowFlakes point : circles) {
			switch (point.snowFlakeType) {
			case 1:
				canvas.drawBitmap(snowFlakeOne, point.savedMatrix, null);
				break;
			case 2:
				canvas.drawBitmap(snowFlakeTwo, point.savedMatrix, null);
				break;
			case 3:
				canvas.drawBitmap(snowFlakeThree, point.savedMatrix, null);
				break;
			}
		}
	}
	// helper function which is adding snowflakes
	private void addSnowflake() {
		int x = 0;
		if (leftCount > rightCount) {
			x = (int) (width / 2 * Math.random() + width / 2);
			rightCount++;
		} else {
			x = (int) (width / 2 * Math.random());
			leftCount++;
		}
		if (leftCount == rightCount) {
			leftCount = 0;
			rightCount = 0;
		}
		int y = 0;
		double random = Math.random();
		int type = 1;
		if (random > 0.66f)
			type = 2;
		else if(random > 0.33f)
			type = 3;
		snowflakes.add(new SnowFlakes(String.valueOf(snowflakes.size() + 1), x,
				y, type, new Matrix()));
	}
	// helper snowflakes class
	public class SnowFlakes {
		String text;
		float x;
		float y;
		boolean moveVertically;
		int snowFlakeType;
		Matrix savedMatrix;
		int snowFlakeWidth;
		int snowFlakeHeigth;

		public SnowFlakes(String text, float x2, float y2, int type, Matrix mat) {
			this.text = text;
			this.x = x2;
			this.y = y2;
			savedMatrix = mat;
			mat.postTranslate(x2, y2);
			snowFlakeType = type;
			moveVertically=Math.random()>0.5f;

			switch (type) {
			case 1:
				snowFlakeHeigth = snowFlakeOne.getHeight();
				snowFlakeWidth = snowFlakeOne.getWidth();
				break;
			case 2:
				snowFlakeHeigth = snowFlakeTwo.getHeight();
				snowFlakeWidth = snowFlakeTwo.getWidth();
				break;
			case 3:
				snowFlakeHeigth = snowFlakeThree.getHeight();
				snowFlakeWidth = snowFlakeThree.getWidth();
				break;
			}
		}
	}
}
