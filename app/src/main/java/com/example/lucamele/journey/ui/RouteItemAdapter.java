package com.example.lucamele.journey.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lucamele.journey.R;
import com.example.lucamele.journey.dtos.ConnectionDto;

import java.util.List;
import java.util.Locale;


public class RouteItemAdapter<T> extends ArrayAdapter {

    private final int mResource;
    private Context mContext;
    private LayoutInflater mInflater;

    public RouteItemAdapter(Context context, int resource, List<T> arr) {
        super(context, resource, arr);
        Context mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mResource = resource;
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView,
                 @NonNull ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }

        TextView textFrom;
        LinearLayout cardContainer;
        TextView textTo;
        try {
            textFrom = view.findViewById(R.id.text_card_departure);
            textTo = view.findViewById(R.id.text_card_arrival);
            cardContainer = view.findViewById(R.id.text_card);
        } catch (ClassCastException e) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a TextView", e);
        }

        final ConnectionDto item = (ConnectionDto) getItem(position);
        int colorToUse = position % 2 == 0 ? R.color.colorZebraA : R.color.colorZebraB;
        Activity activity = (Activity) mInflater.getContext();
        cardContainer.setBackgroundColor(mInflater.getContext().getResources().getColor(colorToUse, activity.getTheme()));
        textFrom.setText(String.format(Locale.GERMAN,"%s %s", mInflater.getContext().getResources().getString(R.string.connection_from), item.from.toString()));
        textTo.setText(String.format(Locale.GERMAN,"%s %s", mInflater.getContext().getResources().getString(R.string.connection_to), item.to.toString()));
        return view;
    }
}