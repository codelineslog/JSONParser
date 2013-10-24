package com.android.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPerson extends Activity{

	private static final String KEY_ID = "id";
	JSONArray person = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_person);
		
		Intent in = getIntent();
		String idperson = in.getStringExtra(KEY_ID);
		String url = "http://10.0.2.2/android-json-parsing/person-detail.php?id="+idperson;
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = jsonParser.GetJSON(url);
		
		try {
			person = json.getJSONArray("person");
			
			for (int i = 0; i < person.length(); i++) {
				//JSONObject jObj = person.getJSONObject(i);
				JSONObject jObj = person.optJSONObject(i);
				
				TextView txt_nama = (TextView) findViewById(R.id.nama);
				TextView txt_telp = (TextView) findViewById(R.id.telepon);
				TextView txt_alamat = (TextView) findViewById(R.id.alamat);
				
				/*
				String nama = jObj.getString("nama");
				String telp = jObj.getString("telepon");
				String alamat = jObj.getString("alamat");
				*/
				
				String nama = jObj.optString("nama");
				String telp = jObj.optString("telepon");
				String alamat = jObj.optString("alamat");
				
				txt_nama.setText(nama);
				txt_telp.setText(telp);
				txt_alamat.setText(alamat);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
