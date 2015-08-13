/**
 * Created by sai on 13/08/2015.
 */
public class ParkingLotOwner implements Subscriber {
    public void getFullNotification(){

        System.out.println("put board");

    }

    public void getSpaceAvailableNotification(){

        System.out.println("remove full board");

    }
}
