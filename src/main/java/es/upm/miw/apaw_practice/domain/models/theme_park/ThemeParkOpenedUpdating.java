package es.upm.miw.apaw_practice.domain.models.theme_park;


public class ThemeParkOpenedUpdating {

    private String id;
    private Boolean opened;

    public ThemeParkOpenedUpdating() {
        //empty for framework
    }

    public ThemeParkOpenedUpdating(String id, Boolean opened) {
        this.id = id;
        this.opened = opened;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return "ThemeParkOpenedUpdating{" +
                "id=" + id +
                ", opened=" + opened +
                '}';
    }
}
