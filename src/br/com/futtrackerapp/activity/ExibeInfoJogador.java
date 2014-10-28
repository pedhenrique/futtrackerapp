package br.com.futtrackerapp.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Jogador;
import br.com.futtrackerapp.fragment.ExibeGraficoJogadorFragment;
import br.com.futtrackerapp.fragment.ExibeInfoJogadorFragment;
import br.com.futtrackerapp.util.TabListener;
import br.com.futtrackerapp.webservice.JogadorREST;

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


	
		
}
