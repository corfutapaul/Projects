package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynomial  {
	List<Monomial> Polinom = new ArrayList<Monomial>();

	public Polynomial(List<Monomial> polinom) {
		super();
		Polinom = polinom;
	}

	public List<Monomial> getPolinom() {
		return Polinom;
	}

	public void setPolinom(List<Monomial> polinom) {
		Polinom = polinom;
	} 
	public void afisarePolinom() {
		
		for (Monomial e : Polinom) 
		{ System.out.print(e); 
		
		}
		System.out.print("\n");
	}
public Polynomial adunarePolinomi(Polynomial p1,Polynomial p2) {
		int exp1,exp2,ok=0;
		List<Monomial> l1=new ArrayList<Monomial>();
		for (Monomial e : p1.Polinom) { 	
		ok=0;
		exp1=e.getExponent();
		  for(Monomial e2: p2.Polinom){
			  exp2=e2.getExponent();
			  if(exp1==exp2)
			  {	  
			  l1.add(new Monomial(e.getCoeficient()+e2.getCoeficient(),exp1));
			  ok=1;
			  }	    
		  }
		  if(ok==0)
			  l1.add(new Monomial(e.getCoeficient(),exp1)); 
		}
		  for(Monomial e: p2.Polinom) {
			  ok=0;
			  for(Monomial e2: l1){
				 if(e.getExponent()==e2.getExponent()) 
					 ok=1;
			  }
			  if(ok==0)
				  l1.add(new Monomial(e.getCoeficient(),e.getExponent())); 
		  } 
		Collections.sort(l1, new CompareByExp());
		return(new Polynomial(l1));	
	}
public Polynomial scaderePolinomi(Polynomial p1,Polynomial p2) {
	int exp1,exp2,ok=0;
	List<Monomial> l1=new ArrayList<Monomial>();
	for (Monomial e : p1.Polinom) { 	
	ok=0;
	exp1=e.getExponent();
	  for(Monomial e2: p2.Polinom){
		  exp2=e2.getExponent();
		  if(exp1==exp2)
		  {	  
		  l1.add(new Monomial(e.getCoeficient()-e2.getCoeficient(),exp1));
		  ok=1;
		  }	    
	  }
	  if(ok==0)
		  l1.add(new Monomial(e.getCoeficient(),exp1)); 
	}
	  for(Monomial e: p2.Polinom) {
		  ok=0;
		  for(Monomial e2: l1){
			 if(e.getExponent()==e2.getExponent()) 
				 ok=1;
		  }
		  if(ok==0)
			  l1.add(new Monomial(-e.getCoeficient(),e.getExponent())); 
	  } 
	Collections.sort(l1, new CompareByExp());
	
	return(new Polynomial(l1));	
}
    public Polynomial derivarePolinom(Polynomial p1) {
    	List<Monomial> l1=new ArrayList<Monomial>();
    	for (Monomial e : p1.Polinom) { 	
    	  
    	   if(e.getExponent()>0)
    		  l1.add(new Monomial(e.getExponent()*e.getCoeficient(),e.getExponent()-1)); 
    	}
    	return(new Polynomial(l1));	
    }
    public Polynomial integrarePolinom(Polynomial p1) {
    	float z;
    	List<Monomial> l1=new ArrayList<Monomial>();
    	for (Monomial e : p1.Polinom) { 	
    	  
    	      z=(float)e.getCoeficient()/(e.getExponent()+1);
    		  l1.add(new Monomial(z,e.getExponent()+1)); 
    	}
    	return(new Polynomial(l1));	
    }
    public Polynomial inmultirePolinomi(Polynomial p1,Polynomial p2) {
    	int exp1,exp2;
    	List<Monomial> l1=new ArrayList<Monomial>();
    	List<Monomial> l2=new ArrayList<Monomial>();
    	Polynomial p=new Polynomial(l2);
    	for (Monomial e : p1.Polinom) { 	
    		l1.removeAll(l1);
    		exp1=e.getExponent();
    		  for(Monomial e2: p2.Polinom){
    			  exp2=e2.getExponent();	  
    			  l1.add(new Monomial(e.getCoeficient()*e2.getCoeficient(),exp1+exp2));
    			  }	
    		  p=p.adunarePolinomi(p, new Polynomial(l1));
    		  } 
    	return p;	
    }
    public String toString() {
    	String s="";
    	for (Monomial e : Polinom) 
		{
		  s+=e.toString();
		}
    	return s;	
    }
}
