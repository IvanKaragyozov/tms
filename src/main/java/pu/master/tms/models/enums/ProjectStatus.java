package pu.master.tms.models.enums;


public enum ProjectStatus {
    DUE("Due"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    private final String name;

    ProjectStatus(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
