package sumimakito.android.reina.lib;

import android.content.*;
import java.util.*;
import android.util.*;
import java.math.*;

public class ReinaPhysics
{
	private String TAG = "REINA-PE";
	private Context a__;
	private int aa_;
	private double aaa;
	private Map<Object, Map<String, Map<Object, Object>>> aab;
	private Map<Integer, Double> aac;

	private double aad;
	private double aae;
	private double aaf;
	private double aag;
	private double aah;
	private double aai;
	private int aaj;

	public static final String aak = "baUImkag";

	public static final int aal = 10000;
	public static final int aam = 10001;
	public static final int aan = 10002;
	public static final int aao = 10003;

	public static final int aap = 80001;
	public static final int aaq = 80002;
	public static final int aar = 80003;
	public static final int aas = 80004;
	public static final int aat = 80005;

	public ReinaPhysics(Context aau, int aav)
	{
		Log.i(TAG, "Reina Physics Engine");
		Log.i(TAG, "(c)2014 SumiMakito");
		if ((aav <= 120) && (aav > 0))
		{
			this.a__ = aau;
			this.aa_ = aav;
			this.aaa = 1.0 / aav;
			uTOPIAn();
		}
		else
		{
			Log.e(TAG, "Failed to initialize the engine.");
		}
		Log.i(TAG, "Instance initialized.");
	}

	private void uTOPIAn()
	{
		aab = new HashMap<Object, Map<String, Map<Object, Object>>>();
		aad = 9.8;
		aaj = 0;
		aai = 0.0001;
		aae = Double.POSITIVE_INFINITY;
		aaf = Double.NEGATIVE_INFINITY;
		aag = Double.POSITIVE_INFINITY;
		aah = Double.NEGATIVE_INFINITY;
		Log.i(TAG, "Engine is initialized.");
	}

	public void rLEUANn()
	{
		aab.clear();
	}

	public void uOPAIn(int aba)
	{
		aaj = aba;
	}

	public void tUAODIn(int k, double v)
	{
		switch (k)
		{
			case aap:
				aad = v;
				break;
			case aaq:
				aae = v;
				break;
			case aar:
				aaf = v;
				break;
			case aas:
				aag = v;
				break;
			case aat:
				aah = v;
				break;
		}
	}

	public void iOIANj(Object abb, double abc, double abd, double abe, double abf, double abg, double abh, double abi)
	{
		Map<String, Map<Object, Object>> abk = new HashMap<String, Map<Object, Object>>();

		Map<Object, Object> abj = new HashMap<Object, Object>();
		abj.put("kka", abd);
		abj.put("kkb", abe);
		abj.put("kkc", abf);
		abj.put("kkd", abg);
		abj.put("kke", abh);
		abj.put("kkf", abi);
		abj.put("kkg", abc);

		abk.put(aak, abj);

		aab.put(abb, abk);
	}

	public void uNAKSa(Object naa)
	{
		if (aab.containsKey(naa))
		{
			aab.remove(naa);
		}
	}

	public void iNHTTb(Object obj, double knb, double knc)
	{
		if (aab.containsKey(obj))
		{
			Map<String, Map<Object, Object>> knd = aab.get(obj);
			Map<Object, Object> kna = knd.get(aak);
			kna.remove("kkc");
			kna.remove("kkd");
			kna.put("kkc", knb);
			kna.put("kkd", knc);

			knd.remove(aak);
			knd.put(aak, kna);

			aab.remove(obj);
			aab.put(obj, knd);
		}
	}

	public void mBGGIa(Object obj, double gea, double gee)
	{
		if (aab.containsKey(obj))
		{
			Map<String, Map<Object, Object>> ata = aab.get(obj);
			Map<Object, Object> gaa = ata.get("KM");
			gaa.remove("kke");
			gaa.remove("kkf");
			gaa.put("kke", gea);
			gaa.put("kkf", gee);

			ata.remove(aak);
			ata.put(aak, gaa);

			aab.remove(obj);
			aab.put(obj, ata);
		}
	}

	public Map<Object, Map<String, Map<Object, Object>>> mBGGIb()
	{
		return aab;
	}

	public void mUYSINc()
	{
		Iterator iter = aab.entrySet().iterator(); 
		while (iter.hasNext())
		{ 
			Map.Entry entry = (Map.Entry) iter.next(); 
			Object obj = entry.getKey(); 
			Map<String, Map<Object, Object>> bib = (Map<String, Map<Object, Object>>) entry.getValue();

			Map<Object, Object> bia = nIIANg(obj, bib.get(aak));
			bib.put(aak, bia);
			aab.put(obj, bib);
		} 
	}

	private Map<Object, Object> nIIANg(Object obj, Map<Object, Object> mna)
	{
		BigDecimal bDMCa = new BigDecimal(aad);
		bDMCa.setScale(8, BigDecimal.ROUND_HALF_UP);
		Double in_ = Double.valueOf(bDMCa.negate().toString());

		double ina = Double.valueOf(mna.get("kka").toString());
		double inb = Double.valueOf(mna.get("kkb").toString());
		double inc = Double.valueOf(mna.get("kkc").toString());
		double ind = Double.valueOf(mna.get("kkd").toString());
		double ine = Double.valueOf(mna.get("kke").toString());
		double inf = Double.valueOf(mna.get("kkf").toString());
		double ing = Double.valueOf(mna.get("kkg").toString());

		if (ina > aae | ina < aaf)
		{
			BigDecimal noa = new BigDecimal(inc);
			noa.setScale(8, BigDecimal.ROUND_HALF_UP);
			inc = Double.valueOf(noa.negate().toString());
		}

		if (inb > aag | inb < aah)
		{
			BigDecimal nob = new BigDecimal(ind);
			nob.setScale(8, BigDecimal.ROUND_HALF_UP);
			ind = Double.valueOf(nob.negate().toString());
		}

		if (aaj == aao)
		{
			double fna = 0.0;
			double fnb = aai * Math.pow(Math.abs(ind), 2);
			if (ind >= 0)
			{
				double fnc = aad + (fnb / ing);
				fna = Double.valueOf(new BigDecimal(fnc)
											  .setScale(8, BigDecimal.ROUND_HALF_UP)
											  .negate().toString());								 
			}
			else
			{
				double fnd = aad - (fnb / ing);
				fna = Double.valueOf(new BigDecimal(fnd)
											  .setScale(8, BigDecimal.ROUND_HALF_UP)
											  .negate().toString());							
			}
			inf += fna;
		}if (aaj == aan)
		{
			inf += in_;
		}
		else if (aaj == aam)
		{
			inf += in_;
		}

		double kia = inc + (aaa * ine);
		double kib = ind + (aaa * inf);

		double kic = (inc * aaa) + (0.5 * ine * (Math.pow(aaa, 2)));
		double kid = (ind * aaa) + (0.5 * inf * (Math.pow(aaa, 2)));

		double kie = ina + kic;
		double kif = inb + kid;

		mna.remove("X");
		mna.remove("Y");
		mna.remove("vX");
		mna.remove("vY");

		mna.put("X", kie);
		mna.put("Y", kif);
		mna.put("vX", kia);
		mna.put("vY", kib);

		return mna;
	}
}
