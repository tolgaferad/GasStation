package main.Car;

import main.GasStation.Column;
import main.GasStation.GasStation;
import main.util.Randomizator;

import java.util.Random;

public class Car extends Thread{
    private GasStation gasStation;
    private boolean isCharged=false;
    private Driver driver;

    public Car(GasStation gasStation,String name){
        super(name);
        this.gasStation=gasStation;
        driver=new Driver(Randomizator.randomName(),Randomizator.randomFromTo(10,40),Randomizator.randomTypeFuel());
    }
    public TypeFuel getТypeFuel(){
        int random=new Random().nextInt(3);
        if (random==0){
            return TypeFuel.GAS;
        }
        else if (random==1){
            return TypeFuel.DIESEL;
        }
        else{
            return TypeFuel.BENZIN;
        }
    }
    public int getLitres(){
        return Randomizator.randomFromTo(10,40);
    }

    public Driver getDriver() {
        return driver;
    }
    public void setCharged(){
        this.isCharged=true;
    }

    @Override
    public void run() {
        gasStation.loadCar(this);
    }
    public boolean isCharged() {
        return isCharged;
    }

}
