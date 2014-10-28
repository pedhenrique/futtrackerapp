package br.com.futtrackerapp.activity;


import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Jogador;
import br.com.futtrackerapp.entidades.Time;
import br.com.futtrackerapp.util.AdapterListView;
import br.com.futtrackerapp.webservice.TimeREST;

public class ExibeListaJogadores extends Activity implements OnItemClickListener{
	private ListView listView;
	private Integer id_time;
	private ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.exibe_lista_jogadores);
		
		actionBar = getActionBar();
		actionBar.setTitle("Time");
		actionBar.setSubtitle("Lista de Jogadores");
		actionBar.setIcon(R.drawable.logo_minas_grande);
		
		listView = (ListView) findViewById(R.id.exibe_lista_jogadores_lista);
		id_time = getIntent().getIntExtra("id_time", 0);
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		new AsyncTask<Integer, Void, Time>() {
			
			@Override
			protected Time doInBackground(Integer... params) {
				Time time = null;
				try {
					time = TimeREST.getTime(params[0]);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return time;
			}

			@Override
			protected void onPostExecute(Time time) {
				super.onPostExecute(time);
				if(time == null){
					Toast.makeText(getApplicationContext(), "Erro de rede!", Toast.LENGTH_LONG).show();
				}
				else{
					List<Jogador> listaJogadores = time.getJogadores();
					AdapterListView<Jogador> adaptador = new AdapterListView<>(getApplicationContext(), listaJogadores);
					
					listView.setAdapter(adaptador);
					actionBar.setTitle(time.getNome());
				}
				//listView.setOnItemClickListener((OnItemClickListener) getApplicationContext());
			}	
		}.execute(id_time);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Jogador jogador = (Jogador) listView.getItemAtPosition(position);
		int[] atrib = { id_time, jogador.getId() };
		
		startActivity(new Intent(this, ExibeInfoJogador.class).putExtra(
				"dados_jogador", atrib));
	}

	

	
	
	
}
