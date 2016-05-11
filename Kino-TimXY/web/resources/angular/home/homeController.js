app.controller("HomeController", ['$scope','$filter', '$log', "HomeService", "$http", "$window",
    function ($scope, $filter, $log, HomeService, $http, $window) {

        $scope.movies = [];
        $scope.upcoming = [];
        $scope.screenings = [];
        $scope.movie1 = null;
        $scope.movie2 = null;
        $scope.movie3 = null;
        $scope.numberOfExtraSlides = 0;
        $scope.Math=Math;
        $scope.range = [];
        $scope.quantity = 4;
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
                $scope.screenings = data;
                angular.forEach($scope.screenings, function(screening) {
                    var tempDate = new Date();
                    tempDate = $filter('date')(tempDate, "dd-MM-yyyy");
                    if(screening.timeBegin.indexOf(tempDate) > -1 == true) {
                        var isPresent = false;
                        angular.forEach($scope.upcoming, function(upcomingMovie){
                            if(upcomingMovie.id == screening.movie.id) {
                                isPresent = true;
                            }
                        });
                        if(!isPresent) {
                            $scope.upcoming.push(screening.movie);
                            $log.log(screening.movie.name);
                        }
                    }
                });
                $scope.numberOfExtraSlides = Math.ceil($scope.upcoming.length/4) - 1;
                $log.log($scope.numberOfExtraSlides);
                var range = [];
                for(var l=1;l<=$scope.numberOfExtraSlides;l++) {
                    range.push(l);
                }
                $scope.range = range;
                //$log.log($scope.range);
            }, function() {
                    $window.location.replace('/403')}
            );
        }, function() {
            $window.location.replace('/403')}
            );
    }]);