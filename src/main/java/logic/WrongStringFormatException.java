package logic;

class WrongStringFormatException extends Exception {
    WrongStringFormatException() {
        super();
    }

    WrongStringFormatException(String message) {
        super(message);
    }

    WrongStringFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    WrongStringFormatException(Throwable cause) {
        super(cause);
    }
}
