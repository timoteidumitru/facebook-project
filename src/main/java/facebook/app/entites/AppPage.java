package facebook.app.entites;

public class AppPage {

    public String pageName;

    private String pageAdmin;

    private Integer pageLikes;

    private String pageBio;

    private static void createNewPost() {

    }

    public AppPage(String pageName, String pageAdmin, Integer pageLikes, String pageBio) {
        this.pageName = pageName;
        this.pageAdmin = pageAdmin;
        this.pageLikes = pageLikes;
        this.pageBio = pageBio;
    }


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageAdmin() {
        return pageAdmin;
    }

    public void setPageAdmin(String pageAdmin) {
        this.pageAdmin = pageAdmin;
    }

    public Integer getPageLikes() {
        return pageLikes;
    }

    public void setPageLikes(Integer pageLikes) {
        this.pageLikes = pageLikes;
    }

    public String getPageBio() {
        return pageBio;
    }

    public void setPageBio(String pageBio) {
        this.pageBio = pageBio;
    }
}
