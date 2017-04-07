var controllers = {};
var mainApp = angular.module('mainApp', ['ngTagsInput', 'ngSanitize', 'ngTable', 'isteven-multi-select']);
mainApp.controller(controllers);

$(document).on('ready', function() {
    var csrfToken = $("#csrfToken").val();
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN' : csrfToken
        }
    });

    $('.active').removeClass('active');
    $('.dropdown .open').removeClass('open');
    var path =  window.location.pathname;
    path = path.toLowerCase();
    if (path.indexOf("add") >= 0){
        path = path.substr(0,path.lastIndexOf('add')); 
        path += 'list';
    } else  if (path.indexOf("edit") >= 0){
        path = path.substr(0,path.lastIndexOf('edit'));
        path += 'list';
    }else  if (path.indexOf("remove") >= 0){
        path = path.substr(0,path.lastIndexOf('remove'));
        path += 'list';
    }

    $.each($('.dropdown-menu').find('li'), function() {
        //menu active-selected item
        var curr_path = $(this).find('a').attr('href');        
        if(path == curr_path){
            $(this).addClass('active');
            $(this).parent().parent().addClass('open');
            if($(this).parent().parent().parent().hasClass('dropdown-menu')){
                $(this).parent().parent().parent().parent().addClass('open');
            }
        }
        //active currnt locale-language
        var locale_current = $(this).parent().attr('data-current-locale');
        var locale_item = $(this).attr('data-locale');
        if(locale_current != null && locale_item == locale_current){
            $(this).addClass('active');
        }
    });
});