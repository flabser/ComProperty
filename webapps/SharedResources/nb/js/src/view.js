nb.getSelectedEntityIDs = function(checkboxName) {
    var $checked = $('input[name=' + (checkboxName || 'docid') + ']:checked');
    if ($checked.length === 0) {
        return [];
    }

    var result = [];
    $checked.each(function() {
        result.push(this.value);
    });

    return result;
};

nb.setReferToSessionStorage = function() {
    sessionStorage.setItem('search_refer', location.href);
};


nb.resetSearchFromRefer = function() {
    location.href = sessionStorage.getItem('search_refer');
};


// init
$(document).ready(function() {
    $('form[name=ft-search]').on('submit', function() {
        nb.setReferToSessionStorage();
        return true;
    });

    $('[data-action=reset_search]').click(function(event) {
        event.preventDefault();
        nb.resetSearchFromRefer();
    });
});
