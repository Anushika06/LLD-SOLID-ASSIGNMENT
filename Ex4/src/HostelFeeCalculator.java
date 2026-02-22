public class HostelFeeCalculator {

    private static final Money FIXED_DEPOSIT = new Money(5000.0);

    public FeeResult calculate(BookingRequest request) {

        RoomPricing room = PricingInfo.resolveRoom(request.roomType);

        Money monthly = room.monthly();

        for (AddOn addOn : request.addOns) {
            AddOnPricing addon = PricingInfo.resolveAddOn(addOn);

            if (addon != null)
                monthly = monthly.plus(addon.monthly());
        }

        return new FeeResult(monthly, FIXED_DEPOSIT);
    }
}