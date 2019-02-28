package live.zema.app.signature;

public abstract class SimpleItem {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_ITEM = 1;

    public int getType() {
        return TYPE_HEADER;
    }
}

