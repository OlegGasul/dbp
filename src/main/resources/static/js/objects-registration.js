controllers.objectsRegistration = function($scope, $http, $filter, $window) {

	$scope.selectedForm = selected;
	$scope.businessUnit = [];

    $scope.loadBusinessUnits = function(query) {
        if (!$scope.tags || $scope.tags.length === 0) {
            return $http.get(contextPath + "typeahead/2?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var bussinesUnits = response.data;
                    var result = [];
                    for (var i in bussinesUnits) {
                        result.push({
                            id: bussinesUnits[i].id,
                            name:  bussinesUnits[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
    //            return users.filter(function(user) {
    //                return user.id != undefined && user.id != null;
    //            });
            });
        }
        return [];
    };

	$scope.objects = [
        {id: 'coating-form', name: 'Coating'},
        {id: 'businessUnit-form', name: 'Business Unit'}
	];

	$scope.containerObjects = [
        {id: 'cubic-form', name: 'Cubic'},
        {id: 'cylindrical-form', name: 'Cylindrical'}
	];
}