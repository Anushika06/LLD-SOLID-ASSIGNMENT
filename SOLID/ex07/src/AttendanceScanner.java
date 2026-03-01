public class AttendanceScanner implements 
        SmartClassroomDevice,
        AttendanceScannable {

    @Override
    public void powerOn() {
        // no output required
    }

    @Override
    public void powerOff() {
        // no output required
    }

    @Override
    public int scanAttendance() {
        return 3;
    }
}