/**
 * Created by sai on 12/08/2015.
 */
import exceptions.CarDoesNotExistException;
import exceptions.CarIsAlreadyParkedException;
import exceptions.SpaceNotAvailableException;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;
public class ParkingTest {


    @Test
    public void shouldParkACar()
    {
        ParkingLot parkingLot=new ParkingLot(10);
        Car car=new Car("MH 1023");
        Assert.assertEquals(true, parkingLot.park(car) instanceof Token);
    }

    @Test
    public void shouldNotifySubsciberWhenParkingFull()
    {
        Subscriber owner=mock(ParkingLotOwner.class);
        ParkingLot parkingLot=new ParkingLot(1,owner);
        Subscriber agent=mock(FBIAgent.class);
        parkingLot.subscribe(NotificationType.EIGHTY_PERCENT_FULL, agent);
        Car car=new Car("MH 1023");
        parkingLot.park(car);
        verify(owner,times(1)).notifySubscriber();
    }


    @Test
    public void shouldNotNotifySubscriberWhenParkingNotFull()
    {
        Subscriber owner=mock(ParkingLotOwner.class);
        ParkingLot parkingLot=new ParkingLot(2,owner);

        parkingLot.subscribe(NotificationType.FULL, owner);
        Car car=new Car("MH 1023");
        parkingLot.park(car);
        verify(owner,never()).notifySubscriber();

    }

   /* @Test
    public void shouldNotifyOnceWhenFull(){
        ParkingLotOwner owner=mock(ParkingLotOwner.class);
        ParkingLot parkingLot=new ParkingLot(1,owner);

        try{
        Car car=new Car("MH 1023");
        parkingLot.park(car);
        Car car2=new Car("MH 1023");
        parkingLot.park(car2);
        }

        catch (SpaceNotAvailableException e){}

        finally {
            verify(owner, times(1)).getFullNotification();
        }
    }
*/
    @Test
    public void shouldNotifyOwnerWhenParkingSpaceAvailable(){
        Subscriber owner=mock(ParkingLotOwner.class);
        ParkingLot parkingLot=new ParkingLot(1,owner);
        Car car=new Car("MH 1023");
        Token token = parkingLot.park(car);
        parkingLot.unParkCar(token);
        verify((ParkingLotOwner)owner,times(2)).notifySubscriber();
    }

    @Test
    public void should_not_send_owner_space_available_notification_when_full(){
        Subscriber owner=mock(ParkingLotOwner.class);
        ParkingLot parkingLot=new ParkingLot(1,owner);
        Car car=new Car("MH 1023");
        parkingLot.park(car);

        verify((ParkingLotOwner)owner,times(1)).notifySubscriber();
    }


    @Test(expected=SpaceNotAvailableException.class)
    public void shouldNotParkACar()
    {
        Subscriber owner=new ParkingLotOwner();
        ParkingLot parkingLot=new ParkingLot(1,owner);
        Car car=new Car("MH 1001");
        Car car1=new Car("MH 1007");
        parkingLot.park(car);
        parkingLot.park(car1);
    }

    @Test
    public void shouldUnParkACar(){
        Subscriber owner=new ParkingLotOwner();
        ParkingLot parkingLot=new ParkingLot(1,owner);
        Car car = new Car("MH 1001");
        Token token=parkingLot.park(car);
        Assert.assertEquals(car, parkingLot.unParkCar(token));
    }


    @Test(expected= CarDoesNotExistException.class)
    public void shouldNotUnParkACar(){
        Subscriber owner=new ParkingLotOwner();
        ParkingLot parkingLot=new ParkingLot(1,owner);
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
