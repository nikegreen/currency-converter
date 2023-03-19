package com.practicum.currencyconverter.data.models;

import com.google.gson.annotations.SerializedName;

public class Valute{

	@SerializedName("CHF")
	private CHF cHF;

	@SerializedName("ZAR")
	private ZAR zAR;

	@SerializedName("CNY")
	private CNY cNY;

	@SerializedName("AUD")
	private AUD aUD;

	@SerializedName("JPY")
	private JPY jPY;

	@SerializedName("PLN")
	private PLN pLN;

	@SerializedName("GBP")
	private GBP gBP;

	@SerializedName("TRY")
	private TRY tRY;

	@SerializedName("HKD")
	private HKD hKD;

	@SerializedName("EUR")
	private EUR eUR;

	@SerializedName("DKK")
	private DKK dKK;

	@SerializedName("USD")
	private USD uSD;

	@SerializedName("CAD")
	private CAD cAD;

	@SerializedName("NOK")
	private NOK nOK;

	@SerializedName("SGD")
	private SGD sGD;

	@SerializedName("CZK")
	private CZK cZK;

	@SerializedName("SEK")
	private SEK sEK;

	public CHF CHF(){
		return cHF;
	}

	public ZAR ZAR(){
		return zAR;
	}

	public CNY CNY(){
		return cNY;
	}

	public AUD AUD(){
		return aUD;
	}

	public JPY JPY(){
		return jPY;
	}

	public PLN PLN(){
		return pLN;
	}

	public GBP GBP(){
		return gBP;
	}

	public TRY TRY(){
		return tRY;
	}

	public HKD HKD(){
		return hKD;
	}

	public EUR EUR(){
		return eUR;
	}

	public DKK DKK(){
		return dKK;
	}

	public USD USD(){
		return uSD;
	}

	public CAD CAD(){
		return cAD;
	}

	public NOK NOK(){
		return nOK;
	}

	public SGD SGD(){
		return sGD;
	}

	public CZK CZK(){
		return cZK;
	}

	public SEK SEK(){
		return sEK;
	}
}