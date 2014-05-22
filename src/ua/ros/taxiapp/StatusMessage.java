package ua.ros.taxiapp;

// A message returned by server as an answer to POST requests
public class StatusMessage {
	public static final String OK = "Successfull";
    public static final String FAIL = "An error occured";


    private String message;
    
    public StatusMessage() {
    }

    public StatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
    	return message;
    }
}
