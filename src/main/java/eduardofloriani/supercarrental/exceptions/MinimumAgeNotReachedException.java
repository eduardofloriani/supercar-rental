package eduardofloriani.supercarrental.exceptions;

public class MinimumAgeNotReachedException extends RuntimeException {

    public MinimumAgeNotReachedException() {
        super("Minimum age not reached to rent a supercar");
    }

}
