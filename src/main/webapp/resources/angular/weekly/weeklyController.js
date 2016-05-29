app.controller("WeeklyController", ['$scope', '$filter', '$log', "WeeklyService", "$window", '$http',
    function ($scope, $filter, $log, WeeklyService, $window, $http) {
        $scope.movies = {
            'monday':null,
            'tuesday':null,
            'wednesday':null,
            'thursday':null,
            'friday':null,
            'saturday':null,
            'sunday':null
        };

        WeeklyService.getWeeklyMovies().then(
        function(data){
            $scope.movies.sunday = data[0];
            $scope.loadMovieInfo($scope.movies.sunday);

            $scope.movies.monday = data[1];
            $scope.loadMovieInfo($scope.movies.monday);

            $scope.movies.tuesday = data[2];
            $scope.loadMovieInfo($scope.movies.tuesday);

            $scope.movies.wednesday = data[3];
            $scope.loadMovieInfo($scope.movies.wednesday);

            $scope.movies.thursday = data[4];
            $scope.loadMovieInfo($scope.movies.thursday);

            $scope.movies.friday = data[5];
            $scope.loadMovieInfo($scope.movies.friday);

            $scope.movies.saturday = data[6];
            $scope.loadMovieInfo($scope.movies.saturday);
        });
        $scope.loadMovieInfo = function(movies){
            angular.forEach(movies, function(movie){
                $http.get("http://www.omdbapi.com/?t=" + movie.name + "&tomatoes=true&plot=full")
                .then(function (response) {
                    movie.imdb = response.data.imdbRating;
                    movie.tomatoes = response.data.tomatoRating;
                    movie.poster = response.data.Poster;
                    movie.linkToImdb= "http://www.imdb.com/title/" + response.data.imdbID + "/";
                    movie.linkToTomatoes = response.data.tomatoURL;
                });
            });
        }

    }]);