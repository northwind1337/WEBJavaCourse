package dao_layer;


public class DaoExceptionLayer extends Exception {
    public DaoExceptionLayer() {
    }

    public DaoExceptionLayer(String message) {
        super(message);
    }

    public DaoExceptionLayer(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoExceptionLayer(Throwable cause) {
        super(cause);
    }
}
