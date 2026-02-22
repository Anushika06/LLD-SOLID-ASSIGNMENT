import java.util.*;

public class EligibilityEngine {

    private final List<EligibilityRule> rules;
    private final RuleInput config;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules,
                             RuleInput config,
                             FakeEligibilityStore store) {

        this.rules = rules;
        this.config = config;
        this.store = store;
    }

    public EligibilityEngineResult evaluate(StudentProfile student) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            rule.apply(student, config, reasons);
        }

        String status = reasons.isEmpty()
                ? "ELIGIBLE"
                : "NOT_ELIGIBLE";

        store.save(student.rollNo);

        return new EligibilityEngineResult(status, reasons);
    }
}