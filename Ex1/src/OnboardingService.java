import java.util.*;

public class OnboardingService {

    private final InputParser parser;
    private final InputValidation validator;
    private final StudentRepository repository;
    private final OnboardingPrinter printer;

    public OnboardingService(InputParser parser,
                             InputValidation validator,
                             StudentRepository repository,
                             OnboardingPrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        StudentInput input = parser.parse(raw);

        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());

        StudentRecord rec = new StudentRecord(
                id,
                input.name,
                input.email,
                input.phone,
                input.program
        );

        repository.save(rec);

        printer.printSuccess(rec, repository.count());
    }
}