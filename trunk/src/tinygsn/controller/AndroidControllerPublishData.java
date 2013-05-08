package tinygsn.controller;

import java.util.ArrayList;
import tinygsn.beans.StreamElement;
import tinygsn.gui.android.ActivityListVS;
import tinygsn.gui.android.ActivityPublishData;
import tinygsn.model.vsensor.VirtualSensor;
import tinygsn.storage.StorageManager;
import tinygsn.storage.db.SqliteStorageManager;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AndroidControllerPublishData extends AbstractController {

	private ActivityPublishData view = null;

	private Handler handlerVS = null;

	private ArrayList<VirtualSensor> vsList = new ArrayList<VirtualSensor>();

	private static final String TAG = "AndroidControllerPublishData";

	public AndroidControllerPublishData(ActivityPublishData androidViewer) {
		this.view = androidViewer;
	}

	public void loadListVS() {
		SqliteStorageManager storage = new SqliteStorageManager(view);
		vsList = storage.getListofVS();
		ArrayList<String> vsListName = new ArrayList<String>();
		for (VirtualSensor vs : vsList) {
			vs.getConfig().setController(this);
			vsListName.add(vs.getConfig().getName());
		}

		Message msg = new Message();
		msg.obj = vsListName;
		handlerVS.sendMessage(msg);
	}

	@Override
	public void startLoadVSList() {
	}

	public void consume(StreamElement streamElement) {
	}

	@Override
	public Activity getActivity() {
		return view;
	}

	@Override
	public StorageManager getStorageManager() {
		return null;
	}
}