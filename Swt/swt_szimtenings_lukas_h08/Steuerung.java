import java.util.Scanner;

public class Steuerung {
    private Context_Politiker politiker;

    public Steuerung(){
        int eingabe=-1;
        politiker=new Context_Politiker();
        while(politiker.aktiv()){
            System.out.println("Welches nächste Ereignis?\n"
                    +" (0) Lob von der eigenen Partei\n"
                    +" (1) Tadel von der eigenen Partei\n"
                    +" (2) Lob von der Wirtschaft\n"
                    +" (3) Erwischt");
            eingabe=new Scanner(System.in).nextInt();
            switch(eingabe){
                case 0:
                    politiker.parteilob();
                    break;
                case 1:
                    politiker.parteitadel();
                    break;
                case 2:
                    politiker.wirtschaftslob();
                    break;
                case 3:
                    politiker.erwischt();
                    break;
            }
            System.out.println("Fiktiver Politiker befindet sich in"
                    +" (Teil)-Zuständen: " + politiker);
        }
    }

    public static void main(String[] args) {
        new Steuerung();
    }
}