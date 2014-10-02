package org.janusz.steven.myoverflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.janusz.steven.myoverflow.stackoverflow.data.Item;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by marek on 03.10.14.
 */
public class StackItemAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private List<Item> mItems;

    public StackItemAdapter(Context ctx, List<Item> items) {
        super(ctx, android.R.layout.simple_list_item_1);
        mContext = ctx;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getPosition(Item item) {
        return mItems.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(
                    android.R.layout.simple_list_item_1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.text.setText(mItems.get(position).getTitle());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(android.R.id.text1) TextView text;

        public ViewHolder(View itemLayout) {
            ButterKnife.inject(this, itemLayout);
        }
    }
}
