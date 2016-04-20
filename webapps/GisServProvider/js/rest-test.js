$(document).ready(function() {
    $('[data-action=do-get]').click(function() {
        var endPoint = $('[name=appcode]').val();
        var dataType = $('[name=data-type]').val();

        $.ajax({
            type: 'get',
            url: endPoint,
            dataType: dataType || 'html'
        }).then(function(response) {
            console.log('ok', response);

            if (response) {
                $('#request-result').html(response);
            } else {
                $('#request-result').html('empty response');
            }
        }, function(err) {
            console.log('error', err);
            $('#request-result').html(err);
        });
    });
});
