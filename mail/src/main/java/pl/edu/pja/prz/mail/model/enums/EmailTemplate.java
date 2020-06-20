package pl.edu.pja.prz.mail.model.enums;

public enum EmailTemplate {
    BASE_TEMPLATE("base-email"),
    ACTIVATE_TEMPLATE("activate-email");

    private final String name;

    EmailTemplate(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
