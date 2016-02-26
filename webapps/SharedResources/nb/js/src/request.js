/**
 * doDelete
 */
nb.xhr.doDelete = function(data) {
    return $.ajax({
        type: 'DELETE',
        dataType: 'json',
        url: location.href + '&' + data
    });
};
