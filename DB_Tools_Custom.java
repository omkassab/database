package com.application.meditracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DB_Tools extends SQLiteOpenHelper {

    public Context context;

    // region Namensdefinitionen
    private static final String DATENBANK_NAME = "Medikationsplan.db";
    private static final int DATENBANK_VERSION = 1;

    // region MedBib
    private static final String MDB_TABLE_NAME = "MedBib";

    private static final String MDB_COLUMN_MEDID = "MedID";
    private static final String MDB_COLUMN_PZN = "PZN";
    private static final String MDB_COLUMN_ZULETZTBEARB = "zuletztBearb";
    private static final String MDB_COLUMN_HANDELSNAME = "Handelsname";
    private static final String MDB_COLUMN_STAERKEDOSIS = "Staerk_Dosis";
    private static final String MDB_COLUMN_EINHEIT = "Einheit";
    private static final String MDB_COLUMN_DARREICHNUNGSFORM = "DarreichungsForm";
    private static final String MDB_COLUMN_NEBENWIRKUNG = "Nebenwirkung";
    private static final String MDB_COLUMN_EINNHINWEIS = "EinnHinweis";
    private static final String MDB_COLUMN_PACKGROESSE = "PackGroesse";
    private static final String MDB_COLUMN_EINNEINHEIT = "EinnEinheit";
    private static final String MDB_COLUMN_ISVALID = "isValid";
    // endregion MedBib

    // region enthaelt
    private static final String ENT_TABLE_NAME = "enthaelt";

    private static final String ENT_COLUMN_MEDID = "MedID";
    private static final String ENT_COLUMN_WIRKSTOFFID = "WirkstoffID";
    // endregion enthaelt

    // region Einheit
    private static final String EIH_TABLE_NAME = "Einheit";

    private static final String EIH_COLUMN_EINID = "EinID";
    private static final String EIH_COLUMN_EINHEIT = "Einheit";
    private static final String EIH_COLUMN_ISVALID = "isValid";
    // endregion Einheit

    // region EinnEinheit
    private static final String EIN_TABLE_NAME = "EinnEinheit";

    private static final String EIN_COLUMN_EINNEINHEITID = "EinnEinheitID";
    private static final String EIN_COLUMN_EINHEIT = "Einheit";
    private static final String EIN_COLUMN_ISVALID = "isValid";
    // endregion EinnEinheit

    // region Wirkstoff
    private static final String WIS_TABLE_NAME = "Wirkstoff";

    private static final String WIS_COLUMN_WIRKSTOFFID = "WirkstoffID";
    private static final String WIS_COLUMN_BEZEICHNUNG = "Bezeichnung";
    private static final String WIS_COLUMN_ISVALID = "isValid";
    // endregion Wirkstoff

    // region wirktFuer
    private static final String WIF_TABLE_NAME = "wirktFuer";

    private static final String WIF_COLUMN_MEDID = "MedID";
    private static final String WIF_COLUMN_WIRKBERID = "WirkBerID";
    // endregion wirktFuer

    // region Wirkbereich
    private static final String WIB_TABLE_NAME = "Wirkbereich";

    private static final String WIB_COLUMN_WIRKBERID = "WirkBerID";
    private static final String WIB_COLUMN_BEZEICHNUNG = "Bezeichnung";
    private static final String WIB_COLUMN_ISVALID = "isValid";
    // endregion Wirkbereich

    // region Darreichungsform
    private static final String DAF_TABLE_NAME = "Darreichungsform";

    private static final String DAF_COLUMN_DARRID = "DarrID";
    private static final String DAF_COLUMN_BEZEICHNUNG = "Bezeichnung";
    private static final String DAF_COLUMN_ISVALID = "isValid";
    // endregion Darreichungsform

    // region Anordnung
    private static final String ANO_TABLE_NAME = "Anordnung";

    private static final String ANO_COLUMN_ANORDNUNGSID = "AnordnungID";
    private static final String ANO_COLUMN_TYP = "Typ";
    private static final String ANO_COLUMN_NICHTEXAKT = "NichtExakt";
    private static final String ANO_COLUMN_MORVOR = "MorVor";
    private static final String ANO_COLUMN_MORZUR = "MorZur";
    private static final String ANO_COLUMN_MORNACH = "MorNach";
    private static final String ANO_COLUMN_MITVOR = "MitVor";
    private static final String ANO_COLUMN_MITZUR = "MitZur";
    private static final String ANO_COLUMN_MITNACH = "MitNach";
    private static final String ANO_COLUMN_ABVOR = "AbVor";
    private static final String ANO_COLUMN_ABZUR = "AbZur";
    private static final String ANO_COLUMN_ABNACH = "AbNach";
    private static final String ANO_COLUMN_NACHT = "Nacht";
    private static final String ANO_COLUMN_STARTZEITP = "Startzeitp";
    private static final String ANO_COLUMN_ENDZEITP = "Endzeitp";
    private static final String ANO_COLUMN_USER = "User";
    private static final String ANO_COLUMN_MEDIKAMENT = "Medikament";
    private static final String ANO_COLUMN_ARZT = "Arzt";
    private static final String ANO_COLUMN_EINNMAX = "EinMax";
    private static final String ANO_COLUMN_ISVALID = "isValid";
    // endregion Anordung

    // region Arzt
    private static final String ARZ_TABLE_NAME = "Arzt";

    private static final String ARZ_COLUMN_ARZTID = "ArztID";
    private static final String ARZ_COLUMN_FACHRICHTUNG = "Fachrichtung";
    private static final String ARZ_COLUMN_NAME = "Name";
    private static final String ARZ_COLUMN_ISVALID = "isValid";

    //endregion Arzt

    // region Fachrichtung
    private static final String FAC_TABLE_NAME = "Fachrichtung";

    private static final String FAC_COLUMN_FACHRICHTUNGID = "FachrichtungID";
    private static final String FAC_COLUMN_BEZEICHNUNG = "Bezeichnung";
    private static final String FAC_COLUMN_ISVALID = "isValid";
    // endregion Fachrichtung

    // region Rhythmus
    private static final String RHY_TABLE_NAME = "Rhythmus";

    private static final String RHY_COLUMN_RHYTHMID ="RhythmID";
    private static final String RHY_COLUMN_ALLXTAGE = "AlleXTage";
    private static final String RHY_COLUMN_WOCHENTAGE = "Wochentage";
    private static final String RHY_COLUMN_SCHEMAAKTIV = "SchemaAktiv";
    private static final String RHY_COLUMN_SCHEMAINAKTIV = "SchemaInaktiv";
    private static final String RHY_COLUMN_ISVALID = "isValid";
    // endregion Rhythmus

    // region hatRhythmus
    private static final String HRH_TABLE_NAME = "hatRhythmus";

    private static final String HRH_COLUMN_ANORDNUNGID = "AnordungID";
    private static final String HRH_COLUMN_RHYTHMID = "RhythmID";
    //endregion hatRhytmus

    // region User
    private static final String USR_TABLE_NAME = "User";

    private static final String USR_COLUMN_USERID = "UserID";
    private static final String USR_COLUMN_NUTZERNAME = "NutzerName";
    private static final String USR_COLUMN_NUTZERVORNAME = "NutzerVorname";
    private static final String USR_COLUMN_GEBURTSTAG = "Geburtstag";
    private static final String USR_COLUMN_ANLEGEDAT = "AnlegeDat";
    private static final String USR_COLUMN_ARZT = "Arzt";
    private static final String USR_COLUMN_ISVALID = "isValid";

    //endregion User

    // region EinnahmeDoku
    private static final String EDO_TABLE_NAME = "EinnahmeDoku";
    private static final String EDO_COLUMN_DOKUID = "DokuID";
    private static final String EDO_COLUMN_ZEITPEINNSOLL = "ZeitpEinnSoll";
    private static final String EDO_COLUMN_ZEITEINNIST = "ZeitpEinnIst";
    private static final String EDO_COLUMN_ZEITEINTRAG = "ZeitpEintrag";
    private static final String EDO_COLUMN_MENGE = "Menge";
    private static final String EDO_COLUMN_EINHEIT = "Einheit";
    private static final String EDO_COLUMN_ANORDNUNG = "Anordnung";
    private static final String EDO_COLUMN_GENOMMEN = "Genommen";
    private static final String EDO_COLUMN_BEMERKUNG = "Bemerkung";
    private static final String EDO_COLUMN_KOMMENTAR = "Kommentar";
    private static final String EDO_COLUMN_ISVALID = "isValid";
    //endregion EinnahmeDoku

    // region Tagebucheintrag
    private static final String TAG_TABLE_NAME = "Tagebucheintrag";
    private static final String TAG_COLUMN_EINTRAGID = "EintragID";
    private static final String TAG_COLUMN_EINTRAGTAG = "EintragTag";
    private static final String TAG_COLUMN_ANLAGEDAT = "AnlageDat";
    private static final String TAG_COLUMN_USER = "User";
    private static final String TAG_COLUMN_EINTRAG = "Eintrag";
    private static final String TAG_COLUMN_ISVALID = "isValid";
    //endregion Tagebucheintrag

    // endregion Namensdefinitionen


    public DB_Tools(@Nullable Context context){
        super(context, DATENBANK_NAME,null,DATENBANK_VERSION);
        this.context = context;
    }

    //Die Methode onCreate wird beim ersten Zugriff auf die Datenbank aufgerufen
    //Das Prinzip dahinter ist wir erstellen immer eine Abfrage mit dem Namen query_[Tabellenabkürzung], in den wir den SQL-CREATE-TABLE-Befehl erstellen. Der wird dann ausgeführt und die nächste query erstellt.
    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String query_MDB =
                "CREATE TABLE " + MDB_TABLE_NAME +
                        " (" + MDB_COLUMN_MEDID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MDB_COLUMN_PZN + " INTEGER, " +
                        MDB_COLUMN_ZULETZTBEARB + " LONG NOT NULL, " +
                        MDB_COLUMN_HANDELSNAME + " TEXT NOT NULL, " +
                        MDB_COLUMN_STAERKEDOSIS + " FLOAT, " +
                        MDB_COLUMN_EINHEIT + " INTEGER, " +
                        MDB_COLUMN_DARREICHNUNGSFORM + " INTEGER NOT NULL, " +
                        MDB_COLUMN_NEBENWIRKUNG + " TEXT, " +
                        MDB_COLUMN_EINNHINWEIS + " TEXT, " +
                        MDB_COLUMN_PACKGROESSE + " FLOAT, " +
                        MDB_COLUMN_EINNEINHEIT + " INTEGER NOT NULL,"+
                        MDB_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_MDB);

        String query_WIS =
                "CREATE TABLE " + WIS_TABLE_NAME +
                        " (" + WIS_COLUMN_WIRKSTOFFID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WIS_COLUMN_BEZEICHNUNG + " TEXT NOT NULL,"+
                        WIS_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_WIS);

        String query_ENT =
                "CREATE TABLE " + ENT_TABLE_NAME +
                        " (" + ENT_COLUMN_MEDID + " INTEGER  NOT NULL, " +
                        ENT_COLUMN_WIRKSTOFFID + " INTEGER NOT NULL,"+
                        "FOREIGN KEY ("+ENT_COLUMN_MEDID+") REFERENCES "+MDB_TABLE_NAME+"("+MDB_COLUMN_MEDID+")," +
                        "FOREIGN KEY ("+ENT_COLUMN_WIRKSTOFFID+") REFERENCES "+WIS_TABLE_NAME+"("+WIS_COLUMN_WIRKSTOFFID+")); ";
        db.execSQL(query_ENT);

        String query_EIH =
                "CREATE TABLE " + EIH_TABLE_NAME +
                        " (" + EIH_COLUMN_EINID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                        EIH_COLUMN_EINHEIT + " TEXT NOT NULL,"+
                        EIH_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_EIH);

        String query_WIB =
                "CREATE TABLE " + WIB_TABLE_NAME +
                        " (" + WIB_COLUMN_WIRKBERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WIB_COLUMN_BEZEICHNUNG + " TEXT NOT NULL,"+
                        WIB_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_WIB);

        String query_WIF =
                "CREATE TABLE " + WIF_TABLE_NAME +
                        " (" + WIF_COLUMN_MEDID + " INTEGER  NOT NULL, " +
                        WIF_COLUMN_WIRKBERID + " INTEGER NOT NULL,"+
                        "FOREIGN KEY ("+WIF_COLUMN_MEDID+") REFERENCES "+MDB_TABLE_NAME+"("+MDB_COLUMN_MEDID+")," +
                        "FOREIGN KEY ("+WIF_COLUMN_WIRKBERID+") REFERENCES "+WIS_TABLE_NAME+"("+WIS_COLUMN_WIRKSTOFFID+")); ";
        db.execSQL(query_WIF);

        String query_DAF =
                "CREATE TABLE " + DAF_TABLE_NAME +
                        " (" + DAF_COLUMN_DARRID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                        DAF_COLUMN_BEZEICHNUNG + " TEXT NOT NULL,"+
                        DAF_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_DAF);

        String query_EIN =
                "CREATE TABLE " + EIN_TABLE_NAME +
                        " (" + EIN_COLUMN_EINNEINHEITID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                        EIN_COLUMN_EINHEIT + " TEXT NOT NULL,"+
                        EIN_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_EIN);

        String query_ANO =
                "CREATE TABLE " + ANO_TABLE_NAME +
                        "(" + ANO_COLUMN_ANORDNUNGSID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ANO_COLUMN_TYP + " INTEGER NOT NULL," +
                        ANO_COLUMN_STARTZEITP + " LONG," +
                        ANO_COLUMN_ENDZEITP + " LONG," +
                        ANO_COLUMN_USER + " INTEGER NOT NULL," +
                        ANO_COLUMN_MEDIKAMENT + " INTEGER NOT NULL," +
                        ANO_COLUMN_ARZT+" INTEGER,"+
                        ANO_COLUMN_MORVOR + " FLOAT," +
                        ANO_COLUMN_MORZUR + " FLOAT," +
                        ANO_COLUMN_MORNACH + " FLOAT," +
                        ANO_COLUMN_MITVOR + " FLOAT," +
                        ANO_COLUMN_MITZUR + " FLOAT," +
                        ANO_COLUMN_MITNACH + " FLOAT," +
                        ANO_COLUMN_ABVOR + " FLOAT," +
                        ANO_COLUMN_ABZUR + " FLOAT," +
                        ANO_COLUMN_ABNACH + " FLOAT," +
                        ANO_COLUMN_NACHT + " FLOAT," +
                        ANO_COLUMN_NICHTEXAKT + " BOOLEAN," +
                        ANO_COLUMN_EINNMAX + " FLOAT,"+
                        ANO_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_ANO);

        String query_RYH =
                "CREATE TABLE " + RHY_TABLE_NAME +
                        "("+ RHY_COLUMN_RHYTHMID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        RHY_COLUMN_ALLXTAGE + " INTEGER," +
                        RHY_COLUMN_WOCHENTAGE + " INTEGER," +
                        RHY_COLUMN_SCHEMAAKTIV + " INTEGER," +
                        RHY_COLUMN_SCHEMAINAKTIV + " INTEGER,"+
                        RHY_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_RYH);

        String query_HRH =
                "CREATE TABLE " + HRH_TABLE_NAME +
                        "("+ HRH_COLUMN_ANORDNUNGID + " INTEGER,"+
                        HRH_COLUMN_RHYTHMID + " INTEGER NOT NULL,"+
                        "FOREIGN KEY ("+HRH_COLUMN_ANORDNUNGID+") REFERENCES " + ANO_TABLE_NAME +" ("+ANO_COLUMN_ANORDNUNGSID +")," +
                        "FOREIGN KEY ("+HRH_COLUMN_RHYTHMID+") REFERENCES " + RHY_TABLE_NAME + " (" + RHY_COLUMN_RHYTHMID + "));";
        db.execSQL(query_HRH);

        String query_ARZ =
                "CREATE TABLE " + ARZ_TABLE_NAME +
                        "("+ ARZ_COLUMN_ARZTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ARZ_COLUMN_FACHRICHTUNG + " INTEGER," +
                        ARZ_COLUMN_NAME + " TEXT,"+
                        ARZ_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_ARZ);

        String query_FAC =
                "CREATE TABLE " + FAC_TABLE_NAME +
                        "("+ FAC_COLUMN_FACHRICHTUNGID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FAC_COLUMN_BEZEICHNUNG + " TEXT NOT NULL,"+
                        FAC_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_FAC);

        String query_USR =
                "CREATE TABLE " + USR_TABLE_NAME +
                        "("+ USR_COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        USR_COLUMN_NUTZERNAME + " TEXT NOT NULL," +
                        USR_COLUMN_NUTZERVORNAME + " TEXT NOT NULL," +
                        USR_COLUMN_GEBURTSTAG + " LONG," +
                        USR_COLUMN_ANLEGEDAT + " LONG NOT NULL," +
                        USR_COLUMN_ARZT + " INTEGER,"+
                        USR_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_USR);

        String query_EDO =
                "CREATE TABLE " + EDO_TABLE_NAME +
                        "("+ EDO_COLUMN_DOKUID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        EDO_COLUMN_ZEITPEINNSOLL + " LONG," +
                        EDO_COLUMN_ZEITEINNIST + " LONG," +
                        EDO_COLUMN_ZEITEINTRAG + " LONG," +
                        EDO_COLUMN_MENGE + " FLOAT," +
                        EDO_COLUMN_EINHEIT + " INTEGER," +
                        EDO_COLUMN_ANORDNUNG + " INTEGER," +
                        EDO_COLUMN_GENOMMEN + " BOOLEAN," +
                        EDO_COLUMN_BEMERKUNG + " TEXT," +
                        EDO_COLUMN_KOMMENTAR + " TEXT,"+
                        EDO_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_EDO);

        String query_TAG =
                "CREATE TABLE " + TAG_TABLE_NAME +
                        "("+ TAG_COLUMN_EINTRAGID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        TAG_COLUMN_EINTRAGTAG + " LONG NOT NULL," +
                        TAG_COLUMN_ANLAGEDAT + " LONG NOT NULL," +
                        TAG_COLUMN_USER + " INTEGER NOT NULL," +
                        TAG_COLUMN_EINTRAG + " TEXT NOT NULL,"+
                        TAG_COLUMN_ISVALID + " BOOLEAN); ";
        db.execSQL(query_TAG);
    }

    //Diese Methode wird ausgeführt, wenn eine Datenbank-Aktualisierung notwendig ist
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //gibt Meldung wenn Datenbank geupgraded werden soll, sollte eigentlich nie sein
        Toast.makeText(context, "Das hier sollte nie passieren...",Toast.LENGTH_LONG).show();
    }

    //Die Methode macht nix außer ein Datenbankobjekt zu initialisieren, legt damit eine Datenbank an, wenn keine da ist.
    //Die onCrate-Methode wird in dem Moment aufgerufen, wo ein SQLiteDatabase-Object erstellt wird und es die entsprechenden Tabellen noch nicht gibt.
    //Die Methode unten macht also nur das, sie erstellt die Tabellen in der Datenbank, wenn es sie noch nocht gibt.
    void initialisiereDatenbankWennNoetig() {
        SQLiteDatabase db = this.getReadableDatabase();
    }

    // region Test/Debug

    //gerade nix hier...

    // endregion Test/Debug

    // region UI-Hilfe

    //Liefert ein Array von SpinnerItems aus einer bestimmten Tabelle – ideal für Dropdown-Menüs
    public SpinnerItem[] gibSpinnerItems(int tabelle){
        String Zieltabelle;

        //Zuordnung der Zieltabelle zum übergebenen Parameter
        switch (tabelle){
            case 0: Zieltabelle = WIS_TABLE_NAME;
                break;
            case 1: Zieltabelle = EIH_TABLE_NAME;
                break;
            case 2: Zieltabelle = DAF_TABLE_NAME;
                break;
            case 3: Zieltabelle = WIB_TABLE_NAME;
                break;
            case 4: Zieltabelle = EIN_TABLE_NAME;
                break;
            default: return null;
        }

        //Abfrage generieren und durchführen. Noch nicht sicher gegen SQL-Injection
        String abfrage = "SELECT * FROM "+Zieltabelle+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor erg = db.rawQuery(abfrage,null);

        //Wenn es kein Ergebnis gibt geht null zurück
        if(erg.getCount()<1) return null;

        //return-Array anlegen, durch den Cursor durchiterieren und schrittweise das Array füllen
        SpinnerItem[] rtn = new SpinnerItem[erg.getCount()];
        if(erg.moveToFirst()){
            do{
                rtn[erg.getPosition()] = new SpinnerItem(erg.getInt(0),erg.getString(1));
            } while (erg.moveToNext());
        }
        erg.close();
        return rtn;
    }

    // endregion UI-Hilfe

    // region Objekt zu ID

    public Medikament MedikamentZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + MDB_TABLE_NAME + " WHERE "+ MDB_COLUMN_MEDID + " = " + id+" AND "+MDB_COLUMN_ISVALID+" = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor abf = db.rawQuery(abfrage,null);

        if (abf.moveToFirst()) {

            Medikament md = new Medikament();

            md.MedID = abf.getInt(0);
            md.PZN = abf.getInt(1);
            md.ZuletztBearb = abf.getLong(2);
            md.Handelsname = abf.getString(3);
            md.Staerke_Dosis = abf.getFloat(4);
            md.Einheit = EinheitZuID(abf.getInt(5));
            md.Darreichungsform = DarreichungsformZuID(abf.getInt(6));
            md.Nebenwirkung = abf.getString(7);
            md.EinnHinweis = abf.getString(8);
            md.PackGroesse = abf.getFloat(9);
            md.EinnEinheit = EinnEinheitZuID(abf.getInt(10));
            md.isValid = abf.getInt(11)==1; //kleiner Trick, booleans werden in SQLite als int gespeichert, 1=true

            md.Wirkstoffe = WirkstoffeZuMedID(abf.getInt(0));
            md.Wirkbereiche = WirkbereicheZuMedID(abf.getInt(0));
            abf.close();

            return md;
        }
        else {
            // Kein Treffer gefunden
            return null;
        }
    }
    public Einheit EinheitZuID(int id) {
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + EIH_TABLE_NAME + " WHERE " + EIH_COLUMN_EINID + " = " + id + " AND " + EIH_COLUMN_ISVALID + " = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor Einheit_abf = db.rawQuery(abfrage, null);

        if (Einheit_abf.moveToFirst()) {
            Einheit einh = new Einheit();
            einh.EinID = Einheit_abf.getInt(0);
            einh.Einheit = Einheit_abf.getString(1);
            einh.isValid = Einheit_abf.getInt(2)==1;
            Einheit_abf.close();
            return einh;
        }
        else return null;
    }
    public EinnEinheit EinnEinheitZuID(int id) {
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + EIN_TABLE_NAME + " WHERE " + EIN_COLUMN_EINNEINHEITID + " = " + id + " AND " + EIN_COLUMN_ISVALID + " = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor EinnEinheit_abf = db.rawQuery(abfrage, null);

        if (EinnEinheit_abf.moveToFirst()) {
            EinnEinheit einnEinh = new EinnEinheit();
            einnEinh.EinnEinheitID = EinnEinheit_abf.getInt(0);
            einnEinh.Einheit = EinnEinheit_abf.getString(1);
            einnEinh.isValid = EinnEinheit_abf.getInt(2)==1;
            EinnEinheit_abf.close();
            return einnEinh;
        }
        else return null;
    }
    public DarreichungsForm DarreichungsformZuID(int id) {
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + DAF_TABLE_NAME + " WHERE " + DAF_COLUMN_DARRID + " = " + id + " AND " + DAF_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Darreichungsform_abf = db.rawQuery(abfrage, null);

        if (Darreichungsform_abf.moveToFirst()) {
            DarreichungsForm darreich = new DarreichungsForm();
            darreich.DarrID = Darreichungsform_abf.getInt(0);
            darreich.Bezeichnung = Darreichungsform_abf.getString(1);
            darreich.isValid = Darreichungsform_abf.getInt(2)==1;
            Darreichungsform_abf.close();
            return darreich;
        }
        else return null;
    }
    public Wirkstoff WirkstoffZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + WIS_TABLE_NAME + " WHERE " + WIS_COLUMN_WIRKSTOFFID + " = " + id + " AND " + WIS_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Wirkstoff_abf = db.rawQuery(abfrage, null);

        if (Wirkstoff_abf.moveToFirst()) {
            Wirkstoff wirkstoff = new Wirkstoff();
            wirkstoff.WirkstoffID = Wirkstoff_abf.getInt(0);
            wirkstoff.Bezeichnung = Wirkstoff_abf.getString(1);
            wirkstoff.isValid = Wirkstoff_abf.getInt(2)==1;
            Wirkstoff_abf.close();
            return wirkstoff;
        }
        else return null;
    }
    public Wirkbereich WirkbereicZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + WIB_TABLE_NAME + " WHERE " + WIB_COLUMN_WIRKBERID + " = " + id + " AND " + WIB_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Wirkbereich_abf = db.rawQuery(abfrage, null);

        if (Wirkbereich_abf.moveToFirst()) {
            Wirkbereich wirkbereich = new Wirkbereich();
            wirkbereich.WirkBerID = Wirkbereich_abf.getInt(0);
            wirkbereich.Bezeichnung = Wirkbereich_abf.getString(1);
            wirkbereich.isValid = Wirkbereich_abf.getInt(2)==1;
            Wirkbereich_abf.close();
            return wirkbereich;
        }
        else return null;
    }
    public Fachrichtung FachrichtungZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + FAC_TABLE_NAME + " WHERE " + FAC_COLUMN_FACHRICHTUNGID + " = " + id + " AND " + FAC_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Fachrichtung_abf = db.rawQuery(abfrage, null);

        if (Fachrichtung_abf.moveToFirst()) {
            Fachrichtung fachrichtung = new Fachrichtung();
            fachrichtung.FachrichtungID = Fachrichtung_abf.getInt(0);
            fachrichtung.Bezeichnung = Fachrichtung_abf.getString(1);
            fachrichtung.isValid = Fachrichtung_abf.getInt(2)==1;
            Fachrichtung_abf.close();
            return fachrichtung;
        }
        else return null;
    }
    public Arzt ArztZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + ARZ_TABLE_NAME + " WHERE " + ARZ_COLUMN_ARZTID + " = " + id + " AND " + ARZ_COLUMN_ARZTID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Arzt_abf = db.rawQuery(abfrage, null);

        if (Arzt_abf.moveToFirst()) {
            Arzt arzt = new Arzt();
            arzt.ArztID = Arzt_abf.getInt(0);
            arzt.Fachrichtung = FachrichtungZuID(Arzt_abf.getInt(1));
            arzt.Name = Arzt_abf.getString(2);
            arzt.isValid = Arzt_abf.getInt(3)==1;
            Arzt_abf.close();
            return arzt;
        }
        else return null;
    }
    public User UserZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + USR_TABLE_NAME + " WHERE " + USR_COLUMN_USERID + " = " + id + " AND " + USR_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor User_abf = db.rawQuery(abfrage, null);

        if (User_abf.moveToFirst()) {
            User user = new User();
            user.UserID = User_abf.getInt(0);
            user.NutzerName = User_abf.getString(1);
            user.NutzerVorname = User_abf.getString(2);
            user.Geburtstag = User_abf.getLong(3);
            user.AnlageDat = User_abf.getLong(4);
            user.Arzt = ArztZuID(User_abf.getInt(5));
            user.isValid = User_abf.getInt(6)==1;
            User_abf.close();
            return user;
        }
        else return null;
    }

    public Tagebucheintrag TagebucheintragZuID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_COLUMN_EINTRAGID + " = " + id + " AND " + TAG_COLUMN_ISVALID + " = 1;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Tagebucheintrag_abf = db.rawQuery(abfrage, null);

        if (Tagebucheintrag_abf.moveToFirst()) {
            Tagebucheintrag tagebucheintrag = new Tagebucheintrag();
            tagebucheintrag.EintragID = Tagebucheintrag_abf.getInt(0);
            tagebucheintrag.EintragTag = Tagebucheintrag_abf.getLong(1);
            tagebucheintrag.AnlageDat = Tagebucheintrag_abf.getLong(2);
            tagebucheintrag.User = UserZuID(Tagebucheintrag_abf.getInt(3));
            tagebucheintrag.Eintrag = Tagebucheintrag_abf.getString(4);
            tagebucheintrag.isValid = Tagebucheintrag_abf.getInt(5)==1;
            Tagebucheintrag_abf.close();
            return tagebucheintrag;
        }
        else return null;
    }

    public Anordnung AnordnungZuID (int id) {
        //SQLite Abfrage
        String abfrage = "SELECT * FROM " + ANO_TABLE_NAME + " WHERE " + ANO_COLUMN_ANORDNUNGSID + " = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Anordnung_abf = db.rawQuery(abfrage, null);
        if (Anordnung_abf.moveToFirst()) {
            Anordnung anordnung = new Anordnung();
            anordnung.AnordnungID = Anordnung_abf.getInt(0);
            anordnung.Typ = Anordnung_abf.getInt(1);
            anordnung.Startzeitp = Anordnung_abf.getLong(2);
            anordnung.Endzeitp = Anordnung_abf.getLong(3);
            anordnung.User = UserZuID(Anordnung_abf.getInt(4));
            anordnung.Medikament = MedikamentZuID(Anordnung_abf.getInt(5));
            anordnung.Arzt = ArztZuID(Anordnung_abf.getInt(6));
            anordnung.MorVor = Anordnung_abf.getFloat(7);
            anordnung.MorZur = Anordnung_abf.getFloat(8);
            anordnung.MorNach = Anordnung_abf.getFloat(9);
            anordnung.MitVor = Anordnung_abf.getFloat(10);
            anordnung.MitZur = Anordnung_abf.getFloat(11);
            anordnung.MitNach = Anordnung_abf.getFloat(12);
            anordnung.AbVor = Anordnung_abf.getFloat(13);
            anordnung.AbZur = Anordnung_abf.getFloat(14);
            anordnung.AbNach = Anordnung_abf.getFloat(15);
            anordnung.Nacht = Anordnung_abf.getFloat(16);
            anordnung.NichtExakt = Anordnung_abf.getInt(17) == 1;
            anordnung.EinnMax = Anordnung_abf.getFloat(18);
            anordnung.isValid = Anordnung_abf.getInt(19) == 1;
            anordnung.Rhytmen = RhythmenZuID(anordnung.AnordnungID);
            return anordnung;
        } else return null;
    }


    public EinnahmeDoku EinnahmeDokuZuID (int id){
        //SQLite Abfrage
        String abfrage = "SELECT * FROM " + EDO_TABLE_NAME + " WHERE " + EDO_COLUMN_DOKUID + " = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor EinnahmeDoku_abf = db.rawQuery(abfrage, null);
        if(EinnahmeDoku_abf.moveToFirst()){
            EinnahmeDoku einnahmeDoku = new EinnahmeDoku();
            einnahmeDoku.DokuID = EinnahmeDoku_abf.getInt(0);
            einnahmeDoku.ZeitpEinnSoll = EinnahmeDoku_abf.getLong(1);
            einnahmeDoku.ZeitpEinnIst = EinnahmeDoku_abf.getLong(2);
            einnahmeDoku.ZeitpEintrag = EinnahmeDoku_abf.getLong(3);
            einnahmeDoku.Menge = EinnahmeDoku_abf.getFloat(4);
            einnahmeDoku.Einheit = EinheitZuID(EinnahmeDoku_abf.getInt(5));
            einnahmeDoku.Anordnung = AnordnungZuID(EinnahmeDoku_abf.getInt(6));
            einnahmeDoku.Genommen = EinnahmeDoku_abf.getInt(7) ==1;
            einnahmeDoku.Bemerkung = EinnahmeDoku_abf.getString(8);
            einnahmeDoku.Kommentar = EinnahmeDoku_abf.getString(9);
            einnahmeDoku.isValid = EinnahmeDoku_abf.getInt(10) ==1;
            EinnahmeDoku_abf.close();
            return einnahmeDoku;
        }
        else {
            //Kein Treffer gefunden
            EinnahmeDoku_abf.close();
            return null;
        }
    }

    public Rhythmus RhythmusZuID(int id){
        String abfrage = "SELECT * FROM " + RHY_TABLE_NAME + " WHERE " + RHY_COLUMN_RHYTHMID + " = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Rhythmus_abf = db.rawQuery(abfrage, null);
        if(Rhythmus_abf.moveToFirst()){
            Rhythmus rhythmus = new Rhythmus();
            rhythmus.RhythmID = Rhythmus_abf.getInt(0);
            rhythmus.AlleXTage = Rhythmus_abf.getInt(1);
            rhythmus.Wochentage = Rhythmus_abf.getInt(2);
            rhythmus.SchemaAktiv = Rhythmus_abf.getInt(3);
            rhythmus.SchemaInaktiv = Rhythmus_abf.getInt(4);
            rhythmus.isValid = Rhythmus_abf.getInt(5) ==1;
            return rhythmus;
        }
        else {
            return null;
        }
    }
    // endregion Objekt zu ID

    // region Verbindungstabellen auslesen

    public Wirkstoff[] WirkstoffeZuMedID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + ENT_TABLE_NAME + " WHERE " + ENT_COLUMN_MEDID + " = " + id+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor enthaelt_abf = db.rawQuery(abfrage, null);
        if (enthaelt_abf.moveToFirst()) {
            Wirkstoff[] wirkstoffe = new Wirkstoff[enthaelt_abf.getCount()];
            do{
                wirkstoffe[enthaelt_abf.getPosition()] = WirkstoffZuID(enthaelt_abf.getInt(1));
            } while (enthaelt_abf.moveToNext());
            return wirkstoffe;
        }
        else return null;
    }
    public Wirkbereich[] WirkbereicheZuMedID(int id){
        //SQLite SELECT-Abfrage
        String abfrage = "SELECT * FROM " + WIF_TABLE_NAME + " WHERE " + WIF_COLUMN_MEDID + " = " + id+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor wirkfuer_abf = db.rawQuery(abfrage, null);
        if (wirkfuer_abf.moveToFirst()) {
            Wirkbereich[] wirkbereiche = new Wirkbereich[wirkfuer_abf.getCount()];
            do{
                wirkbereiche[wirkfuer_abf.getPosition()] = WirkbereicZuID(wirkfuer_abf.getInt(1));
            } while (wirkfuer_abf.moveToNext());
            return wirkbereiche;
        }
        else return null;
    }

    public Rhythmus[] RhythmenZuID(int id){
        //SQLite abfrage
        String abfrage = "SELECT * FROM " + HRH_TABLE_NAME + " WHERE " + HRH_COLUMN_ANORDNUNGID + " = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Rhythmus_abf = db.rawQuery(abfrage, null);
        if(Rhythmus_abf.moveToFirst()){
            Rhythmus[] rhythmen = new Rhythmus[Rhythmus_abf.getCount()]; //das array ist so lang, wie viele beim Cursor rauskommen
            do {
                rhythmen[Rhythmus_abf.getPosition()] = RhythmusZuID(Rhythmus_abf.getInt(1));
            }
            while (Rhythmus_abf.moveToNext());
            return rhythmen;
        }
        else{
            return null;
        }
    }

    // endregion Verbindungstabellen auslesen

    // region Medikamente_Methoden

    //Gibt das zu der eingegebenen PZN passende Medikament zurück
    public Medikament MedikamentZuPzn(int pzn){
        //SQLite SELECT-Abfrage
        String PZNabfrage = "SELECT * FROM " + MDB_TABLE_NAME + " WHERE "+ MDB_COLUMN_PZN + " = " + pzn+" AND "+MDB_COLUMN_ISVALID+" = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor abf = db.rawQuery(PZNabfrage,null);

        if (abf.moveToFirst()) {

            Medikament md = MedikamentZuID(abf.getInt(0));
            abf.close();
            return md;
        }
        else {
            // Kein Treffer gefunden
            return null;
        }
    }

    public long medikamentAnlegen(Medikament medikament){
        //wurde ein gültiger Wert übergeben?
        if(medikament == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(medikament.MedID !=0 && MedikamentZuID(medikament.MedID)!=null){
            return medikament.MedID;
        }
        //Alternativ müssen wir eine neues medikament anlegen
        else{
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(MDB_COLUMN_PZN,medikament.PZN);
            values.put(MDB_COLUMN_ZULETZTBEARB,medikament.ZuletztBearb);
            values.put(MDB_COLUMN_HANDELSNAME,medikament.Handelsname);
            values.put(MDB_COLUMN_STAERKEDOSIS,medikament.Staerke_Dosis);
            if(medikament.Einheit!=null) values.put(MDB_COLUMN_EINHEIT,EinheitAnlegen(medikament.Einheit));
            values.put(MDB_COLUMN_DARREICHNUNGSFORM,DarreichungsformAnlegen(medikament.Darreichungsform));
            values.put(MDB_COLUMN_NEBENWIRKUNG,medikament.Nebenwirkung);
            values.put(MDB_COLUMN_EINNHINWEIS,medikament.EinnHinweis);
            values.put(MDB_COLUMN_PACKGROESSE,medikament.PackGroesse);
            values.put(MDB_COLUMN_EINNEINHEIT,EinnEinheitAnlegen(medikament.EinnEinheit));
            values.put(MDB_COLUMN_ISVALID,true);

            long neuerKey = db.insert(MDB_TABLE_NAME,null,values);

            //jetzt müssen wir noch die Verbindung zu Wirkstoff & Wirkbereich machen

            for(Wirkstoff wirkstoff:medikament.Wirkstoffe){
                ContentValues wirkstoffValues = new ContentValues();
                wirkstoffValues.put(ENT_COLUMN_MEDID,neuerKey);
                wirkstoffValues.put(ENT_COLUMN_WIRKSTOFFID,WirkstofAnlegen(wirkstoff));
                db.insert(ENT_TABLE_NAME,null,wirkstoffValues);
            }

            for(Wirkbereich wirkbereich: medikament.Wirkbereiche){
                ContentValues wirkbereichValues = new ContentValues();
                wirkbereichValues.put(WIF_COLUMN_MEDID,neuerKey);
                wirkbereichValues.put(WIF_COLUMN_WIRKBERID,WirkbereichAnlegen(wirkbereich));
                db.insert(WIF_TABLE_NAME,null,wirkbereichValues);
            }

            return neuerKey;
        }

    }

    public Medikament medikamentZuName(String handelsname){
        //SQLite SELECT-Abfrage
        String MediNameabfrage = "SELECT * FROM " + MDB_TABLE_NAME + " WHERE "+ MDB_COLUMN_HANDELSNAME + " = '" + handelsname+"' AND "+MDB_COLUMN_ISVALID+" = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor abf = db.rawQuery(MediNameabfrage,null);

        if (abf.moveToFirst()) {
            //Log.d("Test", "Suche erfolgreich!");
            Medikament md = MedikamentZuID(abf.getInt(0));
            abf.close();
            return md;
        }
        else {
            // Kein Treffer gefunden
            //Log.e("Test", "Suche nicht Erfolgreich!");
            return null;

        }
    }

    public boolean medikamentloeschen(Medikament medikament) {

        if (medikament == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + MDB_TABLE_NAME + " WHERE " + MDB_COLUMN_MEDID + " = " + medikament.MedID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + MDB_TABLE_NAME + " SET " + MDB_COLUMN_ISVALID + " = 0 " + " WHERE " +
                MDB_COLUMN_MEDID + " = " + medikament.MedID + ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }

    // endregion Medikamente-Methoden

    // region User-Methoden

    //prüft, ob mindestens ein Nutzer bereits in der DB gespeichert ist
    // === Methode ans Ende verschoben ===
public boolean existiertUser(){
        //SQL-Abfrage zum ausführen
        String abfrage = "SELECT * FROM " + USR_TABLE_NAME+";";
        //Auf die Datenbank zugreifen
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor erstellen
        Cursor cursor = db.rawQuery(abfrage,null);

        //Anzahl der Datensätze im Cursor ermitteln
        int count = cursor.getCount();
        cursor.close();
        //Wenn es Datensätze gibt, wird der boolean auf true gesetzt
        boolean datensätzeExistieren = count > 0;

        //If-Abfrage, um abzufragen, ob es Datensätze gibt. Wenn ja --> True, wenn nicht --> False
        if (datensätzeExistieren) {
            return true;
        }
        else{
            return false;
        }
    }

    //Legt den übergebenen User in die Datenbank
    // Gibt true zurück wenn erfolgreich, sonst false
    public long userAnlegen(User user){
        //wurde ein gültiger User übergeben?
        if(user == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(user.UserID !=0 && UserZuID(user.UserID)!=null){
            return user.UserID;
        }
        //Alternativ müssen wir einen neuen User anlegen
        else{
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(USR_COLUMN_NUTZERNAME,user.NutzerName);
            values.put(USR_COLUMN_NUTZERVORNAME,user.NutzerVorname);
            values.put(USR_COLUMN_GEBURTSTAG,user.NutzerVorname);
            values.put(USR_COLUMN_ANLEGEDAT,user.AnlageDat);
            //wenn ein Arzt mitgegeben wurde wird er angelegt/gezogen, der Primärschlüssel wird reingespeichert
            if(user.Arzt!=null) values.put(USR_COLUMN_ARZT ,ArztAnlegen(user.Arzt));
            values.put(USR_COLUMN_ISVALID,true);

            long neuerKey = db.insert(USR_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    //Gibt den User aus der Tabelle als ein User-Objekt zurück
    //Gibt null zurück falls es keinen user gibt
    public User userAbfragen(){

        //Abfrage erstellen
        String abfrage = "SELECT * FROM "+USR_TABLE_NAME+";";

        // Stellt Verbindung mit Datenbank her
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor mit Abfrage belegen
        Cursor cursor = db.rawQuery(abfrage,null);

        // Prüft, ob die Abfrage ein Ergebnis zurückgibt und gibt den User als Objekt zurück
        if (cursor.moveToFirst()) {
            User user = new User();
            user.UserID=cursor.getInt(0);
            user.NutzerName=cursor.getString(1);
            user.NutzerVorname=cursor.getString(2);
            user.Geburtstag=cursor.getLong(3);
            user.AnlageDat=cursor.getLong(4);
            user.Arzt=ArztZuID(cursor.getInt(5));
            cursor.close();
            return user;
        } else {
            cursor.close();
            return null;
        }
    }

    public boolean useraendern(User user){

        if (user == null) {

            return false;
        }

        String abfrage = " SELECT * FROM " + USR_TABLE_NAME + " WHERE " + USR_COLUMN_USERID + " = " + user.UserID + ";";

        // Stellt Verbindung mit Datenbank her
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor mit Abfrage belegen
        Cursor cursor = db.rawQuery(abfrage,null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + USR_TABLE_NAME + " SET " +
                USR_COLUMN_NUTZERNAME + " = " + "'" + user.NutzerName + "'" + "," +
                USR_COLUMN_NUTZERVORNAME + " = " + "'" + user.NutzerVorname + "'" + "," +
                USR_COLUMN_GEBURTSTAG + " = " + user.Geburtstag +
                " WHERE " + USR_COLUMN_USERID + " = " + user.UserID + ";";

        SQLiteDatabase db2 = this.getWritableDatabase();

        //Cursor mit Abfrage belegen
        db2.execSQL(abfrage2);


        return true;

    }

    public boolean userLoeschen(User user) {

        if (user == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + USR_TABLE_NAME + " WHERE " + USR_COLUMN_USERID + " = " + user.UserID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + USR_TABLE_NAME + " SET " + USR_COLUMN_ISVALID + " = 0 " + " WHERE " +
                USR_COLUMN_USERID + " = " + user.UserID + ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }

    // endregion User_Methoden

    // region Arzt_Methoden

    public long ArztAnlegen(Arzt arzt){
        //wurde ein gültiger Wert übergeben?
        if(arzt == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(arzt.ArztID !=0 && ArztZuID(arzt.ArztID)!=null){
            return arzt.ArztID;
        }
        //Alternativ müssen wir einen neuen Arzteintrag schreiben
        else{
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            //wenn eine Fachrichtung mitgegeben wurde wird sie im Fall angelegt, der Primärschlüssel wird reingespeichert
            if(arzt.Fachrichtung!=null) values.put(ARZ_COLUMN_FACHRICHTUNG,FachrichtungAnlegen(arzt.Fachrichtung));
            values.put(ARZ_COLUMN_NAME,arzt.Name);
            values.put(ARZ_COLUMN_ISVALID,true);

            long neuerKey = db.insert(ARZ_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Arzt_Methoden

    // region Fachrichtung_Methoden

    public long FachrichtungAnlegen(Fachrichtung fachrich){
        //wurde ein gültiger Wert übergeben?
        if(fachrich == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(fachrich.FachrichtungID != 0 && FachrichtungZuID(fachrich.FachrichtungID)!=null) {
            return fachrich.FachrichtungID;
        }
        //Alternativ müssen wir eine neue Fachrichtung anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FAC_COLUMN_BEZEICHNUNG, fachrich.Bezeichnung);
            values.put(FAC_COLUMN_ISVALID,true);

            long neuerKey = db.insert(FAC_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Fachrichtung_Methoden

    // region Einheit_Methoden

    public long EinheitAnlegen(Einheit einheit){
        //wurde ein gültiger Wert übergeben?
        if(einheit == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(einheit.EinID != 0 && EinheitZuID(einheit.EinID)!=null) {
            return einheit.EinID;
        }
        //Alternativ müssen wir eine neue Einheit anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EIH_COLUMN_EINHEIT, einheit.Einheit);
            values.put(EIH_COLUMN_ISVALID,true);

            long neuerKey = db.insert(EIH_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Einheit_Methoden

    // region Darreichungsform_Methoden

    public long DarreichungsformAnlegen(DarreichungsForm darreichungsForm){
        //wurde ein gültiger Wert übergeben?
        if(darreichungsForm == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(darreichungsForm.DarrID != 0 && DarreichungsformZuID(darreichungsForm.DarrID)!=null) {
            return darreichungsForm.DarrID;
        }
        //Alternativ müssen wir eine neue Darreichungsform anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DAF_COLUMN_BEZEICHNUNG, darreichungsForm.Bezeichnung);
            values.put(DAF_COLUMN_ISVALID,true);

            long neuerKey = db.insert(DAF_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Darreichungsform_Methoden

    // region Wirkbereich_Methoden

    public long WirkbereichAnlegen(Wirkbereich wirkbereich){
        //wurde ein gültiger Wert übergeben?
        if(wirkbereich == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(wirkbereich.WirkBerID != 0 && WirkbereicZuID(wirkbereich.WirkBerID)!=null) {
            return wirkbereich.WirkBerID;
        }
        //Alternativ müssen wir eine neue Darreichungsform anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(WIB_COLUMN_BEZEICHNUNG, wirkbereich.Bezeichnung);
            values.put(WIB_COLUMN_ISVALID,true);

            long neuerKey = db.insert(WIB_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Wirkbereich_Methoden

    // region EinnEinheit_Methoden

    public long EinnEinheitAnlegen(EinnEinheit einnEinheit){
        //wurde ein gültiger Wert übergeben?
        if(einnEinheit == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(einnEinheit.EinnEinheitID != 0 && EinnEinheitZuID(einnEinheit.EinnEinheitID)!=null) {
            return einnEinheit.EinnEinheitID;
        }
        //Alternativ müssen wir eine neue EinnEinheit anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EIN_COLUMN_EINHEIT, einnEinheit.Einheit);
            values.put(EIN_COLUMN_ISVALID,true);

            long neuerKey = db.insert(EIN_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion EinnEinheit_Methoden

    // region Wirkstoff_Methoden

    public long WirkstofAnlegen(Wirkstoff wirkstoff){
        //wurde ein gültiger Wert übergeben?
        if(wirkstoff == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(wirkstoff.WirkstoffID != 0 && WirkstoffZuID(wirkstoff.WirkstoffID)!=null) {
            return wirkstoff.WirkstoffID;
        }
        //Alternativ müssen wir einen neuen Wirkstoff anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(WIS_COLUMN_BEZEICHNUNG, wirkstoff.Bezeichnung);
            values.put(WIS_COLUMN_ISVALID,true);

            long neuerKey = db.insert(WIS_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    // endregion Wirkstoff_Methoden

    // region Tagebucheintrag_Methoden

    public long tagebucheintragAnlegen(Tagebucheintrag tagebucheintrag){
        //wurde ein gültiger Wert übergeben?
        if(tagebucheintrag == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(tagebucheintrag.EintragID != 0 && TagebucheintragZuID(tagebucheintrag.EintragID)!=null) {
            return tagebucheintrag.EintragID;
        }
        //Alternativ müssen wir einen neuen Wirkstoff anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TAG_COLUMN_EINTRAGTAG, tagebucheintrag.EintragTag);
            values.put(TAG_COLUMN_ANLAGEDAT, tagebucheintrag.AnlageDat);
            values.put(TAG_COLUMN_USER, userAnlegen(tagebucheintrag.User));
            values.put(TAG_COLUMN_EINTRAG, tagebucheintrag.Eintrag);
            values.put(WIS_COLUMN_ISVALID,true);

            long neuerKey = db.insert(TAG_TABLE_NAME,null,values);
            return neuerKey;
        }
    }


    public boolean tagebucheintragaendern(Tagebucheintrag tagebucheintrag) {

        if ( tagebucheintrag == null) {

            return false;
        }

        String abfrage = " SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_COLUMN_EINTRAGID + " = " + tagebucheintrag.EintragID + ";";

        // Stellt Verbindung mit Datenbank her
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor mit Abfrage belegen
        Cursor cursor = db.rawQuery(abfrage,null);

        if (cursor.getCount() == 0 ) {

            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + TAG_TABLE_NAME + " SET " +
                TAG_COLUMN_EINTRAGTAG + " = " + tagebucheintrag.EintragTag + "," +
                TAG_COLUMN_EINTRAG + " = " + "'" + tagebucheintrag.Eintrag + "'" + "," +
                TAG_COLUMN_ANLAGEDAT + " = " + tagebucheintrag.AnlageDat +
                " WHERE " + TAG_COLUMN_EINTRAGID + " = " + tagebucheintrag.EintragID + ";" ;


        SQLiteDatabase db2 = this.getWritableDatabase();

        //Cursor mit Abfrage belegen
        db2.execSQL(abfrage2);

        return true;

    }


    public boolean tagebucheintragLoeschen(Tagebucheintrag tagebuch) {

        if (tagebuch == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_COLUMN_EINTRAGID + " = " + tagebuch.EintragID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + TAG_TABLE_NAME + " SET " + TAG_COLUMN_ISVALID + " = 0 " + " WHERE " +
                TAG_COLUMN_EINTRAGID + " = " + tagebuch.EintragID + ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }

    public Tagebucheintrag[] tagebucheintragSuchen (User user, long von, long bis){   //die "long" geben den Zeitraum an! :D

        //Prüfe ob User da, wenn nicht return null, Methode vorbei
        if (user == null) return null;

        //Abfrage zur Ausgabe der Einträge aus der DB
        String abfrage = " SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_COLUMN_USER + " = " + user.UserID
                + " AND " + TAG_COLUMN_ISVALID + " = 1 "
                + " AND " + TAG_COLUMN_EINTRAGTAG + " BETWEEN " + von
                + " AND " + bis
                + ";" ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        //Array aus Tagebucheinträgen, da mehrere Tagebucheinträge gefunden werden könnten
        Tagebucheintrag[] Tagebucheintraege = new Tagebucheintrag[cursor.getCount()];

        if (cursor.moveToFirst()){
            do{
                Tagebucheintrag temp = TagebucheintragZuID(cursor.getInt(0));
                Tagebucheintraege[cursor.getPosition()] = temp;
            }while (cursor.moveToNext());
        }

        cursor.close();

        return Tagebucheintraege;
    }
    // endregion Tagebucheintrag_Methoden


    // region Anordnung_Methoden

    public long anordungAnlegen(Anordnung anordnung){
        //wurde ein gültiger Wert übergeben?
        if(anordnung == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(anordnung.AnordnungID != 0 && AnordnungZuID(anordnung.AnordnungID)!=null) {
            return anordnung.AnordnungID;
        }
        //Alternativ müssen wir eine neue anordnung anlegen
        else{
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ANO_COLUMN_TYP,anordnung.Typ);
            values.put(ANO_COLUMN_STARTZEITP,anordnung.Startzeitp);
            values.put(ANO_COLUMN_ENDZEITP,anordnung.Endzeitp);
            values.put(ANO_COLUMN_USER,userAnlegen(anordnung.User));
            values.put(ANO_COLUMN_MEDIKAMENT,medikamentAnlegen(anordnung.Medikament));
            if(anordnung.Arzt!=null) values.put(ANO_COLUMN_ARZT,ArztAnlegen(anordnung.Arzt));
            values.put(ANO_COLUMN_MORVOR,anordnung.MorVor);
            values.put(ANO_COLUMN_MORZUR,anordnung.MorZur);
            values.put(ANO_COLUMN_MORNACH,anordnung.MorNach);
            values.put(ANO_COLUMN_MITVOR,anordnung.MitVor);
            values.put(ANO_COLUMN_MITZUR,anordnung.MitZur);
            values.put(ANO_COLUMN_MITNACH,anordnung.MitNach);
            values.put(ANO_COLUMN_ABVOR,anordnung.AbVor);
            values.put(ANO_COLUMN_ABZUR,anordnung.AbZur);
            values.put(ANO_COLUMN_ABNACH,anordnung.AbNach);
            values.put(ANO_COLUMN_NACHT,anordnung.Nacht);
            values.put(ANO_COLUMN_NICHTEXAKT,anordnung.NichtExakt);
            values.put(ANO_COLUMN_EINNMAX,anordnung.EinnMax);
            values.put(ANO_COLUMN_ISVALID,true);

            long neuerKey = db.insert(ANO_TABLE_NAME,null,values);

            //jetzt müssen wir noch die Verbindung zu Rhythmus machen

            if(anordnung.Rhytmen!=null && anordnung.Rhytmen.length>0){
                for(Rhythmus rhythmus:anordnung.Rhytmen){
                    ContentValues rhythmusValues = new ContentValues();
                    rhythmusValues.put(HRH_COLUMN_ANORDNUNGID,neuerKey);
                    rhythmusValues.put(HRH_COLUMN_RHYTHMID,rhythmusAnlegen(rhythmus));
                    db.insert(HRH_TABLE_NAME,null,rhythmusValues);
                }

            }
            return neuerKey;
        }
    }


    public Anordnung[] relevateAnordnungen(long von, long bis){
        //Was ich eigentlich will ist:
        //SELECT * FROM Anordungen WHERE (Anordnung.Startzeitp >= von OR Anordnung.Startzeitp is null) AND (Anordnung.Endzeitp <= von OR Anordnung.Endzeitp is null)

        String abfrage=     "SELECT * FROM "+ANO_TABLE_NAME +" WHERE "+
                "("+ANO_COLUMN_STARTZEITP+" >= "+von+" OR "+ANO_COLUMN_STARTZEITP+" is null) AND "+
                "("+ANO_COLUMN_ENDZEITP+" <= "+bis+" OR "+ANO_COLUMN_ENDZEITP+" is null);";

        //Als nächstes brauchen wie eine Datenbank, von der wir lesen können
        SQLiteDatabase db = this.getReadableDatabase();

        //Und dann feuern wir die Abfrage von oben drüer und erstellen einen Cursor, also eine Tabelle, die die Ergebnisse der Abfrage enthält
        Cursor erg = db.rawQuery(abfrage,null);

        //da können jetzt zwischen 0 (inklusiv) und undendlich viele (exklusiv) viele Egerbnisse rauskommen, deswegen schreiben wir den Cursor in ein Array um
        //Ein Array muss eine feste Größe haben aber die kennen wir ja schon, es sind die Anzahl der Zeilen des Cursors.

        Anordnung[] ergebnis = new Anordnung[erg.getCount()];

        //und jetzt gehen wir den cursor durch und füllen Schritt für Schritt das Array. So 'übersetzen' wir SQL in Java
        //die gute Nachticht ist, wir haben schon eine Methode um aus einer ID ein Anordnungs-Objekt zu erhalten, das rufen wir jetzt auf:

        if(erg.moveToFirst()){  //geht in die erste Zeile, wenn es eine gibt, sonst ist es schon wieder vorbei
            do{
                Anordnung temp = AnordnungZuID(erg.getInt(0));
                ergebnis[erg.getPosition()] = temp;
            }
            while(erg.moveToNext());
        }

        //ganz wichtig, den cursor immer auch zu machen :)
        erg.close();

        return ergebnis;
    }

    public boolean anordnungLoeschen(Anordnung anordnung) {

        if (anordnung == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + ANO_TABLE_NAME + " WHERE " + ANO_COLUMN_ANORDNUNGSID + " = " + anordnung.AnordnungID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + ANO_TABLE_NAME + " SET " + ANO_COLUMN_ISVALID + " = 0 " + " WHERE " +
                ANO_COLUMN_ANORDNUNGSID + " = " + anordnung.AnordnungID+ ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }

    public Anordnung[] bedarfsAnordnungen(){

        String abfrage=     "SELECT * FROM "+ANO_TABLE_NAME +" WHERE "
                +ANO_COLUMN_TYP+" = 0 AND "+
                ANO_COLUMN_ISVALID+" = 1;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor erg = db.rawQuery(abfrage,null);

        Anordnung[] ergebnis = new Anordnung[erg.getCount()];

        if(erg.moveToFirst()){
            do{
                Anordnung temp = AnordnungZuID(erg.getInt(0));
                ergebnis[erg.getPosition()] = temp;
            }
            while(erg.moveToNext());
        }

        erg.close();

        return ergebnis;
    }
    // endregion Anordnung_Methoden

    // region Rhythmus_Methoden

    public  long rhythmusAnlegen(Rhythmus rhythmus){
        //wurde ein gültiger Wert übergeben?
        if(rhythmus == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(rhythmus.RhythmID != 0 && RhythmusZuID(rhythmus.RhythmID)!=null) {
            return rhythmus.RhythmID;
        }
        //Alternativ müssen wir einen neuen Rhythmus anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(RHY_COLUMN_ALLXTAGE,rhythmus.AlleXTage);
            values.put(RHY_COLUMN_WOCHENTAGE,rhythmus.Wochentage);
            values.put(RHY_COLUMN_SCHEMAAKTIV,rhythmus.SchemaAktiv);
            values.put(RHY_COLUMN_SCHEMAINAKTIV,rhythmus.SchemaInaktiv);
            values.put(RHY_COLUMN_ISVALID,true);

            long neuerKey = db.insert(RHY_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    public boolean rhythmusloeschen(Rhythmus rhythmus) {

        if (rhythmus == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + RHY_TABLE_NAME + " WHERE " + RHY_COLUMN_RHYTHMID + " = " + rhythmus.RhythmID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + RHY_TABLE_NAME + " SET " + RHY_COLUMN_ISVALID + " = 0 " + " WHERE " +
                RHY_COLUMN_RHYTHMID + " = " + rhythmus.RhythmID+ ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }


    // endregion Rhythmus_Methoden


    // region EinahmeDoku_Methoden

    public long einnahmeDokuAnlegen(EinnahmeDoku einnahmeDoku){
        //wurde ein gültiger Wert übergeben?
        if(einnahmeDoku == null) return -1;

        //Gibt es diesen Eintrag schon in der Datenbank?
        if(einnahmeDoku.DokuID != 0 && EinnahmeDokuZuID(einnahmeDoku.DokuID)!=null) {
            return einnahmeDoku.DokuID;
        }
        //Alternativ müssen wir einen neuen Rhythmus anlegen
        else {
            // Stellt Verbindung mit Datenbank her
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EDO_COLUMN_ZEITPEINNSOLL,einnahmeDoku.ZeitpEinnSoll);
            values.put(EDO_COLUMN_ZEITEINNIST,einnahmeDoku.ZeitpEinnIst);
            values.put(EDO_COLUMN_ZEITEINTRAG,einnahmeDoku.ZeitpEintrag);
            values.put(EDO_COLUMN_MENGE,einnahmeDoku.Menge);
            if(einnahmeDoku.Einheit!=null) values.put(EDO_COLUMN_EINHEIT,EinheitAnlegen(einnahmeDoku.Einheit));
            if(einnahmeDoku.Anordnung!=null) values.put(EDO_COLUMN_ANORDNUNG,anordungAnlegen(einnahmeDoku.Anordnung));
            values.put(EDO_COLUMN_GENOMMEN,einnahmeDoku.Genommen);
            values.put(EDO_COLUMN_BEMERKUNG,einnahmeDoku.Bemerkung);
            values.put(EDO_COLUMN_KOMMENTAR,einnahmeDoku.Kommentar);
            values.put(EDO_COLUMN_ISVALID,true);

            long neuerKey = db.insert(EDO_TABLE_NAME,null,values);
            return neuerKey;
        }
    }

    public EinnahmeDoku[] einnahmeDokusFuerZeitraum(Anordnung anordnung, long von, long bis){

        //Prüfen, ob Anordnungen vorhanden sind, wenn nicht return null, Methode vorbei
        if(anordnung == null) return null;

        //Abfrage zur Ausgabe der Einträge aus der DB
        String abfrage = " SELECT * FROM " + EDO_TABLE_NAME + " WHERE " + EDO_COLUMN_ANORDNUNG + " = " + anordnung.AnordnungID
                + " AND " + EDO_COLUMN_ISVALID + " = 1"
                + " AND " + EDO_COLUMN_ZEITEINTRAG + " >= " + von
                + " AND " + EDO_COLUMN_ZEITEINTRAG + " <= " + bis + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        //Array aus Einträgen aus der EinnahmeDoku, da es mehrere Einträge geben kann
        EinnahmeDoku[] dokuEintraege = new EinnahmeDoku[cursor.getCount()]; //Array so lang, wie viele Einträge der Cursor rausgibt

        if(cursor.moveToFirst()){  //es wird beim ersten eintrag angefangen, wenn es einträge gibt & sie werden angezeigt
            do{
                EinnahmeDoku temp = EinnahmeDokuZuID(cursor.getInt(0));
                dokuEintraege[cursor.getPosition()] = temp;
            }
            while(cursor.moveToNext());
        }

        cursor.close();

        //Doku Einträge werden rausgegeben
        return dokuEintraege;
    }

    public EinnahmeDoku[] AlleDokus(){

        //Abfrage zur Ausgabe der Einträge aus der DB
        String abfrage = " SELECT * FROM " + EDO_TABLE_NAME + " WHERE " + EDO_COLUMN_ISVALID + " =1 "
                    + "ORDER BY "+EDO_COLUMN_ZEITPEINNSOLL+" ASC;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        //Array aus Einträgen aus der EinnahmeDoku, da es mehrere Einträge geben kann
        EinnahmeDoku[] dokuEintraege = new EinnahmeDoku[cursor.getCount()]; //Array so lang, wie viele Einträge der Cursor rausgibt

        if(cursor.moveToFirst()){  //es wird beim ersten eintrag angefangen, wenn es einträge gibt & sie werden angezeigt
            do{
                EinnahmeDoku temp = EinnahmeDokuZuID(cursor.getInt(0));
                dokuEintraege[cursor.getPosition()] = temp;
            }
            while(cursor.moveToNext());
        }

        cursor.close();

        //Doku Einträge werden rausgegeben
        return dokuEintraege;

    }


    public boolean einnahmeDokuloeschen(EinnahmeDoku einnahmeDoku) {

        if (einnahmeDoku == null) {
            return false;
        }

        String abfrage = " SELECT * FROM " + EDO_TABLE_NAME + " WHERE " + EDO_COLUMN_DOKUID + " = " + einnahmeDoku.DokuID + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + EDO_TABLE_NAME + " SET " + EDO_COLUMN_ISVALID + " = 0 " + " WHERE " +
                EDO_COLUMN_DOKUID + " = " + einnahmeDoku.DokuID+ ";";

        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL(abfrage2);

        return true;
    }

    public boolean einnahmeDokuAendern(EinnahmeDoku einnahmeDoku){

        if (einnahmeDoku == null) {

            return false;
        }

        String abfrage = " SELECT * FROM " + EDO_TABLE_NAME + " WHERE " + EDO_COLUMN_DOKUID + " = " + einnahmeDoku.DokuID + ";";

        // Stellt Verbindung mit Datenbank her
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor mit Abfrage belegen
        Cursor cursor = db.rawQuery(abfrage,null);

        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }

        cursor.close();

        String abfrage2 = " UPDATE " + EDO_TABLE_NAME + " SET " +
                EDO_COLUMN_ZEITEINNIST + " = " +  einnahmeDoku.ZeitpEinnIst +  "," +
                EDO_COLUMN_GENOMMEN + " = " +  einnahmeDoku.Genommen + "," +
                EDO_COLUMN_BEMERKUNG + " = " + "'"+einnahmeDoku.Bemerkung + "'"+
                " WHERE " + EDO_COLUMN_DOKUID + " = " + einnahmeDoku.DokuID + ";";

        SQLiteDatabase db2 = this.getWritableDatabase();

        //Cursor mit Abfrage belegen
        db2.execSQL(abfrage2);


        return true;

    }
    // endregion EinnahmeDoku_Methoden




    // region Debug-Methoden
    //ACHTUNG, diese Methoden können erheblichen Schaden an der Datenbank verursachen, nur nutzen, wenn ihr denkt zu wissen, was ihr tut.

    //Diese Methode löscht die gesamte Datenbank und ersetzt sie durch leere Tabellen
    //Deswegen: Obacht!
    public void nuke(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+MDB_TABLE_NAME);
        db.execSQL("DROP TABLE "+ENT_TABLE_NAME);
        db.execSQL("DROP TABLE "+EIH_TABLE_NAME);
        db.execSQL("DROP TABLE "+EIN_TABLE_NAME);
        db.execSQL("DROP TABLE "+WIS_TABLE_NAME);
        db.execSQL("DROP TABLE "+WIF_TABLE_NAME);
        db.execSQL("DROP TABLE "+WIB_TABLE_NAME);
        db.execSQL("DROP TABLE "+DAF_TABLE_NAME);
        db.execSQL("DROP TABLE "+ANO_TABLE_NAME);
        db.execSQL("DROP TABLE "+RHY_TABLE_NAME);
        db.execSQL("DROP TABLE "+HRH_TABLE_NAME);
        db.execSQL("DROP TABLE "+ARZ_TABLE_NAME);
        db.execSQL("DROP TABLE "+FAC_TABLE_NAME);
        db.execSQL("DROP TABLE "+EDO_TABLE_NAME);
        db.execSQL("DROP TABLE "+USR_TABLE_NAME);
        db.execSQL("DROP TABLE "+TAG_TABLE_NAME);
        onCreate(db);
    }

    //Löscht die Datenbank und ersetzt sie durch den Urzustand
    public void debug_Basis(){
        nuke(); //alles weg

        SQLiteDatabase db = getWritableDatabase();

        //Jeweils den INSERT-String mit Stammdaten
        String abfrageDarreichungsform = "INSERT INTO "+DAF_TABLE_NAME+" ("+DAF_COLUMN_DARRID+","+DAF_COLUMN_BEZEICHNUNG+" ,"+DAF_COLUMN_ISVALID+") VALUES\n" +
                "(1, 'Brausetablette',1),\n" +
                "(2, 'Injektion',1),\n" +
                "(3, 'Filmtabletten',1),\n" +
                "(4, 'Kapseln',1),\n" +
                "(5, 'Retardtabletten',1),\n" +
                "(6, 'Zylinderampullen',1),\n" +
                "(7, 'Tabletten',1),\n" +
                "(8, 'Filterbeutel',1),\n" +
                "(9, 'Pflaster',1);";

        String abfrageEinheit = "INSERT INTO `Einheit` (`EinID`, `Einheit`,`isValid`) VALUES\n" +
                "(1, 'mg',1),\n" +
                "(2, 'mg/ml',1),\n" +
                "(3, 'g',1),\n" +
                "(4, 'ml',1),\n" +
                "(5, 'μg/h',1);";

        String abfrageEinnEinheit = "INSERT INTO `EinnEinheit` (`EinnEinheitID`, `Einheit`,`isValid`) VALUES\n" +
                "(1, 'Stück',1),\n" +
                "(2, 'mg',1),\n" +
                "(3, 'IE',1);";

        String abfrageEnthalet = "INSERT INTO `enthaelt` (`MedID`, `WirkstoffID`) VALUES\n" +
                "(1, 1),\n" +
                "(1, 2),\n" +
                "(3, 3),\n" +
                "(3, 4),\n" +
                "(4, 5),\n" +
                "(5, 6),\n" +
                "(5, 7),\n" +
                "(6, 8),\n" +
                "(6, 9),\n" +
                "(6, 10),\n" +
                "(8, 11),\n" +
                "(9, 12),\n" +
                "(10, 13),\n" +
                "(2, 14);";

        String abfrageMedis = "INSERT INTO `MedBib` (`MedID`, `PZN`, `zuletztBearb`, `Handelsname`, `Staerk_Dosis`, `Einheit`, `DarreichungsForm`, `Nebenwirkung`, `EinnHinweis`, `PackGroesse`, `EinnEinheit`,`isValid`) VALUES\n" +
                "(1, 10836596, 1683319198, 'Aspirin 800mg', 800, 1, 1, ' Nebenwirkungen', ' Dosierung', 10, 1,1),\n" +
                "(2, 574994, 1683283585, 'Durogesic SMAT 75 µg/h', 75, 5, 9, ' Nebenwirkungen', ' Dosierung', 10, 1,1),\n" +
                "(3, 15427276, 1683322837, 'HYDROCORTISON Pfizer 100 mg', 50, 2, 2, ' Nebenwirkungen', ' Dosierung', 100, 2,1),\n" +
                "(4, 1798000, 1683323589, 'NOVAMINSULFON Lichtenst.500 mg', 500, 1, 3, 'Nebenwirkungen', 'Dosierung', 30, 1,1),\n" +
                "(5, 172333, 1683238148, 'Biolectra Magnesium 300 mg', 300, 1, 4, NULL, 'Verzehrsempfehlung:', 100, 1,1),\n" +
                "(6, 230272, 1683239134, 'METOPROLOLSUCCINAT-1A Phar.95', 95, 1, 5, 'Nebenwirkungen', '    Nehmen Sie dieses Arzneimittel ', 50, 1,1),\n" +
                "(7, 2260538,  1683240335, 'Multivitamin pure', 117, 3, 4, NULL, '3 Kapseln pro Tag vor den Mahlzeiten.', 120, 1,1),\n" +
                "(8, 5458347, 1683240940, 'NOVORAPID Penfill 100 E/ml', 3, 4, 6, 'Nebenwirkungen', '    Wenden Sie Ihr Insulin immer genau ', 10, 1,1),\n" +
                "(9, 2223945, 1683241643, 'Ramipril-ratiopharm 5 mg', 5, 1, 7, 'Nebenwirkungen', '    Dosis des Arzneimittels', 100, 1,1),\n" +
                "(10, 12582131, 1683242277, 'BAD Heilbrunner Salbei Tee', 1.6, 3, 8, NULL, NULL, 8, 1,1);";

        String abfrageWirkstoff = "INSERT INTO `Wirkstoff` (`WirkstoffID`, `Bezeichnung`,`isValid`) VALUES\n" +
                "(1, 'Acetylsalicylsäure',1),\n" +
                "(2, 'Ascorbinsäure',1),\n" +
                "(3, 'Hydrocortison-21-hydrogensuccinat',1),\n" +
                "(4, 'Natriumsalz',1),\n" +
                "(5, 'Metamizol-Natrium-Monohydrat',1),\n" +
                "(6, 'Magnesiumoxid',1),\n" +
                "(7, 'Hydroxypropylmethylcellulose ',1),\n" +
                "(8, 'Metoprolol succinat',1),\n" +
                "(9, 'Metoprolol',1),\n" +
                "(10, 'Metoprolol tartrat',1),\n" +
                "(11, 'Insulin aspart',1),\n" +
                "(12, 'Ramipril',1),\n" +
                "(13, 'Salbeiblätter',1),\n" +
                "(14, 'Fentanyl',1);";

        //Jeweils Stammdaten draufbügeln
        db.execSQL(abfrageDarreichungsform);
        db.execSQL(abfrageEinheit);
        db.execSQL(abfrageEinnEinheit);
        db.execSQL(abfrageEnthalet);
        db.execSQL(abfrageMedis);
        db.execSQL(abfrageWirkstoff);
    }

    // endregion Debug-Methoden

    // region Bericht Queries


    public EinnahmeDoku[] EinnahmeDokuGibtBericht (long von, long bis) {

        //Abfrage zur Ausgabe der Einträge aus der DB
        String abfrage = " SELECT * FROM " + EDO_TABLE_NAME
                + " WHERE " + EDO_COLUMN_ISVALID + " = 1"
                + " AND " + EDO_COLUMN_ZEITPEINNSOLL + " >= " + von
                + " AND " + EDO_COLUMN_ZEITPEINNSOLL + " <= " + bis + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(abfrage, null);

        //Array aus Einträgen aus der EinnahmeDoku, da es mehrere Einträge geben kann
        EinnahmeDoku[] dokuEintraege = new EinnahmeDoku[cursor.getCount()]; //Array so lang, wie viele Einträge der Cursor rausgibt

        if(cursor.moveToFirst()){  //es wird beim ersten eintrag angefangen, wenn es einträge gibt & sie werden angezeigt
            do{
                EinnahmeDoku temp = EinnahmeDokuZuID(cursor.getInt(0));
                dokuEintraege[cursor.getPosition()] = temp;
            }
            while(cursor.moveToNext());
        }

        cursor.close();

        //Doku Einträge werden rausgegeben
        return dokuEintraege;
    }

    //endregion Bericht Queries
}
