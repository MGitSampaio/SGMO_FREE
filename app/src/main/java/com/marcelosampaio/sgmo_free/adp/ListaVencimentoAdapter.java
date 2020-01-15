package com.marcelosampaio.sgmo_free.adp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.model.Vencimento;

import java.util.Date;
import java.util.List;

public class ListaVencimentoAdapter extends BaseAdapter {

    private Date date = new Date(System.currentTimeMillis());
    long dataAtual = date.getTime();
    long dataFinal = dataAtual;
    private final List<Vencimento> vencimentos;
    private final Activity activity;

    public ListaVencimentoAdapter(Activity activity, List<Vencimento> vencimentos) {
        this.activity = activity;
        this.vencimentos = vencimentos;
    }
    @Override
    public int getCount() {
        return vencimentos.size();
    }
    @Override
    public Object getItem(int i) {
        return vencimentos.get(i);
    }
    @Override
    public long getItemId(int i) {
        return vencimentos.get(i).getIdVencimento();
    }
    @Override
    @SuppressLint("ResourceAsColor")
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.itens_vencimento, null);

        ImageView imgExperiencia;
        ImageView imgFerias;
        ImageView imgReciclagem;
        ImageView imgAso;
        ImageView imgCracha;
        ImageView imgCnv;
        ImageView imgPsico;


        TextView re = v.findViewById(R.id.tvReUniformeItens);
        TextView nome = v.findViewById(R.id.tvNomeUniformesItens);

        imgExperiencia = v.findViewById(R.id.imgExpLista);
        imgFerias = v.findViewById(R.id.imgFeriasLista);
        imgReciclagem = v.findViewById(R.id.imgReciclagemLista);
        imgAso = v.findViewById(R.id.imgAsoLista);
        imgCracha = v.findViewById(R.id.imgCrachaLista);
        imgCnv = v.findViewById(R.id.imgCnvLista);
        imgPsico = v.findViewById(R.id.imgPsicoLista);

        Vencimento ve = vencimentos.get(i);
        re.setText(ve.getRe());
        nome.setText(ve.getNomeFuncionario());

        //==========================================================================================
//        if (ve.getExperiencia() > 1500L && ve.getExperiencia() <= dataAtual)
//            imgExperiencia.setImageResource(R.drawable.ic_experiencia24_v);

        if (ve.getExperiencia() >= dataAtual && ve.getExperiencia() < dataFinal + (30 * 86400000L))
            imgExperiencia.setImageResource(R.drawable.ic_experiencia24_av);
        //==========================================================================================
        if (ve.getFerias() > 1500L && ve.getFerias() <= dataAtual)
            imgFerias.setImageResource(R.drawable.ic_ferias24_v);

        if (ve.getFerias() >= dataAtual && ve.getFerias() < dataFinal + (90 * 86400000L))
            imgFerias.setImageResource(R.drawable.ic_ferias24_av);
        //==========================================================================================

        if (ve.getReciclagem() > 1500L && ve.getReciclagem() <= dataAtual)
            imgReciclagem.setImageResource(R.drawable.ic_reciclagem24_v);

        if (ve.getReciclagem() >= dataAtual && ve.getReciclagem() < dataFinal + (90 * 86400000L))
            imgReciclagem.setImageResource(R.drawable.ic_reciclagem24_av);
        //==========================================================================================
        if (ve.getAso() > 1500L && ve.getAso() <= dataAtual)
            imgAso.setImageResource(R.drawable.ic_aso24_v);

        if (ve.getAso() >= dataAtual && ve.getAso() < dataFinal + (30 * 86400000L))
            imgAso.setImageResource(R.drawable.ic_aso24_av);
        //==========================================================================================
        if (ve.getCracha() > 1500L && ve.getCracha() <= dataAtual)
            imgCracha.setImageResource(R.drawable.ic_cracha24_v);

        if (ve.getCracha() >= dataAtual && ve.getCracha() < dataFinal + (30 * 86400000L))
            imgCracha.setImageResource(R.drawable.ic_cracha24_av);
        //==========================================================================================
        if (ve.getCnv() > 1500L && ve.getCnv() <= dataAtual)
            imgCnv.setImageResource(R.drawable.ic_cnv24_v);

        if (ve.getCnv() >= dataAtual && ve.getCnv() < dataFinal + (90 * 86400000L))
            imgCnv.setImageResource(R.drawable.ic_cnv24_av);

        //==========================================================================================
        if (ve.getPsicotecnico() > 1500L && ve.getPsicotecnico() <= dataAtual)
            imgPsico.setImageResource(R.drawable.ic_psico24_v);

        if (ve.getPsicotecnico() >= dataAtual && ve.getPsicotecnico() < dataFinal + (30 * 86400000L))
            imgPsico.setImageResource(R.drawable.ic_psico24_av);

        //==========================================================================================

        return v;

    }


}
