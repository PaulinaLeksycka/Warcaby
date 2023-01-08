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

  
  
  
  
}  
