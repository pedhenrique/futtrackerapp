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
import br.com.futtrackerapp.entidades.Time;
import br.com.futtrackerapp.util.AdapterListView;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.TimeREST;

public class ExibeListaTimes extends Activity implements OnItemClickListener {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_lista_times);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Lista de Times");
		listView = (ListView) findViewById(R.id.exibe_lista_times_lista);

		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		new AsyncTask<Void, Void, List<Time>>() {

			@Override
			protected List<Time> doInBackground(Void... params) {
				List<Time> lista = null;
				try {
					lista = TimeREST.getListaTime();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return lista;
			}

			@Override
			protected void onPostExecute(List<Time> lista) {
				super.onPostExecute(lista);

				if (lista == null) {
					Toast.makeText(getApplicationContext(), "Erro de rede!",
							Toast.LENGTH_LONG).show();
				} else {
					AdapterListView<Time> adaptador = new AdapterListView<>(
							getApplicationContext(), lista);
					listView.setAdapter(adaptador);
				}

				// listView.setOnItemClickListener((OnItemClickListener)
				// getApplicationContext());
			}
		}.execute();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Time time = (Time) listView.getItemAtPosition(position);

		startActivity(new Intent(this, ExibeListaJogadores.class).putExtra(
				"id_time", time.getId()));
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
