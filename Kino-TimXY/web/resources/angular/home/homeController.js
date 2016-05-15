app.controller("HomeController", ['$scope','$filter', '$log', "HomeService", "$http", "$window",
    function ($scope, $filter, $log, HomeService, $http, $window) {

        $scope.openNewTab = function (link) {
            $log.log(link);
            //$window.open(link);
        };

        $scope.movies = [];
        $scope.announcements = [];
        $scope.todaysMovies = [];
        $scope.screenings = [];
        $scope.movie1 = null;
        $scope.movie2 = null;
        $scope.movie3 = null;
        $scope.numberOfExtraSlides = 0;
        $scope.numberOfMovieRows = 0;
        $scope.Math=Math;
        $scope.range = [];
        $scope.rowsRange = [];
        $scope.quantity = 4;
        //get all movies, sort them by imdb rating and pick out top three -> FIRST SLIDER
        HomeService.getAllMovies().then(function (data) {
            $scope.movies = data;

            for (var i=0; i<$scope.movies.length; i++) {
                (function(i) {
                    $scope.search = $scope.movies[i].name;
                    $scope.details = "";

                    $http.get("http://www.omdbapi.com/?t=" + $scope.search + "&tomatoes=true&plot=full")
                        .then(function (response) {
                            $scope.details = response.data;
                            $scope.movies[i].imdb = $scope.details.imdbRating;
                            $scope.movies[i].tomatoes = $scope.details.tomatoRating;
                            $scope.movies[i].poster = $scope.details.Poster;
                            $scope.movies[i].linkToImdb= "http://www.imdb.com/title/" + $scope.details.imdbID + "/";
                            $scope.movies[i].linkToTomatoes = $scope.details.tomatoURL;

                            if(i == $scope.movies.length - 1) {
                                $scope.movies.sort(function(a, b) {
                                    return parseFloat(b.imdb) - parseFloat(a.imdb);
                                });
                                $scope.movie1 = $scope.movies[0];
                                $scope.movie2 = $scope.movies[1];
                                $scope.movie3 = $scope.movies[2];
                            }
                        });
                })(i);
            }

            HomeService.getAllScreenings().then(function(data) {
                //get all screenings and pick out movies without screenings -> SECOND SLIDER
                $scope.screenings = data;
                angular.forEach($scope.movies, function(movieForInfo) {
                    var isScheduled = false;
                    angular.forEach($scope.screenings, function(screening){
                        if(screening.movie.id == movieForInfo.id) {
                            isScheduled = true;
                            var newDate =new Date(screening.timeBegin);
                            var tempDate = $filter('date')(newDate, "HH:mm");
                            if(movieForInfo.screeningList != null) {
                                movieForInfo.screeningList = movieForInfo.screeningList + "," + tempDate;
                            }
                            else {
                                movieForInfo.screeningList = tempDate;
                            }
                        }
                    });
                    if(!isScheduled) {
                        var isAdded = false;
                        angular.forEach($scope.announcements, function(upcomingMovie){
                            if(upcomingMovie.id == movieForInfo.id) {
                                isAdded = true;
                            }
                        });
                        if(!isAdded) {
                            $scope.announcements.push(movieForInfo);
                        }
                    }
                });
                $scope.numberOfExtraSlides = Math.ceil($scope.announcements.length/4) - 1;
                var range = [];
                for(var l=1;l<=$scope.numberOfExtraSlides;l++) {
                    range.push(l);
                }
                $scope.range = range;
                //iterate through screenings and get movies that have screenings today -> TILE LAYOUT
                angular.forEach($scope.screenings, function(screening) {
                    var tempDate = new Date();
                    tempDate = $filter('date')(tempDate, "dd-MM-yyyy");
                    if(screening.timeBegin.indexOf(tempDate) > -1 == true) {
                        var isPresent = false;
                        angular.forEach($scope.todaysMovies, function(todayMovie){
                            if(todayMovie.id == screening.movie.id) {
                                isPresent = true;
                            }
                        });
                        if(!isPresent) {
                            angular.forEach($scope.movies, function(movieForInfo) {
                                if(movieForInfo.id == screening.movie.id) {
                                    screening.movie.imdb = movieForInfo.imdb;
                                    screening.movie.tomatoes = movieForInfo.tomatoes;
                                    screening.movie.poster = movieForInfo.poster;
                                    screening.movie.screeningList = movieForInfo.screeningList;
                                    screening.movie.linkToTomatoes = movieForInfo.linkToTomatoes;
                                    screening.movie.linkToImdb = movieForInfo.linkToImdb;
                                }
                            });
                            $scope.todaysMovies.push(screening.movie);
                            $log.log(screening.movie.name);
                        }
                    }
                });
                $scope.numberOfMovieRows = Math.ceil($scope.todaysMovies.length/4);
                $log.log($scope.numberOfMovieRows);
                var rowsRange = [];
                for(var l=1;l<=$scope.numberOfMovieRows;l++) {
                    rowsRange.push(l);
                }
                $scope.rowsRange = rowsRange;
                $log.log($scope.rowsRange);
            }, function() {
                    $window.location.replace('/403')}
            );
        }, function() {
            $window.location.replace('/403')}
            );
    }]);