package abstractfactory;

//import java.util.ArrayList;

import factory.Investment;

/**
 * @author LuAnn Born
 *
 */
public class Portfolio {
	
	Investment i1;
	Investment i2;
	Investment i3;
	Investment i4;
	
	/**
	 * @param i1
	 * @param i2
	 * @param i3
	 * @param i4
	 */
	public Portfolio(Investment i1, Investment i2, Investment i3, Investment i4){
		
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.i4 = i4;
	}

	/**
	 * 
	 */
	public Portfolio() {
		// TODO Auto-generated constructor stub
	}
}


//    ArrayList<Investment> investments;
//	
//	/**
//	 * 
//	 */
//	public Portfolio(){
//		
//		investments = new ArrayList<Investment>();
//		
//	}
