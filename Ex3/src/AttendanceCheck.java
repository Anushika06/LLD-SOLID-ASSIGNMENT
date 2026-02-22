import java.util.*;

public class AttendanceCheck implements EligibilityRule {

    @Override
    public void apply(StudentProfile s,
                      RuleInput config,
                      List<String> reasons) {

        if (s.attendancePct < config.minAttendance)
            reasons.add("attendance below 75");
    }
}