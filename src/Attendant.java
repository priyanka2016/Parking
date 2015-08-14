import exceptions.SpaceNotAvailableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sai on 13/08/2015.
 */
public class Attendant implements Subscriber {

    private List<ParkingLot> parkingLotsSpaceAvailable;
    private List<ParkingLot> parkingLotsFull;


     public Attendant() {
        parkingLotsFull = new ArrayList<ParkingLot>();
        parkingLotsSpaceAvailable = new ArrayList<ParkingLot>();


    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLotsSpaceAvailable.add(parkingLot);
        parkingLot.subscribe(NotificationType.AVAILABLE, this);
        parkingLot.subscribe(NotificationType.FULL,this);
    }

    @Override
    public void notifySubscriber(ParkingLot parkingLot) {
        if(parkingLotsFull.contains(parkingLot))
        {
            parkingLotsFull.remove(parkingLot);
            parkingLotsSpaceAvailable.add(parkingLot);
        }
        else
        {
            parkingLotsSpaceAvailable.remove(parkingLot);
            parkingLotsFull.add(parkingLot);
        }

    }

    public ParkingLot directCar(SearchCriteria criteria) {
        ParkingLot parkingLot;
        if (parkingLotsSpaceAvailable.size() > 0) {

            parkingLot = criteria.searchByCriteria(parkingLotsSpaceAvailable);
            return parkingLot;
            }


        else
            throw new SpaceNotAvailableException("Space is not available");
    }


}
