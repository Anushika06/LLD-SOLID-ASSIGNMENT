import java.util.*;

public class CGRCheck implements EligibilityRule {

    @Override
    public void apply(StudentProfile s,
                      RuleInput config,
                      List<String> reasons) {

        if (s.cgr < config.minCgr)
            reasons.add("cgr below threshold");
    }
}