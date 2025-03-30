package pu.master.tms.models.enums;


public enum TaskStatus {
    DUE("Due"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    private final String name;

    TaskStatus(final String name) {
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
