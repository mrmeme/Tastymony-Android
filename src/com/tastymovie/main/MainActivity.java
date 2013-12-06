package com.tastymovie.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	// the Rotten Tomatoes API key of your application! get this from their website
    private static final String API_KEY = "8mqwe6srkhybvyuyy8qaur8a"; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final Button loginButton = (Button) findViewById(R.id.btn_search);
		  loginButton.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			  new Thread(new Runnable() {
				  public void run() {
				   
			  HttpClient httpclient = new DefaultHttpClient();
	            HttpResponse response;
	            String responseString = null;
	            try
	            {
	                // make a HTTP request
	                response = httpclient.execute(new HttpGet("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + ((EditText) findViewById(R.id.et_search)).getText().toString() + "&page_limit=1"));
	                StatusLine statusLine = response.getStatusLine();
	                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
	                {
	                    // request successful - read the response and close the connection
	                    ByteArrayOutputStream out = new ByteArrayOutputStream();
	                    response.getEntity().writeTo(out);
	                    out.close();
	                    responseString = out.toString();
	                    JSONObject jsonResponse = new JSONObject(responseString);

	                    // fetch the array of movies in the response
	                    JSONArray movies = jsonResponse.getJSONArray("movies");
	                        JSONObject movie = movies.getJSONObject(0);
	                    
	                    Intent intent = new Intent(MainActivity.this, Choice.class);
	        			intent.putExtra("movie", movie.getString("id"));
	        			startActivity(intent);
	                }
	                else
	                {
	                    // request failed - close the connection
	                    response.getEntity().getContent().close();
	                    throw new IOException(statusLine.getReasonPhrase());
	                }
	            }
	            catch (Exception e)
	            {
	                Log.d("Test", "Couldn't make a successful request!");
	            }
				  }
			  }).start();
			
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
