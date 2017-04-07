controllers.couplingController = function($scope, $http, $filter, $window) {
    
    $scope.imageRemovedChanged = function(id) {
        //$("input[type='file']").attr("disabled", $scope.imageRemoved);
        $("#file"+id).attr("disabled", $scope.imageRemoved);
        var fileName = "";
        if(id == 1 ){
            fileName = 'imageFilename';
        } else if(id == 2 ){
            fileName = 'drawingFilename';
        }
        if ($scope.imageRemoved) {
            //$("input[type='file']").val("");
            $("#file"+id).val("");
            $("#"+fileName).removeAttr("name");
        } else {
            $("#"+fileName).attr("name", +fileName);
        }
    };    
    $scope.drawingRemovedChanged = function() {
        $("input[type='file']").attr("disabled", $scope.drawingRemoved);
        if ($scope.drawingRemoved) {
            $("input[type='file']").val("");
            $("#drawingFilename").removeAttr("name");
        } else {
            $("#drawingFilename").attr("name", "drawingFilename");
        }
    };
}