package br.com.futtrackerapp.activity;

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
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Video;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class ExibeVideo extends Activity {
	private int id_video;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_video);
		int[] atribs = getIntent().getIntArrayExtra("id_video");
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Vídeo");
		
		id_video = atribs[0];
		Video video = GetVideo(id_video);

		VideoView vidView = (VideoView)findViewById(R.id.myVideo);
	
		//String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		//String vidAddress = "http://200.131.37.199/output.mp4";
		//String vidAddress = "http://200.131.34.46/seq_hotel.avi";
		String vidAddress = "http://200.131.34.46/" + video.getId() + ".mp4";
		Uri vidUri = Uri.parse(vidAddress);
		
		vidView.setVideoURI(vidUri);
		
		MediaController vidControl = new MediaController(this);
		vidView.setMediaController(vidControl);
		
		vidView.start();
		
	}
	
	private Video GetVideo(int id){
		Video video = null;
		
		new AsyncTask<Integer, Void, Video>() {

			@Override
			protected Video doInBackground(Integer... params) {
				Video video = null;
				try {
					video = VideoREST.getVideo(params[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return video;
			}

		}.execute(id_video);
		return video;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
