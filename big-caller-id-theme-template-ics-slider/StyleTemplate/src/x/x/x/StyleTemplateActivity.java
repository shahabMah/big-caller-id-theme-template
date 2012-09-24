package x.x.x;

import x.x.x.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class StyleTemplateActivity extends Activity {
	/** Called when the activity is first created. */
	public static final String INSTALL_NEW_STYLE = "com.wroclawstudio.screencaller.intent.INSTALL_NEW_STYLE";
	public static final String PACKAGE_NAME = "package_name";
	public static final String WIDTH = "width";
	public static final String HEIGTH = "heigth";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		boolean applicationVersion = false;
		// This part of code checks if version of BIG! caller ID is equal or above 140, this part is needed casue AnimationView was introduced in this version
		try {
			if (getPackageManager().getPackageInfo(
					"com.wroclawstudio.screencaller", 0).versionCode >= 140)
				applicationVersion = true;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent brodcast = new Intent();
		brodcast.setAction(INSTALL_NEW_STYLE);
		brodcast.putExtra(WIDTH, metrics.widthPixels);
		brodcast.putExtra(HEIGTH, metrics.heightPixels);
		brodcast.putExtra(PACKAGE_NAME, getString(R.string.package_name));
		if (applicationVersion)
			getBaseContext().sendBroadcast(brodcast);
	}
}