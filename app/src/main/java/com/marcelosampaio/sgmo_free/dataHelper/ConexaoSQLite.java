package com.marcelosampaio.sgmo_free.dataHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class    ConexaoSQLite extends SQLiteOpenHelper
{

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "SGMO_DB";

    //==============================================================================================

    public ConexaoSQLite(Context context)
    {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    //==============================================================================================

    public static void getInstancia(Context context)
    {
        if (INSTANCIA_CONEXAO == null)
        {
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
    }



    //==============================================================================================
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String Status = "CREATE TABLE IF NOT EXISTS status" +
                "(" +
                "idStatus INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "status TEXT NOT NULL UNIQUE"+
                ")";
        sqLiteDatabase.execSQL(Status);
        //------------------------------------------------------------------------------------------
        String Escala = "CREATE TABLE IF NOT EXISTS escala" +
                "(" +
                "idEscala INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "escala TEXT NOT NULL UNIQUE"+
                ")";
        sqLiteDatabase.execSQL(Escala);
        //------------------------------------------------------------------------------------------
        String Cargo = "CREATE TABLE IF NOT EXISTS cargo" +
                "(" +
                "idCargo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "cargo TEXT NOT NULL UNIQUE,"+
                "obsCargo TEXT" +
                ")";
        sqLiteDatabase.execSQL(Cargo);
        //------------------------------------------------------------------------------------------
        String Cliente = "CREATE TABLE IF NOT EXISTS cliente" +
                "(" +
                "idCliente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "nomeCliente TEXT NOT NULL UNIQUE,"+
                "obsCliente TEXT" +
                ")";
        sqLiteDatabase.execSQL(Cliente);
        //------------------------------------------------------------------------------------------
        String Usuario = "CREATE TABLE IF NOT EXISTS usuario" +
                "(" +
                "idusuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "usuario TEXT NOT NULL ," +
                "area TEXT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(Usuario);
        //------------------------------------------------------------------------------------------
        String Dia = "CREATE TABLE IF NOT EXISTS datas" +
                "(dia INTEGER)";
        sqLiteDatabase.execSQL(Dia);
        //------------------------------------------------------------------------------------------
        String Funcionario = "CREATE TABLE IF NOT EXISTS funcionario" +
                "(" +
                "idFuncionario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE ," +
                "idCargo INTEGER ," +
                "idStatus INTEGER ," +
                "idEscala INTEGER," +
                "folga1 INTEGER ," +
                "folga2 INTEGER ," +
                "re TEXT NOT NULL UNIQUE," +
                "nomeFuncionario TEXT ," +
                "telefone TEXT ," +
                "rg TEXT ," +
                "cpf TEXT," +
                "endereco TEXT ," +
                "bairro TEXT ," +
                "cidade TEXT ," +
                "uf TEXT ," +
                "obsFuncionario TEXT,"+
                "FOREIGN KEY(idCargo) REFERENCES cargo(idCargo)" +
                ")";
        sqLiteDatabase.execSQL(Funcionario);
        //------------------------------------------------------------------------------------------

        String Movimentacao = "CREATE TABLE IF NOT EXISTS movimentacao" +
                "(" +
                "idMovimentacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "dia INTEGER," +
                "cliente TEXT," +
                "unidade TEXT," +
                "motivo TEXT," +
                "reAusente TEXT ," +
                "nomeAusente TEXT, " +
                "reCobertura TEXT," +
                "nomeCobertura TEXT, " +
                "obsMovimentacao TEXT" +
                ")";
        sqLiteDatabase.execSQL(Movimentacao);
        //------------------------------------------------------------------------------------------
        String Ocorrencia = "CREATE TABLE IF NOT EXISTS ocorrencia" +
                "(" +
                "idOcorrencia INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "cliente TEXT, " +
                "unidade TEXT, " +
                "bo TEXT, " +
                "tipoOcorrencia TEXT , " +
                "data INTEGER , " +
                "hora TEXT , " +
                "relato TEXT)";
        sqLiteDatabase.execSQL(Ocorrencia);
        //------------------------------------------------------------------------------------------
        String Ronda = "CREATE TABLE IF NOT EXISTS ronda" +
                "(" +
                "idRonda INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "dataInicial INTEGER," +
                "horaInicial TEXT," +
                "KmInicial TEXT," +
                "dataAbastecimento INTEGER," +
                "horaAbastecimento TEXT," +
                "vtr TEXT," +
                "kmAbastecimento TEXT," +
                "valorAbastecimento INTEGER," +
                "notaAbastecimento TEXT," +
                "dataFinal INTEGER," +
                "horaFinal TEXT," +
                "KmFinal TEXT," +
                "obsRonda TEXT " +
                ")";
        sqLiteDatabase.execSQL(Ronda);
        //------------------------------------------------------------------------------------------
        String Tarefa = "CREATE TABLE IF NOT EXISTS tarefa" +
                "(" +
                "idTarefa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "tarefa TEXT," +
                "data INTEGER," +
                "hora TEXT," +
                "status TEXT," +
                "descricao TEXT" +
                ")";
        sqLiteDatabase.execSQL(Tarefa);
        //------------------------------------------------------------------------------------------
        String Unidade = "CREATE TABLE IF NOT EXISTS unidade" +
                "(" +
                "idUnidade INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "idCliente INTEGER," +
                "nomeUnidade TEXT," +
                "nomeContato TEXT," +
                "telefone TEXT," +
                "endereco TEXT," +
                "bairro TEXT," +
                "cidade TEXT," +
                "uf TEXT," +
                "obsUnidade TEXT,"+
                "FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)" +
                ")";
        sqLiteDatabase.execSQL(Unidade);
        //------------------------------------------------------------------------------------------
        String Posto = "CREATE TABLE IF NOT EXISTS posto" +
                "(" +
                "idPosto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "nomePosto TEXT ,"+
                "idUnidade INTEGER ," +
                "idCargo INTEGER ," +
                "idEscala INTEGER ," +
                "turno TEXT ," +
                "entrada1 TEXT ," +
                "saida1 TEXT ," +
                "entrada2 TEXT ," +
                "saida2 TEXT ," +
                "idFuncionario INTEGER DEFAULT '1',"+
                "idFolguista INTEGER ,"+
                "obsPosto TEXT,"+
                "FOREIGN KEY(idUnidade) REFERENCES unidade(idUnidade),"+
                "FOREIGN KEY(idCargo) REFERENCES cargo(idCargo)," +
                "FOREIGN KEY(idEscala) REFERENCES escala(idEscala)" +
                ")";

        sqLiteDatabase.execSQL(Posto);

        //------------------------------------------------------------------------------------------

        String Material = "CREATE TABLE IF NOT EXISTS material" +
                "(" +
                "idMaterial INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "idUnidade INTEGER," +
                "codigo TEXT NOT NULL UNIQUE," +
                "descricao TEXT," +
                "obsMaterial TEXT," +
                "FOREIGN KEY(idUnidade) REFERENCES unidade(idUnidade)"+
                ")";
        sqLiteDatabase.execSQL(Material);
        //------------------------------------------------------------------------------------------
        String Visita = "CREATE TABLE IF NOT EXISTS visita" +
                "(" +
                "idVisita INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "idRonda INTEGER," +
                "unidade TEXT," +
                "horaChegada TEXT, " +
                "kmChegada TEXT, " +
                "horaSaida TEXT," +
                "obsVisita TEXT,"+
                "FOREIGN KEY(idRonda) REFERENCES ronda(idRonda)"+
                ")";
        sqLiteDatabase.execSQL(Visita);

        //------------------------------------------------------------------------------------------

        String Vencimento = "CREATE TABLE IF NOT EXISTS vencimento" +
                        "(idVencimento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "idFuncionario INTEGER UNIQUE ,"+
                        "experiencia INTEGER," +
                        "ferias INTEGER," +
                        "reciclagem INTEGER ," +
                        "cnv INTEGER ," +
                        "cracha INTEGER ," +
                        "aso INTEGER ," +
                        "psicotecnico INTEGER," +
                        "obsVencimento TEXT ," +
                        "FOREIGN KEY(idFuncionario) REFERENCES ronda(idFuncionario)"+
                        ")";
                sqLiteDatabase.execSQL(Vencimento);

        //------------------------------------------------------------------------------------------

        String Uniforme = "CREATE TABLE IF NOT EXISTS uniforme" +
                "(idUniforme INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "idFuncionario INTEGER UNIQUE,"+
                "nrCobertura TEXT ," +
                "qtdCobertura TEXT ," +
                "dtCobertura INTEGER ," +
                "nrBlusa TEXT ," +
                "qtdBlusa TEXT ," +
                "dtBlusa INTEGER ," +
                "nrCamisa TEXT ," +
                "qtdCamisa TEXT ," +
                "dtCamisa INTEGER ," +
                "nrCalca TEXT ," +
                "qtdCalca TEXT ," +
                "dtCalca INTEGER ," +
                "nrCalcado TEXT ," +
                "qtdCalcado TEXT ," +
                "dtCalcado INTEGER ," +
                "obsUniforme TEXT ," +
                "FOREIGN KEY(idFuncionario) REFERENCES ronda(idFuncionario)"+
                ")";
        sqLiteDatabase.execSQL(Uniforme);

        //------------------------------------------------------------------------------------------


    }

    //==============================================================================================
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
    //==============================================================================================
}

