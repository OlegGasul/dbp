controllers.objectListController = function($scope, $http, $filter, $window, $sce, ngTableParams) {

    $scope.columns = columns; 
    $scope.columnFilter = typeof columnFilter === 'undefined' ? null : columnFilter;

    $scope.tableParams = new ngTableParams (
        {page: 1, count: 10, sorting: {name: 'asc'}},
        {total: 0,
            getData: function($defer, params) {
                var path = contextPath + $scope.actionPath
                    + "?page=" + (params.page() - 1)
                    + "&count=" + params.count();

                if( typeof columnFilter !== 'undefined'){                    
                    for(var p in columnFilter){
                        var p_list = [];
                        for(var o in columnFilter[p].outPut){
                            p_list.push(columnFilter[p].outPut[o].id);
                        }
                        var field_param = '&' + columnFilter[p]['field'] + '=' + p_list.toString();
                        path += field_param;
                    }
                    
                    if($('#filter_search_by').val()){
                        var field_param = '&search=' + $('#filter_search_by').val();
                        path += field_param;
                    }
                }

                $http({
                    method: 'get',
                    url: path,
                    dataType: 'json'
                }).then(
                    function(response) {
                        var result = response.data;
                        if (result.status == 'success') {                            
                            var listItems = result.data;
                            var dataCount = result.dataCount; 
                            if(dataCount > 0){
                                params.total(dataCount);
                            } else {
                                params.total($scope.itemsCount);                                
                            }

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
 
}