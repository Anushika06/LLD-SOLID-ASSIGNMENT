import java.util.*;

public class CreditsCheck implements EligibilityRule {

    @Override
    public void apply(StudentProfile s,
                      RuleInput config,
                      List<String> reasons) {

        if (s.earnedCredits < config.minCredits)
            reasons.add("insufficient credits");
    }
}