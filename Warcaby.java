package projektwarcaby;
import java.util.Scanner;

public class Warcaby {
	char [][] plansza;
	int biale_pionki;
	int czarne_pionki;
	char pionek;
	
	
	public Warcaby() {
		
		plansza = new char[8][8];
		biale_pionki = 12;
		czarne_pionki = 12;
		pionek = 'b';

		int i,j;
		for (i=0;i<8;i++)
		    for (j=0;j<8;j++)
			plansza[i][j] = ' ';

		for (i=1;i<8;i+=2) {
		    plansza[i][1] = 'b';
		    plansza[i][5] = 'c';
		    plansza[i][7] = 'c';
		}
		for (i=0;i<8;i+=2) {
		    plansza[i][0] = 'b';
		    plansza[i][2] = 'b';
		    plansza[i][6] = 'c';
		}
	    }
	public void Plansza() {
		int i,j;
		System.out.println("   1   2   3   4   5   6   7   8 ");
		System.out.println(" +---+---+---+---+---+---+---+---+");
		for (i=0;i<8;i++) {
		    System.out.print((i+1) + "|");
		    for (j=0;j<8;j++) {
			System.out.print(" "+plansza[j][i]+" " + "|");
		    }
		    
		     System.out.println();
		    System.out.print(" +---+---+---+---+---+---+---+---+");
		    System.out.println();
		}
	    }
	boolean MozliwyRuch(int Z, int Do) {
		int x1 = Z%10-1;
		int x2 = Do%10-1;
		int y1 = Z/10-1;
		int y2 = Do/10-1;
			
		if (x1>=0 && x2>=0 && y1<=7 && y2<=7 && x1<=7 && x2<=7 && y1>=0 && y2>=0 ) {
		
			if (Math.abs(x1-x2)==1 && (y1-y2)==1 && pionek=='c'){
				if (plansza[x2][y2]==' ') {
					return true;
				}
			}
			if (Math.abs(x1-x2)==1 && (y1-y2)==-1 && pionek=='b'){
				if (plansza[x2][y2]==' ') {
					return true;
				}
			}
			
			if ((Math.abs(x1-x2)==2 && Math.abs(y1-y2)==2) && (pionek=='b')){
				if (plansza[(x1+x2)/2][(y1+y2)/2]=='c') {
					czarne_pionki--;
					return true;
				}
			}
			if ((Math.abs(x1-x2)==2 && Math.abs(y1-y2)==2) && (pionek=='c')){
				if (plansza[(x1+x2)/2][(y1+y2)/2]=='b') {
					biale_pionki--;
					return true;
				}
			}
		}
		
		return false;
	}
	
	void WykonajRuch(int Z, int Do) {
		int x1 = Z%10-1;
		int x2 = Do%10-1;
		int y1 = Z/10-1;
		int y2 = Do/10-1;
		plansza[x1][y1]=' ';
		plansza[x2][y2]=pionek;
		
		if ((Math.abs(x1-x2)==2) && (Math.abs(y1-y2)==2) ){
			plansza[(x1+x2)/2][(y1+y2)/2]=' ';
		}
		
	}
	void Ruch() {
		
		int ruch = 0;
		int skok=0;
		int a=0;
		Scanner inn = new Scanner(System.in);		
		if (pionek=='b')
		    System.out.println("Ruch bialych pionkow.");
		else
		    System.out.println("Ruch czarnych pionkow.");
		
		while(ruch==0) {
		
			System.out.println("Podaj pozycje pionka, ktory chcesz przestawic.Podaj jako licze calkowita. ");
			int Z = inn.nextInt();
			System.out.print("Podaj pozycje, na ktora chcesz przesunac pionek.");
			int Do = inn.nextInt();			
			int x1 = Z%10-1;
			int x2 = Do%10-1;
			int y1 = Z/10-1;
			int y2 = Do/10-1;
			
			if(Math.abs(x1-x2)==2 && Math.abs(y1-y2)==2){
				skok=1;
			}
			
			if (Nakaz_bicia() && skok==0) {
				a=1;
			}			
		
			if (MozliwyRuch(Z,Do) && a==0) {
				WykonajRuch(Z,Do);				
				
				while(Istnieje_bicie(x2,y2) && skok==1) {
					Plansza();
					System.out.println("Masz jeszcze jedno bicie.");
					Z = Do;
					skok=0;

					System.out.print("Podaj pozycje, na ktora chcesz przesunac ten sam pionek.");
					Do = inn.nextInt();					
					x1 = Z%10-1;
					x2 = Do%10-1;
					y1 = Z/10-1;
					y2 = Do/10-1;
					
					if(Math.abs(x1-x2)==2 && Math.abs(y1-y2)==2){
						skok=1;
					}					
					if (MozliwyRuch(Z,Do) && skok==1) {
						WykonajRuch(Z,Do);
						}
					else {
						System.out.println("Niewlasciwy ruch.");
						x2=x1;
						y2=y1;
						skok=1;
						Do=Z;
					}
					}				
				ruch=1;				
		    }
			else if(a==1) {
				System.out.println("Masz mozliwosc bicia. Musisz je wykonac.");
				a=0;				
			}
			else {
				System.out.println("Niewlasciwy ruch.");
				}
			}
		if (pionek=='b') {
			pionek='c';
		}
		else {
			pionek='b';
		}
	
	}
	
	boolean Koniec() {
		if (biale_pionki ==0 || czarne_pionki==0) {
			return false;
		}
		return true;
	}
	
	void Wygrana() {
		if (biale_pionki!=0) {
			System.out.println("Wygraly biale pionki.");
		}
		else {
			System.out.println("Wygraly czarne pionki.");
		}
	}
	boolean Istnieje_bicie(int x2, int y2) {
		
		if (pionek=='b') {
			if (x2>1 && y2>1 && x2<6 && y2<6) {
				
				if(plansza[x2][y2]=='b' && plansza[x2-1][y2-1]=='c' && plansza[x2-2][y2-2]==' ') {
			    	return true;
			    }
			    if(plansza[x2][y2]=='b' && plansza[x2-1][y2+1]=='c' && plansza[x2-2][y2+2]==' ') {
			    	return true;
			    }
			    if(plansza[x2][y2]=='b' && plansza[x2+1][y2-1]=='c' && plansza[x2+2][y2-2]==' ') {
			    	return true;
			    }
			    if(plansza[x2][y2]=='b' && plansza[x2+1][y2+1]=='c' && plansza[x2+2][y2+2]==' ') {
			    	return true;
			    }
				
			}
			
			if ((x2==0 || x2==1) && (y2>1 && y2<6)) {
				
				 if(plansza[x2][y2]=='b' && plansza[x2+1][y2+1]=='c' && plansza[x2+2][y2+2]==' ') {
				    	return true;
				    }
				 if(plansza[x2][y2]=='b' && plansza[x2+1][y2-1]=='c' && plansza[x2+2][y2-2]==' ') {
				    	return true;
				    }
				
			}
			
			if ((x2==7 || x2==6) && y2>1 && y2<6) {
				  if(plansza[x2][y2]=='b' && plansza[x2-1][y2-1]=='c' && plansza[x2-2][y2-2]==' ') {
				    	return true;
				    }
				  if(plansza[x2][y2]=='b' && plansza[x2-1][y2+1]=='c' && plansza[x2-2][y2+2]==' ') {
				    	return true;
				    }
				    
			}
			
			if ((y2==7 || y2==6) && x2>1 && x2<6) {
				if(plansza[x2][y2]=='b' && plansza[x2-1][y2-1]=='c' && plansza[x2-2][y2-2]==' ') {
			    	return true;
			    }
				if(plansza[x2][y2]=='b' && plansza[x2+1][y2-1]=='c' && plansza[x2+2][y2-2]==' ') {
			    	return true;
			    }
								
			}
			
			if ((y2==0 || y2==1) && x2>1 && x2<6) {
				if(plansza[x2][y2]=='b' && plansza[x2+1][y2+1]=='c' && plansza[x2+2][y2+2]==' ') {
			    	return true;
			    }
				if(plansza[x2][y2]=='b' && plansza[x2-1][y2+1]=='c' && plansza[x2-2][y2+2]==' ') {
					return true;
				}
				
			}
			if((x2==0|| x2==1)&&(y2==0 || y2==1)) {
				if(plansza[x2][y2]=='b' && plansza[x2+1][y2+1]=='c' && plansza[x2+2][y2+2]==' ') {
					return true;
				}
			}
			if((x2==0|| x2==1)&&(y2==6 || y2==7)) {
				if(plansza[x2][y2]=='b' && plansza[x2+1][y2-1]=='c' && plansza[x2+2][y2-2]==' ') {
					return true;
				}
				
			}
			if((x2==6|| x2==7)&&(y2==0 || y2==1)) {
				if(plansza[x2][y2]=='b' && plansza[x2-1][y2+1]=='c' && plansza[x2-2][y2+2]==' ') {
					return true;
				}
				
			}
			if((x2==6|| x2==7)&&(y2==6 || y2==7)) {
				if(plansza[x2][y2]=='b' && plansza[x2-1][y2-1]=='c' && plansza[x2-2][y2-2]==' ') {
					return true;
				}				
			}
			
			
		}
		
		if(pionek=='c') {
			
			if (x2>1 && y2>1 && x2<6 && y2<6) {
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2-1]=='b' && plansza[x2-2][y2-2]==' ') {
					return true;						
				}
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2+1]=='b' && plansza[x2-2][y2+2]==' ') {
					return true;
				}
			    if(plansza[x2][y2]=='c' && plansza[x2+1][y2-1]=='b' && plansza[x2+2][y2-2]==' ') {
			    	return true;
			    }
			    if (plansza[x2][y2]=='c' && plansza[x2+1][y2+1]=='b' && plansza[x2+2][y2+2]==' ') {
			    	return true;
			    }
			}
			
			if ((x2==0 || x2==1) && (y2>1 && y2<6)) {
				
				 if(plansza[x2][y2]=='c' && plansza[x2+1][y2+1]=='b' && plansza[x2+2][y2+2]==' ') {
				    	return true;
				    }
				 if(plansza[x2][y2]=='c' && plansza[x2+1][y2-1]=='b' && plansza[x2+2][y2-2]==' ') {
				    	return true;
				    }
			}
			
			if ((x2==7 || x2==6) && y2>1 && y2<6) {
				
				  if(plansza[x2][y2]=='c' && plansza[x2-1][y2-1]=='b' && plansza[x2-2][y2-2]==' ') {
				    	return true;
				    }
				  if(plansza[x2][y2]=='c' && plansza[x2-1][y2+1]=='b' && plansza[x2-2][y2+2]==' ') {
				    	return true;
				    }
				    
			}
			
			if ((y2==7 || y2==6) && x2>1 && x2<6) {
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2-1]=='b' && plansza[x2-2][y2-2]==' ') {
			    	return true;
			    }
				if(plansza[x2][y2]=='c' && plansza[x2+1][y2-1]=='b' && plansza[x2+2][y2-2]==' ') {
			    	return true;
			    }
				
			}
			
			if ((y2==0 || y2==1) && x2>1 && x2<6) {
				if(plansza[x2][y2]=='c' && plansza[x2+1][y2+1]=='b' && plansza[x2+2][y2+2]==' ') {
			    	return true;
			    }
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2+1]=='b' && plansza[x2-2][y2+2]==' ') {
					return true;
				}
								
			}
			if((x2==0|| x2==1)&&(y2==0 || y2==1)) {
				if(plansza[x2][y2]=='c' && plansza[x2+1][y2+1]=='b' && plansza[x2+2][y2+2]==' ') {
					return true;
				}
			}
			if((x2==0|| x2==1)&&(y2==6 || y2==7)) {
				if(plansza[x2][y2]=='c' && plansza[x2+1][y2-1]=='b' && plansza[x2+2][y2-2]==' ') {
					return true;
				}
				
			}
			if((x2==6|| x2==7)&&(y2==0 || y2==1)) {
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2+1]=='b' && plansza[x2-2][y2+2]==' ') {
					return true;
				}
				
			}
			if((x2==6|| x2==7)&&(y2==6 || y2==7)) {
				if(plansza[x2][y2]=='c' && plansza[x2-1][y2-1]=='b' && plansza[x2-2][y2-2]==' ') {
					return true;
				}				
			}
			
			
		}		
		return false;		
		
	}
	
	boolean Nakaz_bicia() {
		for(int i=0; i<8;i++) {
			for (int j=0;j<8;j++) {
				if(Istnieje_bicie(j, i)) {
					return true;
				}
				
			}
		}
		return false;
	}
	

	public static void main(String[] args) {
		Warcaby gra = new Warcaby();
		gra.Plansza();
		do {
			gra.Ruch();
			gra.Plansza();
		}while(gra.Koniec());
		gra.Wygrana();
	
}
}
