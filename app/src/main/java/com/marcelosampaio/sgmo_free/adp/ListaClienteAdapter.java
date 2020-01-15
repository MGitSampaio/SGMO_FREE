package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Cliente;

import java.util.List;

public class ListaClienteAdapter extends BaseAdapter
{

    private final List<Cliente> clientes;
    private final Activity activity;

    public ListaClienteAdapter(Activity activity, List<Cliente> clientes)
    {
        this.activity = activity;
        this.clientes = clientes;
    }

    @Override
    public int getCount()
    {
        return clientes.size();
    }

    @Override
    public Object getItem(int i)
    {
        return clientes.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return clientes.get(i).getIdCliente();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = activity.getLayoutInflater().inflate(R.layout.itens_cliente,viewGroup,false);


        TextView cliente = v.findViewById(R.id.txtNomeClienteItens);

        Cliente c = clientes.get(i);

        cliente.setText(c.getNomeCliente());

        return v;
    }
}

