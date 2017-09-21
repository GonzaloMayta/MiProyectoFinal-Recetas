package gonzalo.recetas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import gonzalo.recetas.R;
import gonzalo.recetas.model.Food;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.DeviceViewHolder>  {
    private Context context;
    private List<Food> dataset;
    private onComidaSelectedListener onComidaSelectedListener;

    public interface onComidaSelectedListener {
        void onComidaSelectedListener(Food miinterface);
    }

    public FoodAdapter(Context context, onComidaSelectedListener listener) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onComidaSelectedListener = listener;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comida, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Food comida = dataset.get(position);
        holder.titleTextView.setText(comida.getTitle());
        Glide.with(context)
                .load(comida.getImageUrl())
                .into(holder.imageImageView);
        holder.setDeviceSelectedListener(comida, onComidaSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        ImageView imageImageView;
        TextView titleTextView;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
        }

        public void setDeviceSelectedListener(final Food miinterface, final onComidaSelectedListener onComidaSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onComidaSelectedListener.onComidaSelectedListener(miinterface);
                }
            });
        }
    }
    public void setDataset(List<Food> comidas) {
        if (comidas == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = comidas;
        }
        notifyDataSetChanged();
    }

}
