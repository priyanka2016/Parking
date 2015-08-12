/**
 * Created by sai on 12/08/2015.
 */
public class Car {

    String carNo;

    public Car(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return carNo.equals(car.carNo);

    }

}
