package br.com.futtrackerapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.futtrackerapp.R;

public class ExibeGraficoJogadorFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_exibe_grafico_jogador, container, false);
		
		ImageView img = (ImageView) view.findViewById(R.id.fragment_jogador_imagem_grafico);
		img.setImageResource(R.drawable.quadra_futsal);
		
		return view;
	}
		
}
