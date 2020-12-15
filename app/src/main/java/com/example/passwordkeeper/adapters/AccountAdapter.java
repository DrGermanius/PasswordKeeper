package com.example.passwordkeeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.passwordkeeper.R;
import com.example.passwordkeeper.models.Account;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> implements View.OnClickListener {

    private ArrayList<Account> accountList;
    Context mContext;

    private static class ViewHolder {
        TextView txtName;
    }

    public AccountAdapter(ArrayList<Account> data, Context context) {
        super(context, R.layout.account_row_item, data);
        this.accountList = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Account dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.account_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        return convertView;
    }
}
