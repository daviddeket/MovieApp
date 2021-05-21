package com.example.movieApp.models

import com.example.movieApp.R

data class MovieStore(val myMovies: MutableList<Movie> = mutableListOf()){

    val defaultMovies = exampleMovies

    fun findMovieByUUID(uuid: String): Movie? {
        return defaultMovies.find { movie -> movie.id.toString() == uuid }
    }

    companion object{
        val exampleMovies: MutableList<Movie> = createStaticMovieList()

        private fun createStaticMovieList(): MutableList<Movie> {
            val list: MutableList<Movie> = mutableListOf()


            val movie1 = Movie("The Queen's Gambit", "The Queen\'s Gambit follows the life of an orphan chess prodigy, Elizabeth Harmon, during her quest to become the world\'s greatest chess player while struggling with emotional problems and drug and alcohol dependency. The title of the series refers to a chess opening of the same name. The story begins in the mid-1950s and proceeds into the 1960s.[5]\n\n" + "The story begins in Lexington, Kentucky, where a nine-year-old Beth, having lost her mother in a car crash, is taken to an orphanage where she is taught chess by the building\'s custodian, Mr. Shaibel. As was common during the 1950s, the orphanage dispenses daily tranquilizer pills to the girls,[6][7] which turns into an addiction for Beth. She quickly becomes a strong chess player due to her visualization skills, which are enhanced by the tranquilizers. A few years later, Beth is adopted by Alma Wheatley and her husband from Lexington. As she adjusts to her new home, Beth enters a chess tournament and wins despite having no prior experience in competitive chess. She develops friendships with several people, including former Kentucky State Champion Harry Beltik, United States National Champion Benny Watts, and journalist and fellow player D.L. Townes. As Beth rises to the top of the chess world and reaps the financial benefits of her success, her drug and alcohol dependency becomes worse.")
            movie1.id = 1
            movie1.actors.addAll(listOf("Anya Taylor-Joy", "Chloe Pirrie"))
            movie1.genres = listOf("Drama", "Sport")
            movie1.creators.addAll(listOf("Scott Frank", "Alan Scott"))
            movie1.rating = 4.0F
            movie1.imageId = R.drawable.queensgambit

            val movie2 = Movie("Interstellar", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.")
            movie2.id = 2
            movie2.actors.addAll(listOf("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"))
            movie2.genres = listOf("Drama", "SciFi", "Adventure")
            movie2.creators.add("Christopher Nolan")
            movie2.rating = 5.0F
            movie2.imageId = R.drawable.interstellar

            val movie3 = Movie("Avengers: Infinity War",  "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.")
            movie3.id = 3
            movie3.actors.addAll(listOf("Robert Downey Jr.", "Chris Hemsworth", "Mark Ruffalo"))
            movie3.genres = listOf("Fiction", "Superheros", "Drama")
            movie3.creators.add("Christoper Markus, Stephen McFeely, Stan Lee")
            movie3.rating = 5.0F
            movie3.imageId = R.drawable.avengers_infinity_war

            list.add(movie1)
            list.add(movie2)
            list.add(movie3)
            
            return list
        }
    }
}