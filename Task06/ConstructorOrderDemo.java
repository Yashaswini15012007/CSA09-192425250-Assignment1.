class ModelVehicle {
    ModelVehicle(String regNo) {
        System.out.println("Vehicle(regNo) " + regNo);
    }
}

class ModelCar extends ModelVehicle {
    ModelCar(String regNo) {
        super(regNo);
        System.out.println("Car(regNo)");
    }
}

class ModelElectricCar extends ModelCar {
    ModelElectricCar(String regNo) {
        super(regNo);
        System.out.println("ElectricCar(regNo)");
    }
}

public class ConstructorOrderDemo {
    public static void main(String[] args) {
        new ModelElectricCar("TN06EV0006");
    }
}