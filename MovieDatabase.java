public class MovieDatabase {

    private MovieBST movies;

    public MovieDatabase() {
        this.movies = new MovieBST();
    }

    public void addMovie(String movieTitle, String directorFirstName, String directorLastName, int releaseDay,
            int releaseMonth, int releaseYear) {

        // istenen film zaten var mı diye check et
        Movie foundMovie = this.movies.findByTitle(movieTitle);
        if (foundMovie != null) {
            System.out.printf("ERROR: Movie %s already exists\n", movieTitle);
            return;
        }

        Movie newMovie = new Movie(movieTitle, releaseDay, releaseMonth, releaseYear, directorFirstName,
                directorLastName);

        this.movies.add(newMovie);
        System.out.printf("INFO: Movie %s has been added\n", movieTitle);

    }

    public void removeMovie(String movieTitle) {

        // diret remove'a geçiş yapılabilir
        movieTitle = movieTitle.strip();

        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.println("Movie not found");
            return;
        }

        movies.remove(movieTitle);
        System.out.printf("INFO: Movie %s has been removed", movieTitle);

    }

    public void addActor(String movieTitle, String actorFirstName, String actorLastName, String actorRole) {
        Cast newCast = new Cast(actorFirstName, actorLastName, actorRole);

        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.printf("ERROR: Movie %s does not exist\n", movieTitle);
            return;
        }

        Cast foundCast = foundMovie.findCast(newCast.getFullname());
        if (foundCast != null) {
            System.out.printf("ERROR: Cast %s already exists\n", foundCast.getFullname());
            return;
        }

        foundMovie.addCast(newCast);
        System.out.printf("INFO: %s has been added to the movie %s\n", newCast.getFullname(), movieTitle);

        // movies.add(foundMovie);

    }

    public void removeActor(String movieTitle, String actorFirstName, String actorLastName, String roleTitle) {

        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.printf("ERROR: Movie %s does not exist\n", movieTitle);
            return;
        }

        String castFullName = actorFirstName + " " + actorLastName;

        Cast foundCast = foundMovie.findCast(castFullName);

        if (foundCast == null) {
            System.out.printf("ERROR: Cast %s does not exist\n", castFullName);
            return;
        }

        foundMovie.deleteCast(castFullName);

        System.out.printf("INFO: %s has been removed from the movie %s\n", castFullName, movieTitle);
    }

    public void showAllMovies() {
        String movieList = this.movies.printAscendingByRelease();

        if (movieList == null) {
            System.out.println("---none---");
            return;
        }

        System.out.println(movieList);
    }

    public void showMovie(String movieTitle) {
        Movie foundMovie = this.movies.findByTitle(movieTitle);

        System.out.println(movieTitle);

        if (foundMovie == null) {
            System.out.println("---none---");
            return;
        }

        String castInfo = foundMovie.printCast();

        System.out.printf("%d/%d/%d\n", foundMovie.getReleaseDay(), foundMovie.getReleaseMonth(),
                foundMovie.getReleaseYear());

        if (castInfo != null) {
            System.out.printf("%s %s", foundMovie.getDirectorName(), foundMovie.getDirectorSurname());
            System.out.printf("%s\n", foundMovie.printCast());
        } else {
            System.out.printf("%s %s\n", foundMovie.getDirectorName(), foundMovie.getDirectorSurname());
            System.out.println("---none---");
        }

    }

    public void showActorRoles(String actorFirstName, String actorLastName) {
        String castFullName = actorFirstName + " " + actorLastName;

        String rolesOutput = this.movies.findActorRoles(castFullName);

        System.out.println(castFullName);

        if (rolesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(rolesOutput);
    }

    public void showDirectorMovies(String directorFirstName, String directorLastName) {
        String directorFullName = directorFirstName + " " + directorLastName;

        String moviesOutput = this.movies.findDirectorMovies(directorFullName);

        System.out.println(directorFullName);

        if (moviesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(moviesOutput);
    }

    public void showMovies(int releaseYear) {
        String moviesOutput = this.movies.findByYear(releaseYear);

        System.out.println(releaseYear);

        if (moviesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(moviesOutput);

    }

    public void showMovies(int startYear, int endYear) {
        String moviesOutput = this.movies.findByInterval(startYear, endYear);

        System.out.println(startYear + "-" + endYear);

        if (moviesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(moviesOutput);

    }

}