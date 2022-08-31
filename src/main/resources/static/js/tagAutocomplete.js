function addAutocomplete($element) {
    let autocompleteOptions = {
        source: function (request, response) {
            $.ajax({
                url: "/tags",
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function (data) {
                    response(data);
                }
            });
        },
        minLength: 1
    };

    $element.autocomplete(autocompleteOptions)
}
