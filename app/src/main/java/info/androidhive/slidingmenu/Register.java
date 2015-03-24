package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.slidingmenu.TextCaptcha.TextOptions;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Register extends Activity {

	protected static final String MathOptions = null;
	Button Btn, Button;

	ImageView im;
	Button btn;
	TextView ans;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();

	// / tere system ka IP de ipconfig type kar cmd me .and cmd open kar by
	// right clicking run administractore
	// karke and Port no : ke bad

	private static String url_create_user = "http://10.0.2.2:80/phpfile/order_detail.php";
	private static final String TAG_SUCCESS = "success";

	EditText et1, et2, et3, et4, et5, et6, et7, et8, et9;
	Button b1, b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		im = (ImageView) findViewById(R.id.imageView1);
		btn = (Button) findViewById(R.id.button2);
		// ans = (TextView) findViewById(R.id.textView1);

		// addListenerOnButton();
		ActionBar actionBar = getActionBar();
		actionBar.setStackedBackgroundDrawable(getResources().getDrawable(
				R.drawable.arrow_left_white));

		et1 = (EditText) findViewById(R.id.ev1);
		et2 = (EditText) findViewById(R.id.ev2);
		et3 = (EditText) findViewById(R.id.ev3);
		et4 = (EditText) findViewById(R.id.ev4);
		et6 = (EditText) findViewById(R.id.ev6);
		et7 = (EditText) findViewById(R.id.ev7);
		et9 = (EditText) findViewById(R.id.ev12);
		b1 = (Button) findViewById(R.id.button1);
		// b2 = (Button) findViewById(R.id.button2);

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// creating new product in background thread
				new CreateNewUser().execute();
				Intent intent=new Intent(Register.this,Final_Login.class);
				startActivity(intent);

			}
		});

		/*
		 * int position = editText.getText().length(); Editable editObj =
		 * editText.getText(); Selection.setSelection(editObj, position);
		 */
		Captcha c = new TextCaptcha(300, 100, 5,
				TextOptions.NUMBERS_AND_LETTERS);
		im.setImageBitmap(c.image);
		im.setLayoutParams(new LinearLayout.LayoutParams(c.getWidth() * 1, c
				.getHeight() * 1));
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Captcha c = new TextCaptcha(300, 100, 5,
						TextOptions.NUMBERS_AND_LETTERS);
				im.setImageBitmap(c.image);
				im.setLayoutParams(new LinearLayout.LayoutParams(
						c.getWidth() * 1, c.getHeight() * 1));
				// ans.setText(c.answer);
			}
		});
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		Button = (Button) findViewById(R.id.button1);

		Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Register.this, "Registration Succesfully!",
						Toast.LENGTH_LONG).show();

			}

		});

	}

	class CreateNewUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Register.this);
			pDialog.setMessage("Saving Data..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String firstname = et1.getText().toString();
			String lastname = et2.getText().toString();
			String email = et3.getText().toString();
			String telephone = et4.getText().toString();
			String payment_postcode = et9.getText().toString();
			String payment_address_1 = et6.getText().toString();
			String payment_city = et7.getText().toString();

			// Building Parameters
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("firstname", firstname));
			params.add(new BasicNameValuePair("lastname", lastname));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("telephone", telephone));
			params.add(new BasicNameValuePair("payment_postcode",
					payment_postcode));
			params.add(new BasicNameValuePair("payment_city", payment_city));

			params.add(new BasicNameValuePair("payment_address_1",
					payment_address_1));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_user,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// Toast.makeText(getApplicationContext(),
					// "Registration Successfully", Toast.LENGTH_LONG).show();
				} else {
					// failed to create product
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
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
