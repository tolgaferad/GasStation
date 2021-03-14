package main.GasStation;

import main.Car.Car;
import main.Car.Driver;
import main.Exceptions.NotCarAtTheColumnException;
import main.GasStation.Employers.Cashier;
import main.GasStation.Employers.Employer;
import main.util.Randomizator;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class GasStation {
    private ArrayList<Column> columns = new ArrayList<>();
    private ArrayList<Cashier> cashiers = new ArrayList<>();
    private ArrayList<Employer> employers = new ArrayList<>();

    public GasStation() {
        addColumns();
        addCashiers();
        employers.add(new Employer(this,Randomizator.randomName()+" pompadjiqta"));
        employers.add(new Employer(this,Randomizator.randomName()+" pompadjiqta"));
    }
    public synchronized void loadCar(Car car){
        enteringCar(car);
        while(!car.isCharged()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Driver driver=car.getDriver();
        int numberCashier=Randomizator.randomFromTo(0,1);
        cashiers.get(numberCashier).getQueue().offer(driver);
        while(!driver.isServiced()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cashiers.get(numberCashier).getQueue().poll();
        System.out.println(car.getName()+" is leaving...");
        notifyAll();
    }
    public void loadFuel(Employer employer){
        synchronized (this) {
            while (areColumnsEmpty()) {
                try {
                    System.out.println("There is not cars at the columns " + employer.getName() + " is waiting");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (getNonEmptyColumn() == null) {
                        throw new NotCarAtTheColumnException("няма коли на колоните...");
                    }
                } catch (NotCarAtTheColumnException e) {
                    e.getMessage();
                }
            }
        }
        Column column = getNonEmptyColumn();

        Car car=column.getCars().poll();
        if (car==null){
            return;
        }
        car.setCharged();
        System.out.println(employer.getName()+" is fueling "+ car.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public Column getNonEmptyColumn(){
        Column column=null;
        for (int i = 0; i < columns.size(); i++) {
            if (!columns.get(i).getCars().isEmpty()) {
                column=columns.get(i);
            }
        }
        return column;
    }
    public synchronized void enteringCar(Car car){
        Column column=getColumn();
        column.addToQueue(car);
        System.out.println(car.getName()+" entered the station and line up at column");
        notifyAll();

    }
    private boolean areColumnsEmpty() {
        for (int i = 0; i < columns.size(); i++) {
            if (!columns.get(i).getCars().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void serviceClient(){
        synchronized (this) {
            while (areEmptyQueuesOnCash()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (getNonEmptyColumn() == null) {
                        throw new NotCarAtTheColumnException("Опашката е празна...");
                    }
                } catch (NotCarAtTheColumnException e) {
                    e.getMessage();
                }

            }
        }
      Queue<Driver> drivers=getNonEmptyQueue();
      Driver driver=drivers.poll();
      if (driver==null){
          return;
      }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.setServiced();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (!Column.chargings.containsKey(driver.getColumnNumber())) {
            Column.chargings.put(driver.getColumnNumber(),new ArrayList<>());
            Column.chargings.get(driver.getColumnNumber()).add(driver.getChargedLitres()+","+driver.getTypeFuel()+","+ dtf.format(now));
        }
        else{
            Column.chargings.get(driver.getColumnNumber()).add(driver.getChargedLitres()+","+driver.getTypeFuel()+","+ dtf.format(now));
        }
        synchronized (this) {
            notifyAll();
        }
    }

    private boolean areEmptyQueuesOnCash() {
        for (int i = 0; i < cashiers.size(); i++) {
            if (!cashiers.get(i).getQueue().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private Queue<Driver> getNonEmptyQueue(){
        Queue<Driver> nonEmpty=null;
        for (int i = 0; i <cashiers.size() ; i++) {
            if (cashiers.get(i).getQueue().size()>0){
               nonEmpty= cashiers.get(i).getQueue();
               break;
            }
        }
        return nonEmpty;
    }
    public void addColumns() {
        for (int i = 0; i < 5; i++) {
            columns.add(new Column());
        }
    }
    public void addCashiers() {
        for (int i = 0; i < 2; i++) {
            cashiers.add(new Cashier(this,Randomizator.randomName()+" kasiera"));
        }
    }
    public Column getColumn() {
        return columns.get(new Random().nextInt(columns.size()));
    }
    public ArrayList<Column> getColumns() {
        return columns;
    }

    public ArrayList<Cashier> getCashiers() {
        return cashiers;
    }

    public ArrayList<Employer> getEmployers() {
        return employers;
    }
}

