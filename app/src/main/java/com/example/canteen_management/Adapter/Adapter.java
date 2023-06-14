package com.example.canteen_management.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteen_management.Model.Model;
import com.example.canteen_management.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    ArrayList<Model> modelArrayList = new ArrayList<>();
    Context context;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false);
        }
        TextView orderId = convertView.findViewById(R.id.orderId);
        TextView tableId = convertView.findViewById(R.id.tableId);
        TextView items = convertView.findViewById(R.id.items);
        TextView payment = convertView.findViewById(R.id.payment);
        LinearLayout linearLayout = convertView.findViewById(R.id.lin);

        final Model model = modelArrayList.get(position);

        orderId.setText(model.getOrderId());
        tableId.setText(model.getTableId());
        items.setText(model.getItems());
        boolean pmnt = model.isPayment();
        String pmnt1 = pmnt == true ? "yes" : "no";
        payment.setText(pmnt1);
//        if (!model.getImage().equals("")) {
//            Picasso.with(context).load(model.getImage()).into(imageView);
//        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, model.getItems(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}

