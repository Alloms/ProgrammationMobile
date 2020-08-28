package com.example.myapplication.Presentation.Vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Presentation.Modele.Crypto;
import com.example.myapplication.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Crypto> values;
    private OnItemClickListener listener;

    public ListAdapter(List<Crypto> cryptoList) {
        this.values = cryptoList;
    }


    public interface OnItemClickListener {
        void onItemClick(Crypto item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Crypto item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Crypto currentCrypto = values.get(position);
        holder.txtHeader.setText(currentCrypto.getRank() + "   " +currentCrypto.getName() );
        holder.txtFooter.setText(currentCrypto.getPrice_usd()+"$  " + currentCrypto.getSymbol());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("Name",currentCrypto.getName());
                intent.putExtra("Rank",currentCrypto.getRank());
                intent.putExtra("symb",currentCrypto.getSymbol());
                intent.putExtra("usd",currentCrypto.getPrice_usd());
                intent.putExtra("btc",currentCrypto.getPrice_btc());
                intent.putExtra("pc1",currentCrypto.getPercent_change_1h());
                intent.putExtra("pc7",currentCrypto.getPercent_change_7d());
                intent.putExtra("pc24",currentCrypto.getPercent_change_24h());
                intent.putExtra("marketcap",currentCrypto.getMarket_cap_usd());
                context.startActivity(intent);
            }
        });
        }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}