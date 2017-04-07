controllers.coatingController = function($scope, $http, $filter, $window) {

     $scope.certRemovedChanged = function() {
         $("input[type='file']").attr("disabled", $scope.certRemoved);
         if ($scope.certRemoved) {
             $("input[type='file']").val("");
             $("#certDFCFilename").removeAttr("name");
         } else {
             $("#certDFCFilename").attr("name", "certDFCFilename");
         }
     }
}