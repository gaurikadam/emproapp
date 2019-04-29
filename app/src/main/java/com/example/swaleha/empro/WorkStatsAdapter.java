package com.example.swaleha.empro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swaleha.empro.model.PersonalDetails;

import java.util.List;

public class WorkStatsAdapter extends BaseAdapter {

    private List<PersonalDetails> mData;
    private LayoutInflater layoutInflater;
    private Context context;

    public WorkStatsAdapter(Context aContext,  List<PersonalDetails> mData)
    {
        this.context = aContext;
        this.mData = mData;
        layoutInflater = LayoutInflater.from(aContext);

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.work_stats_card, null);
            holder = new ViewHolder();
            holder.leavesusd_tv =  convertView.findViewById(R.id.cardleaveused);
            holder.holidays_tv =  convertView.findViewById(R.id.cardholiday);
            holder.sick_tv =  convertView.findViewById(R.id.cardsick);
            holder.casual_tv =  convertView.findViewById(R.id.cardcasual);
            holder.daysa =  convertView.findViewById(R.id.daysa);
            holder.daysw =  convertView.findViewById(R.id.daysw);
            holder.sal =  convertView.findViewById(R.id.salary);
            holder.monthv =  convertView.findViewById(R.id.monthtxt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PersonalDetails mdata = this.mData.get(position);
        holder.leavesusd_tv.setText("" + mdata.getLeavesused());
        holder.holidays_tv.setText(""+mdata.getHolidays());
        holder.sick_tv.setText(""+mdata.getPendingsick());
        holder.casual_tv.setText(""+mdata.getPendingcasual());
        holder.daysa.setText(""+mdata.getDaysabsent());
        holder.daysw.setText(""+mdata.getDaysworked());
        holder.sal.setText(""+mdata.getSalary());
        holder.monthv.setText(""+mdata.getMonthv());

        return convertView;
    }

    static class ViewHolder {
        TextView leavesusd_tv,holidays_tv,sick_tv,casual_tv,daysw,daysa,sal,monthv;
    }
}
