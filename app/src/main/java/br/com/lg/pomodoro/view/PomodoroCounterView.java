package br.com.lg.pomodoro.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.lg.pomodoro.R;
import br.com.lg.pomodoro.model.Pomodoro;
import br.com.lg.pomodoro.model.PomodoroEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by teste on 25/01/2018.
 */

public class PomodoroCounterView extends LinearLayout {

    @BindView(R.id.view_pomodoro_counter_timer)
    TextView timer;

    @BindView(R.id.view_pomodoro_counter_fab)
    FloatingActionButton fab;

    private boolean runnning;

    private Unbinder unbinder;
    private int minutes;
    private int start;
    private TimerTask timerTask;

    public PomodoroCounterView(Context context) {
        super(context);
        init();
    }

    public PomodoroCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PomodoroCounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PomodoroCounterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        inflate(getContext(), R.layout.view_pomodoro_counter, this);
        unbinder = ButterKnife.bind(this);

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerTask == null)
                {
                    start(1);
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_ff));
                }
                else
                {
                    stop();
                }
            }
        });

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void setMinutes(int minutes)
    {

        if(minutes > 59) minutes = 59;
        if(minutes < 1) minutes = 1;
        this.minutes = minutes * 1000 * 60;

        timer.setTextColor(ContextCompat.getColor(getContext(), R.color.timerColorStopped));

    }

    public void start(int minutes)
    {
        start = minutes * 60 * 1000;

        if(timerTask!=null && !timerTask.isCancelled())
        {
            stop();
        }
        else
        {

            setMinutes(minutes);

            updateTimer.sendEmptyMessage(0);

            timer.setTextColor(ContextCompat.getColor(getContext(), R.color.timerColorStarted));

            timerTask = new TimerTask();
            timerTask.execute();

        }
    }

    public void start()
    {
        start(25);
    }

    public void stop()
    {
        stop(false);
    }

    private void stop(boolean finished)
    {
        if(timerTask!=null && !timerTask.isCancelled())
        {
            timerTask.cancel(true);
            timerTask = null;
        }

        PomodoroEvent event = new PomodoroEvent();
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setTime(minutes);
        pomodoro.setStart(start);
        pomodoro.setFinished(finished);
        event.setPomodoro(pomodoro);
        EventBus.getDefault().post(event);

        reset();

        fab.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));

    }

    private void reset()
    {
        timer.setText("00:00");
        timer.setTextColor(ContextCompat.getColor(getContext(), R.color.timerColorStopped));
    }

    private class TimerTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while(minutes > 0 && !isCancelled())
            {
                try
                {

                    Thread.sleep(1000);

                    minutes-=1000;

                    updateTimer.sendEmptyMessage(0);

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            done();

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            //done();

        }

        private void done()
        {
            minutes = 0;
            stop(true);
        }
    }

    Handler updateTimer = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

            String val = simpleDateFormat.format(new Date((long)(minutes)));

            timer.setText(val);

        }
    };


}



