package com.example.busevent.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.busevent.Util.Config;
import com.example.busevent.Model.DataReceiveEvent;
import com.example.busevent.NetworkRelatedClass.NetworkCall;
import com.example.busevent.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Subscribe(threadMode = ThreadMode.MAIN)

      public void onEvent(DataReceiveEvent event) throws  ClassNotFoundException{

        if (event.isTagMatchWith(Config.DATA_RECEIVED)){
            textView.setText(event.getResponseMessage());
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        textView.setText("no data foune");


    }

    public void buttonClicked(View view) {
        NetworkCall.getData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
