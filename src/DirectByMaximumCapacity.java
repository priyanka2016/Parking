import java.util.List;

/**
 * Created by sai on 14/08/2015.
 */
public class DirectByMaximumCapacity implements SearchCriteria {
    @Override
    public ParkingLot searchByCriteria(List<ParkingLot> availableParkingLots) {
        ParkingLot maximumParkingLot=availableParkingLots.get(0);
        for(ParkingLot p:availableParkingLots)
        {
            if(p.getTOTAL_CAPACITY()>maximumParkingLot.getTOTAL_CAPACITY())
                maximumParkingLot=p;
        }
        return maximumParkingLot;
    }
}
