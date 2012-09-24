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
	private int width; // screen width
	private int height; // screen height

	// BIG! caller ID is importing AnimationView using this constructor with
	// context paramet. Other constructors are unaviable
	public AnimationView(Context context) {
		super(context);

		Resources res;
		try {
			res = getContext().getPackageManager().getResourcesForApplication(packageName); 

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

	// SnowFlake algorithm is implemented in onDraw function, which is executed
	// 40 times per second in BIG! caller ID
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

	}

}
