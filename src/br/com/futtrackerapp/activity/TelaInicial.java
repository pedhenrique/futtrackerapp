package br.com.futtrackerapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.futtrackerapp.R;

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
			startActivity(new Intent(this, ExibeVideo.class));break;
		case 2:
			finish();
		default:
			finish();
		
		}
	}
}
