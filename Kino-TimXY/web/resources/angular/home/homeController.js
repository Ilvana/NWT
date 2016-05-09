app.controller("HomeController", ['$scope','$filter', '$log', "HomeService", "$http", "$window",
    function ($scope, $filter, $log, HomeService, $http, $window) {

        $scope.movies = [];
        $scope.movie1 = null;
        $scope.movie2 = null;
        $scope.movie3 = null;
        HomeService.getAllMovies().then(function (data) {
            $scope.movies = data;
            $scope.extraData = [];

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
                                })
                                $scope.movie1 = $scope.movies[0];
                                $scope.movie2 = $scope.movies[1];
                                $scope.movie3 = $scope.movies[2];
                                //$log.log($scope.movie1.name);
                                //$log.log($scope.movie2.name);
                                //$log.log($scope.movie3.name);
                            }
                        });
                })(i);
            }
        }, function() {
            $window.location.replace('/403')}
            );
    }]);