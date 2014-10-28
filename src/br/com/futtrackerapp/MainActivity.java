package br.com.futtrackerapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.futtrackerapp.activity.ExibeListaTimes;
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
						    	//texto.setText("DESENVOLVIDO POR");
								imagem.setImageResource(R.drawable.cefet_main);
						    }
						});
						sleep(3000);
				
						runOnUiThread(new Runnable() {
						    @Override
						    public void run() {
						    	//texto.setText("APOIO");
								imagem.setImageResource(R.drawable.logo_minas_grande);
						    }
						});
						
						sleep(3000);
				
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}
}
