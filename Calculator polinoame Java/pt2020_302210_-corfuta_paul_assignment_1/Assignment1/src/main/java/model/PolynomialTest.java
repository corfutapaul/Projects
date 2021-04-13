package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PolynomialTest {
	@Test
	public void testSuma() {
		Monomial m01=new Monomial(1,3);
    	Monomial m02=new Monomial(2,3);
    	Monomial m11=new Monomial(1,2);
    	Monomial m12=new Monomial(2,2);
    	Monomial m21=new Monomial(1,1);
    	Monomial m22=new Monomial(2,1);
    	List<Monomial> l1=new ArrayList<Monomial>();
    	l1.add(m01);
    	l1.add(m11);
    	l1.add(m21);    	
    	Polynomial p1=new Polynomial(l1);
    	List<Monomial> l2=new ArrayList<Monomial>();
    	l2.add(m02);
    	l2.add(m12);
    	l2.add(m22);    	
    	Polynomial p2=new Polynomial(l2);
		Polynomial p3=p1.adunarePolinomi(p1, p2);
		assertEquals(p3.toString(),"+3.0x^3+3.0x^2+3.0x^1");
	}	
	@Test
	public void testDiferenta() {
		Monomial m01=new Monomial(1,3);
    	Monomial m02=new Monomial(2,3);
    	Monomial m11=new Monomial(1,2);
    	Monomial m12=new Monomial(2,2);
    	Monomial m21=new Monomial(1,1);
    	Monomial m22=new Monomial(2,1);
    	List<Monomial> l1=new ArrayList<Monomial>();
    	l1.add(m01);
    	l1.add(m11);
    	l1.add(m21);    	
    	Polynomial p1=new Polynomial(l1);
    	List<Monomial> l2=new ArrayList<Monomial>();
    	l2.add(m02);
    	l2.add(m12);
    	l2.add(m22);    	
    	Polynomial p2=new Polynomial(l2);
		Polynomial p3=p1.scaderePolinomi(p1, p2);
		assertEquals(p3.toString(),"-1.0x^3-1.0x^2-1.0x^1");
	}	
	@Test
	public void testDerivare() {
		Monomial m01=new Monomial(1,3);    	
    	Monomial m11=new Monomial(1,2);    	
    	Monomial m21=new Monomial(1,1);  
    	List<Monomial> l1=new ArrayList<Monomial>();
    	l1.add(m01);
    	l1.add(m11);
    	l1.add(m21);    	
    	Polynomial p1=new Polynomial(l1);
		Polynomial p3=p1.derivarePolinom(p1);
		assertEquals(p3.toString(),"+3.0x^2+2.0x^1+1.0");
	}
	@Test
	public void testIntegrare() {
		Monomial m01=new Monomial(4,3);    	
    	Monomial m11=new Monomial(3,2);    	
    	Monomial m21=new Monomial(2,1);  
    	List<Monomial> l1=new ArrayList<Monomial>();
    	l1.add(m01);
    	l1.add(m11);
    	l1.add(m21);    	
    	Polynomial p1=new Polynomial(l1);
		Polynomial p3=p1.integrarePolinom(p1);
		assertEquals(p3.toString(),"+1.0x^4+1.0x^3+1.0x^2");
	}
	@Test
	public void testInmultire() {
		Monomial m01=new Monomial(3,2);
    	Monomial m02=new Monomial(4,2);
    	Monomial m12=new Monomial(2,0);	
    	List<Monomial> l1=new ArrayList<Monomial>();
    	l1.add(m01);	
    	Polynomial p1=new Polynomial(l1);
    	List<Monomial> l2=new ArrayList<Monomial>();
    	l2.add(m02);
    	l2.add(m12); 	
    	Polynomial p2=new Polynomial(l2);
		Polynomial p3=p1.inmultirePolinomi(p1, p2);
		assertEquals(p3.toString(),"+12.0x^4+6.0x^2");
	}	
}
