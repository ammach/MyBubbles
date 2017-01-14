package com.ammach.mybubbles;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.emoji.Emoji;
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener;
import com.vanniktech.emoji.listeners.OnEmojiClickedListener;
import com.vanniktech.emoji.listeners.OnEmojiPopupDismissListener;
import com.vanniktech.emoji.listeners.OnEmojiPopupShownListener;
import com.vanniktech.emoji.listeners.OnSoftKeyboardCloseListener;
import com.vanniktech.emoji.listeners.OnSoftKeyboardOpenListener;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MessageAdapter messageAdapter;
    List<Message> messages=new ArrayList<>();
    EmojiEditText editMsg;
    EmojiPopup emojiPopup;
    ViewGroup rootView;
    Button emojiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        editMsg= (EmojiEditText) findViewById(R.id.editMsg);

        messages.add(new Message("hey man how r u?","1"));
        messages.add(new Message("experiment on android platform for chat applications,we hope it s gonna be a good app","2"));
        messages.add(new Message("experiment on android platform for chat applications,we hope it s gonna be a good app","1"));
        messages.add(new Message("experiment on android platform","2"));

        Message message2=new Message("1");
        message2.setVideoUrl("rtsp://r13---sn-4g57knzd.googlevideo.com/Cj0LENy73wIaNAn2kgGB8JOsUhMYDSANFC1GkQtYMOCoAUIASARgvJyd1pe57IFYigELSUwxRUxPTC1UQU0M/ADC2DB46678C084FA4F00987DAAAE91DB7280F6C.B0C9BE303397E29628281A6793AE5DD2FD4A1B58/yt6/1/video.3gp");
        messages.add(message2);

//        Message message=new Message("2");
//        message.setImgMessage(R.drawable.icon);
//        messages.add(message);


        rootView = (ViewGroup) findViewById(R.id.relative);
        listView= (ListView) findViewById(R.id.listView);
        emojiBtn= (Button) findViewById(R.id.emojiBtn);
        messageAdapter=new MessageAdapter(messages,this);
        listView.setAdapter(messageAdapter);


        listView.setSelection(messages.size()-1);

        setUpEmojiPopup();

    }

    private void setUpEmojiPopup() {
        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).setOnEmojiBackspaceClickListener(new OnEmojiBackspaceClickListener() {
            @Override
            public void onEmojiBackspaceClicked(final View v) {
                Log.d("MainActivity", "Clicked on Backspace");
            }
        }).setOnEmojiClickedListener(new OnEmojiClickedListener() {
            @Override
            public void onEmojiClicked(final Emoji emoji) {
                Log.d("MainActivity", "Clicked on emoji");
            }
        }).setOnEmojiPopupShownListener(new OnEmojiPopupShownListener() {
            @Override
            public void onEmojiPopupShown() {
            }
        }).setOnSoftKeyboardOpenListener(new OnSoftKeyboardOpenListener() {
            @Override
            public void onKeyboardOpen(final int keyBoardHeight) {
                Log.d("MainActivity", "Opened soft keyboard");
            }
        }).setOnEmojiPopupDismissListener(new OnEmojiPopupDismissListener() {
            @Override
            public void onEmojiPopupDismiss() {
            }
        }).setOnSoftKeyboardCloseListener(new OnSoftKeyboardCloseListener() {
            @Override
            public void onKeyboardClose() {
                emojiPopup.dismiss();
            }
        }).build(editMsg);
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    public void animImage(ImageView imageView){

        ImageView imageView1= (ImageView) findViewById(R.id.imgAnim);
        imageView1.setVisibility(View.VISIBLE);
        imageView1.setImageDrawable(imageView.getDrawable());
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_image);
        imageView1.startAnimation(animation);
    }

    public void addMesg(View view) {
        messages.add(new Message(editMsg.getText().toString(),"1"));
        messageAdapter.notifyDataSetChanged();
        editMsg.setText("");
        listView.setSelection(messages.size()-1);
        JCVideoPlayer.releaseAllVideos();
    }

    public void addEmoji(View view) {
        emojiPopup.toggle();
    }


    public void addImgVideo(View view) {
        Intent mediaChooser = new Intent(Intent.ACTION_GET_CONTENT);
        mediaChooser.setType("*/*");
        startActivityForResult(mediaChooser, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedVideoLocation = data.getData();
                Message message=new Message("2");
                message.setImgMessage(selectedVideoLocation.toString());
                messages.add(message);
                messageAdapter.notifyDataSetChanged();
                listView.setSelection(messages.size()-1);
            }

        }
    }

}
