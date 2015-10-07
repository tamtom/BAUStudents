package itdeveapps.baustudents;

/**
 * Created by omar on 10/7/2015.
 */
public class MyNote {
    private String content;
    private String title;
    private String RecoredDate;
    private int itemId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecoredDate() {
        return RecoredDate;
    }

    public void setRecoredDate(String recoredDate) {
        RecoredDate = recoredDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
