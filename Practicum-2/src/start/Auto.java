package start;

import java.io.Serializable;

public class Auto implements Serializable, AutoInterface{
	public static Auto createAuto(String mk, String kt, int id) {
		return new Auto(mk, kt, id);
	}

	private String kenteken;
    private int ID;
	private String merk;
	private int klantNummer;
	
	private Auto(String mk, String kt, int id){
        ID = id;
		kenteken = kt;
		merk = mk;
		klantNummer = 0;
	}
	
	/* (non-Javadoc)
	 * @see start.AutoInterface#getID()
	 */
	@Override
	public int getID(){
		return ID;
	}
	
	/* (non-Javadoc)
	 * @see start.AutoInterface#getMerk()
	 */
	@Override
	public String getMerk(){
		return merk;
	}
	
	/* (non-Javadoc)
	 * @see start.AutoInterface#setMerk(java.lang.String)
	 */
	@Override
	public void setMerk(String mk){
		merk = mk;
	}
	
	/* (non-Javadoc)
	 * @see start.AutoInterface#getKenteken()
	 */
	@Override
	public String getKenteken(){
		return kenteken;
	}
	
	/* (non-Javadoc)
	 * @see start.AutoInterface#setKenteken(java.lang.String)
	 */
	@Override
	public void setKenteken(String kt){
		kenteken = kt;
	}

	/* (non-Javadoc)
	 * @see start.AutoInterface#getKlantNummer()
	 */
	@Override
	public int getKlantNummer() {
		return klantNummer;
	}

	/* (non-Javadoc)
	 * @see start.AutoInterface#setKlantNummer(int)
	 */
	@Override
	public void setKlantNummer(int kNr) {
		this.klantNummer = kNr;
	}
}
