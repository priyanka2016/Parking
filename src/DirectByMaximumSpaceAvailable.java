import java.util.Comparator;
import java.util.List;

/**
 * Created by sai on 14/08/2015.
 */
public class DirectByMaximumSpaceAvailable implements SearchCriteria {

    @Override
    public ParkingLot searchByCriteria(List<ParkingLot> availableParkingLots) {
        ParkingLot maximumParkingLot=availableParkingLots.get(0);
        for(ParkingLot p:availableParkingLots)
        {
            if(p.getAvailbleSpace()>maximumParkingLot.getAvailbleSpace())
                maximumParkingLot=p;
        }
        return maximumParkingLot;
    }
}
