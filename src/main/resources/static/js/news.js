controllers.newsController = function($scope, $http, $filter, $window) {

     $scope.newsRemovedChanged = function() {
         $("input[type='file']").attr("disabled", $scope.newsRemoved);
         if ($scope.newsRemoved) {
             $("input[type='file']").val("");
             $("#newsFilename").removeAttr("name");
         } else {
             $("#newsFilename").attr("name", "newsFilename");
         }
     }
     $scope.newsLinkRemovedChanged = function() {
         $("input[type='file']").attr("disabled", $scope.newsLinkRemoved);
         if ($scope.newsLinkRemoved) {
             $("input[type='file']").val("");
             $("#newsLinkFilename").removeAttr("name");
         } else {
             $("#newsLinkFilename").attr("name", "newsLinkFilename");
         }
     }
}