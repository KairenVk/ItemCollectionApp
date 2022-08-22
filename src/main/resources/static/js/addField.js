$(document).ready(function () {

        $("#repeatFieldBtn").click(function () {
        $newid = $(this).data("increment");
        $fieldType= $("#newFieldDiv").find('select[name="fieldType"]').val();
        $newFieldDiv = $("<div class=\"newFieldDiv m-2\"></div>");
        $newFieldDiv.insertAfter($(".newFieldDiv").last());
        $newFieldDiv.last().attr('id',   "newFieldDiv" + '_' + $newid);
        switch ($fieldType) {
            case 'String':
                $($newFieldDiv).append('<label for="stringField" class="form-label p-1">Name: </label><input type="text" placeholder="Field name" name="fieldNames[]"/><input type="text" id="stringField" value="String" readonly name="customField[]"/><button type="button" class="btn btn-danger removeFieldBtn" data-id="newFieldDiv'+'_'+ $newid+'">Remove</button>');
                break;
            case 'Integer':
                $($newFieldDiv).append('<label for="integerField" class="form-label p-1">Name: </label><input type="text" placeholder="Field name" name="fieldNames[]"/><input type="text" id="stringField" value="Integer" readonly name="customField[]"/><button type="button" class="btn btn-danger removeFieldBtn" data-id="newFieldDiv'+'_'+ $newid+'">Remove</button>');
                break;
            case 'Date':
                $($newFieldDiv).append('<label for="dateField" class="form-label p-1">Name: </label><input type="text" placeholder="Field name" name="fieldNames[]"/><input type="text" id="stringField" value="Date" readonly name="customField[]"/><button type="button" class="btn btn-danger removeFieldBtn" data-id="newFieldDiv'+'_'+ $newid+'">Remove</button>');
                break;
            case 'Boolean':
                $($newFieldDiv).append('<label for="checkboxField" class="form-label p-1">Name: </label><input type="text" placeholder="Field name" name="fieldNames[]"/><input type="text" id="stringField" value="Boolean" readonly name="customField[]"/><button type="button" class="btn btn-danger removeFieldBtn" data-id="newFieldDiv'+'_'+ $newid+'">Remove</button>');
                break;
            case 'Text':
                $($newFieldDiv).append('<label for="textField" class="form-label p-1">Name: </label><input type="text" placeholder="Field name" name="fieldNames[]"/><input type="text" id="stringField" value="Text" readonly name="customField[]"/><button type="button" class="btn btn-danger removeFieldBtn" data-id="newFieldDiv'+'_'+ $newid+'">Remove</button>');
                break;
                }

        $newid++;
        $(this).data("increment", $newid);

        });

        $(document).on('click', '.removeFieldBtn', function () {

		$divId = $(this).data("id");
		$("#"+$divId).remove();
		$inc = $("#repeatFieldBtn").data("increment");
		$("#repeatFieldBtn").data("increment", $inc-1);

	    });
});