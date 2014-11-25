package br.com.futtrackerapp.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.entidades.Jogador;
import br.com.futtrackerapp.entidades.Time;
import br.com.futtrackerapp.entidades.Video;

public class AdapterListView<T> extends BaseAdapter{
	private LayoutInflater mInflater;
    private List<T> itens;
    
    public AdapterListView(Context context, List<T> itens){
    	this.itens = itens;
    	mInflater = LayoutInflater.from(context);	
    }
	
	@Override
	public int getCount() {
	
		return itens.size();
	}

	@Override
	public Object getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ItemSuporte itemHolder;
		
		if(view == null){
			
			view = mInflater.inflate(R.layout.item_list_view, null);
		
			itemHolder = new ItemSuporte();
			itemHolder.txtTitle = ((TextView) view.findViewById(R.id.text));
			itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.imageview));
			
			view.setTag(itemHolder);
		}
		
		else{
			itemHolder = (ItemSuporte) view.getTag();	
		}
		
		T item = itens.get(position);
		if(item instanceof Jogador){
			itemHolder.txtTitle.setText(((Jogador) item).getApelido());
			itemHolder.imgIcon.setImageResource(R.drawable.atletasemfoto2);
			
		}
		else{
			if(item instanceof Time){
				itemHolder.txtTitle.setText(((Time) item).getNome());
				itemHolder.imgIcon.setImageResource(R.drawable.logo_minas_grande);
				
			}
			else{
				if(item instanceof Video){
					itemHolder.txtTitle.setText(((Video) item).getNome());
					itemHolder.imgIcon.setImageResource(R.drawable.logo_minas_grande);					
				}				
			}
			
		}

		return view;
	}
	
	private class ItemSuporte{
		ImageView imgIcon;
		TextView txtTitle;
	}

	
}
