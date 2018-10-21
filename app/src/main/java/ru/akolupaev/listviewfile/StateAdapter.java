package ru.akolupaev.listviewfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    private LayoutInflater inflater;
    private int layout;
    private List<State> states;
    private Context context;

    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.flag);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView capitalView = (TextView) view.findViewById(R.id.capital);

        State state = states.get(position);
        if (state.getNameFile()==null){
            flagView.setImageResource(state.getFlagResource());
            flagView.setVisibility(View.GONE);
        }else{
            flagView.setVisibility(View.VISIBLE);
            Glide.with(context).load(state.getNameFile()).into(flagView);
        }

        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }
}