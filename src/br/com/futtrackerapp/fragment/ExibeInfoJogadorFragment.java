package br.com.futtrackerapp.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.activity.ExibeInfoJogador;
import br.com.futtrackerapp.entidades.Jogador;
import br.com.futtrackerapp.webservice.JogadorREST;

public class ExibeInfoJogadorFragment extends Fragment {
    private static int idJogador;

    private ImageView img;
    private static TextView txtViewNome;
    private static TextView txtViewPosicao;
    private static TextView txtViewNumero;
    private static TextView txtViewVelMax;
    private static TextView txtViewVelMedia;
    private static TextView txtViewDistancia;
    private static T t1;

    /* Controle da Thread */
    boolean running = false;
    Object runningLock = new Object();
    boolean alive = true;

    private class T extends Thread {

        @Override
        public void run() {
            super.run();

            while (alive) {
                try {
                    atualizaDadosJogador();
                    sleep(1 * 1000);
                    Log.i("T", "Thread rodando...");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return; // Não fazer nada enquanto está intenrrompida
                }

                synchronized (runningLock) {
                    while (!running) {
                        try {
                            runningLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_jogador, container,
                false);

        int[] atribs = getActivity().getIntent().getIntArrayExtra(
                "dados_jogador");

        idJogador = atribs[1];

        img = (ImageView) view.findViewById(R.id.imagemJogador);
        txtViewNome = (TextView) view.findViewById(R.id.fragment_jogador_nome);
        txtViewPosicao = (TextView) view
                .findViewById(R.id.fragment_jogador_posicao);
        txtViewNumero = (TextView) view
                .findViewById(R.id.fragment_jogador_numero);
        txtViewVelMax = (TextView) view
                .findViewById(R.id.fragment_jogador_vel_max);
        txtViewVelMedia = (TextView) view
                .findViewById(R.id.fragment_jogador_vel_media);
        txtViewDistancia = (TextView) view
                .findViewById(R.id.fragment_jogador_distancia_percorrida);

        img.setImageResource(R.drawable.atletasemfoto2);
        t1 = new T();
        t1.start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        synchronized (runningLock) {
            running = true;
            runningLock.notifyAll();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        synchronized (runningLock) {
            running = false;
        }
    }

    private void atualizaDadosJogador() {

        new AsyncTask<Integer, Void, Jogador>() {

            @Override
            protected Jogador doInBackground(Integer... params) {
                Jogador jogador = null;
                try {
                    jogador = JogadorREST.getJogador(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jogador;
            }

            @Override
            protected void onPostExecute(Jogador jogador) {
                super.onPostExecute(jogador);
                if (jogador == null) {
                    Toast.makeText(getActivity(), "Erro de rede!",
                            Toast.LENGTH_LONG).show();
                } else {
                    ExibeInfoJogador.actionBar.setTitle(String.valueOf(jogador
                            .getApelido()));

                    txtViewNome.setText(String.valueOf(jogador.getNome()));
                    txtViewNumero.setText(String.valueOf(jogador.getNumero()));
                    txtViewPosicao
                            .setText(String.valueOf(jogador.getPosicao()));
                    txtViewVelMax.setText(String.valueOf(jogador
                            .getVelocidadeMaxima()));
                    txtViewVelMedia.setText(String.valueOf(jogador
                            .getVelocidadeMedia()));
                    txtViewDistancia.setText(String.valueOf(jogador
                            .getDistanciaPercorrida()));
                }
            }
        }.execute(idJogador);
    }
}
