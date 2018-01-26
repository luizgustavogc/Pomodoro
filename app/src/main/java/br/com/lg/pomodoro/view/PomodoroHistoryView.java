package br.com.lg.pomodoro.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import br.com.lg.pomodoro.R;
import br.com.lg.pomodoro.model.Pomodoro;
import br.com.lg.pomodoro.model.PomodoroEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.paperdb.Paper;

/**
 * Created by teste on 25/01/2018.
 */

public class PomodoroHistoryView extends LinearLayout {

    @BindView(R.id.view_pomodoro_history_view_list)
    RecyclerView list;

    private Unbinder unbinder;
    private HistoryAdapter adapter;

    public PomodoroHistoryView(Context context) {
        super(context);
        init();
    }

    public PomodoroHistoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PomodoroHistoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PomodoroHistoryView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        inflate(getContext(), R.layout.view_pomodoro_history, this);
        unbinder = ButterKnife.bind(this);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new HistoryAdapter();

        list.setLayoutManager(verticalLayoutManager);
        list.setAdapter(adapter);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PomodoroEvent pomodoroEvent)
    {
        Pomodoro pomodoro = pomodoroEvent.getPomodoro();
        adapter.add(pomodoro);
        refresh();
    }

    public void refresh() {
        adapter.refresh();
        adapter.notifyDataSetChanged();
    }

    private class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {

        private List<Pomodoro> history;

        public HistoryAdapter()
        {
            refresh();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new PomodoroHistoryItemViewHolder(new PomodoroHistoryItemView(getContext()));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
        {
            Pomodoro pomodoro = history.get(position);

            PomodoroHistoryItemView itemView = (PomodoroHistoryItemView)holder.itemView;

            itemView.fromPomodoro(pomodoro);
        }

        @Override
        public int getItemCount() {
            return history.size();
        }

        public void refresh()
        {
            history = Paper.book().read("history", new ArrayList<Pomodoro>());
        }


        public void add(Pomodoro pomodoro)
        {
            history.add(0, pomodoro);
            Paper.book().write("history", history);
            refresh();
        }

    }

    static class PomodoroHistoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public PomodoroHistoryItemViewHolder(PomodoroHistoryItemView itemView)
        {
            super(itemView);
        }
    }

}
