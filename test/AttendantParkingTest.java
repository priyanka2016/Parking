/**
 * Created by sai on 13/08/2015.
 */
import exceptions.CarDoesNotExistException;
import exceptions.CarIsAlreadyParkedException;
import exceptions.SpaceNotAvailableException;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;
public class AttendantParkingTest {


    @Test
    public void shouldDirectToFirstAvailableParkingLot () {
        Subscriber attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(2,mock(ParkingLotOwner.class));
        ParkingLot parkingLot2 = new ParkingLot(1,mock(ParkingLotOwner.class));
        ((Attendant)attendant).addParkingLot(parkingLot);
        ((Attendant)attendant).addParkingLot(parkingLot2);
        SearchCriteria criteria = new DirectByFirstAvailableParkingLot();
        Assert.assertEquals(parkingLot,((Attendant) attendant).directCar(criteria));
    }

    @Test
    public void shouldDirectToMaximumCapacityParkingLot () {
        Subscriber attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(2,mock(ParkingLotOwner.class));
        ParkingLot parkingLot2 = new ParkingLot(1,mock(ParkingLotOwner.class));
        ((Attendant)attendant).addParkingLot(parkingLot);
        ((Attendant)attendant).addParkingLot(parkingLot2);
        Car car = mock(Car.class);
        SearchCriteria criteria = new DirectByMaximumCapacity();
        Assert.assertEquals(parkingLot, ((Attendant) attendant).directCar(criteria));
    }

    @Test
    public void shouldDirectToMaximumSpaceAvailableParkingLot () {
        Subscriber attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(2,mock(ParkingLotOwner.class));
        ParkingLot parkingLot2 = new ParkingLot(1,mock(ParkingLotOwner.class));
        ((Attendant)attendant).addParkingLot(parkingLot);
        ((Attendant)attendant).addParkingLot(parkingLot2);
       // Car car = mock(Car.class);
        SearchCriteria criteria = new DirectByMaximumSpaceAvailable();
        Assert.assertEquals(parkingLot, ((Attendant) attendant).directCar(criteria));
    }

   }
