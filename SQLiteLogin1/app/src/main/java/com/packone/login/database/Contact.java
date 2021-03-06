package com.packone.login.database;

/**
 * @author: Kanyilidz Muhammedmehdi, SECKIN Berkay
 * @version: 0.9.1
 * @date: 01.10.2014
 * Beschreibung
 */
public class Contact {
    // private variables
    private String _uname;
    private String _pword;
    private String _email;
    private String _gender;
    private int _height;
    private int _weight;

    // fragenkatalog
    private String _ziel;
    private String _akt;
    private String _erfahrung;
    private String _quant;
    private String _ktyp;

    // Empty constructor
    public Contact() {
    }


    // constructor
    public Contact(
            String uname,
            String pword,
            String email,
            String gender,
            int weight,
            int height,
            String ziel,
            String akt,
            String erfahrung,
            String quant,
            String ktyp) {
        this._uname = uname;
        this._pword = pword;
        this._email = email;
        this._gender = gender;
        this._weight = weight;
        this._height = height;
        this._ziel = ziel;
        this._akt = akt;
        this._erfahrung = erfahrung;
        this._quant = quant;
        this._ktyp = ktyp;
    }

    // constructor
    public Contact(String pword, String email) {
        this._pword = pword;
        this._email = email;
    }

    /**
     * Konstrukto für Fragenkatalog query (Db-Handler getFragenkatalog)
     * @param ziel
     * @param akt
     * @param erfahrung
     * @param quant
     */
    public Contact(String ziel, String akt, String erfahrung, String quant) {
        this._ziel = ziel;
        this._akt = akt;
        this._erfahrung = erfahrung;
        this._quant = quant;
    }

    // Setter getter for Username
    public String getUname() {
        return this._uname;
    }

    public void setUname(String uname) {
        this._uname = uname;
    }

    //Setter Getter for Password
    public String getPword() {
        return this._pword;
    }

    public void setPword(String pword) {
        this._pword = pword;
    }

    // Setter Getter for Email
    public String getEmail() {
        return this._email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    // Setter Getter for Gender
    public String getGender() {
        return this._gender;
    }

    public void setGender(String gender) {
        this._gender = gender;
    }

    //Setter Getter for Height
    public int getHeight() {
        return this._height;
    }

    public void setHeight(int height) {
        this._height = height;
    }

    //Setter Getter for Weight
    public int getWeight() {
        return this._weight;
    }

    public void setWeight(int weight) {
        this._weight = weight;
    }

    public String getZiel() {
        return _ziel;
    }

    // FRAGENKATALOG GETTER SETTER
    public void setZiel(String ziel) {
        this._ziel = ziel;
    }

    public String getAkt() {
        return _akt;
    }

    public void setAkt(String akt) {
        this._akt = _akt;
    }

    public void setErfahrung(String erfahrung) {
        this._erfahrung = erfahrung;
    }
    public String getErfahrung() {
        return _erfahrung;
    }

    public void setQuant(String quant) {
        this._quant = quant;
    }

    public String getQuant() {
        return _quant;
    }

    public String get_ktyp() {
        return _ktyp;
    }

    public void set_ktyp(String _ktyp) {
        this._ktyp = _ktyp;
    }

}