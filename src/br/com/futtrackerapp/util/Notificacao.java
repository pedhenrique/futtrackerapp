package br.com.futtrackerapp.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.com.futtrackerapp.R;
import br.com.futtrackerapp.activity.ExibeVideo;
import br.com.futtrackerapp.entidades.Video;

public class Notificacao {
	
	public static void geraNotificacao(Context context, Video v){
		
		NotificationManager gerenciador = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification nota = new Notification(R.drawable.teste, "Vídeo pronto!",
				System.currentTimeMillis());
		PendingIntent i = PendingIntent.getActivity(context, 0, new Intent(context,
				ExibeVideo.class).putExtra("id_video", v.getId()), 0);
		nota.setLatestEventInfo(context, "Vídeo " + v.getNome(), "O vídeo " + v.getNome() + " solicitado já" +
																" foi gerado com sucesso", i);
		nota.flags |= Notification.FLAG_AUTO_CANCEL;
		gerenciador.notify(1, nota);
		
	}
}
