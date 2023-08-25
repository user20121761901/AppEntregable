package com.bastian.androidjavaentregable;

import android.os.Parcel;
import android.os.Parcelable;

public class Empresa implements Parcelable{
    private int id;
    private String razonSocial;
    private String ruc;

    public Empresa(int id, String razonSocial, String ruc) {
        this.id = id;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    protected Empresa(Parcel in) {
        id = in.readInt();
        razonSocial = in.readString();
        ruc = in.readString();
    }
    public static final Parcelable.Creator<Empresa> CREATOR = new Creator<Empresa>() {
        @Override
        public Empresa createFromParcel(Parcel in) {
            return new Empresa(in);
        }

        @Override
        public Empresa[] newArray(int size) {
            return new Empresa[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(razonSocial);
        dest.writeString(ruc);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
