controllers.containerController = function($scope, $http, $filter, $window) {

    $(".tags").find("input[type='text']").addClass("form-control");
    
    $scope.photos = [0];
    $scope.allowNewPhoto = false;    

    $scope.locationTags = [];
    $scope.businessUnitTags = [];
    $scope.paletteMaterialTags = [];
    $scope.paletteTypeTags = [];
    $scope.outletArmaturTags = [];
    $scope.outletCouplingTags = [];
    $scope.containerMaterialTags = [];
    $scope.frameMaterialTags = [];
    $scope.coatingTankTags = [];
    $scope.exZoneTags = [];
    $scope.coatingInsideTags = [];
    $scope.coatingOutsideTags = [];
    $scope.containerCoatingTags = [];
    $scope.coatingFrameTags = [];
    $scope.holeTags = [];
    $scope.handleTags = [];
    $scope.fillingOpeningsTags = [];

    $scope.removePhoto = function(item) {
        $scope.photos = $scope.photos.slice(0, $scope.photos.length - 1);
        var f = $('#photo-' + item + " :input").prop('value');
        if(f != null && f != undefined && f.length  < 1){
            $scope.allowNewPhoto = true; 
        }
    };
    
    $scope.allowRemove = function(item) {
        return $scope.photos.length > 1; 
    };

    $scope.addPhoto = function() {
        $scope.photos.push($scope.photos.length);
        $scope.allowNewPhoto = false;
    }
    
    ;$scope.resetForm = function() {
        $scope.photos = [0];
        $scope.allowNewPhoto = false;
    };

    $scope.validatePhoto = function(this_) {

        var MAX_FILE_SIZ = 8388608;
        var f = this_.files[0];
        var name = f.name; 
        
        var file_size = $.isNumeric( f.size ) ? f.size : f.fileSize;
        //here I CHECK if the FILE SIZE is bigger than 8 MB (numbers below are in bytes) 8388608
        if (file_size < MAX_FILE_SIZ)  { 
            var extension = name.substr((name.lastIndexOf('.') + 1));
            if (extension === 'jpg' || extension === 'jpeg' || extension === 'gif' || extension === 'png' || extension === 'bmp') {
                $scope.allowNewPhoto = true;
                $scope.$digest();
                return;
            }
        }
        $scope.allowNewPhoto = false;
        this_.value = null;
        $scope.$digest();
    }
    
    $scope.initLocation = function() {
        var id = $("#locationID").val();
        var name = $("#locationName").val();

        if (id && name) {
            $scope.locationTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initBusinessUnit = function() {

        var id = $("#businessUnitID").val();
        var name = $("#businessUnitName").val();

        if (id && name) {
            $scope.businessUnitTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initPaletteMaterial = function() {

        var id = $("#paletteMaterialID").val();
        var name = $("#paletteMaterialName").val();

        if (id && name) {
            $scope.paletteMaterialTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initPaletteType = function() {

        var id = $("#paletteTypeID").val();
        var name = $("#paletteTypeName").val();

        if (id && name) {
            $scope.paletteTypeTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initOutletArmatur = function() {

        var id = $("#outletArmaturID").val();
        var name = $("#outletArmaturName").val();

        if (id && name) {
            $scope.outletArmaturTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initFillingOpenings = function() {

        var id = $("#fillingOpeningsID").val();
        var name = $("#fillingOpeningsName").val();

        if (id && name) {
            $scope.fillingOpeningsTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initOutletCoupling = function() {

        var id = $("#outletCouplingID").val();
        var name = $("#outletCouplingName").val();

        if (id && name) {
            $scope.outletCouplingTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initContainerMaterial = function() {

        var id = $("#containerMaterialID").val();
        var name = $("#containerMaterialName").val();

        if (id && name) {
            $scope.containerMaterialTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initFrameMaterial = function() {

        var id = $("#frameMaterialID").val();
        var name = $("#frameMaterialName").val();

        if (id && name) {
            $scope.frameMaterialTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initCoatingTank = function() {

        var id = $("#coatingTankID").val();
        var name = $("#coatingTankName").val();

        if (id && name) {
            $scope.coatingTankTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initExZone = function() {

        var id = $("#exZoneID").val();
        var name = $("#exZoneName").val();

        if (id && name) {
            $scope.exZoneTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initCoatingInside = function() {

        var id = $("#coatingInsideID").val();
        var name = $("#coatingInsideName").val();

        if (id && name) {
            $scope.coatingInsideTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initCoatingOutside = function() {

        var id = $("#coatingOutsideID").val();
        var name = $("#coatingOutsideName").val();

        if (id && name) {
            $scope.coatingOutsideTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initContainerCoating = function() {

        var id = $("#containerCoatingID").val();
        var name = $("#containerCoatingName").val();

        if (id && name) {
            $scope.containerCoatingTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initCoatingFrame = function() {

        var id = $("#coatingFrameID").val();
        var name = $("#coatingFrameName").val();

        if (id && name) {
            $scope.coatingFrameTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initHole = function() {

        var id = $("#holeID").val();
        var name = $("#holeName").val();

        if (id && name) {
            $scope.holeTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initHandle = function() {

        var id = $("#handleID").val();
        var name = $("#handleName").val();

        if (id && name) {
            $scope.handleTags.push({
                id: id,
                name: name
            });
        }
    };

    $scope.initLocation();
    $scope.initBusinessUnit();
    $scope.initPaletteMaterial();
    $scope.initPaletteType();
    $scope.initOutletArmatur();
    $scope.initFillingOpenings();
    $scope.initOutletCoupling();
    $scope.initContainerMaterial();
    $scope.initFrameMaterial();
    $scope.initCoatingTank();
    $scope.initExZone();
    $scope.initCoatingInside();
    $scope.initCoatingOutside();
    $scope.initContainerCoating();
    $scope.initCoatingFrame();
    $scope.initHole();
    $scope.initHandle();

    $scope.loadLocations = function(query) {
        if (!$scope.locationTags || $scope.locationTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/7?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var locations = response.data;
                    var result = [];
                    for (var i in locations) {
                        if ($scope.arrayContains(result, locations[i].name)) {
                            continue;
                        }
                        result.push({
                            id: locations[i].id,
                            name: locations[i].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadBusinessUnits = function(query) {
        if (!$scope.businessUnitTags || $scope.businessUnitTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/2?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var businessUnits = response.data;
                    var result = [];
                    for (var i in businessUnits) {
                        if ($scope.arrayContains(result, businessUnits[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: businessUnits[i].id,
                            name: businessUnits[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadPaletteMaterials = function(query) {
        if (!$scope.paletteMaterialTags || $scope.paletteMaterialTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/5?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var materials = response.data;
                    var result = [];
                    for (var i in materials) {
                        if ($scope.arrayContains(result, materials[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: materials[i].id,
                            name:  materials[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadPaletteTypes = function(query) {
        if (!$scope.paletteTypeTags || $scope.paletteTypeTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/9?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var palettes = response.data;
                    var result = [];
                    for (var i in palettes) {
                        if ($scope.arrayContains(result, palettes[i].infoList[0].type)) {
                            continue;
                        }
                        result.push({
                            id: palettes[i].id,
                            name:  palettes[i].infoList[0].type
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadOutletArmaturTags = function(query) {
        if (!$scope.outletArmaturTags || $scope.outletArmaturTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/8?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var closures = response.data;
                    var result = [];
                    for (var i in closures) {
                        if ($scope.arrayContains(result, closures[i].infoList[0].name)) {
                            console.log('contains', closures[i].id, result);
                            continue;
                        }
                        result.push({
                            id: closures[i].id,
                            name:  closures[i].infoList[0].name
                        });
                    };
                    console.log('result', result);
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadFillingOpeningsTags = function(query) {
        if (!$scope.fillingOpeningsTags || $scope.fillingOpeningsTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/8?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var closures = response.data;
                    var result = [];
                    for (var i in closures) {
                        if ($scope.arrayContains(result, closures[i].infoList[0].name)) {
                            console.log('contains', closures[i].id, result);
                            continue;
                        }
                        result.push({
                            id: closures[i].id,
                            name:  closures[i].infoList[0].name
                        });
                    };
                    console.log('result', result);
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadOutletCouplingTags = function(query) {
        if (!$scope.outletCouplingTags || $scope.outletCouplingTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/18?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var couplings = response.data;
                    var result = [];
                    for (var i in couplings) {
                        if ($scope.arrayContains(result, couplings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: couplings[i].id,
                            name:  couplings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadContainerMaterials = function(query) {
        if (!$scope.containerMaterialTags || $scope.containerMaterialTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/5?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var materials = response.data;
                    var result = [];
                    for (var i in materials) {
                        if ($scope.arrayContains(result, materials[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: materials[i].id,
                            name:  materials[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadFrameMaterials = function(query) {
        if (!$scope.frameMaterialTags || $scope.frameMaterialTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/5?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var materials = response.data;
                    var result = [];
                    for (var i in materials) {
                        if ($scope.arrayContains(result, materials[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: materials[i].id,
                            name:  materials[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadCoatingTanks = function(query) {
        if (!$scope.coatingTankTags || $scope.coatingTankTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/4?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var coatings = response.data;
                    var result = [];
                    for (var i in coatings) {
                        if ($scope.arrayContains(result, coatings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: coatings[i].id,
                            name:  coatings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadExZones = function(query) {
        if (!$scope.exZoneTags || $scope.exZoneTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/10?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var exZones = response.data;
                    var result = [];
                    for (var i in exZones) {
                        if ($scope.arrayContains(result, exZones[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: exZones[i].id,
                            name:  exZones[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadCoatingInsides = function(query) {
        if (!$scope.coatingInsideTags || $scope.coatingInsideTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/4?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var coatings = response.data;
                    var result = [];
                    for (var i in coatings) {
                        if ($scope.arrayContains(result, coatings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: coatings[i].id,
                            name:  coatings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadCoatingOutsides = function(query) {
        if (!$scope.coatingOutsideTags || $scope.coatingOutsideTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/4?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var coatings = response.data;
                    var result = [];
                    for (var i in coatings) {
                        if ($scope.arrayContains(result, coatings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: coatings[i].id,
                            name:  coatings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadContainerCoatings = function(query) {
        if (!$scope.containerCoatingTags || $scope.containerCoatingTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/4?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var coatings = response.data;
                    var result = [];
                    for (var i in coatings) {
                        if ($scope.arrayContains(result, coatings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: coatings[i].id,
                            name:  coatings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadCoatingFrames = function(query) {
        if (!$scope.coatingFrameTags || $scope.coatingFrameTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/4?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var coatings = response.data;
                    var result = [];
                    for (var i in coatings) {
                        if ($scope.arrayContains(result, coatings[i].infoList[0].description)) {
                            continue;
                        }
                        result.push({
                            id: coatings[i].id,
                            name:  coatings[i].infoList[0].description
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadHoles = function(query) {
        if (!$scope.holeTags || $scope.holeTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/8?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var holes = response.data;
                    var result = [];
                    for (var i in holes) {
                        if ($scope.arrayContains(result, holes[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: holes[i].id,
                            name:  holes[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.loadHandles = function(query) {
        if (!$scope.handleTags || $scope.handleTags.length === 0) {
            return $http.get(contextPath + "/objects/typeahead/8?info=" + query, {cache: true}).then(function(response) {
                if (response && response.status == 200) {
                    var handles = response.data;
                    var result = [];
                    for (var i in closures) {
                        if ($scope.arrayContains(result, handles[i].infoList[0].name)) {
                            continue;
                        }
                        result.push({
                            id: handles[i].id,
                            name:  handles[i].infoList[0].name
                        });
                    };
                    return result;
                }
                return [];
            });
        }
        return [];
    };

    $scope.arrayContains = function(array, value) {
        for (var i in array) {
            if (array[i].name == value) {
                return true;
            }
        }
        return false;
    }

    $scope.locationSelected = function(tag) {
        $("#locationID").val(tag.id);
        $("#locationName").val(tag.name);
    };

    $scope.locationRemoved = function(tag) {
        $("#locationID").val("");
        $("#locationName").val("");
    };

    $scope.businessUnitSelected = function(tag) {
        $("#businessUnitID").val(tag.id);
        $("#businessUnitName").val(tag.name);
    };

    $scope.businessUnitRemoved = function(tag) {
        $("#businessUnitID").val("");
        $("#businessUnitName").val("");
    };

    $scope.paletteMaterialSelected = function(tag) {
        $("#paletteMaterialID").val(tag.id);
        $("#paletteMaterialName").val(tag.name);
    };

    $scope.paletteMaterialRemoved = function(tag) {
        $("#paletteMaterialID").val("");
        $("#paletteMaterialName").val("");
    };

    $scope.paletteTypeSelected = function(tag) {
        $("#paletteTypeID").val(tag.id);
        $("#paletteTypeName").val(tag.name);
    };

    $scope.paletteTypeRemoved = function(tag) {
        $("#paletteTypeID").val("");
        $("#paletteTypeName").val("");
    };

    $scope.outletArmaturSelected = function(tag) {
        $("#outletArmaturID").val(tag.id);
        $("#outletArmaturName").val(tag.name);
    };

    $scope.outletArmaturRemoved = function(tag) {
        $("#outletArmaturID").val("");
        $("#outletArmaturName").val("");
    };

    $scope.fillingOpeningsSelected = function(tag) {
        $("#fillingOpeningsID").val(tag.id);
        $("#fillingOpeningsName").val(tag.name);
    };

    $scope.fillingOpeningsRemoved = function(tag) {
        $("#fillingOpeningsID").val("");
        $("#fillingOpeningsName").val("");
    };

    $scope.outletCouplingSelected = function(tag) {
        $("#outletCouplingID").val(tag.id);
        $("#outletCouplingName").val(tag.name);
    };

    $scope.outletCouplingRemoved = function(tag) {
        $("#outletCouplingID").val("");
        $("#outletCouplingName").val("");
    };

    $scope.containerMaterialSelected = function(tag) {
        $("#containerMaterialID").val(tag.id);
        $("#containerMaterialName").val(tag.name);
    };

    $scope.containerMaterialRemoved = function(tag) {
        $("#containerMaterialID").val("");
        $("#containerMaterialName").val("");
    };

    $scope.frameMaterialSelected = function(tag) {
        $("#frameMaterialID").val(tag.id);
        $("#frameMaterialName").val(tag.name);
    };

    $scope.frameMaterialRemoved = function(tag) {
        $("#frameMaterialID").val("");
        $("#frameMaterialName").val("");
    };

    $scope.coatingTankSelected = function(tag) {
        $("#coatingTankID").val(tag.id);
        $("#coatingTankName").val(tag.name);
    };

    $scope.coatingTankRemoved = function(tag) {
        $("#coatingTankID").val("");
        $("#coatingTankName").val("");
    };

    $scope.exZoneSelected = function(tag) {
        $("#exZoneID").val(tag.id);
        $("#exZoneName").val(tag.name);
    };

    $scope.exZoneRemoved = function(tag) {
        $("#exZoneID").val("");
        $("#exZoneName").val("");
    };

    $scope.coatingInsideSelected = function(tag) {
        $("#coatingInsideID").val(tag.id);
        $("#coatingInsideName").val(tag.name);
    };

    $scope.coatingInsideRemoved = function(tag) {
        $("#coatingInsideID").val("");
        $("#coatingInsideName").val("");
    };

    $scope.coatingOutsideSelected = function(tag) {
        $("#coatingOutsideID").val(tag.id);
        $("#coatingOutsideName").val(tag.name);
    };

    $scope.coatingOutsideRemoved = function(tag) {
        $("#coatingOutsideID").val("");
        $("#coatingOutsideName").val("");
    };

    $scope.containerCoatingSelected = function(tag) {
        $("#containerCoatingID").val(tag.id);
        $("#containerCoatingName").val(tag.name);
    };

    $scope.containerCoatingRemoved = function(tag) {
        $("#containerCoatingID").val("");
        $("#containerCoatingName").val("");
    };

    $scope.coatingFrameSelected = function(tag) {
        $("#coatingFrameID").val(tag.id);
        $("#coatingFrameName").val(tag.name);
    };

    $scope.coatingFrameRemoved = function(tag) {
        $("#coatingFrameID").val("");
        $("#coatingFrameName").val("");
    };

    $scope.holeSelected = function(tag) {
        $("#holeID").val(tag.id);
        $("#holeName").val(tag.name);
    };

    $scope.holeRemoved = function(tag) {
        $("#holeID").val("");
        $("#holeName").val("");
    };

    $scope.handleSelected = function(tag) {
        $("#handleID").val(tag.id);
        $("#handleName").val(tag.name);
    };

    $scope.handleRemoved = function(tag) {
        $("#handleID").val("");
        $("#handleName").val("");
    };

    $scope.specificationsRemovedChanged = function() {
        $("#specifications").attr("disabled", $scope.specificationsRemoved);
        if ($scope.specificationsRemoved) {
            $("#specifications").val(""); 
            $("#specificationsFilename").removeAttr("name");
        } else {
            $("#specificationsFilename").attr("name", "manufacturerSpecificationsFilename"); 
        }
    }
    $scope.drawingRemovedChanged = function() {
        $("#drawing").attr("disabled", $scope.drawingRemoved);
        if ($scope.drawingRemoved) {
            $("#drawing").val("");
            $("#drawingFilename").removeAttr("name");
        } else {
            $("#drawingFilename").attr("name", "drawingFilename");
        }
    }
    $scope.drawingCADRemovedChanged = function() {
        $("#drawingCAD").attr("disabled", $scope.drawingCADRemoved);
        if ($scope.drawingRemoved) {
            $("#drawingCAD").val("");
            $("#drawingCADFilename").removeAttr("name");
        } else {
            $("#drawingCADFilename").attr("name", "drawingCADFilename");
        }
    }

    $scope.fileNameChanged = function(this_, id) {
        var MAX_FILE_SIZ = 8388608;
        var f = this_.files[0];
        var file_size = $.isNumeric( f.size ) ? f.size : f.fileSize;
        //here I CHECK if the FILE SIZE is bigger than 8 MB (numbers below are in bytes) 8388608
        if (file_size > MAX_FILE_SIZ)  {
            //show an alert to the user
            console.log("Allowed file size exceeded. (Max. 8 MB)");
            $('#'+id).show();
            //reset file upload control
            this_.value = null;
        } else {
            $('#'+id).hide();
        }
    }

    
}