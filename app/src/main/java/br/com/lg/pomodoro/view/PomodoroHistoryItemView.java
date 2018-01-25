package br.com.lg.pomodoro.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.lg.pomodoro.R;
import br.com.lg.pomodoro.model.Pomodoro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by teste on 25/01/2018.
 */

public class PomodoroHistoryItemView extends LinearLayout
{

    @BindView(R.id.view_pomodoro_history_item_timer)
    TextView timer;

    @BindView(R.id.view_pomodoro_history_item_status)
    TextView status;

    @BindView(R.id.view_pomodoro_history_item_when)
    TextView when;

    private Unbinder unbinder;

    public PomodoroHistoryItemView(Context context) {
        super(context);
        init();
    }

    public PomodoroHistoryItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PomodoroHistoryItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PomodoroHistoryItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        inflate(getContext(), R.layout.view_pomodoro_history_item, this);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        unbinder = ButterKnife.bind(this);
    }

    public void fromPomodoro(Pomodoro pomodoro)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        String val = simpleDateFormat.format(new Date((long)(pomodoro.getTime())));

        timer.setText(val);
        status.setText(pomodoro.isFinished()?"Finished":"Stopped");
        when.setText("1 min ago");

    }
}
