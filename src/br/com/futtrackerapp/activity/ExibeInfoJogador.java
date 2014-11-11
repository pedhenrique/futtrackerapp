package br.com.futtrackerapp.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Jogador;
import br.com.futtrackerapp.fragment.ExibeGraficoJogadorFragment;
import br.com.futtrackerapp.fragment.ExibeInfoJogadorFragment;
import br.com.futtrackerapp.util.TabListener;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.JogadorREST;

/**
 * @author LuísCláudio
 * email: luinascimento.comp@gmail.com
 * 
 * 
 * 
 * */
public class ExibeInfoJogador extends Activity{
	private int id_time;
	private int id_jogador;
	public static ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.exibe_info_jogador);
		
		int[] atribs = getIntent().getIntArrayExtra("dados_jogador");

		id_time = atribs[0];
		id_jogador = atribs[1];
		
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setTitle("Jogador");
		actionBar.setIcon(R.drawable.atletasemfoto2);
		
		Tab tab = actionBar
				.newTab()
				.setText("Informações")
				.setTabListener(
						new TabListener<ExibeInfoJogadorFragment>(this,
								"info_jogador", ExibeInfoJogadorFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("Estatísticas")
				.setTabListener(
						new TabListener<ExibeGraficoJogadorFragment>(this,
								"grafico_jogador",
								ExibeGraficoJogadorFragment.class));
		actionBar.addTab(tab);
		
		
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
