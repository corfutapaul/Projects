package model;

import java.util.Comparator;

public class CompareByExp implements Comparator<Monomial> {
	public int compare(Monomial o1, Monomial o2) {
		return Integer.compare(o2.getExponent(), o1.getExponent());
	}

}
