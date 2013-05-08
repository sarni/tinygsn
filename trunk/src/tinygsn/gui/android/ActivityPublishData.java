package tinygsn.gui.android;

import java.util.ArrayList;
import java.util.List;
import tinygsn.controller.AndroidControllerPublishData;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityPublishData extends Activity {
	private static final String TAG = "ActivityPublishData";
	public static String[] STRATEGY = { "Periodically (time)", "Periodically (values)", "On demand" };
	
	private AndroidControllerPublishData controller;
	static int TEXT_SIZE = 10;

	private Context context = this;
	private Spinner spinner_strategy;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish_data);
		controller = new AndroidControllerPublishData(this);
		
		loadStrategy();
	}

	public void loadStrategy() {
		spinner_strategy = (Spinner) findViewById(R.id.spinner_strategy);
		List<String> list = new ArrayList<String>();

		for (String s : STRATEGY) {
			list.add(s);
		}

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_strategy.setAdapter(dataAdapter);

		spinner_strategy.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				Toast.makeText(
						parent.getContext(),
						"The strategy \"" + parent.getItemAtPosition(pos).toString()
								+ "\" is selected.", Toast.LENGTH_SHORT).show();
				if (pos == 0) {
				}
				else if (pos == 1){
				}
				else{
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
	
}
