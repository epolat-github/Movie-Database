
public class Movie {
    private String title;
    private int releaseDay, releaseMonth, releaseYear;
    private String directorName, directorSurname;
    private CastBST casts;

    public Movie(String title, int releaseDay, int releaseMonth, int releaseYear, String directorName,
            String directorSurname) {
        this.title = title;
        this.releaseDay = releaseDay;
        this.releaseMonth = releaseMonth;
        this.releaseYear = releaseYear;
        this.directorName = directorName;
        this.directorSurname = directorSurname;

        this.casts = new CastBST();
    }

    public String getDirectorFullName() {
        return this.directorName + " " + this.directorSurname;
    }

    public void addCast(Cast newCast) {
        this.casts.add(newCast);
    }

    public void deleteCast(String castName) {
        this.casts.remove(castName);
    }

    public String printCast() {
        return this.casts.printCast();
    }

    public Cast findCast(String castName) {
        return this.casts.findByName(castName);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(int releaseDay) {
        this.releaseDay = releaseDay;
    }

    public int getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(int releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public void setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
    }

    public CastBST getCast() {
        return casts;
    }

    // public void setCast(CastBST casts) {
    //     this.casts = casts;
    // }

}