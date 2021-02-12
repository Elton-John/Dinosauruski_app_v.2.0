package pl.dinosaurus.dinosauruski.slot;

public enum DAY_OF_WEEK {

    MONDAY("poniedziałek"),
    TUESDAY("wtorek"),
    WEDNESDAY("środa"),
    THURSDAY("czwartek"),
    FRIDAY("piątek"),
    SATURDAY("sobota"),
    SUNDAY("niedziela");

    private String inPolish;


    DAY_OF_WEEK() {
    }

    DAY_OF_WEEK(String inPolish) {
        this.inPolish = inPolish;

    }

    public String getInPolish() {
        return inPolish;
    }

}

