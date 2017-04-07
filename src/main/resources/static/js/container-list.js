controllers.listController = function($scope, $http, $filter, $window, $sce, ngTableParams) {

    $scope.item = [];
    $scope.image = [];

    $scope.columns = columns;

    $scope.tableParams = new ngTableParams (
        {page: 1, count: 10, sorting: {name: 'asc'}},
        {total: 0,
            getData: function($defer, params) {
                var path = contextPath + $scope.actionPath
                    + "?page=" + (params.page() - 1)
                    + "&count=" + params.count();
                $http({
                    method: 'get',
                    url: path,
                    dataType: 'json'
                }).then(
                    function(response) {
                        var result = response.data;
                        if (result.status == 'success') {

                            var listItems = result.data;
                            params.total($scope.itemsCount);

                            // use build-in angular filter
                            var sortedItems = params.sorting()
                                ? $filter('orderBy')(listItems, params.orderBy())
                                : listItems;
                            $defer.resolve(sortedItems);
                        }
                    }
                );
                // hides count tools as items is less than 10
                if ($scope.itemsCount < 10) {
                    $(".ng-table-pager").hide();
                }
            }
        }
    );

    $scope.getImagePath = function(info) {
        return contextPath + '/image/flag/' +
            info.language.toLowerCase().substr(0, 2) + '.png';
    };

    $scope.hasPhoto = function(item) {
        return item.photos &&
            item.photos.length > 0 &&
            item.photos[0].id;
    };

    $scope.showPhoto = function(item, objName) {
        $scope.image.src = contextPath + "/" + objName + "/photo/" + item.photos[0].id;
        var filename = item.photos[0].filename;
        if (filename.indexOf(".") != -1) {
            filename = filename.substr(0, filename.indexOf("."));
        }
        $scope.image.filename = filename;
        console.log('$scope.image',$scope.image)
        $('#dlgImage').modal('show');
    };

    $scope.viewDetail = function(item) {
        $scope.item = item;
        $('#dlgItemDetail').modal('show');
    };

    $scope.containerDetails = function() {
        return $sce.trustAsHtml($("#containerDetails").html());
    };

    $scope.editPhoto = function(id) {
        document.location = 'edit-photos/' + id;
    };

    $scope.editItem = function(id) {
        document.location = 'edit/' + id;
    };
}