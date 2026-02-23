import java.util.Map;

public class PricingInfo {
    private static final Map<Integer, FeeComponent> ROOMS = Map.of(
            LegacyRoomTypes.SINGLE, new SingleRoom(),
            LegacyRoomTypes.DOUBLE, new DoubleRoom(),
            LegacyRoomTypes.TRIPLE, new TripleRoom()
    );

    private static final Map<AddOn, FeeComponent> ADDONS = Map.of(
            AddOn.MESS, new MessAddOn(),
            AddOn.LAUNDRY, new LaundryAddOn(),
            AddOn.GYM, new GymAddOn()
    );

    public static FeeComponent resolveRoom(int type) {
        return ROOMS.getOrDefault(type, new DefaultRoom());
    }

    public static FeeComponent resolveAddOn(AddOn addOn) {
        return ADDONS.get(addOn);
    }
}
