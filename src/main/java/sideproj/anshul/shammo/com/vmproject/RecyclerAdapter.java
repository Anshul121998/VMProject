package sideproj.anshul.shammo.com.vmproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    TextView txt_description;

    private ItemClickListner itemClickListner;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_description = itemView.findViewById(R.id.txtDescription);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListner.onClick(view,getAdapterPosition(),true);
        return true;
    }
}
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<String> listData;
    private Context context;
    String type;

    public RecyclerAdapter(List<String> listData, Context context, String type) {
        this.listData = listData;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.txt_description.setText(listData.get(position));
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    if(type.equals("datacenter"))
                    {
                        Intent intent = new Intent(context,DataCenterDetails.class);
                        intent.putExtra("datacenter",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("vmscheduler"))
                    {
                        Intent intent = new Intent(context,VMSchedulerDetails.class);
                        intent.putExtra("hostid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("vmtable"))
                    {
                        Intent intent = new Intent(context,VMTableDetails.class);
                        intent.putExtra("vmid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("process"))
                    {
                        Intent intent = new Intent(context,ProcessDetail.class);
                        intent.putExtra("processid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }

                }
                else {
                    if(type.equals("datacenter"))
                    {
                        Intent intent = new Intent(context,VMSchedulerHostMainPage.class);
                        intent.putExtra("datacenter",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("vmscheduler"))
                    {
                        Intent intent = new Intent(context,VMTableMainPage.class);
                        intent.putExtra("hostid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("vmtable"))
                    {
                        Intent intent = new Intent(context,ProcessMainPage.class);
                        intent.putExtra("vmid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                    else if(type.equals("process"))
                    {
                        Intent intent = new Intent(context,ProcessDetail.class);
                        intent.putExtra("processid",listData.get(position));
                        view.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
