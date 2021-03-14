package main.GasStation.Employers;

import main.Car.Driver;
import main.GasStation.GasStation;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Cashier extends Thread{
    public ConcurrentLinkedQueue<Driver> drivers=new ConcurrentLinkedQueue<>();
    private GasStation gasStation;

    public Cashier(GasStation gasStation,String name) {
        super(name);
        this.gasStation = gasStation;
    }
    public ConcurrentLinkedQueue<Driver> getQueue() {
        return drivers;
    }

    @Override
    public void run() {
        while(true) {
            gasStation.serviceClient();
            System.out.println(Thread.currentThread().getName() +" serviced customer");
        }
    }
}
