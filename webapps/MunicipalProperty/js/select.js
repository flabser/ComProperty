nbApp.selectOptions = {
    balanceholder: {
        url: 'Provider?id=get-organizations'
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
    district: {
        url: 'Provider?id=get-districts',
        data: ['region']
    },
    street: {
        url: 'Provider?id=get-streets',
        data: ['district']
    },
    tags: {
        url: 'Provider?id=get-tags',
        fields: ['color'],
        templateResult: function(item) {
            if (!item.id || !item.color) {
                return item.text;
            }

            var $item = $('<span style="color:' + item.color + '">' + item.text + '</span>');
            return $item;
        }
    }
};
