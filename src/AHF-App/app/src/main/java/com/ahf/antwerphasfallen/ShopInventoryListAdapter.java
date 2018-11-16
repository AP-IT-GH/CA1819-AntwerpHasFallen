package com.ahf.antwerphasfallen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jorren on 16/11/2018.
 */

public class ShopInventoryListAdapter extends ArrayAdapter<ShopItem> {
    public ShopInventoryListAdapter(@NonNull Context context, @NonNull ArrayList<ShopItem> objects) {
        super(context, -1, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_item_shop_inventory, null);

        TextView lblName = itemView.findViewById(R.id.lbl_name);
        TextView lblQuantity = itemView.findViewById(R.id.lbl_quantity);
        TextView lblDescription = itemView.findViewById(R.id.lbl_description);

        lblName.setText(getItem(position).getName());
        lblQuantity.setText(getItem(position).getQuantity());
        lblDescription.setText(getItem(position).getDescription());

        return itemView;
    }
}
