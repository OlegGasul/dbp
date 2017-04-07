controllers.unapprovalController = function($scope,  $http, $filter, $window) { 
    
    $('.datepicker').datepicker({
        format: 'mm/dd/yyyy'
    });

    $(".tags").find("input[type='text']").addClass("form-control");

    $scope.testMediumTags = [];
    $scope.companyTags = [];
    $scope.assimilationListTags = [];
    $scope.packagingInstructionTags = [];
    $scope.transportTags = [];

    $scope.initTestMedium = function() {

        var id = $("#testMediumId").val();
        var name = $("#testMediumName").val();

        if (id && name) {
            $scope.testMediumTags.push({
                id: id,
                name: name
            });
        }
    };
    $scope.initCompany = function() {

        var id = $("#companyId").val();
        var name = $("#companyName").val();

        if (id && name) {
            $scope.companyTags.push({
                id: id,
                name: name
            });
        }
    };
    $scope.initAssimilationList = function() {

        var id = $("#assimilationListId").val();
        var name = $("#assimilationListName").val();

        if (id && name) {
            $scope.assimilationListTags.push({
                id: id,
                name: name
            });
        }
    };
    $scope.initPackagingInstruction = function() {

        var id = $("#packagingInstructionId").val();
        var name = $("#packagingInstructionName").val();

        if (id && name) {
            $scope.packagingInstructionTags.push({
                id: id,
                name: name
            });
        }
    };
    $scope.initTransport = function() {

        var id = $("#transportId").val();
        var name = $("#transportName").val();

        if (id && name) {
            $scope.transportTags.push({
                id: id,
                name: name
            });
        }
    };
    $scope.initTestMedium();
    $scope.initCompany();
    $scope.initAssimilationList();
    $scope.initPackagingInstruction();
    $scope.initTransport();

    $scope.loadTestMediums = function(query) {
        if (!$scope.testMediumTags || $scope.testMediumTags.length === 0) {
            return $http.get(contextPath +"/objects/typeahead/12?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var testMediums = response.data;
                    var result = [];
                    for (var i in testMediums) {
                        result.push({
                            id: testMediums[i].id,
                            name:  testMediums[i].infoList[0].medium
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };
    $scope.loadCompanies = function(query) {
        if (!$scope.companyTags || $scope.companyTags.length === 0) {
            return $http.get(contextPath +"/objects/typeahead/13?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var companies = response.data;
                    var result = [];
                    for (var i in companies) {
                        result.push({
                            id: companies[i].id,
                            name:  companies[i].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };
    $scope.loadAssimilationLists = function(query) {
        if (!$scope.assimilationListTags || $scope.assimilationListTags.length === 0) {
            return $http.get(contextPath +"/objects/typeahead/14?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var assimilationLists = response.data;
                    var result = [];
                    for (var i in assimilationLists) {
                        result.push({
                            id: assimilationLists[i].id,
                            name:  assimilationLists[i].designation
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };
    $scope.loadPackagingInstructions = function(query) {
        if (!$scope.packagingInstructionTags || $scope.packagingInstructionTags.length === 0) {
            return $http.get(contextPath +"/objects/typeahead/15?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var packagingInstructions = response.data;
                    var result = [];
                    for (var i in packagingInstructions) {
                        result.push({
                            id: packagingInstructions[i].id,
                            name:  packagingInstructions[i].transportLaw
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };
    $scope.loadTransports = function(query) {
        if (!$scope.transportTags || $scope.transportTags.length === 0) {
            return $http.get(contextPath +"/objects/typeahead/16?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var transports = response.data;
                    var result = [];
                    for (var i in transports) {
                        result.push({
                            id: transports[i].id,
                            name:  transports[i].verpGrp
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.testMediumSelected = function(tag) {
       $("#testMediumId").val(tag.id);
       $("#testMediumName").val(tag.name);
    };
    $scope.testMediumRemoved = function(tag) {
       $("#testMediumId").val("");
       $("#testMediumName").val("");
    };

    $scope.companySelected = function(tag) {
       $("#companyId").val(tag.id);
       $("#companyName").val(tag.name);
    };
    $scope.companyRemoved = function(tag) {
       $("#companyId").val("");
       $("#companyName").val("");
    };

    $scope.initAssimilationListSelected = function(tag) {
       $("#assimilationListId").val(tag.id);
       $("#assimilationListName").val(tag.name);
    };
    $scope.initAssimilationListRemoved = function(tag) {
       $("#assimilationListId").val("");
       $("#assimilationListName").val("");
    };

    $scope.packagingInstructionSelected = function(tag) {
       $("#packagingInstructionId").val(tag.id);
       $("#packagingInstructionName").val(tag.name);
    };
    $scope.packagingInstructionRemoved = function(tag) {
       $("#packagingInstructionId").val("");
       $("#packagingInstructionName").val("");
    };

    $scope.transportSelected = function(tag) {
       $("#transportId").val(tag.id);
       $("#transportName").val(tag.name);
    };
    $scope.transportRemoved = function(tag) {
       $("#transportId").val("");
       $("#transportName").val("");
    };


    $scope.fileRemovedChanged = function() {
        $("input[type='file']").attr("disabled", $scope.fileRemoved);
        if ($scope.fileRemoved) {
            $("input[type='file']").val("");
            $("#fileFilename").removeAttr("name");
        } else {
            $("#fileFilename").attr("name", "fileFilename");
        }
    };    
    
}