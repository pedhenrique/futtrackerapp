package br.com.futtrackerapp.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Comando;
import br.com.futtrackerapp.entidades.Video;
import br.com.futtrackerapp.util.Notificacao;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class TelaInicial extends ListActivity{
	private ListView listView;
	private ArrayAdapter<String> adaptador = null;
	private static final String[] menu = new String[]{
		"Lista de Times", "Vídeos","Sair"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
	}	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch(position){
		case 0:
			startActivity(new Intent(this, ExibeListaTimes.class));break;
		case 1:
			startActivity(new Intent(this, ExibeListaVideos.class));break;
		case 2:
			finish();
		default:
			finish();
		
		}
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
