package github.changweitu.com.an.model;

/**
 * Created by vale on 7/14/16.
 */

public class NetworkEvent {
    public static final int AVALIABLE = 1;
    public static final int UNAVALIABLE = -1;
    private int type;

    public NetworkEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
