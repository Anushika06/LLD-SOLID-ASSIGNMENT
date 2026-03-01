import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceRegistry {

    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) {
        devices.add(d);
    }

    public <T> T getFirstOfType(Class<T> type) {
        for (Object d : devices) {
            if (type.isInstance(d)) {
                return type.cast(d);
            }
        }
        throw new IllegalStateException("Missing: " + type.getSimpleName());
    }
    public <T> List<T> getAllOfType(Class<T> type) {
        return devices.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
}