import java.util.*;

public class HostelFeeCalculator {

    public FeeResult calculate(BookingRequest request,
                               List<FeeComponent> extras) {

        FeeComponent room = PricingInfo.resolveRoom(request.roomType);

        Money monthly = room.monthly();
        Money deposit = room.deposit();

        for (AddOn addOn : request.addOns) {
            FeeComponent addon = PricingInfo.resolveAddOn(addOn);

            if (addon != null)
                monthly = monthly.plus(addon.monthly());
        }

        for (FeeComponent extra : extras) {
            monthly = monthly.plus(extra.monthly());
            deposit = deposit.plus(extra.deposit());
        }

        return new FeeResult(monthly, deposit);
    }
}