package com.ahf.antwerphasfallen.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ahf.antwerphasfallen.InGameActivity;
import com.ahf.antwerphasfallen.Adapters.IngredientsListAdapter;
import com.ahf.antwerphasfallen.Model.InventoryItem;
import com.ahf.antwerphasfallen.Adapters.ItemListAdapter;
import com.ahf.antwerphasfallen.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {

    private InGameActivity host;
    private IngredientsListAdapter ingredientsAdapter;
    private ItemListAdapter shopInventoryAdapter;
    private ListView lvIngredients;
    private ListView lvShopItems;

    public InventoryFragment() {
    }

    public static InventoryFragment newInstance() {
        InventoryFragment fragment = new InventoryFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public void setAdapters() {
        if (host != null) {
            if (host.CurrentTeam != null) {
                if (host.CurrentTeam.getInventory() != null) {
                    if (host.CurrentTeam.getInventory().getIngredients() != null)
                        ingredientsAdapter = new IngredientsListAdapter(getContext(), (ArrayList) host.CurrentTeam.getInventory().getIngredients());
                    if (host.CurrentTeam.getInventory().getItems() != null)
                        shopInventoryAdapter = new ItemListAdapter(getContext(), (ArrayList) host.CurrentTeam.getInventory().getItems());
                    ingredientsAdapter.notifyDataSetChanged();
                    shopInventoryAdapter.notifyDataSetChanged();
                }
            } else {
                ingredientsAdapter = new IngredientsListAdapter(getContext(), new ArrayList<InventoryItem>());
                shopInventoryAdapter = new ItemListAdapter(getContext(), new ArrayList<InventoryItem>());
            }
//            lvIngredients.setAdapter(ingredientsAdapter);
//            lvShopItems.setAdapter(shopInventoryAdapter);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_inventory, container, false);

        lvIngredients = fragmentView.findViewById(R.id.lv_ingredients);
        lvIngredients.setAdapter(ingredientsAdapter);

        lvShopItems = fragmentView.findViewById(R.id.lv_shopItems);
        lvShopItems.setAdapter(shopInventoryAdapter);

        setAdapters();

        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InGameActivity) {
            host = (InGameActivity) context;
            setAdapters();
        } else {
            throw new RuntimeException(context.toString()
                    + " is not inGameActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        host = null;
    }
}