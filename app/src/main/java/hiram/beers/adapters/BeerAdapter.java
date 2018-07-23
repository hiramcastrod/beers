package hiram.beers.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hiram.beers.R;
import hiram.beers.models.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {
    private ArrayList<Beer> beers;
    private int resource;
    private Activity activity;

    public BeerAdapter(ArrayList<Beer> beers, int resource, Activity activity) {
        this.beers = beers;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BeerViewHolder holder, final int position) {
        final Beer beer = beers.get(position);
        holder.nameCard.setText(beer.getName());
        holder.taglineCard.setText(beer.getTagline());
        holder.descriptionCard.setText(beer.getDescription());

        Picasso.with(activity).load(beer.getImage()).into(holder.imageCard);
        holder.cvBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddress = "hiramc@live.com.mx";
                String body = beers.get(position).getDescription().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
                intent.putExtra(Intent.EXTRA_SUBJECT, beers.get(position).getName().toString());
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder{
        private TextView nameCard, descriptionCard, taglineCard;
        private ImageView imageCard;
        private CardView cvBeers;
        public BeerViewHolder(View itemView) {
            super(itemView);
            nameCard = itemView.findViewById(R.id.texview_beer_name);
            descriptionCard = itemView.findViewById(R.id.textview_description);
            taglineCard = itemView.findViewById(R.id.textview_tagline);
            imageCard = itemView.findViewById(R.id.imageview_icon);
            cvBeers = itemView.findViewById(R.id.cardview_beers);
        }
    }
}
