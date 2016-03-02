/**
 * doDelete
 */
nb.xhrDelete = function(data) {
    return $.ajax({
        type: 'DELETE',
        dataType: 'json',
        url: location.href + '&' + data
    });
};
