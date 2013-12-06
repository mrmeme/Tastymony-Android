package com.tastymovie.main;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Choice extends Activity {

	// the Rotten Tomatoes API key of your application! get this from their website
    private static final String API_KEY = "8mqwe6srkhybvyuyy8qaur8a"; 

    // the number of movies you want to get in a single request to their web server
    private static final int MOVIE_PAGE_LIMIT = 3;    
    
    private static String MOVIE;

	private static  Button btn_movie1;
	private static  Button btn_movie2;
	private static  Button btn_movie3;
	private static  TextView tv_title1;
	private static  TextView tv_title2;
	private static  TextView tv_title3;
	private static  TextView tv_synopsis1;
	private static  TextView tv_synopsis2;
	private static  TextView tv_synopsis3;
	private static  TextView tv_date1;
	private static  TextView tv_date2;
	private static  TextView tv_date3;
	private static  ImageView iv_movie1;
	private static  ImageView iv_movie2;
	private static  ImageView iv_movie3;
	private static 	Bitmap[] bitTab;
	private static LinearLayout ll_movie1 ;//= (LinearLayout) findViewById(R.id.ll_movie1);
	private static LinearLayout ll_movie2 ;// = (LinearLayout) findViewById(R.id.ll_movie2);
	private static LinearLayout ll_movie3 ;//= (LinearLayout) findViewById(R.id.ll_movie3);
	private static 	String[] idMovies = new String[3];
	private static LinearLayout ll_wait ;//= (LinearLayout) findViewById(R.id.ll_waiting);
	
	
	protected ProgressDialog progress;
    final Handler progressHandler = new Handler(){
		
		public void handleMessage(Message msg){
			progress.dismiss();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice);
		
		Intent intent = this.getIntent();
		MOVIE = intent.getStringExtra("movie");
		ll_wait = (LinearLayout) findViewById(R.id.ll_waiting);
		btn_movie1 = (Button) findViewById(R.id.btn_movie1);
		btn_movie2 = (Button) findViewById(R.id.btn_movie2);
		btn_movie3 = (Button) findViewById(R.id.btn_movie3);
		tv_title1 = (TextView) findViewById(R.id.tv_title1);
		tv_title2 = (TextView) findViewById(R.id.tv_title2);
		tv_title3 = (TextView) findViewById(R.id.tv_title3);
		tv_synopsis1 = (TextView) findViewById(R.id.tv_synopsis1);
		tv_synopsis2 = (TextView) findViewById(R.id.tv_synopsis2);
		tv_synopsis3 = (TextView) findViewById(R.id.tv_synopsis3);
		tv_date1 = (TextView) findViewById(R.id.tv_date1);
		tv_date2 = (TextView) findViewById(R.id.tv_date2);
		tv_date3 = (TextView) findViewById(R.id.tv_date3);
		iv_movie1 = (ImageView) findViewById(R.id.iv_movie1);
		iv_movie2 = (ImageView) findViewById(R.id.iv_movie2);
		iv_movie3 = (ImageView) findViewById(R.id.iv_movie3);
		
		ll_movie1 = (LinearLayout) findViewById(R.id.ll_movie1);
		ll_movie2 = (LinearLayout) findViewById(R.id.ll_movie2);
		ll_movie3 = (LinearLayout) findViewById(R.id.ll_movie3);
		
		Button btn_more1 = (Button) findViewById(R.id.btn_more1);
		Button btn_more2 = (Button) findViewById(R.id.btn_more2);
		Button btn_more3 = (Button) findViewById(R.id.btn_more3);
		
		bitTab = new Bitmap[3];
		ll_movie1.setVisibility(LinearLayout.GONE);
		  ll_movie2.setVisibility(LinearLayout.GONE);
		  ll_movie3.setVisibility(LinearLayout.GONE);
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
		
		btn_more1.setOnClickListener(new OnClickListener() {					
			  @Override
			  public void onClick(View v) {
				  ll_wait.setVisibility(LinearLayout.VISIBLE);
				  new RequestTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"+ idMovies[0] +"/similar.json?apikey="+ API_KEY +"&limit=3");//http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + MOVIE + "&page_limit=" + MOVIE_PAGE_LIMIT);
					
				}
			});
		btn_more2.setOnClickListener(new OnClickListener() {					
			  @Override
			  public void onClick(View v) {
				  ll_wait.setVisibility(LinearLayout.VISIBLE);
				  new RequestTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"+ idMovies[1] +"/similar.json?apikey="+ API_KEY +"&limit=3");//http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + MOVIE + "&page_limit=" + MOVIE_PAGE_LIMIT);
					
				}
			});
		btn_more3.setOnClickListener(new OnClickListener() {					
			  @Override
			  public void onClick(View v) {
				  ll_wait.setVisibility(LinearLayout.VISIBLE);
				  new RequestTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"+ idMovies[2] +"/similar.json?apikey="+ API_KEY +"&limit=3");//http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + MOVIE + "&page_limit=" + MOVIE_PAGE_LIMIT);
					
				}
			});
		
		new RequestTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies/"+ MOVIE +"/similar.json?apikey="+ API_KEY +"&limit=3");//http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + MOVIE + "&page_limit=" + MOVIE_PAGE_LIMIT);
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void refreshMoviesList(String[] movieTitles , String[] movieSynopsis , String[] thumbnailTitles , String[] dateTitles)
    {
		btn_movie1.setText(movieTitles[0]);
		btn_movie2.setText(movieTitles[1]);
		btn_movie3.setText(movieTitles[2]);
		tv_title1.setText(movieTitles[0]);
		tv_title2.setText(movieTitles[1]);
		tv_title3.setText(movieTitles[2]);
		tv_synopsis1.setText(movieSynopsis[0]);
		tv_synopsis2.setText(movieSynopsis[1]);
		tv_synopsis3.setText(movieSynopsis[2]);
		tv_date1.setText(dateTitles[0]);
		tv_date2.setText(dateTitles[1]);
		tv_date3.setText(dateTitles[2]);
		
		//progress = ProgressDialog.show(Choice.this, null, "Authentication in progress...", true);
		class OneShotTask implements Runnable {
	        String[] thumbnailTitles;
	        OneShotTask(String[] thumbnailTitle) { thumbnailTitles = thumbnailTitle; }
	        public void run() {
	        	try {
	        		URL url = new URL(thumbnailTitles[0]);
	        		bitTab[0] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
	        		url = new URL(thumbnailTitles[1]);
	        		bitTab[1] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
	        		url = new URL(thumbnailTitles[2]);
	        		bitTab[2] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
	        		
	        		//progressHandler.sendMessage(progressHandler.obtainMessage());	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
		Thread t = new Thread(new OneShotTask(thumbnailTitles));
	    t.start();
	    try {
	        t.join();
	    } catch (InterruptedException e) {
	        // ...
	    }
	    
	    iv_movie1.setImageBitmap(bitTab[0]);
	    iv_movie2.setImageBitmap(bitTab[1]);
	    iv_movie3.setImageBitmap(bitTab[2]);
	    
	    ll_movie1.setVisibility(LinearLayout.VISIBLE);
		  ll_movie2.setVisibility(LinearLayout.GONE);
		  ll_movie3.setVisibility(LinearLayout.GONE);
		  ll_wait.setVisibility(LinearLayout.GONE);
//		new Thread( new Runnable(){
//			@Override
//			public void run() {
//				try {
//					iv_movie1.setImageBitmap(BitmapFactory.decodeStream((new URL(thumbnailTitles[0])).openConnection().getInputStream()));
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}			
//			}
//		}).start();
		
		
        //moviesList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));
    }
	
	private class RequestTask extends AsyncTask<String, String, String>
    {
        // make a request to the specified url
        @Override
        protected String doInBackground(String... uri)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try
            {
                // make a HTTP request
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {
                    // request successful - read the response and close the connection
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
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
            return responseString;
        }

        // if the request above completed successfully, this method will 
        // automatically run so you can do something with the response
        @Override
        protected void onPostExecute(String response)
        {
            super.onPostExecute(response);

            if (response != null)
            {
                try
                {
                    // convert the String response to a JSON object,
                    // because JSON is the response format Rotten Tomatoes uses
                    JSONObject jsonResponse = new JSONObject(response);

                    // fetch the array of movies in the response
                    JSONArray movies = jsonResponse.getJSONArray("movies");

                    // add each movie's title to an array
                    String[] movieTitles = new String[movies.length()];
                    String[] dateTitles = new String[movies.length()];
                    String[] synopsisTitles = new String[movies.length()];
                    String[] thumbnailTitles = new String[movies.length()];
                    for (int i = 0; i < movies.length() && i < MOVIE_PAGE_LIMIT; i++)
                    {
                        JSONObject movie = movies.getJSONObject(i);
                        movieTitles[i] = movie.getString("title");
                        dateTitles[i] = movie.getString("year");
                        if(movie.names().toString().contains("critics_consensus"))
                        	synopsisTitles[i] = movie.getString("critics_consensus");
                        else
                        	synopsisTitles[i]="No synopsis found";
                        thumbnailTitles[i] = movie.getJSONObject("posters").getString("detailed");
                        idMovies[i] = movie.getString("id");
                    }

                    // update the UI
                    refreshMoviesList(movieTitles, synopsisTitles, thumbnailTitles , dateTitles);
                }
                catch (JSONException e)
                {
                    Log.d("Test", "Failed to parse the JSON response!");
                }
            }
        }
    }
}
