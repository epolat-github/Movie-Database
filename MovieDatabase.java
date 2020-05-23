//-----------------------------------------------------
// Title:       Movie Database with BST
// Author:      Erinç Polat
//              Çağla Su Keşan
// ID:          13715949830
//              ~~other id~~
// Section:     01
// Assignment:  4
// Description: This class is the middleware between the 
//              client class and actual data structures.
//-----------------------------------------------------

public class MovieDatabase {

    // BST structure that holds the movies
    private MovieBST movies;

    public MovieDatabase() {
        this.movies = new MovieBST();
    }

    public void addMovie(String movieTitle, String directorFirstName, String directorLastName, int releaseDay,
            int releaseMonth, int releaseYear) {
        // --------------------------------------------------------
        // Summary: Creates a new movie with the given information and adds that to the
        // movies BST.
        // Precondition: Given movie shouldn't be added before the call of this method.
        // Given information should be valid.
        // Postcondition: if there is already a same movie, printed an error. Otherwise,
        // new movie is added and printed an info message.
        // --------------------------------------------------------

        // check whether the movie is already in the records
        Movie foundMovie = this.movies.findByTitle(movieTitle);
        if (foundMovie != null) {
            System.out.printf("ERROR: Movie %s already exists\n", movieTitle);
            return;
        }

        // create the new movie object
        Movie newMovie = new Movie(movieTitle, releaseDay, releaseMonth, releaseYear, directorFirstName,
                directorLastName);

        // add the new movie to the movies BST
        this.movies.add(newMovie);
        System.out.printf("INFO: Movie %s has been added\n", movieTitle);

    }

    public void removeMovie(String movieTitle) {
        // --------------------------------------------------------
        // Summary: Removes the corresponding movie from the movies BST.
        // Precondition: Given movie title should belong to an existing movie.
        // Postcondition: Removed if it exists. Otherwise, an error message is printed.
        // --------------------------------------------------------

        // movieTitle = movieTitle.strip();

        // check the movie existence
        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.printf("ERROR: Movie %s does not exist\n", movieTitle);
            return;
        }

        // remove the movie
        movies.remove(movieTitle);
        System.out.printf("INFO: Movie %s has been removed", movieTitle);

    }

    public void addActor(String movieTitle, String actorFirstName, String actorLastName, String actorRole) {
        // --------------------------------------------------------
        // Summary: Create a new cast object with the given information and add it to
        // the corresponding movie's cast BST.
        // Precondition: Given movie must exist.
        // Given cast member shouldn't be already added to the particular movie.
        // Given information must be valid.
        // Postcondition: if movie exists and cast hadn't been added before, it's added
        // and printed an info message. Otherwise, printed corresponding error messages.
        // --------------------------------------------------------

        // create the new cast member
        Cast newCast = new Cast(actorFirstName, actorLastName, actorRole);

        // check the movie
        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.printf("ERROR: Movie %s does not exist\n", movieTitle);
            return;
        }

        // check the cast
        Cast foundCast = foundMovie.findCast(newCast.getFullname());

        if (foundCast != null) {
            System.out.printf("ERROR: Cast %s already exists\n", foundCast.getFullname());
            return;
        }

        // add the new cast to the found movie
        foundMovie.addCast(newCast);

        System.out.printf("INFO: %s has been added to the movie %s\n", newCast.getFullname(), movieTitle);
    }

    public void removeActor(String movieTitle, String actorFirstName, String actorLastName, String roleTitle) {
        // --------------------------------------------------------
        // Summary: Removes the corresponding actor from the movie.
        // Precondition: Movie and actor should exist.
        // Postcondition: Cast member is removed if both it and the movie exist.
        // Otherwise, an error message is printed.
        // --------------------------------------------------------

        // check the film
        Movie foundMovie = movies.findByTitle(movieTitle);

        if (foundMovie == null) {
            System.out.printf("ERROR: Movie %s does not exist\n", movieTitle);
            return;
        }

        // check the cast
        String castFullName = actorFirstName + " " + actorLastName;

        Cast foundCast = foundMovie.findCast(castFullName);

        if (foundCast == null) {
            System.out.printf("ERROR: Cast %s does not exist\n", castFullName);
            return;
        }

        // delete the cast
        foundMovie.deleteCast(castFullName);

        System.out.printf("INFO: %s has been removed from the movie %s\n", castFullName, movieTitle);
    }

    public void showAllMovies() {
        // --------------------------------------------------------
        // Summary: Prints all movies.
        // Precondition: returned value of the initial print method shouldn't be empty.
        // Postcondition: Printed "---none---" if the corresponding BST is empty.
        // Otherwise, printed all movies in the correct format.
        // --------------------------------------------------------

        // Get the formatted list
        String movieList = this.movies.printAscendingByRelease();

        if (movieList == null) {
            System.out.println("---none---");
            return;
        }

        System.out.println(movieList);
    }

    public void showMovie(String movieTitle) {
        // --------------------------------------------------------
        // Summary: Prints one particular movie with its cast information.
        // Precondition: Movie should exist.
        // Postcondition: Printed if the movie exists with its cast. Otherwise,
        // "---none---" is printed.
        // --------------------------------------------------------

        // check the movie
        Movie foundMovie = this.movies.findByTitle(movieTitle);

        System.out.println(movieTitle);

        if (foundMovie == null) {
            System.out.println("---none---");
            return;
        }

        // get the cast list
        String castInfo = foundMovie.printCast();

        // print them in the correct format
        System.out.printf("%d/%d/%d\n", foundMovie.getReleaseDay(), foundMovie.getReleaseMonth(),
                foundMovie.getReleaseYear());

        // this conditional operation is necessary for the precise formatting. Newline
        // before "---none---"
        if (castInfo != "") {
            System.out.printf("%s %s", foundMovie.getDirectorName(), foundMovie.getDirectorSurname());
            System.out.printf("%s\n", foundMovie.printCast());
        } else {
            System.out.printf("%s %s\n", foundMovie.getDirectorName(), foundMovie.getDirectorSurname());
            System.out.println("---none---");
        }

    }

    public void showActorRoles(String actorFirstName, String actorLastName) {
        // --------------------------------------------------------
        // Summary: Prints a list of the given actor's movies in DESCENDING order.
        // Precondition: Actor should exist.
        // Postcondition: Printed if the actor exists. Otherwise, "---none---" is
        // printed.
        // --------------------------------------------------------

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
        // --------------------------------------------------------
        // Summary: Prints a list of the director's movies in DESCENDING order.
        // Precondition: Director should exist.
        // Postcondition: Printed if the director exists. Otherwise, an error message is
        // printed.
        // --------------------------------------------------------

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
        // --------------------------------------------------------
        // Summary: Prints the movies that have been shot in the given year in
        // DESCENDING
        // order.
        // Precondition: There should be movies in the given year.
        // Postcondition: Printed if any exists. Otherwise, an error message is printed.
        // --------------------------------------------------------

        String moviesOutput = this.movies.findByYear(releaseYear);

        System.out.println(releaseYear);

        if (moviesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(moviesOutput);

    }

    public void showMovies(int startYear, int endYear) {
        // --------------------------------------------------------
        // Summary: Prints the movies that have been shot between the given years in
        // DESCENDING order.
        // Precondition: There should be movies in between the given years.
        // Postcondition: Printed if any exists. Otherwise, an error message is printed.
        // --------------------------------------------------------

        String moviesOutput = this.movies.findByInterval(startYear, endYear);

        System.out.println(startYear + "-" + endYear);

        if (moviesOutput.equals("")) {
            System.out.println("---none---");
            return;
        }

        System.out.print(moviesOutput);

    }

}