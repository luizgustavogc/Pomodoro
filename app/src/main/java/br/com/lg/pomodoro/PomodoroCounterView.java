package br.com.lg.pomodoro;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by teste on 25/01/2018.
 */

public class PomodoroCounterView extends LinearLayout {

    @BindView(R.id.view_pomodoro_counter_timer)
    TextView timer;

    private Unbinder unbinder;
    private int minutes;
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
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
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

        setMinutes(minutes);

        updateTimer.sendEmptyMessage(0);

        timer.setTextColor(ContextCompat.getColor(getContext(), R.color.timerColorStarted));

        TimerTask timerTask = new TimerTask();
        timerTask.execute();

    }

    public void start()
    {
        start(25);
    }

    public void stop()
    {
        timerTask.cancel(true);
    }

    private class TimerTask extends AsyncTask<Void, Void, Void>
    {

        private boolean running;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = false;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            running = true;

            while(running)
            {
                try
                {

                    Thread.sleep(1000);

                    minutes-=1000;

                    updateTimer.sendEmptyMessage(0);
                    running = minutes > 0;

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

            running = false;

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();



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

