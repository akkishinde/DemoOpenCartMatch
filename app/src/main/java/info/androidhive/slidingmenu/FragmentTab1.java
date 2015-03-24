package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;

public class FragmentTab1 extends Fragment implements OnClickListener {

	EditText user, etpass;
	Button mSubmit, mRegister,b2;
	Intent myIntent;
	String tempforcheck;
	public String TAG = FragmentTab1.class.getSimpleName();
	public int count;
	public ArrayList<HashMap<String, String>> arraylist;
	public String username, pass;
	public int countjson = 0;

	// Progress Dialog
	private ProgressDialog pDialog;
	private static final String LOGIN_URL = "http://10.0.2.2:80/phpfile/login.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragmenttab1, container,
				false);
		mRegister = (Button) rootView.findViewById(R.id.button1);
		// buttonTest.setOnClickListener(this);
		user = (EditText) rootView.findViewById(R.id.ev1);
		etpass = (EditText) rootView.findViewById(R.id.ev2);
		b2 = (Button) rootView.findViewById(R.id.button2);
		b2.setOnClickListener(this);

		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			new AttemptLogin().execute();
			break;
		case R.id.button2:
			Intent intent1 = new Intent(getActivity(), Registration.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String username = user.getText().toString();
			String pass = etpass.getText().toString();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", pass));

				Log.d("request!", "starting");
				Log.d("username", username);
				Log.d("password", pass);

				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						params);

				// check your log for json response
				Log.d("Login attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Login Successful!", json.toString());
					Intent i = new Intent(getActivity(), MainActivity.class);

					startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();

		}

	}

}
