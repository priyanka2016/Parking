import exceptions.CarDoesNotExistException;
import exceptions.CarIsAlreadyParkedException;
import exceptions.SpaceNotAvailableException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by sai on 12/08/2015.
 */
public class ParkingLot {

    private final int TOTAL_CAPACITY;
    private Map<Token,Car> parkedCars;
    private ParkingLotOwner owner;

    public ParkingLot(int totalCapacity,ParkingLotOwner owner) {

        TOTAL_CAPACITY=totalCapacity;
        parkedCars=new HashMap<Token, Car>();
        this.owner=owner;

    }

    public ParkingLot(int totalCapacity) {

        TOTAL_CAPACITY=totalCapacity;
        parkedCars=new HashMap<Token, Car>();

    }
    public Token park(Car car)
    {

        if(isParkingSpaceAvailable() && isNotParked(car)) {

            Token token=new Token();
            parkedCars.put(token,car);
            if(parkedCars.size()==TOTAL_CAPACITY)
                notifyOwner();
            return token;
        }

        if(!isParkingSpaceAvailable())
            throw new SpaceNotAvailableException("Ooops Space Not Available!!!");
        else
            throw new CarIsAlreadyParkedException();


    }

    private boolean isNotParked(Car car){
        if(parkedCars.containsValue(car))
            return false;
        else return true;
    }

    private boolean isParkingSpaceAvailable()
    {
        if(parkedCars.size()>=TOTAL_CAPACITY)
        {
            return false;
        }

            return true;

    }

    private void notifyOwner()
    {
        owner.getNotification();
    }

    public Car unParkCar(Token token){
        if(isThisCarParked(token)) {
            Car car = parkedCars.get(token);
            parkedCars.remove(token);
            return car;
        }
        else
             throw new CarDoesNotExistException("sorry car does not exist");
    }

    private boolean isThisCarParked(Token token){
        return parkedCars.containsKey(token);

    }

}
