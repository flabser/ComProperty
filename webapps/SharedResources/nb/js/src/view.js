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

nb.setSearchReferToSessionStorage = function() {
    if (location.href.indexOf('id=search') == -1) {
        sessionStorage.setItem('search_refer', location.href);
    }
};

nb.resetSearchFromRefer = function() {
    var refer = sessionStorage.getItem('search_refer');
    if (refer) {
        location.href = refer;
    } else {
        history.back();
    }
};

// init
$(document).ready(function() {
    $('form[name=ft-search]').on('submit', function() {
        nb.setSearchReferToSessionStorage();
        return true;
    });

    $('[data-action=reset_search]').click(function(event) {
        event.preventDefault();
        nb.resetSearchFromRefer();
    });
});
