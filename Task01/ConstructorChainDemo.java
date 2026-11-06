class VehicleBase {
    VehicleBase() {
        System.out.println("1. VehicleBase constructor");
    }
}

class CarBase extends VehicleBase {
    CarBase() {
        System.out.println("2. CarBase constructor");
    }
}

class ElectricCarDemo extends CarBase {
    ElectricCarDemo() {
        System.out.println("3. ElectricCar constructor");
    }
}

public class ConstructorChainDemo {
    public static void main(String[] args) {
        new ElectricCarDemo();
    }
}