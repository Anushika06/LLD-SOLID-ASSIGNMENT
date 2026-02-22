import java.util.Map;

public class PricingInfo {
    private static final Map<Integer, RoomPricing> ROOMS = Map.of(
            LegacyRoomTypes.SINGLE, new SingleRoom(),
            LegacyRoomTypes.DOUBLE, new DoubleRoom(),
            LegacyRoomTypes.TRIPLE, new TripleRoom(),
            LegacyRoomTypes.DELUXE, new DefaultRoom()
    );

    private static final Map<AddOn, AddOnPricing> ADDONS = Map.of(
            AddOn.MESS, new MessAddOn(),
            AddOn.LAUNDRY, new LaundryAddOn(),
            AddOn.GYM, new GymAddOn()
    );

    public static RoomPricing resolveRoom(int type) {
        return ROOMS.getOrDefault(type, new DefaultRoom());
    }

    public static AddOnPricing resolveAddOn(AddOn addOn) {
        return ADDONS.get(addOn);
    }
}
