package es.upm.miw.apaw_practice.domain.models.gym;

public class AthleteNameUpdating {
    private String oldName;
    private String newName;


    public AthleteNameUpdating() {
        //empty for framework
    }

    public AthleteNameUpdating(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override
    public String toString() {
        return "AthleteNameUpdating{" +
                "oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                '}';
    }
}
