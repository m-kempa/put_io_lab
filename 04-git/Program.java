//Program testowy
//potrzbne do schowka

public class Car {


    public String model;
    public String engineType;
    public int vehicleNum;
    public String Comment;


    public Sting Color;




    public Car(String model,String engineType,int vehicleNum) {
        this.model = model;
        this.engineType = engineType;
        this.vehicleNum = vehicleNum;
    }


    //Wybranie modeluuu


    public void setModel(String model) {
        this.model = model;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getModel() {
        return this.model;
    }

    public String getEngineType() {
        return this.engineType;
    }

    public int getVehicleNum() {
        return this.vehicleNum;
    }


    //Wydrukowanie informacjii


    public void printInfo() {

        System.out.println("Model " + getModel());
        System.out.println("engineType " + getEngineType());
        System.out.println("VehicleNum " + getVehicleNum());

    }
