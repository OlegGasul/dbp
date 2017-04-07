controllers.searchController = function($scope, $http, $filter, $window, $sce, ngTableParams) {

    $scope.options = options;
    $scope.fields = fields;
    $scope.chosenField = null;
    $scope.objectValues = [];
    $scope.value = '';

    $scope.columns = columns;
    $scope.item = [];
    $scope.image = [];
    
    $scope.operators = [
        {value: 'eq', displayName: 'equals'},
        {value: 'neq', displayName: 'not equal'}
    ];

    $scope.fieldOptions = [];
    
    $scope.updateOptions = function(fieldName) {
        for (i in $scope.fields) {
            if ($scope.fields[i].name == fieldName) {
                
                $scope.chosenField = $scope.fields[i];
                if ($scope.chosenField.type.$name === 'OBJECT') {
                     
                    $scope.loadObjects($scope.fields[i].url);
                    
                    //console.log($scope.objectValues);
                    //$scope.selectedObject = $scope.objectValues.value[0].name;
                }
                break;
            }
        }
        if ( $scope.chosenField) {
            $scope.fieldOptions =  $scope.chosenField.options;
            $scope.option = $scope.fieldOptions[0].$name;
        }
    };

    $scope.isTextField = function() {
        return $scope.chosenField
            && $scope.chosenField.type.$name === 'STRING';
    };

    $scope.isObjectField = function() {
        return $scope.chosenField
            && $scope.chosenField.type.$name === 'OBJECT';
    };

    $scope.isEnumField = function() {
        return $scope.chosenField
            && $scope.chosenField.type.$name === 'ENUM';
    };

    $scope.isDateField = function() {
        return $scope.chosenField
            && $scope.chosenField.type.$name === 'DATE';
    };

    $scope.isNumberField = function() {
        return $scope.chosenField
            && $scope.chosenField.type.$name === 'DOUBLE';
    };

    $scope.search = function() {
        var value = $scope.value;
        if ($scope.isObjectField()) {
            value = $("#objectID").val();
        }

        var csrfToken = $("#csrfToken").val();
        $http({
            method: 'post',
            url: contextPath + "/container/search",
            params: {
                fieldName: $scope.fieldName,
                fieldOperator: $scope.option,
                fieldValue: value
            },
            headers: {
                'X-CSRF-TOKEN' : csrfToken,
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data;
                if (result.status == 'success') {

                    var listItems = result.data;
                    params.total($scope.itemsCount);

                    // use build-in angular filter
                    /*var sortedItems = params.sorting()
                        ? $filter('orderBy')(listItems, params.orderBy())
                        : listItems;
                    $defer.resolve(sortedItems);*/
                }

                // hides count tools as items is less than 10
                if ($scope.itemsCount < 10) {
                    $(".ng-table-pager").hide();
                }
            }
        );
    };

    $scope.initDate = function() {
        $("#dateField").datepicker();
    }

    $scope.tableParams = new ngTableParams (
        
        {page: 1, count: 10, sorting: {name: 'asc'}},
        {total: 0,
            getData: function($defer, params) {

                var csrfToken = $("#csrfToken").val();
                var path = contextPath + "/container/search";

                var page = params.page();
                var count = params.count();
                
                var fieldName = $scope.fieldName;
                var option = $scope.option;
                var value = $scope.value;
                if ($scope.isObjectField()) {
                    value = $("#objectID").val();
                } else {
                    value = $(".fieldValue").val();
                }
                if ( typeof(fieldName) === "undefined" || fieldName === null ) {fieldName = ''}
                if ( typeof(option) === "undefined" || option === null ) {option = ''}
                if ( typeof(value) === "undefined" || value === null ) {value = ''} 
                
                $http({
                    method: 'post',
                    url: path,
                    params: {
                        fieldName : fieldName,
                        fieldOperator : option,
                        fieldValue : value,
                        page : page,
                        count : count
                    },
                    headers: {
                        'X-CSRF-TOKEN' : csrfToken,
                        'Content-Type' : 'application/x-www-form-urlencoded'
                    },
                    dataType: 'json',
                    headers: {
                        'X-CSRF-TOKEN' : csrfToken,
                        'Content-Type' : 'application/x-www-form-urlencoded'
                    } 
               }).then(
                   function(response) {
                       var result = response.data;
                       if (result.status == 'success') {

                           var listItems = result.data;
                           var dataCount = result.dataCount;
                           $scope.itemsCount = dataCount;
                           params.total(dataCount);

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
        return item.fileName && item.fileName.length >0 && item.fileId > 0; 
    };

    $scope.showPhoto = function(item) {
        var objName= item.packagingForm;
        objName = objName.replace('Entity','').toLowerCase(); 
        $scope.image.src = contextPath + "/" + objName + "/photo/" + item.fileId;
        var filename = item.fileName;
        if (filename.indexOf(".") != -1) {
            filename = filename.substr(0, filename.indexOf("."));
        }
        $scope.image.filename = filename;
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

    $scope.loadObjects = function(fieldName) {
        var path = contextPath + "/" + fieldName.toLowerCase() + "/list-items" + "?page=0&count=0";
        $http({
            method: 'get',
            url: path,
            dataType: 'json'
        }).then(
            function (response) {
                var result = response.data;
                if (result.status == 'success') {
                    $scope.objectValues = [];
                    var objects = result.data;
                    for (var i in objects) {
                        $scope.objectValues.push({
                            id: objects[i].id,
                            name: objects[i].name
                        });
                    }
                };
            }
        );
    }
}