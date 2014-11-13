package br.com.futtrackerapp.activity;

import java.util.List;

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
import br.com.futtrackerapp.util.AdapterListView;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class ExibeVideo extends Activity {
	
	// Declaração de variaveis necessárias
	private int id_video;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_video);
		
		// Declaração de componentes da View
		final VideoView vidView = (VideoView)findViewById(R.id.myVideo);
		
		// Seta ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Vídeo");
		
		// Instanciação de atributos necessários
		id_video = getIntent().getIntExtra("id_video", 0); // Pega o id do Vídeo que o usuário escolheu
		final MediaController vidControl = new MediaController(this); //Usado na reprodução do vídeo
		
		// Busca e exibe Video
		new AsyncTask<Integer, Void, Video>() {
			Video video = null;
			
			@Override
			protected Video doInBackground(Integer... params) {
				video = null;
				try {
					video = VideoREST.getVideo(params[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return video;
			}
			
			@Override
			protected void onPostExecute(Video video) {
				super.onPostExecute(video);

				if (video == null) {
					Toast.makeText(getApplicationContext(), "Erro de rede!",
							Toast.LENGTH_LONG).show();
				} else {
					String vidAddress = "http://200.131.37.199/" + video.getNome() + ".mp4";
					Uri vidUri = Uri.parse(vidAddress);
					vidView.setVideoURI(vidUri);
					
					vidView.setMediaController(vidControl);
					
					vidView.start();					
				}
			}
		}.execute(id_video);

		
	
		//String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		//String vidAddress = "http://200.131.37.199/output.mp4";
		//String vidAddress = "http://200.131.34.46/seq_hotel.avi";
		//String vidAddress = "http://200.131.37.199/" + video.getNome() + ".mp4";
		//String vidAddress = "http://200.131.37.199/24.mp4";
		
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
