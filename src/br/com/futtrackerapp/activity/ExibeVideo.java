package br.com.futtrackerapp.activity;

import br.com.futtrackerapp.R;
import br.com.futtrackerapp.webservice.ComandoREST;
import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ExibeVideo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_video);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Vídeo");
		
		VideoView vidView = (VideoView)findViewById(R.id.myVideo);
		
		
		
		//String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		String vidAddress = "http://200.131.37.199/output.mp4";
		//String vidAddress = "http://200.131.34.46/seq_hotel.avi";
		Uri vidUri = Uri.parse(vidAddress);
		
		vidView.setVideoURI(vidUri);
		
		MediaController vidControl = new MediaController(this);
		vidView.setMediaController(vidControl);
		
		vidView.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*
		 * getMenuInflater().inflate(R.menu.main, menu); return true;
		 */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_gravar_jogada:
			
			final ComandoREST cdao = new ComandoREST();
			new AsyncTask<Void, Void, Void>(){

				@Override
				protected Void doInBackground(Void... params) {
					try {
						cdao.solicitaVideo();
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return null;
				}

			}.execute();
			Toast.makeText(this, "Vídeo solicitado com sucesso. Por favor, aguarde seu processamento.", Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
