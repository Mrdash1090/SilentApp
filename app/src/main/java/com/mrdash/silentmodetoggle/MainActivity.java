package com.mrdash.silentmodetoggle;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private AudioManager mAudioManager;
    private boolean mPhoneIsSilent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonClickListener();
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        checkIfPhoneIsSilent();
    }
     private void setButtonClickListener(){
        Button toggleButton = (Button)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                if(mPhoneIsSilent)
                {
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    mPhoneIsSilent = false;
                }else{
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    mPhoneIsSilent=true;
                }
                toggleUi();
            }
        });
    }

    private void checkIfPhoneIsSilent(){
         int ringerMode = mAudioManager.getRingerMode();
         if(ringerMode == AudioManager.RINGER_MODE_SILENT){
             mPhoneIsSilent = true;
         }else{
             mPhoneIsSilent=false;
         }
    }

    private void toggleUi(){
        ImageView imageView = (ImageView)findViewById(R.id.normal);
        Drawable newPhoneImage;
        if(mPhoneIsSilent){
            newPhoneImage=getResources().getDrawable(R.drawable.silent);
        }else{
            newPhoneImage = getResources().getDrawable(R.drawable.normal);
        }
        imageView.setImageDrawable(newPhoneImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfPhoneIsSilent();
        toggleUi();
    }
}
