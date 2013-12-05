package com.tastymovie.main;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Choice extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice);
		
		final Button btn_movie1 = (Button) findViewById(R.id.btn_movie1);
		final Button btn_movie2 = (Button) findViewById(R.id.btn_movie2);
		final Button btn_movie3 = (Button) findViewById(R.id.btn_movie3);
		final LinearLayout ll_movie1 = (LinearLayout) findViewById(R.id.ll_movie1);
		final LinearLayout ll_movie2 = (LinearLayout) findViewById(R.id.ll_movie2);
		final LinearLayout ll_movie3 = (LinearLayout) findViewById(R.id.ll_movie3);
		
		btn_movie1.setOnClickListener(new OnClickListener() {					
		  @Override
		  public void onClick(View v) {
			  ll_movie1.setVisibility(LinearLayout.VISIBLE);
			  ll_movie2.setVisibility(LinearLayout.GONE);
			  ll_movie3.setVisibility(LinearLayout.GONE);
			}
		});
		btn_movie2.setOnClickListener(new OnClickListener() {					
			  @Override
			  public void onClick(View v) {
				  ll_movie1.setVisibility(LinearLayout.GONE);
				  ll_movie2.setVisibility(LinearLayout.VISIBLE);
				  ll_movie3.setVisibility(LinearLayout.GONE);
				}
			});
		btn_movie3.setOnClickListener(new OnClickListener() {					
			  @Override
			  public void onClick(View v) {
				  ll_movie1.setVisibility(LinearLayout.GONE);
				  ll_movie2.setVisibility(LinearLayout.GONE);
				  ll_movie3.setVisibility(LinearLayout.VISIBLE);
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
