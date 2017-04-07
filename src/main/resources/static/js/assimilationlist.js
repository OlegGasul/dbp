controllers.assimilationlistController = function($scope, $http, $filter, $window) {

     $scope.dataSourceRemovedChanged = function() {
         $("input[type='file']").attr("disabled", $scope.dataSourceRemoved);
         if ($scope.dataSourceRemoved) {
             $("input[type='file']").val("");
             $("#dataSourceFilename").removeAttr("name");
         } else {
             $("#dataSourceFilename").attr("name", "dataSourceFilename");
         }
     }
}