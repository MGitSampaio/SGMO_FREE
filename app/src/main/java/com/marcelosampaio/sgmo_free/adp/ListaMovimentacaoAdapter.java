package com.marcelosampaio.sgmo_free.adp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Movimentacao;

import java.util.List;

public class ListaMovimentacaoAdapter extends BaseAdapter {

    private final List<Movimentacao> movimentacoes;
    private final Activity activity;
    private DataHelper dataHelper = new DataHelper();


    public ListaMovimentacaoAdapter(Activity activity, List<Movimentacao> movimentacoes)
    {
        this.activity = activity;
        this.movimentacoes = movimentacoes;
    }


    @Override
    public int getCount() {
        return movimentacoes.size();
    }

    @Override
    public Object getItem(int i) {
        return movimentacoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return movimentacoes.get(i).getIdMovimentacao();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_movimentacao,viewGroup,false);

        TextView tvDtMovimentacao = v.findViewById(R.id.tvDtMovimentacao);
        TextView tvClienteMov = v.findViewById(R.id.tvClienteMov);
        TextView tvUnidadeMov = v.findViewById(R.id.tvUnidadeMov);
        TextView tvReMov = v.findViewById(R.id.tvReMov);
        TextView tvNomeMov = v.findViewById(R.id.tvNomeMov);
        TextView tvMotivoMov = v.findViewById(R.id.tvMotivoMov);

        Movimentacao movimentacao = movimentacoes.get(i);
        tvDtMovimentacao.setText(dataHelper.convertLongEmStringData(movimentacao.getDia()));
        tvClienteMov.setText(movimentacao.getCliente());
        tvUnidadeMov.setText(movimentacao.getUnidade());
        tvReMov.setText(movimentacao.getReAusente());
        tvNomeMov.setText(movimentacao.getNomeAusente());
        tvMotivoMov.setText(movimentacao.getMotivo());
        return v;
    }
}
