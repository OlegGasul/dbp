controllers.companyController = function($scope, $http, $filter, $window) {

    $(".tags").find("input[type='text']").addClass("form-control");

    $scope.categories = [];

    $scope.initCategories = function(items) {
        if (items && items.length > 0) {
            for (i in items) {
                $scope.categories.push({
                    id: items[i].id,
                    name: items[i].infoList[0].name
                });
            }
        }
    };

    $scope.loadCategories = function(query) {
        return $http.get(contextPath + "/objects/typeahead/11?info=" + query, {cache: true}).then(function(response) {
            if (response && response.status == 200) {
                var categories = response.data;
                var result = [];
                for (var i in categories) {
                    if ($scope.arrayContains(result, categories[i].infoList[0].name)) {
                        continue;
                    }
                    result.push({
                        id: categories[i].id,
                        name: categories[i].infoList[0].name
                    });
                };
                console.log('res', result);
                return result;
            }
            return [];
        });
    };

    $scope.arrayContains = function(array, value) {
        for (var i in array) {
            if (array[i].name == value) {
                return true;
            }
        }
        return false;
    };

    $scope.categorySelected = function(tag) {
        var index = $scope.categories.indexOf(tag);
        if (index == -1) {
            $scope.categories.push({
                id: tag.id,
                name: tag.name
            });
        }
    };

    $scope.categoryRemoved = function(tag) {
        var index = $scope.categories.indexOf(tag);
        $scope.categories.splice(index, 1);
    };
}