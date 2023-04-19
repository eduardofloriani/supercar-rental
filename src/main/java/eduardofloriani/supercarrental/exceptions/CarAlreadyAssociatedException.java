package eduardofloriani.supercarrental.exceptions;

import java.util.UUID;

public class CarAlreadyAssociatedException extends RuntimeException {

    public CarAlreadyAssociatedException(UUID id) {
        super("Car already associated with a rental. " +
                "Car id: " + id);
    }

}
