$(document).ready(function() {
    $('[data-action=do-get]').click(function() {
        var $scope = $(this).parents('.fieldset');
        var endPoint = $('input[type=text]', $scope).val();
        var dataType = 'json';

        $.ajax({
            type: 'get',
            url: endPoint,
            dataType: dataType,
        }).then(function(response) {
            if (response) {
                $('pre', $scope).html(JSON.stringify(response, null, 2));
                PR.prettyPrint();
            } else {
                $('pre', $scope).html('empty response');
            }
        }, function(err) {
            console.log('error', err);
            $('pre', $scope).html(err);
        });
    });
});
