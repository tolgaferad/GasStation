package main;

import main.Car.Car;
import main.GasStation.GasStation;
import main.util.Randomizator;

public class CarGenerator extends Thread{
    private GasStation gasStation;

    public CarGenerator(GasStation gasStation) {
        this.gasStation = gasStation;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Car car=new Car(gasStation, Randomizator.getCarMarque());
            car.start();

        }
    }
}
