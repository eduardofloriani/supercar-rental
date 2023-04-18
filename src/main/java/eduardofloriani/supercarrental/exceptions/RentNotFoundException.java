package eduardofloriani.supercarrental.exceptions;

import java.util.UUID;

public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException(UUID id) {
        super("Rent not found with id: " + id);
    }

}
