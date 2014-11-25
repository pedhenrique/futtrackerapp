package br.com.futtrackerapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.futtrackerapp.activity.TelaInicial;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView imagem = (ImageView) findViewById(R.id.main_imagem);
		final TextView texto = (TextView) findViewById(R.id.main_texto);
	
		new Thread(){

				@Override
				public void run() {
					super.run();
					try {
						runOnUiThread(new Runnable() {
						     @Override
						     public void run() {
						    	texto.setText("DESENVOLVIDO POR");
								imagem.setImageResource(R.drawable.cefet_main);
						    }
						});
						sleep(2 * 1000);				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finally{
						startActivity(new Intent(MainActivity.this, TelaInicial.class));	
					}
				}	
		}.start();				
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		finish(); // Finaliza a activity logo após chamar a tela inicial.
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
}
