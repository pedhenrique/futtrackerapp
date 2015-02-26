package br.com.futtrackerapp.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Comando;
import br.com.futtrackerapp.entidades.Video;
import br.com.futtrackerapp.fragment.ExibeGraficoJogadorFragment;
import br.com.futtrackerapp.fragment.ExibeInfoJogadorFragment;
import br.com.futtrackerapp.util.Notificacao;
import br.com.futtrackerapp.util.TabListener;
import br.com.futtrackerapp.webservice.ComandoREST;
import br.com.futtrackerapp.webservice.VideoREST;

public class ExibeInfoJogador extends FragmentActivity{
	public static ActionBar actionBar;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.exibe_info_jogador);
		
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
