package main.util;

import main.Car.TypeFuel;

import java.util.Random;

public class Randomizator {
    public static int randomFromTo(int from,int to){
        return new Random().nextInt(to-from+1)+from;
    }
    public static TypeFuel randomTypeFuel(){
        TypeFuel[] tFuel= {TypeFuel.BENZIN,TypeFuel.DIESEL,TypeFuel.GAS};
        return tFuel[new Random().nextInt(tFuel.length)];
    }
    public static String randomName(){
        String[] names={"Gosho","Pesho","Ivan","Tolga","Krasi","Stoqn","Elica","Melice","Melica","Belisa","Sinem,"};
        return names[new Random().nextInt(names.length)];
    }
    public static String getCarMarque(){
        String[] marques={"Mercedes","Bmw","Audi","Trabant","Ferrari","Nissan","VW","Skoda","Saab","Volvo"};
        return marques[new Random().nextInt(marques.length)];
    }
}
