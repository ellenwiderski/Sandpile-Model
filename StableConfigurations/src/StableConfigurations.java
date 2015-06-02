
public class StableConfigurations {
	public static int C(int l, int n) {
		int sum = 0;
		
		if (l == 1 && n < 3) {
			sum = 1;
		}
		else {
			for (int i = n-2; i < 2*l-1; i++) {		
				if (i >= 0 && l != 1) {
					sum += C(l-1,i);
				}
			}
		}
		return sum;
	}
	
	public static int S(int l) {
		int sum = 0;
		
		for (int i=0; i < 2*l+1; i++) {
			sum += C(l,i);
		}
		
		return sum;
	}
	
	public static int P(int l, int n) {
		int sum = 0;
		
		if (n >= l) {
			if (l == 1 && (n == 1 || n == 2)) {
				sum = 1;
			}
			else {
				for (int i = n-2; i < 2*l-1; i++) {
					if (i > 0 && l != 1) {
						sum += P(l-1,i);
					}
				}
			}
		}	
		return sum;
	}
	
	public static int R(int l) {
		int sum = 0;
		
		for (int i=0;i < 2*l+1; i++) {
			sum += P(l,i);
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(R(1));
		return;
	}
}
