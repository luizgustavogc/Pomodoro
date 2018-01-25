package br.com.lg.pomodoro;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import br.com.lg.pomodoro.model.Pomodoro;
import br.com.lg.pomodoro.model.PomodoroEvent;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(this);

        setContentView(R.layout.activity_main);


        new Thread(this).start();
    }

    @Override
    public void run() {

        int i = 0;
        while(true)
        {
            i++;
            ev.sendEmptyMessage(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    Handler ev = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            PomodoroEvent event = new PomodoroEvent();
            Pomodoro pomodoro = new Pomodoro();
            pomodoro.setTime((1 + msg.what) * 1000);
            pomodoro.setFinished(true);
            event.setPomodoro(pomodoro);
            EventBus.getDefault().post(event);


        }
    };
}
