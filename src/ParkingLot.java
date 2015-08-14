import exceptions.CarDoesNotExistException;
import exceptions.CarIsAlreadyParkedException;
import exceptions.SpaceNotAvailableException;

import java.util.*;

/**
 * Created by sai on 12/08/2015.
 */
public class ParkingLot {

    private final int TOTAL_CAPACITY;
    private Map<Token,Car> parkedCars;
    private Subscriber parkingLotOwner;
    private boolean eightyPercentFullFlag=false;

    private Map<NotificationType,List<Subscriber>>  subscribers;

    public ParkingLot(int totalCapacity,Subscriber owner) {

        TOTAL_CAPACITY=totalCapacity;
        parkedCars=new HashMap<Token, Car>();
       subscribers=new HashMap<NotificationType, List<Subscriber>>();
        subscribers.put(NotificationType.AVAILABLE,new ArrayList<Subscriber>() );
        subscribers.put(NotificationType.EIGHTY_PERCENT_FULL,new ArrayList<Subscriber>() );
        subscribers.put(NotificationType.FULL,new ArrayList<Subscriber>() );
        this.parkingLotOwner = owner;
        subscribe(NotificationType.AVAILABLE,owner);
        subscribe(NotificationType.FULL,owner);

    }

    public ParkingLot(int totalCapacity) {

        TOTAL_CAPACITY=totalCapacity;
        parkedCars=new HashMap<Token, Car>();

    }

    public int getTOTAL_CAPACITY(){
        return TOTAL_CAPACITY;
    }

    public void subscribe(NotificationType type,Subscriber subscriber)
    {
       if( subscribers.containsKey(type))
                subscribers.get(type).add(subscriber);

        else{
           subscribers.put(type,new ArrayList<Subscriber>());
           subscribers.get(type).add(subscriber);
       }
    }


    public void unSubscribe(NotificationType type,Subscriber subscriber)
    {
        if( subscribers.containsKey(type))
        {
            for(Subscriber s: subscribers.get(type)){
                if(s==subscriber)
                    subscribers.get(type).remove(s);
            }
        }
    }
    public Token park(Car car)
    {


        if(isParkingSpaceAvailable() && isNotParked(car)) {

            Token token=new Token();
            parkedCars.put(token,car);
            if(parkedCars.size()==TOTAL_CAPACITY)//WHEN pARKING FULL
                 notifySubscriber(NotificationType.FULL);
            if(isParkingEightyPercentFull()&& eightyPercentFullFlag ==false)
            {
                eightyPercentFullFlag =true;
                notifySubscriber(NotificationType.EIGHTY_PERCENT_FULL);
            }
            return token;
        }

        if(!isParkingSpaceAvailable())
            throw new SpaceNotAvailableException("Ooops Space Not Available!!!");
        else
            throw new CarIsAlreadyParkedException();
    }
    private boolean isParkingEightyPercentFull()
    {
        if(parkedCars.size()>=.8*TOTAL_CAPACITY)
        {
            return true;
        }
        return false;
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

    private void notifySubscriber(NotificationType type)
    {
        for(NotificationType key : subscribers.keySet()) {
            if(key==type)
            {
                for(Subscriber s:subscribers.get(key))
                {
                    s.notifySubscriber(this);
                }
            }
        }
    }

    public Car unParkCar(Token token){
        if(isThisCarParked(token)) {
            Car car = parkedCars.get(token);
            parkedCars.remove(token);
            if(parkedCars.size()==TOTAL_CAPACITY-1)//AVAILABLE AFTER FULL PARKING
                notifySubscriber(NotificationType.AVAILABLE);
            return car;
        }
        else
             throw new CarDoesNotExistException("sorry car does not exist");
    }

    private boolean isThisCarParked(Token token){
        return parkedCars.containsKey(token);

    }
    public int getAvailbleSpace(){
        return TOTAL_CAPACITY - parkedCars.size();
    }

}
