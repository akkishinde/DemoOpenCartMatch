package info.androidhive.slidingmenu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Splash extends Activity {
	Context context;
	Button b1, b2, b3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		getActionBar().setTitle("               A-mall        " );
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Splash.this, Login.class);
				startActivity(intent);

			}

		});

		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Splash.this, Registration.class);
				startActivity(intent);
			}

		});

		Button b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Splash.this, MainActivity.class);
				startActivity(intent);
			}

		});
	}

}
