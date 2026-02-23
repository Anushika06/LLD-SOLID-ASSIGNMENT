import java.util.*;

public class DisciplinaryCheck implements EligibilityRule {

    @Override
    public void apply(StudentProfile s,
                      RuleInput config,
                      List<String> reasons) {

        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            reasons.add("disciplinary flag present");
        }
    }
}