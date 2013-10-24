package com.android.jsonparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class JSONParserActivity extends ListActivity {
	
	private static String url = "http://10.0.2.2/android-json-parsing/person.php";
	
	private static final String KEY_ID = "id";
	private static final String KEY_NAMA = "nama";
	
	JSONArray person = null;
	ArrayList<HashMap<String, String>> list_person = new ArrayList<HashMap<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jsonparser);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = jsonParser.GetJSON(url);
		
		try {
			person = json.getJSONArray("person");
			
			for (int i = 0; i < person.length(); i++) {
				// JSONObject jObj = person.getJSONObject(i);
				JSONObject jObj = person.optJSONObject(i);
				
				// String id = jObj.getString(KEY_ID);
				// String nama = jObj.getString(KEY_NAMA);
				String id = jObj.optString("id");
				String nama = jObj.optString("nama");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put(KEY_ID, id);
				map.put(KEY_NAMA, nama);
				list_person.add(map);
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.ListViewAdapter();
	}

	private void ListViewAdapter() {
		
		ListAdapter adapter = new SimpleAdapter(this, list_person, R.layout.list_person, 
				new String[] {KEY_ID, KEY_NAMA}, new int[] {R.id.kode, R.id.nama});
		
		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String kode = ((TextView) view.findViewById(R.id.kode)).getText().toString();
				
				Intent in = new Intent(JSONParserActivity.this, DetailPerson.class);
				in.putExtra(KEY_ID, kode);
				startActivity(in);

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jsonparser, menu);
		return true;
	}

}
