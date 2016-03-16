nbApp.selectOptions = {
    organization: {
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
    }
};
