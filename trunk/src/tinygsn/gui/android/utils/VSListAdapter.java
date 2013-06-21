package tinygsn.gui.android.utils;

import java.util.List;
import tinygsn.controller.AndroidControllerListVSNew;
import tinygsn.gui.android.ActivityListVSNew;
import tinygsn.gui.android.ActivityViewDataNew;
import tinygsn.gui.android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This list adapter for the ListActivity of Virtual Sensors UI
 * @author Do Ngoc Hoan (hoan.do@epfl.ch)
 *
 */
public class VSListAdapter extends ArrayAdapter<VSRow> {

	public static final String EXTRA_VS_NAME = "vs_name";
	private int resource;
	private LayoutInflater inflater;
	private Context context;
	static int TEXT_SIZE = 8;
	AndroidControllerListVSNew controller;
	ActivityListVSNew activityListVSNew;

	public VSListAdapter(Context ctx, int resourceId, List<VSRow> objects,
			AndroidControllerListVSNew controller, ActivityListVSNew activityListVSNew) {

		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		context = ctx;
		this.controller = controller;
		this.activityListVSNew = activityListVSNew;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* create a new view of my layout and inflate it in the row */
		convertView = (LinearLayout) inflater.inflate(resource, null);

		/* Extract the city's object to show */
		final VSRow vs = getItem(position);

		/* Take the TextView from layout and set the city's name */
		TextView txtName = (TextView) convertView.findViewById(R.id.vs_name);
		txtName.setText(vs.getName());

		final Switch runningSwitch = (Switch) convertView
				.findViewById(R.id.enableSwitch);
		runningSwitch.setTextOn("Running");
		runningSwitch.setTextOff("Disabled");
		// runningSwitch.setTextSize(TEXT_SIZE); //doesn't work
		runningSwitch.setChecked(vs.isRunning());
		runningSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				controller.startStopVS(vs.getName(), runningSwitch.isChecked());

				String state = "enabled";
				if (runningSwitch.isChecked() == false)
					state = "disabled";
				Toast.makeText(context,
						vs.getName() + " is " + state + " successfully!",
						Toast.LENGTH_SHORT).show();
			}
		});

		TextView txtWiki = (TextView) convertView.findViewById(R.id.latest_values);
		txtWiki.setText(vs.getLatestValue());

		ImageButton view = (ImageButton) convertView.findViewById(R.id.view);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityViewData(vs.getName());
			}
		});

		ImageButton edit = (ImageButton) convertView.findViewById(R.id.config);
		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Config has not been implemented!", Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:
							controller.startStopVS(vs.getName(), false);
							controller.deleteVS(vs.getName());
							Toast.makeText(context, vs.getName() + " is deleted!",
									Toast.LENGTH_SHORT).show();
							activityListVSNew.setUpController();

							break;
						case DialogInterface.BUTTON_NEGATIVE:
							break;
						}
					}
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Are you sure to delete \'" + vs.getName() + "\'?")
						.setPositiveButton("Yes", dialogClickListener)
						.setNegativeButton("No", dialogClickListener).show();
			}
		});

		return convertView;

	}

	protected void startActivityViewData(String vsName) {
		Intent myIntent = new Intent(context, ActivityViewDataNew.class);
		myIntent.putExtra(EXTRA_VS_NAME, vsName);
		
		context.startActivity(myIntent);
	}
}
