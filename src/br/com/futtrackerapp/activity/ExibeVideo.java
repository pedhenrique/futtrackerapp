package br.com.futtrackerapp.activity;

import br.com.futtrackerapp.R;
import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class ExibeVideo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exibe_video);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Vídeo");
		
		VideoView vidView = (VideoView)findViewById(R.id.myVideo);
		
		
		
		//String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		String vidAddress = "http://200.131.37.199/output.mp4";
		//String vidAddress = "http://200.131.34.46/seq_hotel.avi";
		Uri vidUri = Uri.parse(vidAddress);
		
		vidView.setVideoURI(vidUri);
		
		MediaController vidControl = new MediaController(this);
		vidView.setMediaController(vidControl);
		
		vidView.start();
		
	}

	
	
}
