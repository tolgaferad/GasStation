package main.Car;

public class Driver {
    private String name;
    private int chargedLitres;
    private TypeFuel typeFuel;
    private int columnNumber;
    private boolean isServiced=false;

    public void setChargedLitres(int chargedLitres) {
        this.chargedLitres = chargedLitres;
    }

    public Driver(String name, int chargedLitres, TypeFuel typeFuel) {
        this.name = name;
        this.chargedLitres = chargedLitres;
        this.typeFuel = typeFuel;
    }

    public void setTypeFuel(TypeFuel typeFuel) {
        this.typeFuel = typeFuel;
    }

    public void setColumn(int numColumn) {
        this.columnNumber=numColumn;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public int getChargedLitres() {
        return chargedLitres;
    }
    public boolean isServiced() {
        return isServiced;
    }
    public TypeFuel getTypeFuel() {
        return typeFuel;
    }

    public void setServiced() {
        isServiced=true;
    }
}
