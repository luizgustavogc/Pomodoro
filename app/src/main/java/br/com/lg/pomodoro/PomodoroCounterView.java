package br.com.lg.pomodoro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public void start()
    {

    }

    public void stop()
    {

    }
}
