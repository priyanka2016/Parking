/**
 * Created by sai on 12/08/2015.
 */
public class ParkingSlot {

     private String parkingSlotNo;

    public ParkingSlot(String parkingSlotNo) {
        this.parkingSlotNo = parkingSlotNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingSlot that = (ParkingSlot) o;

        return !(parkingSlotNo != null ? !parkingSlotNo.equals(that.parkingSlotNo) : that.parkingSlotNo != null);

    }

    @Override
    public int hashCode() {
        return parkingSlotNo != null ? parkingSlotNo.hashCode() : 0;
    }
}
