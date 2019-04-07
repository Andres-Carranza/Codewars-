// Andres Carranza
//7/22/18
// To evaluate a mathematical expression in the form of a String
import java.util.ArrayList;
import java.util.List;

public class MathEvaluator {
	private String exp;
	public double calculate(String expression) {
		//System.out.println(expression);
		exp = expression;
		deleteWhitespaces();
		evaluateParenthesis();
		return evaluate();
	}
	public double evaluate() {
		List<Double> nums = new ArrayList<Double>();
		List<Character> ops = new ArrayList<Character>();//operators
		List<Integer> opsI = new ArrayList<Integer>();//operator index
		boolean number =false;//determines if there has been a number before the last operator
		for(int i = 0;i<exp.length();i++) {
			if(exp.charAt(i)=='0'||exp.charAt(i)=='1'||exp.charAt(i)=='2'||exp.charAt(i)=='3'||exp.charAt(i)=='4'||exp.charAt(i)=='5'||exp.charAt(i)=='6'||exp.charAt(i)=='7'||exp.charAt(i)=='8'||exp.charAt(i)=='9') {
				number = true;

			}
			else if(exp.charAt(i)=='+'||exp.charAt(i)=='-'||exp.charAt(i)=='*'||exp.charAt(i)=='/') {
				if(number) {
					ops.add(exp.charAt(i));
					opsI.add(i);
				}
				number = false;
			}
		}
		if(ops.size()==0) {
			double value =Double.parseDouble(exp.substring(0, exp.length()));
			return value;
		}
		nums.add(Double.parseDouble(exp.substring(0,opsI.get(0))));
		for(int i = 1; i<opsI.size();i++) {
			nums.add(Double.parseDouble(exp.substring(opsI.get(i-1)+1,opsI.get(i))));
		}
			nums.add(Double.parseDouble(exp.substring(opsI.get(opsI.size()-1)+1,exp.length())));
			
		//after this point all values and operators have been filled in
		for(int i =0; i<ops.size();i++) {
			if(ops.get(i)=='*') {
				nums.set(i, nums.get(i)*nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;
			}
			else if(ops.get(i)=='/') {
				nums.set(i, nums.get(i)/nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;

			}
		}
		for(int i =0; i<ops.size();i++) {
			if(ops.get(i)=='+') {
				nums.set(i, nums.get(i)+nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;
			}
			else if(ops.get(i)=='-') {
				nums.set(i, nums.get(i)-nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;

			}
		}
		return nums.get(0);
	}
	public void deleteWhitespaces() {
		for(int i=0; i< exp.length();i++) {
			if(exp.charAt(i)==' ') {
				delete(i,i+1);
				i--;
			}
		}
	}
	public void evaluateParenthesis() {
		int pib =0;//( index
		int pie = exp.length();// ) index
		boolean pf = false;// true if a pair of parenthesis is found
		boolean lfp = true;//look for parenthesis
		boolean neg = false;
		while(find(0,'(')!=-1) {
			if(lfp) {
				for(int i =0;i< exp.length();i++) {
					if(exp.charAt(i)=='(') {
						pib =i;
						i = exp.length();
					}	
				}
			}
			for(int i = pib+1;i< exp.length(); i++) {
				if(exp.charAt(i)== ')') {
					pie =i;
					pf = true;
					i = exp.length();
					lfp =true;
				}
				else if(exp.charAt(i)=='(') {
					pib = i;
					i= exp.length();
					pf = false;
					lfp = false;
				}
			}
			if(pf) {
				if(pib==0)
					neg = false;
				else if(pib == 1)
					if(exp.charAt(0)=='-')
						neg = true;
					else
						neg = false;
				else
					if((exp.charAt(pib-1)=='-')&&(exp.charAt(pib-2)=='+'||exp.charAt(pib-2)=='-'||exp.charAt(pib-2)=='*'||exp.charAt(pib-2)=='/'||exp.charAt(pib-2)=='('))
						neg = true;
					else
						neg = false;
 				if(neg)
					exp = exp.substring(0, pib-1)+""+evaluateP(pib,pie,neg)+""+exp.substring(pib-1,exp.length());
				else
					exp = exp.substring(0, pib)+""+evaluateP(pib,pie,neg)+""+exp.substring(pib,exp.length());
			}

		}
	}
	public double evaluateP(int i1,int i2, boolean neg) {
		List<Double> nums = new ArrayList<Double>();
		List<Character> ops = new ArrayList<Character>();//operators
		List<Integer> opsI = new ArrayList<Integer>();//operator index
		boolean number =false;//determines if there has been a number before the last operator
		for(int i = i1+1;i<i2;i++) {
			if(exp.charAt(i)=='0'||exp.charAt(i)=='1'||exp.charAt(i)=='2'||exp.charAt(i)=='3'||exp.charAt(i)=='4'||exp.charAt(i)=='5'||exp.charAt(i)=='6'||exp.charAt(i)=='7'||exp.charAt(i)=='8'||exp.charAt(i)=='9') {
				number = true;

			}
			else if(exp.charAt(i)=='+'||exp.charAt(i)=='-'||exp.charAt(i)=='*'||exp.charAt(i)=='/') {
				if(number) {
					ops.add(exp.charAt(i));
					opsI.add(i);
				}
				number = false;
			}
		}
		if(ops.size()==0) {
			double value;
			if(neg)
				value=Double.parseDouble(exp.substring(i1+1,i2))*-1;
			else
				value=Double.parseDouble(exp.substring(i1+1,i2));
			if(neg)
				delete(i1-1,i2+1);
			else
				delete(i1,i2+1);
			return value;
		}
		nums.add(Double.parseDouble(exp.substring(i1+1,opsI.get(0))));
		for(int i = 1; i<opsI.size();i++) {
			nums.add(Double.parseDouble(exp.substring(opsI.get(i-1)+1,opsI.get(i))));
		}
		nums.add(Double.parseDouble(exp.substring(opsI.get(opsI.size()-1)+1,i2)));
		
		if(neg)
			for(int i =0; i< nums.size();i++) {
				nums.set(i, nums.get(i)*(-1));
			}
		//after this point all values and operators have been filled in
		if(neg)
			delete(i1-1,i2+1);
		else
			delete(i1,i2+1);
		for(int i =0; i<ops.size();i++) {
			if(ops.get(i)=='*') {
				nums.set(i, nums.get(i)*nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;
			}
			else if(ops.get(i)=='/') {
				nums.set(i, nums.get(i)/nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;

			}
		}
		for(int i =0; i<ops.size();i++) {
			if(ops.get(i)=='+') {
				nums.set(i, nums.get(i)+nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;
			}
			else if(ops.get(i)=='-') {
				nums.set(i, nums.get(i)-nums.get(i+1));
				nums.remove(i+1);
				ops.remove(i);
				i--;

			}
		}
		return nums.get(0);
	}
	public int find(int index, char c) {
		for(int i= index; i< exp.length(); i++) {
			if(exp.charAt(i)==c)
				return i;
		}
		return -1;
	}
	public void delete(int i1, int i2) {
		//System.out.println("Before: "+ exp);
		String hold;
		hold = exp.substring(0,i1)+exp.substring(i2,exp.length());
		exp = hold;
	}
}
