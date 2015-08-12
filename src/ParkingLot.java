import exceptions.SpaceNotAvailableException;

/**
 * Created by sai on 12/08/2015.
 */
public class ParkingLot {

    private final int TOTAL_CAPACITY;
    private int availableCapacity;

    public ParkingLot(int totalCapacity) {

        TOTAL_CAPACITY=totalCapacity;
        availableCapacity=TOTAL_CAPACITY;

    }

    public boolean park(Car car)
    {
        if(isParkingSpaceAvailable()) {
            availableCapacity--;
            return true;
        }
            throw new SpaceNotAvailableException("Ooops Space Not Available!!!");


    }

    private boolean isParkingSpaceAvailable()
    {
        if(TOTAL_CAPACITY-availableCapacity==TOTAL_CAPACITY)
        {
            return false;
        }
        return true;

    }

}
