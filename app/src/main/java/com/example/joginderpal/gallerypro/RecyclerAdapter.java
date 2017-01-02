package com.example.joginderpal.gallerypro;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import java.util.List;

/**
 * Created by joginderpal on 02-01-2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] titles={
            "one",
            "two",
            "thrree",
            "four",
            "five",
            "six"
    };
    Context contextList;
      List<String> li;

    public RecyclerAdapter(Context contextList, List<String> li) {
        this.li = li;
        this.contextList=contextList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public TextView itemTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle= (TextView) itemView.findViewById(R.id.tx);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);


        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(li.get(position));
        Animation animation=AnimationUtils.loadAnimation(contextList,R.anim.bounce);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return li.size();
    }


}
