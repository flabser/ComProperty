$(document).ready(function() {
    $('.js-rest-scope [data-action=do-get]').click(function() {
        var $scope = $(this).parents('.js-rest-scope');
        var endPoint = $('input[type=text]', $scope).val();
        var dataType = 'json';

        $.ajax({
            type: 'get',
            url: endPoint,
            dataType: dataType,
        }).then(function(response) {
            if (response) {
                $('pre', $scope).html(JSON.stringify(response, null, 2)).removeClass('prettyprinted');
                PR.prettyPrint();
            } else {
                $('pre', $scope).html('empty response');
            }
        }, function(err) {
            $('pre', $scope).html(err);
        });
    });

    $('.js-rest-scope [data-action=do-clear]').click(function() {
        var $scope = $(this).parents('.js-rest-scope');
        $('pre', $scope).html('');
    });
});
