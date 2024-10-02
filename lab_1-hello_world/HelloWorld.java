// Jedna publiczna klasa
// Plik musi nazywać się tak jak klasa (case sensitive)
package lab_1;

import java.util.Scanner;
import java.util.Locale;

public class HelloWorld {
    // Musi istnieć publiczna statyczna klasa main

    // Używając java ...
    // -cp <class search path of directories and zip/jar files>
    // można zmienić miejsce szukania ścieżek do plików jar
    public static void main(String args[]) {
        System.out.println("Hello world");

        System.out.print("..."); // Bez '\n' na końcu
        System.out.println("..."); // Z '\n' na końcu
        System.out.printf("String %s int %d double %f", "STRING", 120, 5.7);

        Scanner scan = new Scanner(System.in).useLocale(Locale.US); // Locale US aby separatorem części dziesiętnej była kropka
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f",s,i,d);
    }
}