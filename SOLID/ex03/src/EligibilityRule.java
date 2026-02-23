import java.util.*;

public interface EligibilityRule {
    void apply(StudentProfile student,
               RuleInput config,
               List<String> reasons);
}