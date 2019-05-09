package live.tv.listview;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.FieldsModel> {
    Context context;
    ArrayList<Model> data;

    static ClickInterface clickInterface;

    public MyRecyclerViewAdapter(ArrayList<Model> data,Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FieldsModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view;
        //LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=LayoutInflater.from(context).inflate(R.layout.row, viewGroup,false);

        return new FieldsModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsModel fieldsModel, int i) {
        Model item = data.get(i);

        fieldsModel.title.setText(item.title);

        Glide.with(context).load(item.imageUrl).into(fieldsModel.image);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class FieldsModel extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;


        public FieldsModel(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtRow);

            image = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(clickInterface!=null){
                clickInterface.onClick(v,getAdapterPosition());
            }
        }
    }

    public void setClickListener(ClickInterface listener){
        clickInterface = listener;
    }

    public interface ClickInterface{
        public void onClick(View view,int position);
    }
}
