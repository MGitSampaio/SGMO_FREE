package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Status;

import java.util.List;

public class ListaStatusAdapter extends BaseAdapter
{

    private final List<Status> status;
    private final Activity activity;

    public ListaStatusAdapter(Activity activity, List<Status> status)
    {
        this.activity = activity;
        this.status = status;
    }

    @Override
    public int getCount()
    {
        return status.size();
    }

    @Override
    public Object getItem(int i)
    {
        return status.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return status.get(i).getIdStatus();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_status,viewGroup,false);


        TextView tvStatus = v.findViewById(R.id.txtStatusItens);
        Status s = status.get(i);

        tvStatus.setText(s.getStatus());

        return v;
    }
}

