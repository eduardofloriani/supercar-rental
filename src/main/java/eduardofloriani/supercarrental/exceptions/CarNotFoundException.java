package eduardofloriani.supercarrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(UUID id) {
        super("Car not found with id: " + id);
    }

}
