package tinygsn.gui.android;

import android.os.Bundle;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;

public class ActivityHelp extends SherlockActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Sherlock___Theme_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		String text = "TinyGSN allows you to collect the sensor data accessible through Android-powered devices, process the data according to your customed specification, exchange the data with the Global Sensor Network (GSN) servers."
				+ "\n\n\tMain functionalities:"
				+ "\n\t\t* Collect sensor data: "
				+ "\n\t\t* View and share sensor data: "
				+ "\n\t\t* Publish sensor data to GSN servers: "
				+ "\n\t\t* Get sensor data from GSN servers: ";
		((TextView) findViewById(R.id.text)).setText(text);
	}
}
