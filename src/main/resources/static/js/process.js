controllers.processController = function($scope, $http, $filter, $window) {

     $scope.processFileRemovedChanged = function() {
         $("input[type='file']").attr("disabled", $scope.processFileRemoved);
         if ($scope.processFileRemoved) {
             $("input[type='file']").val("");
             $("#processFilename").removeAttr("name");
         } else {
             $("#processFilename").attr("name", "processFilename");
         }
     }
}