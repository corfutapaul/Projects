package controller;

import model.Monomial;
import model.Polynomial;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    private Polynomial polynomial1;
    private Polynomial polynomial2;   
    private View view;
    private int index=0;

    public Controller(Polynomial polynomial1,Polynomial polynomial2, View view) {
        this.polynomial1 = polynomial1;
        this.polynomial2 = polynomial2;
        this.view = view;
        initView();
    }

    private void initView() {

        view.getTextArea().setText("Aici se introduce primul polinom");
        view.getTextArea1().setText("Aici se introduce al doilea polinom");
        view.getTextArea2().setText("Rezultatul");;
        view.getButton1().addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent actionEvent) {        	
            	
            	citestePolinomi();
                Polynomial p3=new Polynomial(null);
                p3=polynomial1.adunarePolinomi(polynomial1, polynomial2);
                view.getTextArea2().setText(p3.toString());
            
            }

        });
        view.getButton2().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {        	
            	
            	citestePolinomi();
                Polynomial p3=new Polynomial(null);
                p3=polynomial1.scaderePolinomi(polynomial2, polynomial1);
                view.getTextArea2().setText(p3.toString());
            
            }

        });
        view.getButton3().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {        	
            	
            	citestePolinomi();
                Polynomial p3=new Polynomial(null);
                if(index%2==0) {
                p3=polynomial1.derivarePolinom( polynomial2);
                view.getTextArea2().setText(p3.toString());
                }
                else
                {
                    p3=polynomial1.derivarePolinom( polynomial1);
                    view.getTextArea2().setText(p3.toString());
                    }
                index++;
            }

        });
        view.getButton4().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {        	
            	
            	citestePolinomi();
                Polynomial p3=new Polynomial(null);              
                p3=polynomial1.inmultirePolinomi(polynomial1, polynomial2);
                view.getTextArea2().setText(p3.toString());
        
               
            }

        });
        view.getButton5().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {        	
            	
            	citestePolinomi();
                Polynomial p3=new Polynomial(null);
                if(index%2==0) {
                p3=polynomial1.integrarePolinom( polynomial2);
                view.getTextArea2().setText(p3.toString());
                }
                else
                {
                    p3=polynomial1.integrarePolinom( polynomial1);
                    view.getTextArea2().setText(p3.toString());
                    }
                index++;
            }

        });
    }
    public List<Integer> citesteCoeficienti(List<Integer> coeff,String s1){
    	
    	String[] parts = s1.split("x\\^\\d+\\+?");
    	    for (String part : parts) {	      
    	        coeff.add(Integer.parseInt(part));
    	    }
        return coeff;
    }
    public List<Integer> citesteExponenti(List<Integer> expo,String s1){
    	
    	String[] terms = s1.split("(-|\\+)");
    	for (String term : terms) {
    	    String[] parts1 = term.split("\\^"); 
    	    expo.add(Integer.parseInt(parts1.length > 1 ? parts1[1] : "0"));
    	}
		return expo;
    
    }
    public void citestePolinomi() {
    	List<Integer> coeff1 = new ArrayList<Integer>();
        List<Integer> expo1 = new ArrayList<Integer>();
        List<Integer> coeff2 = new ArrayList<Integer>();
        List<Integer> expo2 = new ArrayList<Integer>();
        List<Monomial> pol1 = new ArrayList<Monomial>(); 
        List<Monomial> pol2 = new ArrayList<Monomial>(); 
        String s1 = new String();  
        String s2 = new String();
    	s1= view.getTextArea1().getText();
    	coeff1= citesteCoeficienti(coeff1,s1);
    	expo1= citesteExponenti(expo1,s1);
    	s2=view.getTextArea().getText();
    	coeff2= citesteCoeficienti(coeff2,s2);
    	expo2= citesteExponenti(expo2,s2);
        int i=0;
        while(i<coeff1.size()) {
        	pol1.add(new Monomial(coeff1.get(i),expo1.get(i)));
        	i++;
        }
        i=0;
        while(i<coeff2.size()) {
        	pol2.add(new Monomial(coeff2.get(i),expo2.get(i)));
        	i++;
        }
        polynomial1=new Polynomial(pol1);
        polynomial2=new Polynomial(pol2);
    }
    

}