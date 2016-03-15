nbApp.selectOptions = {
    balanceholder: {
        url: 'Provider?id=get-organizations',
        search: true
    },
    orgcategory: {
        url: 'Provider?id=get-org-categories'
    },
    department: {
        url: 'Provider?id=get-departments',
        data: ['organization']
    },
    position: {
        url: 'Provider?id=get-positions'
    },
    propertycode: {
        url: 'Provider?id=get-property-codes'
    },
    receivingreason: {
        url: 'Provider?id=get-receiving-reasons'
    },
    tags: {
        url: 'Provider?id=get-tags'
    }
};

nbApp.getSelectOptions = function(optionId) {
    var options = nbApp.selectOptions[optionId];
    return {
        ajax: {
            url: options.url,
            dataType: 'json',
            delay: 250,
            data: function(params) {
                console.log(params, this);
                var _data = {
                    page: params.page
                };
                if (options.search) {
                    _data.keyword = params.term
                }
                for (var k in options.data) {
                    _data[options.data] = this[0].form[options.data].value;
                }

                return _data;
            },
            processResults: function(data, params) {
                params.page = params.page || 1;
                var items = [],
                    list = data.objects[0].list,
                    meta = data.objects[0].meta;
                for (var k in list) {
                    items.push({
                        id: list[k].id,
                        text: list[k].name
                    });
                }
                console.log(items.length, meta.count);
                return {
                    results: items,
                    pagination: {
                        more: items.length < meta.count
                    }
                };
            },
            cache: true,
            minimumResultsForSearch: -1
        }
    }
};

$(document).ready(function() {
    $('select[name]').each(function() {
        if (nbApp.selectOptions[this.name]) {
            $(this).select2(nbApp.getSelectOptions(this.name));
        } else {
            if (nb.isMobile()) {
                if (this.multiple) {
                    $(this).select2();
                }
            } else {
                $(this).select2();
            }
        }
    });
});
