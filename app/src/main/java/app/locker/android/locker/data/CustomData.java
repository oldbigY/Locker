package app.locker.android.locker.data;

/**
 * Created by john on 2015/8/26.
 */
public class CustomData {

    public String name="";
    public int iconId=0;

    public CustomData(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
