/**
 * Created by sai on 12/08/2015.
 */
import exceptions.CarDoesNotExistException;
import exceptions.CarIsAlreadyParkedException;
import exceptions.SpaceNotAvailableException;
import org.junit.Assert;
import org.junit.Test;
public class ParkingTest {


    @Test
    public void shouldParkACar()
    {
        ParkingLot parkingLot=new ParkingLot(10);
        Car car=new Car("MH 1023");
        Assert.assertEquals(true, parkingLot.park(car) instanceof Token);
    }

    @Test(expected=SpaceNotAvailableException.class)
    public void shouldNotParkACar()
    {
        ParkingLot parkingLot=new ParkingLot(1);
        Car car=new Car("MH 1001");
        Car car1=new Car("MH 1007");
        parkingLot.park(car);
        parkingLot.park(car1);
    }

    @Test
    public void shouldUnParkACar(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("MH 1001");
        Token token=parkingLot.park(car);
        Assert.assertEquals(car, parkingLot.unParkCar(token));
    }


    @Test(expected= CarDoesNotExistException.class)
    public void shouldNotUnParkACar(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("MH 1001");
        Car car2 = new Car("MH 007");
        Token token=parkingLot.park(car);
        parkingLot.unParkCar(new Token());
    }
    @Test(expected = CarIsAlreadyParkedException.class)
    public void shouldNotParkACarIfDuplicateCar()
    {
        ParkingLot parkingLot=new ParkingLot(2);
        Car car=new Car("MH 1001");
        parkingLot.park(car);
        parkingLot.park(car);
    }




}
