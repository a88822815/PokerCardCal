package com.example.pokercardcal.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pokercardcal.R;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<HistoryRecord> {
    private int resourceId;

    // 适配器的构造函数，把要适配的数据传入这里
    public HistoryAdapter(Context context, int textViewResourceId, List<HistoryRecord> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        class ViewHolder{
            TextView name;
            TextView score;

            public ViewHolder(TextView name, TextView score) {
                this.name = name;
                this.score = score;
            }
        }
        HistoryRecord str=getItem(position); //获取当前项的Fruit实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            TextView name = view.findViewById(R.id.history_name);
            TextView score = view.findViewById(R.id.history_score);
            viewHolder = new ViewHolder(name,score);

            // 将ViewHolder存储在View中（即将控件的实例存储在其中）
            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        // 获取控件实例，并调用set...方法使其显示出来
        viewHolder.name.setText(str.name);
        viewHolder.score.setText(str.score);
        viewHolder.name.setTextSize(22);
        viewHolder.score.setTextSize(22);
        return view;
    }
}
