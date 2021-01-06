package ca.georgebrown.comp3074.prototype2;

public class ScreenItem {

    String Title, Description;
    int screenImg;

    public ScreenItem(String tile, String description, int screenImg){
        Title = tile;
        Description = description;
        this.screenImg = screenImg;
    }

    public void  setTile(String tile){ Title = tile; }
    public void  setDescription(String description){ Description = description; }
    public void  setScreenImg(int img){ screenImg = img; }

    public String getTitle(){ return Title; }
    public String getDescription() { return Description; }
    public int    getScreenImg() { return screenImg; }
}
