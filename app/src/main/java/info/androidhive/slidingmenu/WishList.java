package info.androidhive.slidingmenu;

import info.androidhive.customlistviewvolley.app.AppController;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.android.volley.toolbox.ImageLoader;

public class WishList extends Activity {
	String title, description;

	String price;
	String bitmap;
	String position;
	private DbHelper mHelper;
	private SQLiteDatabase dataBase;

	private ArrayList<String> userId = new ArrayList<String>();
	private ArrayList<String> user_fName = new ArrayList<String>();
	private ArrayList<String> user_lName = new ArrayList<String>();
//	private ArrayList<String>image = new ArrayList<String>();

	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	String showString = "";
	TextView txtrank;
	private ListView userList;
	private AlertDialog.Builder build;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wishlist);
		// HashMap<String,String> map = (HashMap<String,String>)
		// getIntent().getExtras().get("mylist");
		userList = (ListView) findViewById(R.id.listView2);
		mHelper = new DbHelper(this);
		Intent i = getIntent();
		// Get the result of rank
		title = i.getStringExtra("name");
		// Get the result of country
		bitmap = i.getStringExtra("image");
		// Get the result of population
		price = i.getStringExtra("price");
		description = i.getStringExtra("description");
		// Get the result of flag

		

		/*
		 * Button b1 = (Button) findViewById(R.id.button1);
		 * b1.setOnClickListener(new View.OnClickListener() {
		 * 
		 * public void onClick(View arg0) { // TODO Auto-generated method stub
		 * // Intent intent = new Intent(MyCart.this, TabF.class); //
		 * startActivity(intent); Toast.makeText(getBaseContext(), "" + mylist,
		 * Toast.LENGTH_SHORT).show(); }
		 * 
		 * });
		 */

		userList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(), Full_Info.class);
				i.putExtra("name", title);
				i.putExtra("price", price);
				i.putExtra("image", bitmap);
				i.putExtra("description", description);
				i.putExtra("update", true);
				startActivity(i);

			}
		});
		userList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				build = new AlertDialog.Builder(WishList.this);
				build.setTitle("Delete " + user_fName.get(arg2) + " "
						+ user_lName.get(arg2));
				build.setMessage("Do you want to delete ?");
				build.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

								Toast.makeText(
										getApplicationContext(),
										user_fName.get(arg2) + " "
												+ user_lName.get(arg2)
												+ " is deleted.", 3000).show();

								dataBase.delete(
										DbHelper.TABLE_NAME,
										DbHelper.KEY_ID + "="
												+ userId.get(arg2), null);
								displayData();
								dialog.cancel();
							}
						});

				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog alert = build.create();
				alert.show();

				return true;
			}
		});

	}

	protected void onResume() {
		displayData();
		super.onResume();
	}

	private void displayData() {
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
				+ DbHelper.TABLE_NAME, null);

		userId.clear();
		user_fName.clear();
		user_lName.clear();
		//image.clear();
		if (mCursor.moveToFirst()) {
			do {
				userId.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_ID)));
				user_fName.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_FNAME)));
				user_lName.add(mCursor.getString(mCursor
						.getColumnIndex(DbHelper.KEY_LNAME)));
				//image.add(mCursor.getString(mCursor
					//	.getColumnIndex(DbHelper.KEY_LNAME)));
			} while (mCursor.moveToNext());
		}
	DisplayAdapter disadpt = new DisplayAdapter(WishList.this, userId,user_fName, user_lName);
		userList.setAdapter(disadpt);
		mCursor.close();
	}
}
