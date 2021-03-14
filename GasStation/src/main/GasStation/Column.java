package main.GasStation;

import main.Car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Column {
    private ConcurrentLinkedQueue<Car> cars = new ConcurrentLinkedQueue<>();
    public static HashMap<Integer,ArrayList<String>> chargings=new HashMap<>();
    public void addToQueue(Car car) {
        cars.offer(car);
    }
    public Queue<Car> getCars() {
        return cars;
    }
}

