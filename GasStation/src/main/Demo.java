package main;

import main.Car.Car;
import main.GasStation.GasStation;

public class Demo {
    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        CarGenerator carGenerator=new CarGenerator(gasStation);
        carGenerator.start();
        for (int i = 0; i <gasStation.getCashiers().size() ; i++) {
            gasStation.getCashiers().get(i).start();
        }
        for (int i = 0; i <gasStation.getEmployers().size() ; i++) {
            gasStation.getEmployers().get(i).start();
        }
    }
}
