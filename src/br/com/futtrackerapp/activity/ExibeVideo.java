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
import br.com.futtrackerapp.entidades.Comando;
import br.com.futtrackerapp.entidades.Video;
import br.com.futtrackerapp.util.Notificacao;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class ExibeVideo extends Activity {

	// Declaração de variaveis necessárias
	private int id_video;
	private MediaController vidControl;
	private VideoView vidView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_video);

		// Declaração de componentes da View
		vidView = (VideoView) findViewById(R.id.myVideo);

		// Instanciação de atributos necessários
		id_video = getIntent().getIntExtra("id_video", 0); // Pega o id do Video que o usuario escolheu
		vidControl = new MediaController(this); // Usado na reprodução do video

		// Seta ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Vídeo");
	}

	@Override
	protected void onStart() {
		super.onStart();

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
					String vidAddress = "http://200.131.37.199/"
							+ video.getNome();
					Uri vidUri = Uri.parse(vidAddress);
					vidView.setVideoURI(vidUri);
					vidView.setMediaController(vidControl);

					vidView.start();
				}
			}
		}.execute(id_video);
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
			new AsyncTask<Void, Void, Video>() {

				@Override
				protected Video doInBackground(Void... params) {
					Video v = null;
					try {
						Comando c = cdao.solicitaVideo();
						boolean pronto = false;
						do{
							v = VideoREST.getVideo(c.getIdVideo());
							if(v.isPronto2()){
								pronto = true;
							}
						}while(!pronto);											
					} catch (Exception e) {

						e.printStackTrace();
					}
					return v;
				}

				@Override
				protected void onPostExecute(Video result) {
					super.onPostExecute(result);
					
					Notificacao.geraNotificacao(getApplicationContext(), result);
				}
				
			}.execute();
			Toast.makeText(
					this,
					"Vídeo solicitado com sucesso. Por favor, aguarde seu processamento.",
					Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
