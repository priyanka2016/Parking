/**
 * Created by sai on 12/08/2015.
 */
import exceptions.SpaceNotAvailableException;
import org.junit.Assert;
import org.junit.Test;
public class ParkingTest {


    @Test
    public void shouldParkACar()
    {
        ParkingLot parkingLot=new ParkingLot(10);
        Car car=new Car("MH 1023");
        Assert.assertTrue(parkingLot.park(car));
    }

    @Test(expected=SpaceNotAvailableException.class)
    public void shouldNotParkACar()
    {
        ParkingLot parkingLot=new ParkingLot(1);
        Car car=new Car("MH 1001");
        parkingLot.park(car);
       parkingLot.park(car);
    }


}
