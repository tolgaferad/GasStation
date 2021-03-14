package main.GasStation.Employers;

import main.Car.Car;
import main.Exceptions.NotCarAtTheColumnException;
import main.GasStation.GasStation;

import java.util.concurrent.atomic.AtomicBoolean;

public class Employer extends Thread{
    private final GasStation gasStation;
    private AtomicBoolean isBusy=new AtomicBoolean(false);
    public Employer(GasStation gasStation,String name){
        super(name);
        this.gasStation=gasStation;
    }

    @Override
    public void run() {
        while(true) {
        gasStation.loadFuel(this);
        }
    }
}
