package info.androidhive.slidingmenu;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Full_Info extends Activity {
	String title;
	String description;
	String prize;
	int year;
	String position, id;
	String bitmap;
	String bitmap1, user;
	TextView txtrank, desc;
	ImageView imageView;
	ImageButton imagebtn;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_user = "http://192.168.0.107:80/phpfile/order_product.php";
	public static String URLs = "http://192.168.0.107:80/user/index.php";
	public static String Url = "http://192.168.0.107:80/phpfile/update.php";
	public static String Url1 = "http://192.168.0.107:80/phpfile/wishlist.php";
	ArrayList<HashMap<String, String>> arraylist;
	private static final String TAG_SUCCESS = "success";
	private DbHelper mHelper;
	private SQLiteDatabase dataBase;
	private boolean isUpdate;
	NetworkImageView itemImage;
	protected static final String TAG = null;

	public static String uri = "http://192.168.0.107:80/images/index.php";

	ArrayList<String> images;
	public static String url;

	String url1;

	NetworkImageView im1, im2, im3;
	String product_id;

	public static ArrayList<Drawable> drawable;
	CustomListAdapter adpter;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	public ArrayList<String> img = new ArrayList<String>(3);
	

	// ImageLoader imageLoader1 = new ImageLoader(this);
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.full_info);

		Intent i = getIntent();
		// Get the result of rank
		title = i.getStringExtra("name");
		description = i.getStringExtra("description");
		// Get the result of country
		bitmap = i.getStringExtra("image");
		year = i.getIntExtra("product_id", 0);

		// Get the result of population
		prize = i.getStringExtra("price");
		im1 = (NetworkImageView) findViewById(R.id.itemImage1);
		im2 = (NetworkImageView) findViewById(R.id.itemImage2);
		im3 = (NetworkImageView) findViewById(R.id.itemImage3);
		ImageButton imagrbtn = (ImageButton) findViewById(R.id.imageView2);
		ImageButton imagrbtn1 = (ImageButton) findViewById(R.id.imageView1);
		Log.i(TAG, "Before invoke");

		invokeWS(uri);

		Log.i(TAG, "after invoke");

		desc = (TextView) findViewById(R.id.desc);
		desc.setText("      " + description);

		txtrank = (TextView) findViewById(R.id.title);
		txtrank.setText("      " + title);

		itemImage = (NetworkImageView) findViewById(R.id.itemImage);
		itemImage.setImageUrl(bitmap, imageLoader);

		mHelper = new DbHelper(this);
		imagrbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				invokeW(URLs);

			}
		});
		
		im1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (img.get(0) != null) {
					itemImage.setImageUrl(img.get(0), imageLoader);
				}
				else {
					Toast.makeText(getBaseContext(), "Image Not Available",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		im2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (img.get(1) != null) {
					itemImage.setImageUrl(img.get(1), imageLoader);
				}
				else {
					Toast.makeText(getBaseContext(), "Image Not Available",
							Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		
		im3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (img.get(2) != null) {
					itemImage.setImageUrl(img.get(2), imageLoader);
				} else {
					Toast.makeText(getBaseContext(), "Image Not Available",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		imagrbtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				invokeWa(URLs);
			}
		});

		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new CreateNewUser().execute();
				Intent intent = new Intent(Full_Info.this, Final_Login.class);

				startActivity(intent);

			}

		});

	}

	JSONObject jsonobject;
	JSONArray jsonarray;

	private void invokeWS(final String uri2) {
		Log.i(TAG, "in invoke");
		// TODO Auto-generated method stub
		final AsyncHttpClient client = new AsyncHttpClient();
		client.put(uri2, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Error Occcured",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int statusCode, String response) {
				if (statusCode == 200)
					;
				Log.i(TAG, "Got Status 200");
				// Toast.makeText(getBaseContext(), "Success",
				// Toast.LENGTH_SHORT).show();
				// Log.i(TAG,response);
				// TODO Auto-generated method stub

				Uri myUri;

				try {

					JSONArray arr = new JSONArray(response);
			
					for (int i = 0; i < arr.length(); i++) {
						JSONObject newobj = arr.getJSONObject(i);

						if (newobj.getInt("product_id") == year) {
							// Toast.makeText(getBaseContext(),
							// "got prod id 28", Toast.LENGTH_SHORT).show();
							img.add(newobj.getString("image"));
						

						}
						else
							img.add(null);

					}

					im1.setImageUrl(img.get(0), imageLoader);
					im2.setImageUrl(img.get(1), imageLoader);
					im3.setImageUrl(img.get(2), imageLoader);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	private void invokeW(final String URLs) {
		Log.i(TAG, "in invoke");
		// TODO Auto-generated method stub
		final AsyncHttpClient client = new AsyncHttpClient();
		client.put(URLs, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Error Occcured",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int statusCode, String response) {
				if (statusCode == 200)
					;
				Log.i(TAG, "Got username");
				// Toast.makeText(getBaseContext(), "Success",
				// Toast.LENGTH_SHORT).show();
				// Log.i(TAG,response);
				// TODO Auto-generated method stub

				try {

					JSONArray arr = new JSONArray(response);
					Intent ii = getIntent();
					final AppController global = (AppController) getApplicationContext();
					user = global.getSession_id();

					Log.i(TAG, "global user" + user);
					for (int i = 0; i < arr.length(); i++) {
						JSONObject newobj = arr.getJSONObject(i);
						if (newobj.getString("username").equals(user)) {
							Log.i(TAG, "entered" + newobj.getString("username"));
							// Toast.makeText(getBaseContext(),
							// "got prod id 28", Toast.LENGTH_SHORT).show();
							new CreateNew().execute();

						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	private void invokeWa(final String URLs) {
		Log.i(TAG, "in invoke");
		// TODO Auto-generated method stub
		final AsyncHttpClient client = new AsyncHttpClient();
		client.put(URLs, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Error Occcured",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int statusCode, String response) {
				if (statusCode == 200)
					;
				Log.i(TAG, "Got username");
				// Toast.makeText(getBaseContext(), "Success",
				// Toast.LENGTH_SHORT).show();
				// Log.i(TAG,response);
				// TODO Auto-generated method stub

				try {

					JSONArray arr = new JSONArray(response);
					Intent ii = getIntent();
					final AppController global = (AppController) getApplicationContext();
					user = global.getSession_id();

					Log.i(TAG, "global user" + user);
					for (int i = 0; i < arr.length(); i++) {
						JSONObject newobj = arr.getJSONObject(i);
						if (newobj.getString("username").equals(user)) {
							Log.i(TAG, "entered" + newobj.getString("username"));
							// Toast.makeText(getBaseContext(),
							// "got prod id 28", Toast.LENGTH_SHORT).show();
							new CreateNewa().execute();

						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
			pDialog = new ProgressDialog(Full_Info.this);
			pDialog.setMessage("Registration Succesfully!");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String name = title;
			String model = bitmap;
			String price = prize;

			// String product_id=year;

			// Building Parameters
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("model", model));
			params.add(new BasicNameValuePair("price", price));

			// params.add(new BasicNameValuePair("product_id", product_id));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = JSONParser.makeHttpRequest(url_create_user,
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

	class CreateNew extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Full_Info.this);
			pDialog.setMessage("ADDED TO CART!");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			String model = bitmap;
			Log.i(TAG, "user value" + user);
			// String product_id=year;

			// Building Parameters
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("code", model));
			params.add(new BasicNameValuePair("username", user));

			// params.add(new BasicNameValuePair("product_id", product_id));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = JSONParser.makeHttpRequest(Url, "POST", params);
			Log.i(TAG, "Value pass" + params);
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

	class CreateNewa extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Full_Info.this);
			pDialog.setMessage("ADDED TO CART!");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			String model = bitmap;
			Log.i(TAG, "user value" + user);
			// String product_id=year;

			// Building Parameters
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("status", model));
			params.add(new BasicNameValuePair("username", user));

			// params.add(new BasicNameValuePair("product_id", product_id));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = JSONParser.makeHttpRequest(Url1, "POST", params);
			Log.i(TAG, "Value pass" + params);
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
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;

			// case R.id.action_cart:
			// onClick(imageView);
			// return true;
		default:
		}
		return super.onOptionsItemSelected(item);

	}

	public void onClick(View imageview) {
		// TODO Auto-generated method stub

		// Intent i = new Intent(getApplicationContext(), MyCart.class);

		// startActivity(i);

	}

}
