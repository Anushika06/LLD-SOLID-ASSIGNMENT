import java.util.*;

public class Demo03 {

    public static void main(String[] args) {

        System.out.println("=== Placement Eligibility ===");

        RuleInput config = new RuleInput();

        List<EligibilityRule> rules = List.of(
                new CGRCheck(),
                new AttendanceCheck(),
                new CreditsCheck(),
                new DisciplinaryCheck()
        );

        EligibilityEngine engine = new EligibilityEngine(
                rules,
                config,
                new FakeEligibilityStore()
        );


        StudentProfile ayaan = new StudentProfile(
                "23BCS1001",
                "Ayaan",
                8.10,
                72,
                18,
                LegacyFlags.NONE
        );

        EligibilityEngineResult result1 = engine.evaluate(ayaan);
        ReportPrinter.print(ayaan, result1);

        System.out.println();   


        StudentProfile rhea = new StudentProfile(
                "23BCS1025",
                "Rhea",
                8.75,
                82,
                24,
                LegacyFlags.NONE
        );

        EligibilityEngineResult result2 = engine.evaluate(rhea);
        ReportPrinter.print(rhea, result2);
    }
}