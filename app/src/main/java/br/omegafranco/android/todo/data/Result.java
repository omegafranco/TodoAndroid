package br.omegafranco.android.todo.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        }
        else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    public Boolean getProcessed() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return success.getProcessed();
        }
        else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return error.getProcessed();
        }
        return false;
    }

    public void setProcessed(final Boolean processed) {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            success.setProcessed(processed);
        }
        else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            error.setProcessed(processed);
        }
    }


    // Success sub-class
    public final static class Success<T> extends Result {
        private T data;
        private Boolean isProcessed = new Boolean(false);

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public Boolean getProcessed() {
            return isProcessed;
        }

        public void setProcessed(final Boolean processed) {
            isProcessed = processed;
        }

    }

    // Error sub-class
    public final static class Error extends Result {
        private Exception error;
        private Boolean isProcessed = new Boolean(false);

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }

        public Boolean getProcessed() {
            return isProcessed;
        }

        public void setProcessed(final Boolean processed) {
            isProcessed = processed;
        }
    }
}