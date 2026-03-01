public class ClassroomController {

    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {

        // Projector
        SmartClassroomDevice projectorPower =
                reg.getFirstOfType(SmartClassroomDevice.class);
        projectorPower.powerOn();

        InputConnector projectorInput =
                reg.getFirstOfType(InputConnector.class);
        projectorInput.connectInput("HDMI-1");

        // Lights
        BrightnessController lights =
                reg.getFirstOfType(BrightnessController.class);
        lights.setBrightness(60);

        // AC
        TemperatureController ac =
                reg.getFirstOfType(TemperatureController.class);
        ac.setTemperatureC(24);

        // Attendance
        AttendanceScannable scanner =
                reg.getFirstOfType(AttendanceScannable.class);
        System.out.println("Attendance scanned: present="
                + scanner.scanAttendance());
    }

    public void endClass() {

        System.out.println("Shutdown sequence:");

        reg.getAllOfType(SmartClassroomDevice.class).forEach(device -> {
        device.powerOff();
    });
    }
}