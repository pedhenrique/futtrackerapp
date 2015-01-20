package br.com.futtrackerapp.activity;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Comando;
import br.com.futtrackerapp.entidades.Video;
import br.com.futtrackerapp.util.AdapterListView;
import br.com.futtrackerapp.util.Notificacao;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class ExibeListaVideos extends Activity implements OnItemClickListener {

	private ListView listView;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_lista_videos); //Seta Layout da View
		
		// Seta ActionBar
		actionBar = getActionBar();
		actionBar.setTitle("Videos");
		actionBar.setSubtitle("Lista de Vídeos");
		actionBar.setIcon(R.drawable.logo_minas_grande);
		
		//Seta componentes da View
		listView = (ListView) findViewById(R.id.exibe_lista_videos_lista);
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		new AsyncTask<Void, Void, List<Video>>() {

			@Override
			protected List<Video> doInBackground(Void... params) {
				List<Video> lista = null;
				try {
					
					lista = VideoREST.getListaVideo();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return lista;
			}

			@Override
			protected void onPostExecute(List<Video> lista) {
				super.onPostExecute(lista);

				if (lista == null) {
					Toast.makeText(getApplicationContext(), "Erro de rede!",
							Toast.LENGTH_LONG).show();
				} else {
					AdapterListView<Video> adaptador = new AdapterListView<>(
							getApplicationContext(), lista);
					listView.setAdapter(adaptador);
				}
			}
		}.execute();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Video video = (Video) listView.getItemAtPosition(position);
		startActivity(new Intent(this, ExibeVideo.class).putExtra("id_video",
				video.getId()));
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
