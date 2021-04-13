package model;

public class Monomial {
    private float coeficient;
    private int exponent;
	public Monomial(float coeficient, int exponent) {
		super();
		this.coeficient = coeficient;
		this.exponent = exponent;
	}
	public int getExponent() {
		return exponent;
	}
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	public float getCoeficient() {
		return coeficient;
	}
	public void setCoeficient(float coeficient) {
		this.coeficient = coeficient;
	}
    public String toString()
    {
    	String s="";
    	if(coeficient==0)
    	{
    		s+="+0";
    		return s;
    	}
    	if(coeficient>0)
    		s+="+";
    	
    	s+=coeficient;
    	if(exponent!=0)
    	{
    	if(exponent<0)
    		s+="x"+"^"+"("+exponent+")";
    	else 
    		s+="x"+"^"+exponent;
    	}
    	
    	return s;
    }
}
