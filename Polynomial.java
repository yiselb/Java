package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		
		//answer LL 
		Node p3 = null; 
		
		//term = head/node? 
		//hmm not too sure about this
		Node p1 = poly1;
		Node p2 = poly2;
		
		//poly1 != null && poly2 != null
		while(p1 != null) {
			p3 = insert(p3, p1.term.coeff, p1.term.degree);
			//increment p1
			p1 = p1.next;
		}
		while(p2 != null)  {
			//insert p2 coeff and degree into third LL
			p3 = insert(p3, p2.term.coeff, p2.term.degree);
			//increment p2
			p2 = p2.next;
		}

		p3 = reverse(p3);
		return p3;
	}
	//reverse LL so it prints in right order
	private static Node reverse(Node p3) {
		Node prev = null;
		Node curr = p3; 
		Node next = null;
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr; 
			curr = next;
		}
		p3 = prev; 
		return p3;
		//return null;
	}
	//private method ?
	private static Node insert(Node p3, float coeff, int degree) {
		if(coeff == 0) {
			return p3;
		}
		Node n = new Node(coeff, degree, null); 
		
		if(p3 == null) {
			return n;
		}else {
			if(p3.term.degree == degree) {
				p3.term.coeff = p3.term.coeff + coeff;
				return p3;
			}else if(p3.term.degree < degree) {
				n.next = p3;
				p3 = n;
				return p3;
			}
			
			//empty out LL --- get all the lower powers
			Node p = p3;
			while(p.next != null) {
				if(degree == p.term.degree) {
					p.term.coeff = p.term.coeff + coeff;
					return p3;
				}else if(degree > p.next.term.degree) {
					n.next = p.next;
					p.next = n;
					return p3;
				}
				p = p.next;
			}
			if(p.term.degree == degree) {
				p.term.coeff = p.term.coeff + coeff;
				return p3; 
			}
		p.next = n;
		return p3;
		}
	}
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		
		Node p3 = null; 
		//start at poly not at poly.next 
		while(poly1 != null) {
			Node p2 = poly2;
			while(p2 != null) {
				p3 = insert(p3, poly1.term.coeff*p2.term.coeff, poly1.term.degree + p2.term.degree);
				p2 = p2.next;
			}
			poly1 = poly1.next;
		}
		p3 = reverse(p3);
		return p3;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		
		float ans = 0;
		//traverse through poly
		//where there is x in the LL replace it and solve 
		while(poly != null) {
			ans += poly.term.coeff*Math.pow(x, poly.term.degree);
			poly = poly.next;
		}
		return ans;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
