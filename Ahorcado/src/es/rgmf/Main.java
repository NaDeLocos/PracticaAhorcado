package es.rgmf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//importes practica
import java.util.ArrayList;
import java.lang.Thread;
//fin importes pcatrica

public class Main {

    public static void main(String[] args) {
        String frase;
        String[] frases;
        char[] fraseOculta;
        Scanner entrada = new Scanner(System.in);
        Path path;

        // Carga el fichero con las frases
        path = Paths.get("Ahorcado/src/es/rgmf/frases.txt");
        try {
            frases = Files.lines(path).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            frases = new String[] {"Hola Amigo"};
        }

        // Prepara la frase
        frase = frases[(int) (Math.random() * frases.length)];
        fraseOculta = new char[frase.length()];

        // Construye la frase oculta.
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                fraseOculta[i] = ' ';
            } else {
                fraseOculta[i] = '_';
            }
        }

        // Muestra la frase oculta.
        System.out.println(fraseOculta);

        // TODO Completa el programa.

        //estético:
        String cuadritoDec = "";for(int i = 0;i < fraseOculta.length;i++){cuadritoDec = cuadritoDec + "-";}

        //variables
        boolean 
        jugando = true,
        EntradaYaEscogida = false;

        char caracterElegido = ' ';
        ArrayList<Character> vocalesUsadas = new ArrayList<>();

        String fraseTransformada = frase.toLowerCase();
        int 
        intentos = 0,
        fallos = 0,
        letrasEncontradas = 0,
        letrasPorAdivinar = 0;for(int i = 0; i < fraseOculta.length;i++){if(fraseOculta[i] == '_'){letrasPorAdivinar++;}}


        

        //programa
        while(jugando){
            System.out.println("La frase es:\n\n");
            System.out.print("-" + cuadritoDec + "-\n|");
            System.out.print(fraseOculta);
            System.out.print("|\n-" + cuadritoDec + "-\n");
            

            //entrada del teclado
            do{
                EntradaYaEscogida = false;
                if(vocalesUsadas.size() != 0){System.out.println("Letras usadas:");}
                for(int i = 0; i < vocalesUsadas.size();i++){
                    System.out.print(" "+vocalesUsadas.get(i));
                }
                System.out.println("\nIntroduce una letra: ");
                caracterElegido = entrada.nextLine().toLowerCase().charAt(0);
                for(int i = 0; i < vocalesUsadas.size();i++){
                    if(vocalesUsadas.get(i) == caracterElegido){
                        EntradaYaEscogida = true;
                        break;
                    }
                }
            }while(EntradaYaEscogida);



            //encontrar la palabra y modificarla
            vocalesUsadas.add(caracterElegido);
            intentos++;

            for(int i = 0; i < fraseOculta.length;i++){
                if(caracterElegido == fraseTransformada.charAt(i)){
                    letrasEncontradas++;
                    letrasPorAdivinar--;
                    //fraseOculta[i] = frase.charAt(i);
                }
            }
            if(letrasEncontradas > 0){
                System.out.println("Se han encontrado " +letrasEncontradas+" letras!!!");
                letrasEncontradas = 0;
                for(int i = 0; i < fraseOculta.length;i++){
                    if(caracterElegido == fraseTransformada.charAt(i)){
                        letrasEncontradas++;
                        fraseOculta[i] = frase.charAt(i);
                        System.out.println(letrasEncontradas + " x " + caracterElegido + "!!("+ letrasPorAdivinar + " restantes)");
                        System.out.print("-" + cuadritoDec + "-\n|");
                        System.out.print(fraseOculta);
                        System.out.print("|\n-" + cuadritoDec + "-\n");
                        try{Thread.sleep(500);}catch(Exception e){e.printStackTrace();}
                    }
                }
            }else{
                fallos++;
                System.out.println("Eres tontissimo, reintentaló.");
            }


            //comprobaciones finales
            if(letrasPorAdivinar == 0){
                jugando = false;
            }

        }

        String dec = "LO HAS COMPLETADO!!!!!";
        for(int i = 0; i < dec.length();i++){
            System.out.println(dec.substring(0,i));
            try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();}
        }

        System.out.println("\n\n\nBuena partida!");
        try{Thread.sleep(300);}catch(Exception e){e.printStackTrace();}
        System.out.println("Estadisticas:");
        try{Thread.sleep(300);}catch(Exception e){e.printStackTrace();}
        System.out.println("Intentos:\t\t" + intentos);
        try{Thread.sleep(300);}catch(Exception e){e.printStackTrace();}
        System.out.println("Aciertos:\t\t" + (intentos-fallos));
        try{Thread.sleep(300);}catch(Exception e){e.printStackTrace();}
        System.out.println("Fallos:\t\t" + fallos);
        try{Thread.sleep(300);}catch(Exception e){e.printStackTrace();}
        System.out.println("Frase:\t\t" + frase);

        entrada.close();
       
    }
}
