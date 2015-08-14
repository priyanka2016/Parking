import java.util.List;

/**
 * Created by sai on 14/08/2015.
 */
public class DirectByFirstAvailableParkingLot implements SearchCriteria {
    @Override
    public ParkingLot searchByCriteria(List<ParkingLot> availableParkingLots) {
        return availableParkingLots.get(0);
    }
}
